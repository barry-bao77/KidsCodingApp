package com.example.kidscoding.models

/**
 * 游戏执行结果
 */
data class GameResult(
    val success: Boolean,               // 是否成功
    val finalPosition: Position,        // 最终位置
    val collectedItems: Int,            // 收集的物品数量
    val totalItems: Int,                // 总物品数量
    val reachedEnd: Boolean,            // 是否到达终点
    val executionPath: List<Position>   // 执行路径记录
) {
    /**
     * 计算星星评分（1-3星）
     */
    fun getStars(): Int {
        if (!success) return 0
        // 根据收集物品数量评分
        return when {
            collectedItems == totalItems && reachedEnd -> 3
            collectedItems >= totalItems / 2 && reachedEnd -> 2
            reachedEnd -> 1
            else -> 0
        }
    }
}