package com.example.kidscoding.models

/**
 * 单个关卡的进度记录
 */
data class LevelProgress(
    val stars: Int = 0,         // 星星数（0-3）
    val completed: Boolean = false  // 是否完成
)