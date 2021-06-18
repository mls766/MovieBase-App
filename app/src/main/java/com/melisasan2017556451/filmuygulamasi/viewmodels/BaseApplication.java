package com.melisasan2017556451.filmuygulamasi.viewmodels;

import android.app.Application;


import dagger.hilt.android.HiltAndroidApp;


//This BaseApplication class is necessary for the hilt and it annotated with @HiltAndroidApp.
// It also add it to the manifest file in the application tag.

@HiltAndroidApp
public class BaseApplication extends Application {

}

