package com.example.kidscoding.models

/**
 * 角色主题模型
 */
data class Theme(
    val id: String,             // 主题ID
    val name: String,           // 角色名称
    val storyBackground: String, // 故事背景简介
    val colorScheme: String,    // 颜色方案（用于界面配色）
    val levels: List<Level>     // 该主题的关卡列表
) {
    /**
     * 获取可玩关卡数量
     */
    fun getPlayableLevelCount(): Int = levels.size
}