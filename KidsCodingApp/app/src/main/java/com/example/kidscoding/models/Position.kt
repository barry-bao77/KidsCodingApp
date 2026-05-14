package com.example.kidscoding.models

/**
 * 格子位置坐标
 */
data class Position(
    val row: Int,     // 行坐标（从0开始）
    val col: Int      // 列坐标（从0开始）
) {
    fun moveForward(): Position = Position(row, col + 1)
    fun moveUp(): Position = Position(row - 1, col)
    fun moveDown(): Position = Position(row + 1, col)
}