package com.one.cryptotracker;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by vibhu on 12/27/2017.
 */

public interface ApiImplimentation {
    @GET("rate")
    Call<USD> getBitCoinValue();
}
