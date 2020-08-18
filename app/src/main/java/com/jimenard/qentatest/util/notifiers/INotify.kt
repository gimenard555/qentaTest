package com.jimenard.qentatest.util.notifiers

/**
 * Se notifica cuando se selecciona un item de un adapter
 * para el recicler generico
 */
interface INotify {

    /**
     * Se notifica cuando se selecciona item del recicler
     * @param position posicion del arreglo que se selecciona
     */
    fun itemSelected(position: Int)
}