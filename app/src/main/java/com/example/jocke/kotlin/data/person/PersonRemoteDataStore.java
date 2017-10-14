package com.example.jocke.kotlin.data.person;

import android.util.Log;

import com.mobandme.android.transformer.Transformer;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Retrofit;
import rx.Observable;

/**
 * Created by Haris on 2017-01-09.
 */

public class PersonRemoteDataStore {

    private final String TAG = getClass().getSimpleName();

    private Retrofit mRetrofit;
    private Transformer mTransformer;

    @Inject
    public PersonRemoteDataStore(Retrofit retrofit) {
        mRetrofit = retrofit;
        mTransformer = new Transformer.Builder().build(PersonDTO.class);
    }

    public Observable<List<PersonService>> query(String token){
        Log.d(TAG, token);
        Log.d(TAG, "Fetching Persons data remotely...");

        return mRetrofit.create(PersonService.class).GET(token)
            .map(PersonDTOs -> {
                List<PersonService> personServices = new ArrayList<>();
                for(PersonDTO PersonDTO : PersonDTOs)
                    personServices.add(mTransformer.transform(PersonDTO, PersonService.class));

                return personServices;
            });
    }

    public Observable<PersonService> query(String token, String id){
        Log.d(TAG, "Fetching PersonService data remotely...");

        return mRetrofit.create(PersonService.class).GET(token, id)
                .map(PersonDTO -> mTransformer.transform(PersonDTO, PersonService.class));
    }
    public Observable<PersonService> update(String token, List<PersonService> personServices){
        Log.d(TAG, "Fetching PersonService data remotely...");

        return mRetrofit.create(PersonService.class).UPDATE(token, personServices)
                .map(PersonDTO -> mTransformer.transform(PersonDTO, PersonService.class));
    }
}