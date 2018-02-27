package com.example.jocke.kotlin;

import android.app.Activity;

import com.example.jocke.kotlin.data.dal.TeamMember;

import java.util.List;
import java.util.Objects;

/**
 * Created by Jocke on 2018-02-11.
 */

public class TeamStatus {

    private final List<TeamMember> teamMembers;
    Activity mainActivity;

    public TeamStatus(List<TeamMember> teamMembers){
        this.teamMembers = Objects.requireNonNull(teamMembers);
    }

    public int getAverageAge(){
        int totalAge = 0;
        for (TeamMember member : teamMembers) {
            totalAge += member.getAge();
        }

        return totalAge / teamMembers.size();
    }
}
