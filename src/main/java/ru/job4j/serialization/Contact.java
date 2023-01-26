package ru.job4j.serialization;

import javax.xml.bind.*;
import javax.xml.bind.annotation.*;

@XmlRootElement(name = "Contact")
public class Contact {

    @XmlAttribute
    private String phone;

    public String getPhone() {
        return this.phone;
    }

    public Contact() {
    }

    public Contact(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Contact{"
                + "phone='" + phone + '\''
                + '}';
    }
}