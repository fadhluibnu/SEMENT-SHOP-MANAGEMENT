package com.example.sement_shop_management

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_create_penjualan.*

class CreatePenjualanActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var NamaPenjualan : EditText
    private lateinit var HargaPenjualan : EditText
    private lateinit var btnsave: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_penjualan)


        lightStatusBar(window)

//        untuk menangkap id pada view
        NamaPenjualan = findViewById(R.id.inputNamaPenjualan)
        HargaPenjualan = findViewById(R.id.inputHargaPenjualan)
        btnsave = findViewById(R.id.btnSavePenjualan)

//        kondisi saat button di klik
        btnsave.setOnClickListener (this)
        arr_back_penjualan.setOnClickListener {
            onBackPressed()
        }
    }

//    menjalankan saveData() saat button di klik
    override fun onClick(v: View?) {
        saveData()
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

//    menyimpan ke database
    private fun saveData(){
        val nama = NamaPenjualan.text.toString().trim()
        val harga = HargaPenjualan.text.toString().trim()

//        mengecek apakah nama sudah di isi
        if (nama.isEmpty()){
            NamaPenjualan.error = "Nama haru di isi"
            return
        }
        if (harga.isEmpty()){
            HargaPenjualan.error = "Harga harus di isi"
            return
        }
//        memanggil database pada firebase
        val ref = FirebaseDatabase.getInstance().getReference("data_penjualan")
        val dtPnjualanId = ref.push().key

//    inisialisasi objek DataPenjualan.kt
        val dtPnjlan = DataPenjualan(dtPnjualanId, nama, harga)

        if (dtPnjualanId != null) {
            ref.child(dtPnjualanId).setValue(dtPnjlan).addOnCompleteListener {
                Toast.makeText(applicationContext, "Data Penjualan Berhasil Ditambahkan!!", Toast.LENGTH_SHORT).show()
            }
        }
    }

}




