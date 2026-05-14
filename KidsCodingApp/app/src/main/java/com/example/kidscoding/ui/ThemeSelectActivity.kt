package com.example.kidscoding.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kidscoding.R
import com.example.kidscoding.data.LevelRepository
import com.example.kidscoding.data.ProgressManager
import com.example.kidscoding.models.UserProgress
import com.example.kidscoding.ui.adapters.ThemeAdapter

class ThemeSelectActivity : AppCompatActivity() {

    private lateinit var rvThemes: RecyclerView
    private lateinit var progressManager: ProgressManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_theme_select)

        progressManager = ProgressManager(this)

        // 初始化进度（如果是首次运行）
        if (!progressManager.hasProgress()) {
            val userProgress = UserProgress()
            userProgress.initializeDefault(LevelRepository.getAllThemes().map { it.id })
            progressManager.saveProgress(userProgress)
        }

        setupViews()
    }

    private fun setupViews() {
        rvThemes = findViewById(R.id.rvThemes)

        // 设置网格布局（2列）
        rvThemes.layoutManager = GridLayoutManager(this, 2)

        // 设置适配器
        val themes = LevelRepository.getAllThemes()
        val adapter = ThemeAdapter(themes) { theme ->
            // 点击主题卡片，跳转到关卡选择页
            val intent = Intent(this, LevelSelectActivity::class.java)
            intent.putExtra("themeId", theme.id)
            intent.putExtra("themeName", theme.name)
            startActivity(intent)
        }
        rvThemes.adapter = adapter
    }
}