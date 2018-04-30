package com.colossussoftware.titanwalking.retrofittry.retrofit;

import com.colossussoftware.titanwalking.retrofittry.model.Repo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GithubService {
    //Call to get a user by username
    @GET("users/{user}")
    Call<User> getUser(@Path("user") String user);

    //Call to get a users repositories with username
    @GET("users/{user}/repos")
    Call<List<Repo>> listRepos(@Path("user") String user);

}
