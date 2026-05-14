package com.example.kidscoding.ui

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kidscoding.R
import com.example.kidscoding.data.LevelRepository
import com.example.kidscoding.data.ProgressManager
import com.example.kidscoding.ui.adapters.LevelAdapter

class LevelSelectActivity : AppCompatActivity() {

    private lateinit var ivCharacter: ImageView
    private lateinit var tvName: TextView
    private lateinit var tvStory: TextView
    private lateinit var rvLevels: RecyclerView
    private lateinit var tvBack: TextView
    private lateinit var progressManager: ProgressManager

    private var themeId: String = ""
    private var themeName: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_level_select)

        progressManager = ProgressManager(this)

        // 获取传递的主题信息
        themeId = intent.getStringExtra("themeId") ?: ""
        themeName = intent.getStringExtra("themeName") ?: ""

        setupViews()
    }

    private fun setupViews() {
        ivCharacter = findViewById(R.id.ivCharacter)
        tvName = findViewById(R.id.tvName)
        tvStory = findViewById(R.id.tvStory)
        rvLevels = findViewById(R.id.rvLevels)
        tvBack = findViewById(R.id.tvBack)

        // 获取主题数据
        val theme = LevelRepository.getThemeById(themeId)
        if (theme != null) {
            tvName.text = theme.name
            tvStory.text = theme.storyBackground
            ivCharacter.setImageResource(R.drawable.ic_placeholder)

            // 设置关卡列表
            rvLevels.layoutManager = LinearLayoutManager(this)
            val adapter = LevelAdapter(
                levels = theme.levels,
                progressManager = progressManager,
                themeId = themeId
            ) { level, isPlayable ->
                if (isPlayable) {
                    val intent = Intent(this, GameActivity::class.java)
                    intent.putExtra("themeId", themeId)
                    intent.putExtra("themeName", themeName)
                    intent.putExtra("levelId", level.id)
                    startActivity(intent)
                }
            }
            rvLevels.adapter = adapter
        }

        // 返回按钮
        tvBack.setOnClickListener {
            finish()
        }
    }
}