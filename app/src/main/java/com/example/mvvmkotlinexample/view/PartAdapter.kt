package com.example.mvvmkotlinexample.view;

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
import com.example.mvvmkotlinexample.db.QueryData
import com.example.mvvmkotlinexample.db.SendStatusUpdate
import com.example.mvvmkotlinexample.model.Result
import kotlinx.android.synthetic.main.part_list_item.view.*


class PartAdapter(val context: Context, var partItemList: List<Result>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.part_list_item, parent, false)
        return PartViewHolder(context, view);

    }

    override fun getItemCount() = partItemList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as PartViewHolder).bind(partItemList[position])
    }

    class PartViewHolder(val context: Context, itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(part: Result) {
            itemView.Name.text = part.name.first + " " + part.name.last
            itemView.txtAge.text = part.dob.age.toString() + " yrs,"
            itemView.txtState.text = part.location.street.number.toString() + ", " +
                    part.location.street.name + ", " +
                    part.location.city + ", " +
                    part.location.state + ", " +
                    part.location.country

            Glide.with(itemView)
                .asBitmap()
                .load(part.picture.large)
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
                QueryData.onUpdateN(context, "A", part.name.last)
                itemView.btnAccept.setBackgroundColor(Color.GREEN)

            }

            itemView.btnReject.setOnClickListener {
                QueryData.onUpdateN(context, "R", part.name.last)
                itemView.btnAccept.setBackgroundColor(Color.RED)

            }
        }
    }
}
