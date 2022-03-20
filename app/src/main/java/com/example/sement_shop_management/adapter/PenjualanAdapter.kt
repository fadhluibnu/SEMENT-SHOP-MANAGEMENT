package com.example.sement_shop_management.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sement_shop_management.R
import com.example.sement_shop_management.read.Penjualan

class PenjualanAdapter(private val penjualanList: ArrayList<Penjualan>): RecyclerView.Adapter<PenjualanAdapter.PenjualanViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PenjualanViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_barang_penjualan, parent, false)
        return PenjualanViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PenjualanViewHolder, position: Int) {
        val curretitem = penjualanList[position]
        holder.nama.text = curretitem.nama
    }

    override fun getItemCount(): Int {
        return penjualanList.size
    }

    class PenjualanViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val nama: TextView = itemView.findViewById(R.id.text_title_penjualan)
    }
}