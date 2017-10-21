package com.example.jocke.kotlin.data.person;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Jocke on 2017-10-14.
 */

public interface TeamService {

    //TOOD CHANGE PERSON TO TEAM IN SERVER
    @POST("/api/Team/AddPerson{id}")
    Call<List<TeamDTO>> addPerson(int id);

    @GET("/api/Team/Get{id}")
    Call<List<TeamDTO>> getPerson(int id);

    @DELETE("/api/Team/{id}")
    Call<List<TeamDTO>> deletePerson(int id);

    @GET("/api/Team/GetAll")
    Call<List<TeamDTO>> getAllPersons();
}
