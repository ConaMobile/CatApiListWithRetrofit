package com.example.catapilistretrofit.networking

import com.example.catapilistretrofit.model.CatUpload
import com.example.catapilistretrofit.model.CatsModel
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

interface HomeService {

    @Multipart
    @POST("upload")
    fun uploadFile(
        @Part image: MultipartBody.Part,
        @Part("sub_id") name: String
    ): Call<CatUpload>

    @GET("search?")
    fun searchPhotos(
        @Query("limit") limit: Int,
        @Query("page") page: Int,
        @Query("order") search: String
    ): Call<ArrayList<CatsModel>>

}


//    @GET("photos/{id}")
//    fun singlePhotos(@Path("id")id:Int):Call<ResponseItem>
//
//    @POST("photos")
//    fun createPhotos(@Body post: ResponseItem):Call<ResponseItem>
//
//    @PUT("photos/{id}")
//    fun updatePhotos(@Path("id")id: Int,@Body post: ResponseItem):Call<ResponseItem>
//
//    @DELETE("photos/{id}")
//    fun deletePhotos(@Path("id")id:Int):Call<ResponseItem>

//    @GET("search/photos?")
////    @GET("search/photos?page=2&query=tourism")
//    fun searchPhotos(@Query("query") search: String) : Call<Welcome>
////    fun searchPhotos() : Call<Welcome>