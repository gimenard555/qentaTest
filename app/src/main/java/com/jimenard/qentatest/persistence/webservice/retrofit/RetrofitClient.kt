package com.jimenard.qentatest.persistence.webservice.retrofit

import com.jimenard.qentatest.persistence.webservice.services.ImageService
import com.jimenard.qentatest.persistence.webservice.util.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Cliente para el consumo de servicios
 */
object RetrofitClient {

    //Instancia Singleton de conexion con el servicio
    private var RETROFIT_INSTANCE: Retrofit? = null

    /**
     *  Se obtiene la conexion para consumo de servicios
     *  patron singleton
     *  @return unica instancia para conexion
     */
    private fun getClient(): Retrofit {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val okHttpClient = OkHttpClient()
            .newBuilder()
            .connectTimeout(2, TimeUnit.MINUTES)
            .writeTimeout(2, TimeUnit.MINUTES)
            .readTimeout(2, TimeUnit.MINUTES)
            .addInterceptor(interceptor)
            .build()
        if (RETROFIT_INSTANCE == null) {
            RETROFIT_INSTANCE = Retrofit.Builder()
                    .baseUrl(Constants.SERVER_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
        }
        return RETROFIT_INSTANCE!!
    }

    /**
     * Instancias unicas para el acceso a cada uno de los servicios
     */
    val quentaImageService: ImageService = getClient().create(ImageService::class.java)
}