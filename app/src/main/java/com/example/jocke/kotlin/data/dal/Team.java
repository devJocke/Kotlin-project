package com.example.jocke.kotlin.data.dal;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Jocke on 2017-10-14.
 */

@Entity
public class Team {

    private Long id;
    @NonNull private String firstName;
    @NonNull private String lastName;
    @Nullable private String thirdName;
    @Generated(hash = 61806813)
    public Team(Long id, @NonNull String firstName, @NonNull String lastName,
            String thirdName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.thirdName = thirdName;
    }
    @Generated(hash = 882286361)
    public Team() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getFirstName() {
        return this.firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return this.lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getThirdName() {
        return this.thirdName;
    }
    public void setThirdName(String thirdName) {
        this.thirdName = thirdName;
    }
 
 
}
