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
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.delete_dialog.view.*
import kotlinx.android.synthetic.main.update_dialog.view.*

class PengeluaranAdapter(val mCtx: Context, val layoutResId: Int, val pnglist: List<Pengeluaran>): ArrayAdapter<Pengeluaran>(mCtx, layoutResId, pnglist){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)

        val view: View = layoutInflater.inflate(layoutResId, null)
        val nama: TextView = view.findViewById(R.id.text_title_pengeluaran)
        val edit: ImageView = view.findViewById(R.id.icon_edit_pengeluaran)
        val delete: ImageView = view.findViewById(R.id.icon_delete_pengeluaran)

        val pengeluaran = pnglist[position]

        nama.text = pengeluaran.nama
        edit.setOnClickListener {
            setUpdateDialog(pengeluaran)
        }
        delete.setOnClickListener {
            setDeleteDialog(pengeluaran)
        }

        return view
    }

    fun setDeleteDialog(pengeluaran: Pengeluaran){
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
            val dbPengeluaran = FirebaseDatabase.getInstance().getReference("data_pengeluaran").child(pengeluaran.id)
            dbPengeluaran.removeValue()

            Toast.makeText(mCtx, "Data deleted!!", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }
    }

    fun setUpdateDialog(pengeluaran: Pengeluaran) {
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
        etnama.setText(pengeluaran.nama)
        etharga.setText(pengeluaran.harga)
        titleEdit.setText("Edit Data : "+ pengeluaran.nama)

        view.icon_update.setOnClickListener {
            val db = FirebaseDatabase.getInstance().getReference("data_pengeluaran")
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
            val pengeluaran = Pengeluaran(pengeluaran.id, nama, harga)

            db.child(pengeluaran.id).setValue(pengeluaran)
            Toast.makeText(mCtx, "Data Updated", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }
        view.imageView_close_update.setOnClickListener {
            dialog.dismiss()
        }
    }

}