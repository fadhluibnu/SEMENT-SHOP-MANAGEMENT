package com.example.sement_shop_management

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lightStatusBar(window)

        button_create_data_penjualan.setOnClickListener {
            startActivity(Intent(this, CreatePenjualanActivity::class.java))
        }

        button_create_data_pengeluaran.setOnClickListener {
            startActivity(Intent(this, CreatePengeluaranActivity::class.java))
        }
        to_pengeluaran.setOnClickListener {
            startActivity(Intent(this, ReadPengeluaranActivity::class.java))
        }
        to_penjualan.setOnClickListener {
            startActivity(Intent(this, ReadPenjualanActivity::class.java))
        }
    }
}