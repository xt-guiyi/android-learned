package com.example.androidlearned.api

import com.example.androidlearned.api.ApiConfig.BASE_URL
import com.example.androidlearned.entity.Article
import com.example.androidlearned.entity.HeatWord
import com.example.androidlearned.entity.User
import okhttp3.MultipartBody
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.HTTP
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface MokeAPi {
    @GET("hotkey/json")
    suspend fun getHeatWord(@Query("description") description: String?): IResponse<List<HeatWord>>

    @GET("article/list/{page}/json")
    suspend fun getArticles(@Path("page") page: Int): IResponse<List<Article>>

    // 以下都是示例，无法直接使用，仅参考
//    @POST("hotkey/json/{id}")
//    suspend fun setXXX(@Field("description") description: String?, @Path("id") id: Int): IResponse<List<HeatWord>>
//
//    @POST("hotkey/json")
//    @FormUrlEncoded // 表单提交格式
//    suspend fun setYYY(@Field("description") description: String?): IResponse<List<HeatWord>>
//    // 提交json格式数据
//    @POST("hotkey/json")
//    suspend fun setHHH(@Body user: User): IResponse<User>
//
//    @POST("hotkey/json")
//    @Headers(value = ["Content-Type: application/json", "Accept: application/json"])
//    suspend fun setSSS(@Field("description") description: String?): IResponse<List<HeatWord>>
//
//    @HTTP(method = "DELETE", path = "hotkey/json", hasBody = true)
//    suspend fun deleteZZZ(@Field("description") description: String?, @Header("token") token: String): IResponse<List<HeatWord>>
//
////  文件上传
//    @POST("hotkey/json")
//    @Multipart
//    suspend fun setAAA(@Part("part") part: MultipartBody.Part): IResponse<User>
//    // 文件下载
//    @GET("hotkey/json")
//    suspend fun setBBB(@Url url: String): IResponse<User>
//
    companion object {
        fun create(): MokeAPi {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MokeAPi::class.java)
        }
    }
}

