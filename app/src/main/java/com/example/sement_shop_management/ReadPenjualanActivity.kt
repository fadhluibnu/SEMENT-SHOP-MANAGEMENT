package com.example.sement_shop_management

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sement_shop_management.adapter.PenjualanAdapter
import com.example.sement_shop_management.read.Pengeluaran
import com.example.sement_shop_management.read.Penjualan
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_read_pengeluaran.*
import kotlinx.android.synthetic.main.activity_read_penjualan.*
import kotlinx.android.synthetic.main.list_barang_penjualan.*
import kotlinx.android.synthetic.main.update_dialog.*

class ReadPenjualanActivity : AppCompatActivity() {


    private lateinit var dbref : DatabaseReference
    private lateinit var nama: EditText
    private lateinit var penjList: MutableList<Penjualan>
    private lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read_penjualan)

        lightStatusBar(window)

        arr_back_read_penjualan.setOnClickListener {
            onBackPressed()
        }
        dbref = FirebaseDatabase.getInstance().getReference("data_penjualan")
        getPenjualanData()

//        button_dialog.setOnClickListener {
//            val dialog = BottomSheetDialog(this)
//            val view = layoutInflater.inflate(R.layout.update_dialog, null)
//            val btnClose = view.findViewById<Button>(R.id.btnSaveUpdatePenjualan)
//            btnClose.setOnClickListener {
//                dialog.dismiss()
//            }
//            dialog.setCancelable(false)
//            dialog.setContentView(view)
//            dialog.show()
//        }

    }

    private fun getPenjualanData() {
        listView = findViewById(R.id.rdpenjualan)
        penjList = mutableListOf()
        dbref.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for (hsl in snapshot.children){
                        val penjualan = hsl.getValue(Penjualan::class.java)
                        if(penjualan != null){
                            penjList.add(penjualan)
                        }
                    }
                    val adapter = PenjualanAdapter(applicationContext, R.layout.list_barang_penjualan, penjList)
                    listView.adapter = adapter
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}