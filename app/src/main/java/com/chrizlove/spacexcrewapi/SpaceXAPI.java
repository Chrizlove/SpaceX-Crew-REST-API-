package com.chrizlove.spacexcrewapi;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SpaceXAPI {

    @GET("v4/crew")
    Call<List<CrewModel>> getCrew();
}
