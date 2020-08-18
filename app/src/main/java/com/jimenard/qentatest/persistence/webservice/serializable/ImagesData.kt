package com.jimenard.qentatest.persistence.webservice.serializable

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ImagesData(
    @SerializedName(HITS)
    @Expose
    var images: List<ImageData>? = null
) {
    companion object {
        const val HITS = "hits"
        const val ID = "id"
        const val PREVIEW = "previewURL"
        const val IMAGE = "webformatURL"
        const val COMMENTS = "comments"
        const val USER = "user"
    }

    inner class ImageData(
        @SerializedName(ID)
        @Expose
        var id: String? = null,

        @SerializedName(PREVIEW)
        @Expose
        var previewImage: String? = null,

        @SerializedName(IMAGE)
        @Expose
        var image: String? = null,

        @SerializedName(COMMENTS)
        @Expose
        var comments: String? = null,

        @SerializedName(USER)
        @Expose
        var user: String? = null
    )

}