package com.example.exampleretrofitvsokhttp.remote

import com.example.exampleretrofitvsokhttp.model.Post
import retrofit2.Call
import retrofit2.http.*
import rx.Observable

interface APIService {

    @GET("posts")
    open fun getPosts(): Observable<List<Post>>

    @GET("todos/{id}")
    open fun getUserInfo(
        @Path("id") id: String
    ):Observable<Post>

    @POST("posts/")
    @FormUrlEncoded
    open fun savePost(
        @Field("title") title: String,
        @Field("body") body: String,
        @Field("userId") userId: Long
    ): Observable<Post>
}
