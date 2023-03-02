package com.eypancakir.oyundenemesiv1

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class PieceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(imageResource: Int) {
        itemView.findViewById<ImageView>(R.id.imgPiece).setImageResource(imageResource)
    }
}
