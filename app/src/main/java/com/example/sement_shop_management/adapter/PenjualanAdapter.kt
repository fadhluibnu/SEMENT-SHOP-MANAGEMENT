package com.example.sement_shop_management.adapter

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sement_shop_management.R
import com.example.sement_shop_management.ReadPenjualanActivity
import com.example.sement_shop_management.read.Penjualan
import com.google.android.material.bottomsheet.BottomSheetDialog

class PenjualanAdapter(val mCtx: Context, val layoutResId: Int, val pnjlist: List<Penjualan>): ArrayAdapter<Penjualan>(mCtx, layoutResId, pnjlist) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)

        val view: View = layoutInflater.inflate(layoutResId, null)
        val nama: TextView = view.findViewById(R.id.text_title_penjualan)

        val penjualan = pnjlist[position]

        nama.text = penjualan.nama

        return view
    }

}