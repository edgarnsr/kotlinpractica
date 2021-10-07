package com.example.kotlinpractica.presentation.view.recyclerview.superhero

import android.content.Context
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinpractica.R
import com.example.kotlinpractica.data.model.SuperHeroModel
import com.example.kotlinpractica.data.utils.Constant
import com.squareup.picasso.Picasso

class ItemsRecyclerAdapter(
    private var itemsCells: ArrayList<SuperHeroModel?>,
    private val cellClickListener: CellClickListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    lateinit var mcontext: Context

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    class LoadingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    fun addData(dataViews: ArrayList<SuperHeroModel?>) {
        this.itemsCells.addAll(dataViews)
        notifyDataSetChanged()
    }

    fun getItemAtPosition(position: Int): SuperHeroModel? {
        return itemsCells[position]
    }

    fun addLoadingView() {
        //Add loading item
        Handler().post {
            itemsCells.add(null)
            notifyItemInserted(itemsCells.size - 1)
        }
    }

    fun removeLoadingView() {
        //Remove loading item
        if (itemsCells.size != 0) {
            itemsCells.removeAt(itemsCells.size - 1)
            notifyItemRemoved(itemsCells.size)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        mcontext = parent.context
        return if (viewType == Constant.VIEW_TYPE_ITEM) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_row, parent, false)
            ItemViewHolder(view)
        } else {
            val view = LayoutInflater.from(mcontext).inflate(R.layout.progress_loading, parent, false)
            LoadingViewHolder(view)
        }
    }

    override fun getItemCount(): Int {
        return itemsCells.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (itemsCells[position] == null) {
            Constant.VIEW_TYPE_LOADING
        } else {
            Constant.VIEW_TYPE_ITEM
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder.itemViewType == Constant.VIEW_TYPE_ITEM) {
            val textView = holder.itemView.findViewById<TextView>(R.id.txtView)
            textView.text = itemsCells[position]!!.name
            val imgView = holder.itemView.findViewById<ImageView>(R.id.imgView)
            Picasso.get().load(itemsCells[position]!!.image.url).into(imgView);
            val parent: LinearLayout = holder.itemView.findViewById(R.id.linearItemRow)
            holder.itemView.setOnClickListener {
                cellClickListener.onCellClickListener(itemsCells[position]!!)
            }
        }
    }
}