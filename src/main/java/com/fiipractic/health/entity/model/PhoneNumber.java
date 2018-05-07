package com.fiipractic.health.entity.model;

import javax.persistence.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Entity
@Table(name="phoneNumber")
public class PhoneNumber {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="phoneNumber")
    private String phoneNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public static boolean isValid(String phoneNumber){

        Pattern p = Pattern.compile("(07)?[0-9]{10}");
        Matcher m = p.matcher(phoneNumber);
        return  m.matches();
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (isValid(phoneNumber)) {
            this.phoneNumber = phoneNumber;
        }
        else
        {
            this.phoneNumber = "Invalid Number";
        }
    }

}
