package com.jimenard.qentatest.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.jimenard.qentatest.R
import com.jimenard.qentatest.persistence.webservice.util.Status
import com.jimenard.qentatest.util.Utils
import com.jimenard.qentatest.util.base.BaseFragment
import com.jimenard.qentatest.util.base.QuentaViewModelFactory
import com.jimenard.qentatest.viewmodels.QentaViewModel
import kotlinx.android.synthetic.main.fragment_detail.*
import javax.inject.Inject

class DetailFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: QuentaViewModelFactory<QentaViewModel>
    private val viewModel: QentaViewModel by lazy { this.viewModelFactory.get() }

    private lateinit var imageId: String


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) =
        inflater.inflate(R.layout.fragment_detail, container, false)!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadArguments()
    }

    private fun loadArguments() {
        arguments?.getString("IMAGE_ID")?.let {
            imageId = it
        }
        chargeImage()
    }

    private fun chargeImage() {
        this.viewModel.getCurrentImage(this.imageId)
            .observe(this.viewLifecycleOwner, Observer {
                it?.let { resource ->
                    when (resource.status) {
                        Status.SUCCESS -> {
                          image_detail.setImageBitmap(Utils.getImageFromString(resource.data!!))
                        }
                        Status.ERROR -> {
                            Log.e("DetailFragment", resource.message!!)
                        }
                        Status.LOADING -> {
                        }
                    }
                }
            })
    }
}