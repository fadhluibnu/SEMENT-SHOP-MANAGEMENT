package com.example.sement_shop_management.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sement_shop_management.DataPengeluaran
import com.example.sement_shop_management.R
import com.example.sement_shop_management.read.Pengeluaran
import com.example.sement_shop_management.read.Penjualan

class PengeluaranAdapter(val mCtx: Context, val layoutResId: Int, val pnglist: List<Pengeluaran>): ArrayAdapter<Pengeluaran>(mCtx, layoutResId, pnglist){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)

        val view: View = layoutInflater.inflate(layoutResId, null)
        val nama: TextView = view.findViewById(R.id.text_title_Pengeluaran)

        val penjualan = pnglist[position]

        nama.text = penjualan.nama

        return view
    }

}