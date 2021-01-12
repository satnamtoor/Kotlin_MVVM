package com.example.mvvmkotlinexample.view

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.mvvmkotlinexample.R
import com.example.mvvmkotlinexample.db.EnqDB
import com.example.mvvmkotlinexample.db.QueryData
import com.example.mvvmkotlinexample.model.Result
import kotlinx.android.synthetic.main.part_list_item.view.*

class LocalAdapter (val context: Context, var partItemList: List<EnqDB>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var filterList = listOf<EnqDB>()
    var originalList = listOf<EnqDB>()

    fun setAppList(modelList: List<EnqDB>) {
        this.filterList = modelList
        this.originalList = modelList

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.part_list_item, parent, false)
        return LocalAdapter(context, view);

    }

    override fun getItemCount() = partItemList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as LocalAdapter).bind(partItemList[position])
    }

    class LocalAdapter(val context: Context, itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(part: EnqDB) {
            itemView.Name.text = part.Name
            itemView.txtAge.text = part.Age + " yrs,"
            itemView.txtState.text = part.Address

            /* Glide.with(itemView)
                 .load(part.p)
                 .override(600, 600)
                 .fitCenter()
                 .into(itemView.imageProfile)*/

            Glide.with(itemView)
                .asBitmap()
                .load(part.Profileimage)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(object : CustomTarget<Bitmap?>() {
                    override fun onResourceReady(
                        resource: Bitmap,
                        @Nullable transition: Transition<in Bitmap?>?
                    ) {
                        var bitmapDrawable = BitmapDrawable(resource);
                        itemView.imageProfile.setBackground(bitmapDrawable)
                        //itemView.imageProfile.buildDrawingCache()
                    }

                    override fun onLoadCleared(@Nullable placeholder: Drawable?) {}
                })

            itemView.btnAccept.setOnClickListener {
                QueryData.onUpdateN(context, "A", itemView.Name.last)
                itemView.btnAccept.setBackgroundColor(Color.GREEN)

            }

            itemView.btnReject.setOnClickListener {
                QueryData.onUpdateN(context, "R", itemView.Name.last)
                itemView.btnAccept.setBackgroundColor(Color.RED)

            }
        }
    }
}