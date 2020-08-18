package com.jimenard.qentatest.persistence.webservice.services

import com.jimenard.qentatest.persistence.webservice.serializable.ImagesData
import com.jimenard.qentatest.persistence.webservice.util.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface ImageService {

    /**
     * Se obtiene el lkistado de imagenes
     */
    @GET(Constants.END_POINT_IMAGES)
    suspend fun getQuentaImage(
        @Query("q") wordSearch: String,
        @Query("page") page: Int = 1
    ): ImagesData
}