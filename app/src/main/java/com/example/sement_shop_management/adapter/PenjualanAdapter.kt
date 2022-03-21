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
import kotlinx.android.synthetic.main.delete_dialog.view.*
import kotlinx.android.synthetic.main.update_dialog.view.*

class PenjualanAdapter(val mCtx: Context, val layoutResId: Int, val pnjlist: List<Penjualan>): ArrayAdapter<Penjualan>(mCtx, layoutResId, pnjlist) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)

        val view: View = layoutInflater.inflate(layoutResId, null)
        val nama: TextView = view.findViewById(R.id.text_title_penjualan)
        val edit: ImageView = view.findViewById(R.id.icon_edit_penjualan)
        val delete: ImageView = view.findViewById(R.id.icon_delete_penjualan)

        val penjualan = pnjlist[position]
        nama.text = penjualan.nama

        edit.setOnClickListener {
            showUpdateDialog(penjualan)
        }
        delete.setOnClickListener {
            setDeleteDialog(penjualan)
        }

        return view
    }

    fun setDeleteDialog(penjualan: Penjualan){
        val inflater = LayoutInflater.from(mCtx)
        val view = inflater.inflate(R.layout.delete_dialog, null)

        val builder = AlertDialog.Builder(mCtx)
        builder.setView(view)
        val dialog = builder.create()
        dialog.show()
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.setCancelable(false)

        view.icon_delete_batal.setOnClickListener {
            dialog.dismiss()
        }
        view.icon_delete_permanent.setOnClickListener {
            val dbPenjualan = FirebaseDatabase.getInstance().getReference("data_penjualan").child(penjualan.id)
            dbPenjualan.removeValue()

            Toast.makeText(mCtx, "Data deleted!!", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }
    }

    fun showUpdateDialog(penjualan: Penjualan){
        val inflater = LayoutInflater.from(mCtx)
        val view = inflater.inflate(R.layout.update_dialog, null)

        val builder = AlertDialog.Builder(mCtx)
        builder.setView(view)
        val dialog = builder.create()
        dialog.show()
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.setCancelable(false)

        val etnama = view.findViewById<EditText>(R.id.editNamaPenjualan)
        val etharga = view.findViewById<EditText>(R.id.editHargaPenjualan)
        val titleEdit = view.findViewById<TextView>(R.id.textView3)
        etnama.setText(penjualan.nama)
        etharga.setText(penjualan.harga)
        titleEdit.setText("Edit Data : "+ penjualan.nama)

        view.icon_update.setOnClickListener {
            val db = FirebaseDatabase.getInstance().getReference("data_penjualan")
            val nama  = etnama.text.toString().trim()
            val harga = etharga.text.toString().trim()

            if (nama.isEmpty()){
                etnama.error = "Nama harus diisi"
                etnama.requestFocus()
                return@setOnClickListener
            }
            if (harga.isEmpty())  {
                etharga.error = "Harga harus diisi"
                etharga.requestFocus()
                return@setOnClickListener
            }
            val penjualan = Penjualan(penjualan.id, nama, harga)

            db.child(penjualan.id).setValue(penjualan)
            Toast.makeText(mCtx, "Data Updated", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }
        view.imageView_close_update.setOnClickListener {
            dialog.dismiss()
        }
    }

//    fun showUpdateDialog(penjualan: Penjualan){
//
//        val builder = AlertDialog.Builder(mCtx)
//
//        builder.setTitle("Edit Data : "+ penjualan.nama)
//        val inflater = LayoutInflater.from(mCtx)
//        val view = inflater.inflate(R.layout.update_dialog, null)
//
//        val etnama = view.findViewById<EditText>(R.id.editNamaPenjualan)
//        val etharga = view.findViewById<EditText>(R.id.editHargaPenjualan)
//
//
//        etnama.setText(penjualan.nama)
//        etharga.setText(penjualan.harga)
//
//        builder.setView(view)
//
//        builder.setPositiveButton("Update"){p0,p1 ->
//            val db = FirebaseDatabase.getInstance().getReference("data_penjualan")
//            val nama  = etnama.text.toString().trim()
//            val harga = etharga.text.toString().trim()
//
//            if (nama.isEmpty()){
//                etnama.error = "Nama harus diisi"
//                etnama.requestFocus()
//                return@setPositiveButton
//            }
//            if (harga.isEmpty())  {
//                etharga.error = "Harga harus diisi"
//                etharga.requestFocus()
//                return@setPositiveButton
//            }
//            val penjualan = Penjualan(penjualan.id, nama, harga)
//
//            db.child(penjualan.id).setValue(penjualan)
//            Toast.makeText(mCtx, "Data Updated", Toast.LENGTH_SHORT).show()
//        }
//        builder.setNegativeButton("No"){po,p1->
//
//        }
//        val alert = builder.create()
//        alert.show()
//    }


}