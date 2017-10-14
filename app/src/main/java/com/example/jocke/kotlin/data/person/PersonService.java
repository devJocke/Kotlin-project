package com.example.jocke.kotlin.data.person;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Jocke on 2017-10-14.
 */

public interface PersonService {
    @GET("/api/person")
    Observable<List<PersonDTO>> GET(@Header("Authorization") String token);

    @GET("/api/person/{id}")
    Observable<PersonDTO> GET(@Header("Authorization") String token, @Path("id") String id);

    @PUT("/api/person/{id}")
    Observable<PersonDTO> UPDATE(String token,  @Body List<PersonService> assignments);
}
