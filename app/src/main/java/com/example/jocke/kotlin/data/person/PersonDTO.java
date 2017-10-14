package com.example.jocke.kotlin.data.person;

import com.example.jocke.kotlin.data.dal.Person;
import com.google.gson.annotations.SerializedName;
import com.mobandme.android.transformer.compiler.Mappable;
import com.mobandme.android.transformer.compiler.Mapped;

/**
 * Created by Jocke on 2017-10-14.
 */

@Mappable(with = Person.class)
public class PersonDTO {

    @Mapped
    @SerializedName("id")
    private Long id;

    @Mapped
    @SerializedName("firstname")
    private String firstname;

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

    @Mapped
    @SerializedName("lastname")
    private String lastname;
    @Mapped
    @SerializedName("thirdname")
    private String thirdname;
}
