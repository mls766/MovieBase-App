package com.melisasan2017556451.filmuygulamasi.viewmodels;

import java.util.concurrent.Executors;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;

public class ExecutorsClass {

    //singleton pattern
    private static ExecutorsClass instance;

    public static ExecutorsClass getInstance(){
        if(instance == null){
            instance = new ExecutorsClass();
        }
    return instance;

    }

    //background retrofit calls(without any display)(scheduledExecutorService) is execute background calls and getting the data using retrofit from Api,
    //and go back to web service will stored the live data using background threads and removed data source is
    //passing the repository then the view model and then UI
    private final ScheduledExecutorService mNetworkIO = Executors.newScheduledThreadPool(3);
    //1 for connecting to retrofit
    //1 for cancelling the retrofit
    //3rd one
    public ScheduledExecutorService networkIO(){
        return mNetworkIO;
    }
}
