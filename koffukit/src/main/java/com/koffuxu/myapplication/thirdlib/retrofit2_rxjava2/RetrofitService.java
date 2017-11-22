package com.koffuxu.myapplication.thirdlib.retrofit2_rxjava2;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by koffuxu on 2017/11/22.
 */

public interface RetrofitService {
    @GET("book/search")
 /*   Call<Book> getSearchBook(@Query("q") String name,
                             @Query("tag") String tag,
                             @Query("start") int start,
                             @Query("count") int count
                             );*/
    Observable<Book> getSearchBook(@Query("q") String name,
                                   @Query("tag") String tag,
                                   @Query("start") int start,
                                   @Query("count") int count
                                    );

}
