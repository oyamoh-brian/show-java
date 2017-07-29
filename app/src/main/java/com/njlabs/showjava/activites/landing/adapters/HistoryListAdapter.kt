package com.njlabs.showjava.activites.landing.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.njlabs.showjava.R
import com.njlabs.showjava.models.SourceInfo
import kotlinx.android.synthetic.main.history_list_item.view.*
import android.graphics.drawable.BitmapDrawable
import android.graphics.BitmapFactory
import android.os.Environment
import java.io.File

class HistoryListAdapter(private val historyItems: List<SourceInfo>, private val itemClick: (SourceInfo) -> Unit) : RecyclerView.Adapter<HistoryListAdapter.ViewHolder>() {

    class ViewHolder(view: View, val itemClick: (SourceInfo) -> Unit) : RecyclerView.ViewHolder(view) {

        fun bindSourceInfo(sourceInfo: SourceInfo) {
            with(sourceInfo) {
                itemView.historyItemLabel.text = sourceInfo.packageLabel
                itemView.historyItemPackage.text = sourceInfo.packageName
                val iconPath = "${Environment.getExternalStorageDirectory()}/ShowJava/sources/${sourceInfo.packageName}/icon.png"
                if (File(iconPath).exists()) {
                    val iconBitmap = BitmapFactory.decodeFile(iconPath)
                    itemView.historyItemIcon.setImageDrawable(BitmapDrawable(itemView.context.resources, iconBitmap))
                } else {
                    itemView.historyItemIcon.setImageResource(R.drawable.ic_list_generic)
                }
                itemView.historyItemCard.setOnClickListener { itemClick(this) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryListAdapter.ViewHolder? {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.history_list_item, parent, false)
        return ViewHolder(view, itemClick)
    }

    override fun onBindViewHolder(holder: HistoryListAdapter.ViewHolder, position: Int) {
        holder.bindSourceInfo(historyItems[position])
    }

    override fun getItemCount(): Int {
        return historyItems.size
    }
}
