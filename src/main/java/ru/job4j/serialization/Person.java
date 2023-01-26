package ru.job4j.serialization;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@XmlRootElement(name = "person")
@XmlAccessorType(XmlAccessType.FIELD)
public class Person {

    @XmlAttribute
    private boolean sex;

    @XmlElement
    private int age;

    private Contact contact;

    @XmlElementWrapper(name = "statuses")
    @XmlElement(name = "status")
    private String[] statuses;

    public boolean isSex() {
        return this.sex;
    }

    public int getAge() {
        return this.age;
    }

    public Contact getContact() {
        return this.contact;
    }

    public String[] getStatuses() {
        return this.statuses;
    }

    public Person() {

    }

    public Person(boolean sex, int age, Contact contact, String... statuses) {
        this.sex = sex;
        this.age = age;
        this.contact = contact;
        this.statuses = statuses;
    }

    @Override
    public String toString() {
        return "Person{"
                + "sex=" + sex
                + ", age=" + age
                + ", contact=" + contact
                + ", statuses=" + Arrays.toString(statuses)
                + '}';
    }

    public static void main(String[] args) throws JAXBException {

        final Person person = new Person(false, 30, new Contact("11-111"), "Worker", "Married");
        /** Получаем контекст для доступа к АПИ */
        JAXBContext context = JAXBContext.newInstance(Person.class);
        /** Создаем сериализатор */
        Marshaller marshaller = context.createMarshaller();
        /** Указываем, что нам нужно форматирование */
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            /** Сериализуем */
            marshaller.marshal(person, writer);
            String result = writer.getBuffer().toString();
            System.out.println(xml);
        } catch (Exception e) {
            e.printStackTrace();
        }
        /** Для десериализации нам нужно создать десериализатор
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            Person result = (Person) unmarshaller.unmarshal(reader);
            System.out.println(result);
        } */

        /** JSONObject из json-строки строки */
        JSONObject jsonContact = new JSONObject("{\"phone\":\"+7(924)111-111-11-11\"}");

        /** JSONArray из ArrayList */
        List<String> list = new ArrayList<>();
        list.add("Student");
        list.add("Free");
        JSONArray jsonStatuses = new JSONArray(list);

        /** JSONObject напрямую методом put */
        final Person anotherPerson = new Person(false, 30, new Contact("11-111"), "Worker", "Married");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sex", anotherPerson.isSex());
        jsonObject.put("age", anotherPerson.getAge());
        jsonObject.put("contact", jsonContact);
        jsonObject.put("statuses", jsonStatuses);

        /** Выведем результат в консоль */
        System.out.println(jsonObject.toString());

        /** Преобразуем объект person в json-строку */
        System.out.println(new JSONObject(person).toString());
    }
}