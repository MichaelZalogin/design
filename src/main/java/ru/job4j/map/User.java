package ru.job4j.map;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class User {

    private String name;

    private int children;

    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    public static void main(String[] args) {
        User user1 = new User("Michael", 10, new GregorianCalendar(2015, Calendar.JUNE, 29));
        User user2 = new User("Michael", 10, new GregorianCalendar(2015, Calendar.JUNE, 29));
        Map<User, Object> map = new HashMap<>();
        map.put(user1, new Object());
        int hashCode1 = user1.hashCode();
        int hash1 = hashCode1 ^ (hashCode1 >>> 16);
        int bucket1 = hash1 % 15;
        map.put(user2, new Object());
        int hashCode2 = user2.hashCode();
        int hash2 = hashCode2 ^ (hashCode2 >>> 16);
        int bucket2 = hash2 % 15;
        System.out.println("user1 hashCode: " + hashCode1 + " hash: " + hash1 + " bucket " + bucket1);
        System.out.println("user2 hashCode: " + hashCode2 + " hash: " + hash2 + " bucket " + bucket2);
    }
}