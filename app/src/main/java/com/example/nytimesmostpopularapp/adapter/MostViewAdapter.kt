package com.example.nytimesmostpopularapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nytimesmostpopularapp.R
import com.example.nytimesmostpopularapp.callback.ItemSelect
import com.example.nytimesmostpopularapp.data.network.responses.ResultsItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.most_popular_item.view.*
import kotlinx.android.synthetic.main.most_popular_item.view.image_view
import kotlinx.android.synthetic.main.most_popular_item.view.parent_view
import kotlinx.android.synthetic.main.most_popular_item.view.title

class MostViewAdapter(
    searchList: MutableList<ResultsItem>
) :
    RecyclerView.Adapter<MostViewAdapter.MostViewHolder>() {
    private var mDataset: MutableList<ResultsItem?> = mutableListOf()
    var callbacks: ItemSelect? = null

    inner class MostViewHolder(private val view: View) : RecyclerView.ViewHolder(view),
        View.OnClickListener {

        fun bind(itemModel: ResultsItem, position: Int) {
            itemView.parent_view.tag = position
            itemView.parent_view.setOnClickListener(this)
            itemView.title.text = itemModel.section
            itemView.sub_title.text = itemModel.title

            val url =
                itemModel.media?.filter { it?.type == "image" }?.map { it?.mediaMetadata?.get(2)?.url }
                    ?.first()

            Picasso.get()
                .load(url)
                .error(com.google.android.material.R.drawable.mtrl_ic_error)
                .into(itemView.image_view)

        }

        override fun onClick(p0: View?) {
            val model = mDataset[view.tag as Int]
            callbacks!!.onItemSelected(model!!)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MostViewAdapter.MostViewHolder {
        return MostViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.most_popular_item,
                parent,
                false
            )
        )

    }

    override fun getItemCount(): Int = mDataset.size

    override fun onBindViewHolder(holder: MostViewAdapter.MostViewHolder, position: Int) {
        val model: ResultsItem = this.mDataset[position]!!
        holder.bind(model, position)
    }

    fun deleteItem(
        position: Int
    ) {
        mDataset.removeAt(position)
        notifyDataSetChanged()
    }

    fun addList(lst: List<ResultsItem?>?) {
        mDataset.clear()
        mDataset = ((lst as MutableList<ResultsItem>).toMutableList())
        notifyDataSetChanged()
    }

    fun removeAll() {
        mDataset.clear()
        notifyDataSetChanged()
    }

    fun getCurrentList(): MutableList<ResultsItem?> {
        if (mDataset.isNullOrEmpty())
            return mutableListOf()
        return mDataset
    }

    fun addNewItem(obj: ResultsItem?) {
        obj.let {
            it?.let { it1 -> mDataset.add(it1) }
        }
        notifyDataSetChanged()
    }

    init {
        mDataset = searchList.toMutableList()
    }

    fun updateItem(pos: Int, obj: ResultsItem?) {
        obj?.let { mDataset.removeAt(pos) }
        obj?.let { mDataset.add(pos, it) }
        notifyDataSetChanged()
    }


}
