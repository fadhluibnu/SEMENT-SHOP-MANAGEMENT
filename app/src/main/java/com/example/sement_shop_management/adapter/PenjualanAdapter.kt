package com.example.sement_shop_management.adapter

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.sement_shop_management.R
import com.example.sement_shop_management.ReadPenjualanActivity
import com.example.sement_shop_management.read.Penjualan
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class PenjualanAdapter(val mCtx: Context, val layoutResId: Int, val pnjlist: List<Penjualan>): ArrayAdapter<Penjualan>(mCtx, layoutResId, pnjlist) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)

        val view: View = layoutInflater.inflate(layoutResId, null)
        val nama: TextView = view.findViewById(R.id.text_title_penjualan)
        val edit: ImageView = view.findViewById(R.id.icon_edit_penjualan)

        val penjualan = pnjlist[position]
        nama.text = penjualan.nama

        edit.setOnClickListener {
            showUpdateDialog(penjualan)
        }

        return view
    }

    fun showUpdateDialog(penjualan: Penjualan){

        val builder = AlertDialog.Builder(mCtx)

        builder.setTitle("Edit Data : "+ penjualan.nama)
        val inflater = LayoutInflater.from(mCtx)
        val view = inflater.inflate(R.layout.update_dialog, null)

        val etnama = view.findViewById<EditText>(R.id.editNamaPenjualan)
        val etharga = view.findViewById<EditText>(R.id.editHargaPenjualan)


        etnama.setText(penjualan.nama)
        etharga.setText(penjualan.harga)

        builder.setView(view)

        builder.setPositiveButton("Update"){p0,p1 ->
            val db = FirebaseDatabase.getInstance().getReference("data_penjualan")
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
            val penjualan = Penjualan(penjualan.id, nama, harga)

            db.child(penjualan.id).setValue(penjualan)
            Toast.makeText(mCtx, "Data Updated", Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton("No"){po,p1->

        }
        val alert = builder.create()
        alert.show()
    }

//    private fun showUpdateDialog(penjualan: Penjualan){
//        val dialog = AlertDialog.Builder(mCtx)
//
//        val inflater = LayoutInflater.from(mCtx)
//        val view = inflater.inflate(R.layout.update_dialog, null)
//
//        val btnClose = view.findViewById<ImageView>(R.id.arr_back_update_pengeluaran)
//        val btnSave = view.findViewById<Button>(R.id.btnsaveEditPenjualan)
//        val etnama = view.findViewById<EditText>(R.id.editNamaPenjualan)
//        val etharga = view.findViewById<EditText>(R.id.editHargaPenjualan)
//
//        etnama.setText(penjualan.nama)
//        etharga.setText(penjualan.harga)
//
//
//        dialog.setView(view)
//        btnSave.setOnClickListener{
//            val db = FirebaseDatabase.getInstance().getReference("data_penjualan")
//            val nama  = etnama.text.toString().trim()
//            val harga = etharga.text.toString().trim()
//
//            if (nama.isEmpty()){
//                etnama.error = "Nama harus diisi"
//                etnama.requestFocus()
//                return@setOnClickListener
//            }
//            if (harga.isEmpty())  {
//                etharga.error = "Harga harus diisi"
//                etharga.requestFocus()
//                return@setOnClickListener
//            }
//            val penjualan = Penjualan(penjualan.id, nama, harga)
//
//            db.child(penjualan.id).setValue(penjualan)
//            Toast.makeText(mCtx, "Data Updated", Toast.LENGTH_SHORT).show()
//        }
//
//        dialog.setNegativeButton("Batal"){
//            dialog, which ->
//
//        }
//
//        val alert = dialog.create()
//        alert.show()
//
//
//    }

}