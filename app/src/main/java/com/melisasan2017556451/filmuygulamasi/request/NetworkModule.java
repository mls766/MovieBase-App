package com.melisasan2017556451.filmuygulamasi.request;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import utils.Info;
import utils.MovieApiService;

//Network Module is a class that provides information to the hilt about how to provide an instance of a class it doesn't own.

@Module
//@module annotation shows that this class is a module.

//@InstallIn annotation will pass ApplicationComponent here because NetworkModule will be available for application scope.
@InstallIn(ApplicationComponent.class)

public class NetworkModule {


    //when we don't own a class @Provide annotation inside the module class to tell hilt to instantiate these objects for us.
    //In the module, it provides a method to get the MovieApiService object. Create a method provideMovieApiService of MovieApiService return type and annotate it with @Provide annotation.
    @Provides
    @Singleton
    public static MovieApiService provideMovieApiService(){
        //To publish network requests to a REST API with Retrofit, create an instance using the Retrofit.
        //Builder class and configure it with a base URL.
        return  new Retrofit.Builder()
                .baseUrl(Info.BASE_URL)
                // BASE_URL is the basic URL of our API where we will make a call
                .addConverterFactory(GsonConverterFactory.create())
                //addConverterFactory(GsonConverterFactory.create()) takes coming gson inside  Movies class
                //It needs to notify it to Retrofit for that it made gson object
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                //.addCallAdapterFactory(RxJava3CallAdapterFactory.create()) It notifies Retrofit that we use RxJava3
                .build()
                .create(MovieApiService.class);
    }
}
