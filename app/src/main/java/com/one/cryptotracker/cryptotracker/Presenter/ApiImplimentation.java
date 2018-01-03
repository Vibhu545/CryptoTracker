package com.one.cryptotracker.cryptotracker.Presenter;

import com.one.cryptotracker.cryptotracker.Model.BitcoinResponse;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by vibhu on 12/27/2017.
 */

public interface ApiImplimentation {
    @GET("v1/bpi/currentprice.json")
    Call<BitcoinResponse> getBitCoinValue();
}
