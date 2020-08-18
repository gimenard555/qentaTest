package com.jimenard.qentatest.repository

import android.content.Context
import androidx.lifecycle.liveData
import com.jimenard.qentatest.persistence.database.QentaDb
import com.jimenard.qentatest.persistence.database.entities.QentaEntity
import com.jimenard.qentatest.persistence.webservice.retrofit.RetrofitClient
import com.jimenard.qentatest.persistence.webservice.serializable.ImagesData
import com.jimenard.qentatest.persistence.webservice.util.Resource
import com.jimenard.qentatest.util.Utils
import com.jimenard.qentatest.util.adapters.ItemImage
import kotlinx.coroutines.Dispatchers

class QentaRepository(context: Context) {

    private val quentaService = RetrofitClient.quentaImageService
    private val quentaDao = QentaDb.getDatabase(context)!!.getQentaDb()

    /**
     * Metodo para obetener listado de imagenes
     * @param searchData informacion para realizar la busqueda de imagene
     */
    fun getQuentaImages(searchData: String, page: Int) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            val imagesList = if (Utils.internetIsConnected()) {
                val serviceList =  quentaService.getQuentaImage(searchData, page).images!!
                quentaDao.insertRows(serviceList.map { imageS ->
                    QentaEntity(
                        webServiceId = imageS.id!!,
                        preview = Utils.getBase64EncodedImage(imageS.previewImage),
                        image = Utils.getBase64EncodedImage(imageS.image),
                        comments = imageS.comments,
                        user = imageS.user
                    )
                })
                serviceToItem(serviceList)
            } else {
                entityToItem(quentaDao.getImagesFromDatabase())
            }
            System.out.println(imagesList)
            emit(Resource.success(data = imagesList))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    fun getCurrentImage(imageId: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = quentaDao.getCurrentImage(imageId)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    private fun entityToItem(entities: List<QentaEntity>) =
        entities.map {
            ItemImage(
                id = it.webServiceId,
                previewImage = it.preview ?: "",
                userName = it.user ?: "",
                commentsText = it.comments ?: ""
            )
        }

    private fun serviceToItem(serviceI: List<ImagesData.ImageData>) =
        serviceI.map {
            ItemImage(
                id = it.id!!,
                previewImage = Utils.getBase64EncodedImage(it.previewImage),
                userName = Utils.getBase64EncodedImage(it.user),
                commentsText = it.comments ?: ""
            )
        }

}