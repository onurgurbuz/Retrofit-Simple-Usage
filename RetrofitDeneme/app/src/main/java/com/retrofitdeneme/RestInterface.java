package com.retrofitdeneme;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RestInterface {

    @GET("login.php")
    Call<LoginPOJO> login(@Query("MusteriNo") String musteriNo, @Query("Kod") String kod);

}
