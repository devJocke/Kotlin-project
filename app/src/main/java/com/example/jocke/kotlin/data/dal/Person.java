package com.example.jocke.kotlin.data.dal;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Jocke on 2017-10-14.
 */

@Entity
public class Person {

    @org.greenrobot.greendao.annotation.Id(autoincrement = true)
    private Long id;
    private String firstname;
    private String lastname;
    private String thirdname;

    public Person(String firstname, String lastname, String thirdname) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.thirdname = thirdname;
    }

    @Generated(hash = 288337984)
    public Person(Long id, String firstname, String lastname, String thirdname) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.thirdname = thirdname;
    }

    @Generated(hash = 1024547259)
    public Person() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getThirdname() {
        return thirdname;
    }

    public void setThirdname(String thirdname) {
        this.thirdname = thirdname;
    }
}
