package com.example.kidscoding.models

/**
 * 用户总进度模型
 */
data class UserProgress(
    var currentTheme: String = "小马宝莉",                          // 当前选中的主题
    val unlockedLevels: MutableMap<String, List<Int>> = mutableMapOf(),  // 已解锁关卡
    val completedLevels: MutableMap<String, MutableMap<Int, LevelProgress>> = mutableMapOf()  // 已完成关卡进度
) {
    /**
     * 初始化默认进度（每个主题第1关解锁）
     */
    fun initializeDefault(themes: List<String>) {
        for (theme in themes) {
            unlockedLevels[theme] = listOf(1)
            completedLevels[theme] = mutableMapOf()
        }
    }

    /**
     * 检查关卡是否已解锁
     */
    fun isLevelUnlocked(themeId: String, levelId: Int): Boolean {
        val unlocked = unlockedLevels[themeId] ?: return false
        return levelId in unlocked
    }

    /**
     * 解锁下一关
     */
    fun unlockNextLevel(themeId: String, currentLevelId: Int) {
        val nextLevel = currentLevelId + 1
        val currentList = unlockedLevels[themeId] ?: listOf()
        if (nextLevel !in currentList) {
            unlockedLevels[themeId] = currentList + nextLevel
        }
    }

    /**
     * 更新关卡完成记录
     */
    fun updateLevelProgress(themeId: String, levelId: Int, stars: Int) {
        val themeProgress = completedLevels[themeId] ?: mutableMapOf()
        themeProgress[levelId] = LevelProgress(stars, true)
        completedLevels[themeId] = themeProgress
    }

    /**
     * 获取关卡进度
     */
    fun getLevelProgress(themeId: String, levelId: Int): LevelProgress? {
        return completedLevels[themeId]?.get(levelId)
    }
}