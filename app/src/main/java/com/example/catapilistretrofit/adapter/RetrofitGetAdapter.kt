package com.example.catapilistretrofit.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.catapilistretrofit.R
import com.example.catapilistretrofit.model.CatsModel
import com.google.android.material.imageview.ShapeableImageView

class RetrofitGetAdapter(private val context: Context, private val lists: List<CatsModel>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.search_item, parent, false)
        return MyViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val list = lists[position]

        if (holder is MyViewHolder) {

            holder.apply {
                itemId.text = "id: ${list.id}"
                itemWidth.text = "${list.width.toString()} x"
                itemHeight.text = list.height.toString()

                Glide.with(context)
                    .load(list.url)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.img)
                    .into(itemImage)
            }

        }
    }


    override fun getItemCount(): Int {
        return lists.size
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val itemImage: ShapeableImageView = view.findViewById(R.id.item_image)
        val itemId: AppCompatTextView = view.findViewById(R.id.item_id)
        val itemWidth: AppCompatTextView = view.findViewById(R.id.item_width)
        val itemHeight: AppCompatTextView = view.findViewById(R.id.item_height)
    }
}