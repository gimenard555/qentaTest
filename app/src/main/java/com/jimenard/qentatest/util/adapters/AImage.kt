package com.jimenard.qentatest.util.adapters

import android.annotation.SuppressLint
import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.jimenard.qentatest.R
import com.jimenard.qentatest.util.Utils
import com.jimenard.qentatest.util.notifiers.INotify
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.fragment_detail.view.*
import kotlinx.android.synthetic.main.item_image_list_view.view.*


class AImage(layout: Int = R.layout.item_image_list_view) : AdapterObject(layout) {

    override var foregroundView: Int? = null

    override fun chargeView(
        itemRecyclerView: ItemRecyclerView,
        view: View,
        adapterPosition: Int,
        iNotify: INotify?
    ) {
        val imageData = itemRecyclerView as ItemImage
        val bundle = bundleOf("IMAGE_ID" to imageData.id)
        view.image_detail.setImageBitmap(Utils.getImageFromString(imageData.previewImage))
        view.author_username.text = "Autor: ${imageData.userName}"
        view.comments.text = "Comments: ${imageData.commentsText}"
        view.item_container.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_main_to_details, bundle)
        }
    }

    override fun changeViewState(lastView: View, currentView: View) {}
}