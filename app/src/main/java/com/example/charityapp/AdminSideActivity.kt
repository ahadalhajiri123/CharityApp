package com.example.charityapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.charityapp.databinding.ActivityAdminSideBinding

class AdminSideActivity : AppCompatActivity() {
    private var binding:ActivityAdminSideBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminSideBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        binding?.run {
            cardViewPending.setOnClickListener {
             startActivity(Intent(this@AdminSideActivity,DonarSideActivity::class.java).putExtra("admin","pending"))
            }
            cardViewAccept.setOnClickListener {
                startActivity(Intent(this@AdminSideActivity,DonarSideActivity::class.java).putExtra("admin","accept"))
            }
        }
    }
}