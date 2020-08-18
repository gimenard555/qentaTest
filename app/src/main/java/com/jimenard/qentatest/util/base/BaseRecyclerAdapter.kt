package com.jimenard.qentatest.util.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jimenard.qentatest.util.adapters.AdapterObject
import com.jimenard.qentatest.util.adapters.ItemRecyclerView
import com.jimenard.qentatest.util.notifiers.INotify

/**
 * Adaptador generico para todos los recicler view utlizados en la aplicacion
 */
class BaseRecyclerAdapter(var items: List<ItemRecyclerView>,
                          private val adapterObject: AdapterObject,
                          private val iNotify: INotify? = null)
    : RecyclerView.Adapter<BaseRecyclerAdapter.BaseRecyclerViewHolder>() {

    private var holders: ArrayList<BaseRecyclerViewHolder> = ArrayList()
    var selectedPosition: Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): BaseRecyclerViewHolder {
        val holder = BaseRecyclerViewHolder(LayoutInflater.from(parent.context)
                .inflate(this.adapterObject.layoutToCharge, parent, false))
        this.holders.add(holder)
        return holder
    }

    override fun getItemCount() = this.items.size

    override fun onBindViewHolder(holder: BaseRecyclerViewHolder, position: Int) {
        holder.setParams(this.items[position])
    }

    /**
     * Se actuliza la informacion de la lista que se carga
     * @param itemsUpdated nueva lista a actualizar
     */
    fun setDataList(itemsUpdated: List<ItemRecyclerView>) {
        this.items = itemsUpdated
        this.notifyDataSetChanged()
    }

    /**
     * Se valida posicion seleccionada
     * @param position posicion seleccionada que llega desde interfaz
     */
    fun setCurrentPosition(position: Int) {
        this.holders[position].changeState(position)
    }

    inner class BaseRecyclerViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        val foregroundView: View? = view.findViewById(adapterObject.foregroundView ?: -1)

        /**
         * Método encargado de recibir los parámetros cuando se renderiza la vista
         * @param itemRecyclerView item con la informacion para actualizar la vista
         */
        fun setParams(itemRecyclerView: ItemRecyclerView) {
            adapterObject.chargeView(itemRecyclerView, this.view, this.adapterPosition, iNotify)
        }

        /**
         * Se notifica para que el item cambie de estado
         * @param position posicion del item que se quiere cambiar de estado
         */
        fun changeState(position: Int) {
            adapterObject.changeViewState(holders[selectedPosition].view, holders[position].view)
            selectedPosition = position
        }
    }
}
