package developer.unam.practicocoppel.retrofit

import developer.unam.practicocoppel.retrofit.character.Characters
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface RetrofitInstance {
    @GET("characters")
    fun getAllMovies(@QueryMap params:Map<String,String>): Call<Characters>

    companion object {
        val urlBase = "https://gateway.marvel.com/v1/public/"
        var retrofitInstance: RetrofitInstance? = null
        fun getInstance(): RetrofitInstance =
            retrofitInstance ?: Retrofit.Builder().baseUrl(urlBase)
                .addConverterFactory(GsonConverterFactory.create())
                .client(
                    OkHttpClient.Builder()
                        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                        .build()
                )
                .build().create(RetrofitInstance::class.java)


    }
}