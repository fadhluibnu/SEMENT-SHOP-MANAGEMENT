package com.example.sement_shop_management.adapter

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.sement_shop_management.DataPengeluaran
import com.example.sement_shop_management.R
import com.example.sement_shop_management.read.Pengeluaran
import com.example.sement_shop_management.read.Penjualan
import com.google.firebase.database.FirebaseDatabase

class PengeluaranAdapter(val mCtx: Context, val layoutResId: Int, val pnglist: List<Pengeluaran>): ArrayAdapter<Pengeluaran>(mCtx, layoutResId, pnglist){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)

        val view: View = layoutInflater.inflate(layoutResId, null)
        val nama: TextView = view.findViewById(R.id.text_title_pengeluaran)
        val edit: ImageView = view.findViewById(R.id.icon_edit_pengeluaran)

        val pengeluaran = pnglist[position]

        nama.text = pengeluaran.nama
        edit.setOnClickListener {
            setUpdateDialog(pengeluaran)
        }

        return view
    }

    fun setUpdateDialog(pengeluaran: Pengeluaran) {

        val builder = AlertDialog.Builder(mCtx)

        builder.setTitle("Edit Data : "+ pengeluaran.nama)
        val inflater = LayoutInflater.from(mCtx)
        val view = inflater.inflate(R.layout.update_dialog, null)

        val etnama = view.findViewById<EditText>(R.id.editNamaPenjualan)
        val etharga = view.findViewById<EditText>(R.id.editHargaPenjualan)

        etnama.setText(pengeluaran.nama)
        etharga.setText(pengeluaran.harga)

        builder.setView(view)
        builder.setPositiveButton("Update"){p0,p1 ->
            val db = FirebaseDatabase.getInstance().getReference("data_pengeluaran")
            val nama  = etnama.text.toString().trim()
            val harga = etharga.text.toString().trim()

            if (nama.isEmpty()){
                etnama.error = "Nama harus diisi"
                etnama.requestFocus()
                return@setPositiveButton
            }
            if (harga.isEmpty())  {
                etharga.error = "Harga harus diisi"
                etharga.requestFocus()
                return@setPositiveButton
            }
            val pengeluaran = Pengeluaran(pengeluaran.id, nama, harga)

            db.child(pengeluaran.id).setValue(pengeluaran)
            Toast.makeText(mCtx, "Data Updated", Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton("No"){po,p1->

        }
        val alert = builder.create()
        alert.show()
    }

}