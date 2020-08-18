package com.jimenard.qentatest.util.adapters

/**
 * Representa los atributos que comparten los items para cargar en recyclerView
 * @param itemImage imagen del item
 * @param itemTitle titulo del item
 */
abstract class ItemRecyclerView(private var itemImage: String,
                                private var itemTitle: String)