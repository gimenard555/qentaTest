package com.jimenard.qentatest.util.adapters

data class ItemImage(
    var id: String,
    var previewImage: String,
    var userName: String,
    var commentsText: String
) : ItemRecyclerView(
    previewImage,
    userName
)