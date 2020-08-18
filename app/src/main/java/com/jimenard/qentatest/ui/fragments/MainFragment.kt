package com.jimenard.qentatest.ui.fragments

import android.nfc.tech.MifareUltralight.PAGE_SIZE
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jimenard.qentatest.R
import com.jimenard.qentatest.persistence.webservice.util.Status
import com.jimenard.qentatest.util.adapters.AImage
import com.jimenard.qentatest.util.base.BaseFragment
import com.jimenard.qentatest.util.base.BaseRecyclerAdapter
import com.jimenard.qentatest.util.base.QuentaViewModelFactory
import com.jimenard.qentatest.viewmodels.QentaViewModel
import kotlinx.android.synthetic.main.fragment_main.*
import javax.inject.Inject


class MainFragment : BaseFragment(), TextWatcher {

    @Inject
    lateinit var viewModelFactory: QuentaViewModelFactory<QentaViewModel>
    private val viewModel: QentaViewModel by lazy { this.viewModelFactory.get() }
    private lateinit var imageAdapter: BaseRecyclerAdapter
    private var page:Int = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) =
        inflater.inflate(R.layout.fragment_main, container, false)!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        search_edit_text.addTextChangedListener(this)
        imageAdapter = BaseRecyclerAdapter(listOf(), AImage())
        val linearLayoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        image_list.apply {
            adapter = imageAdapter
            layoutManager = linearLayoutManager
        }
        filter("")
        image_list.addOnScrollListener(recyclerViewOnScrollListener)
    }

    override fun afterTextChanged(s: Editable?) {
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    private var search =""

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        if (!s.isNullOrEmpty()) {
            filter(s.toString())
            search = s.toString()
        }
    }

    /**
     * Se filtran imagenes por el texto
     */
    private fun filter(filterText: String) {
        this.viewModel.getSearchList(filterText, page)
            .observe(this.viewLifecycleOwner, Observer {
                it?.let { resource ->
                    when (resource.status) {
                        Status.SUCCESS -> {
                            this.imageAdapter.setDataList(resource.data!!)
                        }
                        Status.ERROR -> {
                            Log.e("MainFragment", resource.message!!)
                        }
                        Status.LOADING -> {
                        }
                    }
                }
            })
    }

    private val recyclerViewOnScrollListener: RecyclerView.OnScrollListener =
        object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                page +=1
                filter(search)
            }
        }
}