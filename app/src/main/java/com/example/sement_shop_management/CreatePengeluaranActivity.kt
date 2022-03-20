package com.example.sement_shop_management

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_create_pengeluaran.*

class CreatePengeluaranActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var NamaPengeluaran : EditText
    private lateinit var HargaPengeluaran : EditText
    private lateinit var btnsave: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_pengeluaran)

        lightStatusBar(window)

        NamaPengeluaran = findViewById(R.id.inputNamaPengeluaran)
        HargaPengeluaran = findViewById(R.id.inputHargaPengeluaran)
        btnsave = findViewById(R.id.btnSavePengeluaran)


        btnsave.setOnClickListener (this)
        arr_back_pengluaran.setOnClickListener {
            onBackPressed()
        }

    }

    override fun onClick(v: View?) {
        saveData()
        startActivity(Intent(this, ReadPengeluaranActivity::class.java))
        finish()
    }

    private fun saveData(){
        val nama = NamaPengeluaran.text.toString().trim()
        val harga = HargaPengeluaran.text.toString().trim()

        if (nama.isEmpty()){
            NamaPengeluaran.error = "Nama haru di isi"
            return
        }
        if (harga.isEmpty()){
            HargaPengeluaran.error = "Harga harus di isi"
            return
        }

        val ref = FirebaseDatabase.getInstance().getReference("data_pengeluaran")
        val dtPngranId = ref.push().key

        val dtPngran = DataPenjualan(dtPngranId, nama, harga)

        if (dtPngranId != null) {
            ref.child(dtPngranId).setValue(dtPngran).addOnCompleteListener {
                Toast.makeText(applicationContext, "Data Pengluaran Berhasil Ditambahkan!!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}