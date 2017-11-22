package com.koffuxu.myapplication.thirdlib.retrofit2;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by XGF on 2017/11/21.
 */

public interface GetRequest_Interface {
    @GET("ajax.php?a=fy&f=auto&t=auto&w=hello%20linux")
    Call<Translation> getCall();
}
