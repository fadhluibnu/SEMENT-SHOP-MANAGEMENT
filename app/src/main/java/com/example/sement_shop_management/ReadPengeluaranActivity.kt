package com.example.sement_shop_management

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sement_shop_management.adapter.PengeluaranAdapter
import com.example.sement_shop_management.read.Pengeluaran
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_read_pengeluaran.*

class ReadPengeluaranActivity : AppCompatActivity() {

    private lateinit var dbref : DatabaseReference
    private lateinit var pengeluaranRecyclerView: RecyclerView
    private lateinit var PengeluaranArrayList: ArrayList<Pengeluaran>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read_pengeluaran)
        lightStatusBar(window)


        pengeluaranRecyclerView = findViewById(R.id.rdpengeluaran)
        pengeluaranRecyclerView.layoutManager = LinearLayoutManager(this)
        pengeluaranRecyclerView.setHasFixedSize(true)

        PengeluaranArrayList = arrayListOf<Pengeluaran>()
        getPengeluaranData()

        arr_back_read_pengeluaran.setOnClickListener {
            onBackPressed()
        }
    }

    private fun getPengeluaranData() {
        dbref = FirebaseDatabase.getInstance().getReference("data_pengeluaran")

        dbref.addValueEventListener(object : ValueEventListener{

            override fun onDataChange(snapshot: DataSnapshot) {

                if(snapshot.exists()){
                    for(pengeluaranSnap in snapshot.children){
                        val pengeluaran = pengeluaranSnap.getValue(Pengeluaran::class.java)
                        PengeluaranArrayList.add(pengeluaran!!)
                    }

                    pengeluaranRecyclerView.adapter = PengeluaranAdapter(PengeluaranArrayList)

                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

    }
}