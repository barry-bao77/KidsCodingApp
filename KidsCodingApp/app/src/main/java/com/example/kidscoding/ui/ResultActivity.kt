package com.example.kidscoding.ui

import android.content.Intent
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.airbnb.lottie.LottieAnimationView
import com.example.kidscoding.R
import com.example.kidscoding.data.LevelRepository
import com.example.kidscoding.data.ProgressManager

class ResultActivity : AppCompatActivity() {

    private lateinit var rootLayout: ConstraintLayout
    private lateinit var lottieAnimation: LottieAnimationView
    private lateinit var ivCharacter: ImageView
    private lateinit var tvTitle: TextView
    private lateinit var tvMessage: TextView
    private lateinit var tvLearnedTitle: TextView
    private lateinit var tvLearnedContent: TextView
    private lateinit var tvStars: TextView
    private lateinit var tvPerfect: TextView
    private lateinit var tvNextLevel: TextView
    private lateinit var tvRetry: TextView

    private lateinit var progressManager: ProgressManager

    private var themeId: String = ""
    private var themeName: String = ""
    private var levelId: Int = 1
    private var success: Boolean = false
    private var stars: Int = 0
    private var learnConcept: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        progressManager = ProgressManager(this)

        // 获取传递的结果信息
        themeId = intent.getStringExtra("themeId") ?: ""
        themeName = intent.getStringExtra("themeName") ?: ""
        levelId = intent.getIntExtra("levelId", 1)
        success = intent.getBooleanExtra("success", false)
        stars = intent.getIntExtra("stars", 0)
        learnConcept = intent.getStringExtra("learnConcept") ?: ""

        setupViews()
        updateProgress()
    }

    private fun setupViews() {
        rootLayout = findViewById(R.id.rootLayout)
        lottieAnimation = findViewById(R.id.lottieAnimation)
        ivCharacter = findViewById(R.id.ivCharacter)
        tvTitle = findViewById(R.id.tvTitle)
        tvMessage = findViewById(R.id.tvMessage)
        tvLearnedTitle = findViewById(R.id.tvLearnedTitle)
        tvLearnedContent = findViewById(R.id.tvLearnedContent)
        tvStars = findViewById(R.id.tvStars)
        tvPerfect = findViewById(R.id.tvPerfect)
        tvNextLevel = findViewById(R.id.tvNextLevel)
        tvRetry = findViewById(R.id.tvRetry)

        if (success) {
            // 成功界面
            rootLayout.setBackgroundResource(R.drawable.bg_result_success)
            tvTitle.text = getString(R.string.result_success_title)
            tvMessage.text = "你成功帮助${themeName}完成任务！"
            tvLearnedContent.text = "✅ $learnConcept"

            val starsText = when (stars) {
                3 -> "⭐⭐⭐"
                2 -> "⭐⭐"
                1 -> "⭐"
                else -> ""
            }
            tvStars.text = starsText
            tvPerfect.text = if (stars == 3) getString(R.string.result_perfect) else ""

            ivCharacter.visibility = ImageView.VISIBLE
            lottieAnimation.visibility = LottieAnimationView.GONE

            tvNextLevel.visibility = TextView.VISIBLE
            tvNextLevel.setOnClickListener {
                goToNextLevel()
            }

            tvRetry.text = getString(R.string.result_retry)
            tvRetry.setOnClickListener {
                retryLevel()
            }

        } else {
            // 失败界面
            rootLayout.setBackgroundResource(R.drawable.bg_result_fail)
            tvTitle.text = getString(R.string.result_fail_title)
            tvMessage.text = "${themeName}相信你一定能做到！"
            tvLearnedTitle.text = getString(R.string.result_hint_title)
            tvLearnedContent.text = "想想看，要怎样安排积木的顺序呢？"
            tvStars.text = ""
            tvPerfect.text = ""

            ivCharacter.visibility = ImageView.VISIBLE
            lottieAnimation.visibility = LottieAnimationView.GONE

            tvNextLevel.visibility = TextView.GONE
            tvRetry.text = getString(R.string.result_try_again)
            tvRetry.setOnClickListener {
                retryLevel()
            }
        }
    }

    private fun updateProgress() {
        if (success) {
            val userProgress = progressManager.loadProgress()
            userProgress.updateLevelProgress(themeId, levelId, stars)

            // 解锁下一关
            userProgress.unlockNextLevel(themeId, levelId)
            progressManager.saveProgress(userProgress)
        }
    }

    private fun goToNextLevel() {
        val theme = LevelRepository.getThemeById(themeId)
        val nextLevelId = levelId + 1

        if (theme != null && nextLevelId <= theme.levels.size) {
            val intent = Intent(this, GameActivity::class.java)
            intent.putExtra("themeId", themeId)
            intent.putExtra("themeName", themeName)
            intent.putExtra("levelId", nextLevelId)
            startActivity(intent)
            finish()
        } else {
            finish()
        }
    }

    private fun retryLevel() {
        val intent = Intent(this, GameActivity::class.java)
        intent.putExtra("themeId", themeId)
        intent.putExtra("themeName", themeName)
        intent.putExtra("levelId", levelId)
        startActivity(intent)
        finish()
    }
}