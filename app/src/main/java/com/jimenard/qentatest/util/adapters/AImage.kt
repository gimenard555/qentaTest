package com.jimenard.qentatest.util.adapters

import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.jimenard.qentatest.R
import com.jimenard.qentatest.util.Utils
import com.jimenard.qentatest.util.notifiers.INotify
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
        if (imageData.previewImage.contains("https://cdn.pixabay.com/")) {
            Glide.with(view.context)
                .load(imageData.previewImage)
                .into(view.image)
        } else {
            view.image.setImageBitmap(Utils.getImageFromString(imageData.previewImage))
        }
        view.author_username.text = "Autor: ${imageData.userName}"
        view.comments.text = "Comments: ${imageData.commentsText}"
        view.item_container.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_main_to_details, bundle)
        }
    }

    override fun changeViewState(lastView: View, currentView: View) {}
}