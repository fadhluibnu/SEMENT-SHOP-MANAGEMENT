package com.example.sement_shop_management.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sement_shop_management.DataPengeluaran
import com.example.sement_shop_management.R
import com.example.sement_shop_management.read.Pengeluaran

class PengeluaranAdapter(private val pengeluaranList: ArrayList<Pengeluaran>) :
    RecyclerView.Adapter<PengeluaranAdapter.PengeluaranViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PengeluaranViewHolder {

        val itemVIew = LayoutInflater.from(parent.context).inflate(R.layout.list_barang_pengeluaran, parent, false)
        return PengeluaranViewHolder(itemVIew)

    }

    override fun onBindViewHolder(holder: PengeluaranViewHolder, position: Int) {

        val curretitem = pengeluaranList[position]
        holder.nama.text = curretitem.nama

    }

    override fun getItemCount(): Int {
        return pengeluaranList.size
    }

    class PengeluaranViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val nama: TextView = itemView.findViewById(R.id.text_title_Pengeluaran)

    }
}