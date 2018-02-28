package com.example.jocke.kotlin.data.dal;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Jocke on 2017-10-14.
 */

public class TeamMember extends Team implements Parcelable {

    //TODO TRANSISTENT WITH PARCELABLE ?

    private Long id;
    private final String firstName;
    private final String lastName;
    private final String thirdName;
    private final int age;

    TeamMember(TeamBuilder builder) {
        this.id = id;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.thirdName = builder.thirdName;
        this.age = builder.age;
    }

    private TeamMember(Parcel source) {
        firstName = source.readString();
        lastName = source.readString();
        thirdName = source.readString();
        age = source.readInt();
    }


    public static final class TeamBuilder {
        private final String firstName;
        private final String lastName;
        private final int age;
        private String thirdName = "";

        public TeamBuilder(String firstName, String lastName, int age) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.age = age;
        }

        public TeamBuilder setThirdName(String thirdName) {
            this.thirdName = thirdName;
            return this;
        }

        public TeamMember build() {
            return new TeamMember(this);
        }
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeString(thirdName);
        dest.writeInt(age);
    }

    public final static Parcelable.Creator<TeamMember> CREATOR = new ClassLoaderCreator<TeamMember>() {
        @Override
        public TeamMember createFromParcel(Parcel source) {
            return new TeamMember(source);
        }

        @Override
        public TeamMember[] newArray(int size) {
            return new TeamMember[size];
        }

        @Override
        public TeamMember createFromParcel(Parcel source, ClassLoader loader) {
            return null;
        }
    };

    public Long getId() {
        return this.id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getThirdName() { return this.thirdName; }

    public Integer getAge() { return this.age; }
}
