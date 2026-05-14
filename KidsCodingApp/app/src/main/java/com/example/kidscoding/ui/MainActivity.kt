package com.example.kidscoding.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 直接跳转到主题选择页
        val intent = Intent(this, ThemeSelectActivity::class.java)
        startActivity(intent)
        finish()
    }
}