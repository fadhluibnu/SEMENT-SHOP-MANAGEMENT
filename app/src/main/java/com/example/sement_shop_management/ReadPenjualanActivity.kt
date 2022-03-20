package com.example.sement_shop_management

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sement_shop_management.adapter.PenjualanAdapter
import com.example.sement_shop_management.read.Pengeluaran
import com.example.sement_shop_management.read.Penjualan
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_read_pengeluaran.*
import kotlinx.android.synthetic.main.activity_read_penjualan.*

class ReadPenjualanActivity : AppCompatActivity() {


    private lateinit var dbref : DatabaseReference
    private lateinit var penjualanRecyclerView: RecyclerView
    private lateinit var penjualanArrayList: ArrayList<Penjualan>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read_penjualan)

        lightStatusBar(window)

        penjualanRecyclerView = findViewById(R.id.rdpenjualan)
        penjualanRecyclerView.layoutManager = LinearLayoutManager(this)
        penjualanRecyclerView.setHasFixedSize(true)

        penjualanArrayList = ArrayList<Penjualan>()
        getPenjualanData()

        arr_back_read_penjualan.setOnClickListener {
            onBackPressed()
        }
    }

    private fun getPenjualanData() {
        dbref = FirebaseDatabase.getInstance().getReference("data_penjualan")
        dbref.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for (penjualanSnap in snapshot.children){
                        val penjualan = penjualanSnap.getValue(Penjualan::class.java)
                        penjualanArrayList.add(penjualan!!)
                    }
                    penjualanRecyclerView.adapter = PenjualanAdapter(penjualanArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}