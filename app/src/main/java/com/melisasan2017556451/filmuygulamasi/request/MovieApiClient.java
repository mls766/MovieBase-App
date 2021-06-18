package com.melisasan2017556451.filmuygulamasi.request;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.melisasan2017556451.filmuygulamasi.models.Genres;
import com.melisasan2017556451.filmuygulamasi.models.Movies;
import com.melisasan2017556451.filmuygulamasi.response.GenreResponse;
import com.melisasan2017556451.filmuygulamasi.response.MovieListResponse;
import com.melisasan2017556451.filmuygulamasi.viewmodels.ExecutorsClass;

import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Response;
import utils.Info;

//removed live data from repository to remote data source(client)
public class MovieApiClient { //this client is a bridge between retrofit data and live data
/*
    private static MovieApiClient instance;

    //Live data for searching
    private MutableLiveData<List<Movies>> mMovies;
    //global runnable
    private RetrieveMoviesRunnable retrieveMoviesRunnable;


    //live data for popular movies
    private MutableLiveData<List<Movies>> mMoviesPop;
    //popular runnable
    private RetrieveMoviesRunnablePop retrieveMoviesRunnablePop;


    //live data for top rated movies
    private MutableLiveData<List<Movies>> mMoviesTop;
    //top rated runnable
    private RetrieveMoviesRunnableTop retrieveMoviesRunnableTop;

    private MutableLiveData<List<Genres>> mGenre;
    //top rated runnable
    private RetrieveGenreRunnable retrieveGenreRunnable;


    private MutableLiveData<List<Movies>> mMoviesLatest;
    //global runnable
    private RetrieveMoviesRunnableLatest retrieveMoviesRunnableLatest;

    private MutableLiveData<List<Movies>> mMoviesUp;
    //global runnable
    private RetrieveMoviesRunnableUp retrieveMoviesRunnableUp;





    public static MovieApiClient getInstance() { //singleton pattern
        if (instance == null) {
            instance = new MovieApiClient();

        }
        return instance;
    }

    private MovieApiClient() {

        mMovies = new MutableLiveData<>();
        mMoviesPop = new MutableLiveData<>();
        mMoviesTop = new MutableLiveData<>();
        mGenre = new MutableLiveData<>();
        mMoviesLatest = new MutableLiveData<>();
        mMoviesUp = new MutableLiveData<>();
    }


    public LiveData<List<Movies>> getMovies() {
        return mMovies;
    }

    public LiveData<List<Movies>> getPopMovies() {
        return mMoviesPop;
    }

    public LiveData<List<Movies>> getTopMovies() {
        return mMoviesTop;
    }

    public LiveData<List<Movies>> getLatestMovies() {
        return mMoviesLatest;
    }

    public LiveData<List<Movies>> getUpcomingMovies() {
        return mMoviesUp;
    }


    public LiveData<List<Genres>> getGenre() {
        return mGenre;
    }


    //searchMovieApi method is going to call through the classes
    public void searchMovieApi(String query, int page_number) {

        if (retrieveMoviesRunnable != null) {
            retrieveMoviesRunnable = null;
        }
        retrieveMoviesRunnable = new RetrieveMoviesRunnable(query, page_number);
        //Future is used for threads
        final Future Handler = ExecutorsClass.getInstance().networkIO().submit(retrieveMoviesRunnable);

        ExecutorsClass.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                //time lapse is used for threads; set a time out
                //for cancel the retrofit call
                Handler.cancel(true);
            }
        }, 3000, TimeUnit.MILLISECONDS); // if there is no requests for 3 seconds ,cancel the request
    }


    public void searchMoviePop(int page_number) {

        if (retrieveMoviesRunnablePop != null) {
            retrieveMoviesRunnablePop = null;
        }
        retrieveMoviesRunnablePop = new RetrieveMoviesRunnablePop(page_number);
        //Future is used for threads
        final Future Handler2 = ExecutorsClass.getInstance().networkIO().submit(retrieveMoviesRunnablePop);

        ExecutorsClass.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                //time lapse is used for threads; set a time out
                //for cancel the retrofit call
                Handler2.cancel(true);
            }
        }, 3000, TimeUnit.MILLISECONDS); // if there is no requests for 3 seconds ,cancel the request
    }


    public void searchMovieTop(int page_number) {

        if (retrieveMoviesRunnableTop != null) {
            retrieveMoviesRunnableTop = null;
        }
        retrieveMoviesRunnableTop = new RetrieveMoviesRunnableTop(page_number);
        //Future is used for threads
        final Future Handler3 = ExecutorsClass.getInstance().networkIO().submit(retrieveMoviesRunnableTop);

        ExecutorsClass.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                //time lapse is used for threads; set a time out
                //for cancel the retrofit call
                Handler3.cancel(true);
            }
        }, 3000, TimeUnit.MILLISECONDS); // if there is no requests for 3 seconds ,cancel the request
    }

    public void searchMovieLatest(int page_number) {

        if (retrieveMoviesRunnableLatest != null) {
            retrieveMoviesRunnableLatest = null;
        }
        retrieveMoviesRunnableLatest = new RetrieveMoviesRunnableLatest(page_number);
        //Future is used for threads
        final Future Handler5 = ExecutorsClass.getInstance().networkIO().submit(retrieveMoviesRunnableLatest);

        ExecutorsClass.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                //time lapse is used for threads; set a time out
                //for cancel the retrofit call
                Handler5.cancel(true);
            }
        }, 3000, TimeUnit.MILLISECONDS); // if there is no requests for 3 seconds ,cancel the request
    }

    public void searchMovieUp(int page_number) {

        if (retrieveMoviesRunnableUp != null) {
            retrieveMoviesRunnableUp = null;
        }
        retrieveMoviesRunnableUp = new RetrieveMoviesRunnableUp(page_number);
        //Future is used for threads
        final Future Handler6 = ExecutorsClass.getInstance().networkIO().submit(retrieveMoviesRunnableUp);

        ExecutorsClass.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                //time lapse is used for threads; set a time out
                //for cancel the retrofit call
                Handler6.cancel(true);
            }
        }, 3000, TimeUnit.MILLISECONDS); // if there is no requests for 3 seconds ,cancel the request
    }


    public void searchGenre() {

        if (retrieveMoviesRunnableTop != null) {
            retrieveMoviesRunnableTop = null;
        }
        retrieveGenreRunnable = new RetrieveGenreRunnable();
        //Future is used for threads
        final Future Handler4 = ExecutorsClass.getInstance().networkIO().submit(retrieveGenreRunnable);

        ExecutorsClass.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                //time lapse is used for threads; set a time out
                //for cancel the retrofit call
                Handler4.cancel(true);
            }
        }, 3000, TimeUnit.MILLISECONDS); // if there is no requests for 3 seconds ,cancel the request
    }


    //retreiving data from restapi by runnable class
    //Queries: ID & search
    //moving every query ,every request to background threads using executers int the movieapiclient
    private class RetrieveMoviesRunnable implements Runnable {

        private String query;
        private int page_number;
        boolean cancelRequest;

        public RetrieveMoviesRunnable(String query, int page_number) {
            this.query = query;
            this.page_number = page_number;
            cancelRequest = false; //default
        }

        @Override
        public void run() {

            //get the response objects
            try {
                Response response = getMovies(query, page_number).execute();
                if (cancelRequest) {
                    return;
                }
                if (response.code() == 200) {
                    List<Movies> movielist = new ArrayList<>(((MovieListResponse) response.body()).getMovies());
                    if (page_number == 1) {
                        //sending data to live data
                        //postvalue used for background thread
                        //setvalue is not for background
                        mMovies.postValue(movielist);
                    } else {
                        List<Movies> updatedMovies = mMovies.getValue();
                        updatedMovies.addAll(movielist);
                        mMovies.postValue(updatedMovies);
                    }
                } else {
                    String error = response.errorBody().string();
                    Log.e("Tag", "Error Occured" + error);
                    mMovies.postValue(null);
                }

            } catch (IOException e) {
                e.printStackTrace();
                mMovies.postValue(null);
            }


        }

        //search query in background thread, send them into live data ad then live data pass it to activity
        private Call<MovieListResponse> getMovies(String query, int page_number) {
            return Service.getMovieApi().searchMovie(
                    Info.API_KEY,
                    query,
                    page_number
            );

        }

        private void CancelRequest() {
            Log.v("Tag", "Cancel search list request");
            cancelRequest = true;
        }


    }

    private class RetrieveMoviesRunnablePop implements Runnable {

        private int page_number;
        boolean cancelRequest;

        public RetrieveMoviesRunnablePop(int page_number) {
            this.page_number = page_number;
            cancelRequest = false; //default
        }

        @Override
        public void run() {

            //get the response objects
            try {
                Response response2 = getPopMovies(page_number).execute();
                if (cancelRequest) {
                    return;
                }
                if (response2.code() == 200) {
                    List<Movies> movielist = new ArrayList<>(((MovieListResponse) response2.body()).getMovies());
                    if (page_number == 1) {
                        //sending data to live data
                        //postvalue used for background thread
                        //setvalue is not for background
                        mMoviesPop.postValue(movielist);
                    } else {
                        List<Movies> updatedMovies = mMoviesPop.getValue();
                        updatedMovies.addAll(movielist);
                        mMoviesPop.postValue(updatedMovies);
                    }
                } else {
                    String error = response2.errorBody().string();
                    Log.e("Tag", "Error Occured" + error);
                    mMoviesPop.postValue(null);
                }

            } catch (IOException e) {
                e.printStackTrace();
                mMoviesPop.postValue(null);
            }


        }

        //search query in background thread, send them into live data ad then live data pass it to activity
        private Call<MovieListResponse> getPopMovies(int page_number) {
            return Service.getMovieApi().getPopularMovies(
                    Info.API_KEY,
                    page_number
            );

        }

        private void CancelRequest() {
            Log.v("Tag", "Cancel search list request");
            cancelRequest = true;
        }


    }

    private class RetrieveMoviesRunnableTop implements Runnable {

        private int page_number;
        boolean cancelRequest;

        public RetrieveMoviesRunnableTop(int page_number) {
            this.page_number = page_number;
            cancelRequest = false; //default
        }

        @Override
        public void run() {

            //get the response objects
            try {
                Response response3 = getTopMovies(page_number).execute();
                if (cancelRequest) {
                    return;
                }
                if (response3.code() == 200) {
                    List<Movies> movielist = new ArrayList<>(((MovieListResponse) response3.body()).getMovies());
                    if (page_number == 1) {
                        //sending data to live data
                        //postvalue used for background thread
                        //setvalue is not for background
                        mMoviesTop.postValue(movielist);
                    } else {
                        List<Movies> updatedMovies = mMoviesTop.getValue();
                        updatedMovies.addAll(movielist);
                        mMoviesTop.postValue(updatedMovies);
                    }
                } else {
                    String error = response3.errorBody().string();
                    Log.e("Tag", "Error Occured" + error);
                    mMoviesTop.postValue(null);
                }

            } catch (IOException e) {
                e.printStackTrace();
                mMoviesTop.postValue(null);
            }


        }

        //search query in background thread, send them into live data ad then live data pass it to activity
        private Call<MovieListResponse> getTopMovies(int page_number) {
            return Service.getMovieApi().getTopRatedMovies(
                    Info.API_KEY,
                    page_number
            );

        }

        private void CancelRequest() {
            Log.v("Tag", "Cancel search list request");
            cancelRequest = true;
        }


    }

    private class RetrieveMoviesRunnableLatest implements Runnable {
        private int page_number;
        boolean cancelRequest;

        public RetrieveMoviesRunnableLatest( int page_number) {
            this.page_number = page_number;
            cancelRequest = false; //default
        }

        @Override
        public void run() {

            //get the response objects
            try {
                Response response5 = getLatestMovies(page_number).execute();
                if (cancelRequest) {
                    return;
                }
                if (response5.code() == 200) {
                    List<Movies> movielist = new ArrayList<>(((MovieListResponse) response5.body()).getMovies());
                    if (page_number == 1) {
                        //sending data to live data
                        //postvalue used for background thread
                        //setvalue is not for background
                        mMoviesLatest.postValue(movielist);
                    } else {
                        List<Movies> updatedMovies = mMoviesLatest.getValue();
                        updatedMovies.addAll(movielist);
                        mMoviesLatest.postValue(updatedMovies);
                    }
                } else {
                    String error = response5.errorBody().string();
                    Log.e("Tag", "Error Occured" + error);
                    mMoviesLatest.postValue(null);
                }

            } catch (IOException e) {
                e.printStackTrace();
                mMoviesLatest.postValue(null);
            }


        }

        //search query in background thread, send them into live data ad then live data pass it to activity
        private Call<MovieListResponse> getLatestMovies( int page_number) {
            return Service.getMovieApi().getLatestMovies(
                    Info.API_KEY,
                    page_number
            );

        }

        private void CancelRequest() {
            Log.v("Tag", "Cancel search list request");
            cancelRequest = true;
        }


    }


    private class RetrieveMoviesRunnableUp implements Runnable {
        private int page_number;
        boolean cancelRequest;

        public RetrieveMoviesRunnableUp( int page_number) {
            this.page_number = page_number;
            cancelRequest = false; //default
        }

        @Override
        public void run() {

            //get the response objects
            try {
                Response response6 = getUpcomingMovies(page_number).execute();
                if (cancelRequest) {
                    return;
                }
                if (response6.code() == 200) {
                    List<Movies> movielist = new ArrayList<>(((MovieListResponse) response6.body()).getMovies());
                    if (page_number == 1) {
                        //sending data to live data
                        //postvalue used for background thread
                        //setvalue is not for background
                        mMoviesUp.postValue(movielist);
                    } else {
                        List<Movies> updatedMovies = mMoviesUp.getValue();
                        updatedMovies.addAll(movielist);
                        mMoviesUp.postValue(updatedMovies);
                    }
                } else {
                    String error = response6.errorBody().string();
                    Log.e("Tag", "Error Occured" + error);
                    mMoviesUp.postValue(null);
                }

            } catch (IOException e) {
                e.printStackTrace();
                mMoviesUp.postValue(null);
            }


        }

        //search query in background thread, send them into live data ad then live data pass it to activity
        private Call<MovieListResponse> getUpcomingMovies( int page_number) {
            return Service.getMovieApi().getUpcomingMovies(
                    Info.API_KEY,
                    page_number
            );

        }

        private void CancelRequest() {
            Log.v("Tag", "Cancel search list request");
            cancelRequest = true;
        }


    }


    private class RetrieveGenreRunnable implements Runnable {

        boolean cancelRequest;

        public RetrieveGenreRunnable() {
            cancelRequest = false; //default
        }

        @Override
        public void run() {

            //get the response objects
            try {
                Response response4 = getGenres().execute();
                if (cancelRequest) {
                    return;
                }
                if (response4.code() == 200) {
                    List<Genres> genrelist = new ArrayList<>(((GenreResponse) response4.body()).getGenres());
                    mGenre.postValue(genrelist);
                } else {
                    String error = response4.errorBody().string();
                    Log.e("Tag", "Error Occured" + error);
                    mGenre.postValue(null);
                }

            } catch (IOException e) {
                e.printStackTrace();
                mGenre.postValue(null);
            }


        }

        //search query in background thread, send them into live data ad then live data pass it to activity
        private Call<GenreResponse> getGenres() {
            return Service.getMovieApi().getGenre(
                    Info.API_KEY
            );

        }

        private void CancelRequest() {
            Log.v("Tag", "Cancel search list request");
            cancelRequest = true;
        }


    }

 */
}
