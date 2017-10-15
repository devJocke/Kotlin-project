package com.example.jocke.kotlin.data.person;

import com.example.jocke.kotlin.data.dal.Person;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Jocke on 2017-10-14.
 */

public interface PersonService {
    @GET("/api/person")
    Observable<List<PersonDTO>> GET(@Header("Authorization") String token);
    Call<List<Person>> loadChanges(@Query("q") String status);

    @GET("/api/person/{id}")
    Observable<PersonDTO> GET(@Query("Authorization") String token, @Path("id") String id);

    @PUT("/api/person/{id}")
    Observable<PersonDTO> UPDATE(String token,  @Body List<PersonService> assignments);
}
