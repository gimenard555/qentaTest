package com.jimenard.qentatest.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import com.jimenard.qentatest.repository.QentaRepository
import javax.inject.Inject

class QentaViewModel @Inject constructor(val context: Context) : ViewModel() {

    private val quentaRepository = QentaRepository(this.context)

    /**
     * Se obtiene listado de imagenes
     * @param word informacion para hacer busqueda de las imagenes
     */
    fun getSearchList(word: String, page:Int) = this.quentaRepository.getQuentaImages(word, page)

    fun getCurrentImage(imageId:String) = this.quentaRepository.getCurrentImage(imageId)
}