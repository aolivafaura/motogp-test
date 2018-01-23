package com.oliva.antonio.brastlewarkguide.ui.main.list.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.oliva.antonio.dornatest.R
import com.oliva.antonio.dornatest.entity.EventUI
import com.oliva.antonio.dornatest.extensions.loadUrl
import com.oliva.antonio.dornatest.extensions.loadUrlRounded
import com.oliva.antonio.dornatest.utils.formatEventDates

/**
 * Created by antonio
 */

class EventsAdapter(val items: MutableList<EventUI>) : RecyclerView.Adapter<EventsAdapter.EventHolder>() {

    // CALLBACKS -----------------------------------------------------------------------------------
    var onClickItem: ((item: Int) -> Unit)? = null

    // INIT BLOCK ----------------------------------------------------------------------------------
    init {
        setHasStableIds(true)
    }

    // RECYCLER VIEW OVERRIDES ---------------------------------------------------------------------
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventHolder? {
        val layoutInflater = LayoutInflater.from(parent.context)
        return EventHolder(layoutInflater.inflate(R.layout.row_event, parent, false))
    }

    override fun onBindViewHolder(holder: EventHolder, position: Int) {
        val event = (items[position])
        holder.image.loadUrl(event.imageUrl)
        holder.name.text = event.name
        holder.flag.loadUrlRounded(event.circuitFlag)
        holder.dates.text = formatEventDates(event.dateBegin, event.dateFinish)
        holder.itemView.setOnClickListener { onClickItem?.invoke(event.id) }
    }

    override fun getItemId(position: Int) = items[position].id.toLong()

    override fun getItemCount() = items.size

    // PUBLIC FUNCTIONS ----------------------------------------------------------------------------
    /**
     * Replaces existing items with the new items
     * @param events new items for adapter
     */
    fun updateList(events: MutableList<EventUI>) {
        this.items.apply {
            clear()
            addAll(events)
        }

        notifyDataSetChanged()
    }

    // HOLDER CLASSES ------------------------------------------------------------------------------
    /**
     * Row holder
     */
    class EventHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image = itemView.findViewById<ImageView>(R.id.iv_event_image)!!
        val flag = itemView.findViewById<ImageView>(R.id.iv_flag)!!
        val name = itemView.findViewById<TextView>(R.id.tv_name)!!
        val dates = itemView.findViewById<TextView>(R.id.tv_dates)!!
    }
}
