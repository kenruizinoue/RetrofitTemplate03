package com.kenruizinoue.retrofittemplate3

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/3/movie/popular")
    fun getMovies(@Query("api_key") key: String): Observable<PopularMovies>
}