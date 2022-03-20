package com.example.sement_shop_management

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.widget.EditText
import android.widget.ListView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sement_shop_management.adapter.PengeluaranAdapter
import com.example.sement_shop_management.adapter.PenjualanAdapter
import com.example.sement_shop_management.read.Pengeluaran
import com.example.sement_shop_management.read.Penjualan
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_read_pengeluaran.*

class ReadPengeluaranActivity : AppCompatActivity() {

    private lateinit var dbref : DatabaseReference
    private lateinit var nama: EditText
    private lateinit var pengList: MutableList<Pengeluaran>
    private lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read_pengeluaran)
        lightStatusBar(window)
        arr_back_read_pengeluaran.setOnClickListener {
            onBackPressed()
        }
        dbref = FirebaseDatabase.getInstance().getReference("data_pengeluaran")
        listView = findViewById(R.id.rdpengeluaran)
        pengList = mutableListOf()
        getPengeluaranData()
    }

    private fun getPengeluaranData() {
        dbref.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    pengList.clear()
                    for (hsl in snapshot.children){
                        val pengeluaran = hsl.getValue(Pengeluaran::class.java)
                        if(pengeluaran != null){
                            pengList.add(pengeluaran)
                        }
                    }
                    val adapter = PengeluaranAdapter(this@ReadPengeluaranActivity, R.layout.list_barang_pengeluaran, pengList)
                    listView.adapter = adapter
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

    }
}