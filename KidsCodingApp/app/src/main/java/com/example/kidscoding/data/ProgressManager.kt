package com.example.kidscoding.data

import android.content.Context
import android.content.SharedPreferences
import com.example.kidscoding.models.UserProgress
import com.google.gson.Gson

/**
 * 用户进度存储管理器
 * 使用 SharedPreferences + Gson 存储 JSON 格式的进度数据
 */
class ProgressManager(context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("kids_coding_progress", Context.MODE_PRIVATE)

    private val gson = Gson()

    companion object {
        private const val KEY_USER_PROGRESS = "user_progress"
    }

    /**
     * 保存用户进度
     */
    fun saveProgress(userProgress: UserProgress) {
        val json = gson.toJson(userProgress)
        sharedPreferences.edit()
            .putString(KEY_USER_PROGRESS, json)
            .apply()
    }

    /**
     * 加载用户进度
     * 如果没有存储数据，返回默认进度
     */
    fun loadProgress(): UserProgress {
        val json = sharedPreferences.getString(KEY_USER_PROGRESS, null)
        if (json != null) {
            return gson.fromJson(json, UserProgress::class.java)
        }
        // 返回默认进度
        return UserProgress()
    }

    /**
     * 重置用户进度（清除所有存储数据）
     */
    fun resetProgress() {
        sharedPreferences.edit()
            .remove(KEY_USER_PROGRESS)
            .apply()
    }

    /**
     * 检查是否有保存的进度
     */
    fun hasProgress(): Boolean {
        return sharedPreferences.contains(KEY_USER_PROGRESS)
    }
}