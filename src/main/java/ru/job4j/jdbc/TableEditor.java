package ru.job4j.jdbc;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private Properties properties;

    public TableEditor(Properties properties) {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() {
        try (InputStream in = TableEditor.class.getClassLoader()
                .getResourceAsStream("app.properties")) {
            properties.load(in);
            connection = getConnection(properties);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private static Connection getConnection(Properties properties) throws Exception {
        Class.forName(properties.getProperty("driver"));
        return DriverManager.getConnection(properties.getProperty("url"),
                properties.getProperty("login"), properties.getProperty("password"));
    }

    private void statementRequest(String sql) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        }
    }

    public void createTable(String tableName) throws Exception {
        String sql = String.format("CREATE TABLE IF NOT EXISTS %s (%s, %s);",
                tableName,
                "id serial PRIMARY KEY",
                "name varchar(255)");
        statementRequest(sql);
    }

    public void dropTable(String tableName) throws Exception {
        String sql = String.format("DROP TABLE IF EXISTS %s;", tableName);
        statementRequest(sql);
    }

    public void addColumn(String tableName, String columnName, String type) throws Exception {
        String sql = String.format("ALTER TABLE %s ADD COLUMN %s %s;",
                tableName, columnName, type);
        statementRequest(sql);
    }

    public void dropColumn(String tableName, String columnName) throws Exception {
        String sql = String.format("ALTER TABLE %s DROP COLUMN %s;",
                tableName, columnName);
        statementRequest(sql);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws Exception {
        String sql = String.format("ALTER TABLE %s RENAME COLUMN %s TO %s;",
                tableName, columnName, newColumnName);
        statementRequest(sql);
    }

    public String getTableScheme(String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception {
        try (TableEditor tableEditor = new TableEditor(new Properties())) {
            tableEditor.createTable("new_table");
            System.out.println(tableEditor.getTableScheme("new_table"));
            System.out.println("**********************************************");
            tableEditor.addColumn("new_table", "new_column1", "int");
            tableEditor.addColumn("new_table", "new_column2", "int");
            System.out.println(tableEditor.getTableScheme("new_table"));
            System.out.println("**********************************************");
            tableEditor.dropColumn("new_table", "new_column2");
            System.out.println(tableEditor.getTableScheme("new_table"));
            System.out.println("**********************************************");
            tableEditor.renameColumn("new_table", "new_column1", "new_new_column");
            System.out.println(tableEditor.getTableScheme("new_table"));
            System.out.println("**********************************************");
            tableEditor.dropTable("new_table");
        }
    }
}