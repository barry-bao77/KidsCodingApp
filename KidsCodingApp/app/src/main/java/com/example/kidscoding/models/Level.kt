package com.example.kidscoding.models

/**
 * 关卡数据模型
 */
data class Level(
    val id: Int,                           // 关卡编号
    val themeId: String,                   // 所属主题ID
    val title: String,                     // 关卡标题
    val story: String,                     // 故事开场
    val task: String,                      // 任务描述
    val map: Array<Array<Int>>,            // 格子地图（0=空,1=障碍,2=物品,3=终点）
    val startPos: Position,                // 角色起始位置
    val itemPositions: List<Position>,     // 目标物品位置
    val availableBlocks: List<BlockType>,  // 可用积木类型
    val learnConcept: String               // 学习概念说明
) {
    /**
     * 获取地图行数
     */
    fun getRows(): Int = map.size

    /**
     * 获取地图列数
     */
    fun getCols(): Int = if (map.isNotEmpty()) map[0].size else 0

    /**
     * 检查位置是否在地图范围内
     */
    fun isValidPosition(pos: Position): Boolean {
        return pos.row in 0 until getRows() && pos.col in 0 until getCols()
    }

    /**
     * 获取指定位置的格子类型
     */
    fun getCellType(pos: Position): Int {
        if (!isValidPosition(pos)) return 1 // 越界视为障碍
        return map[pos.row][pos.col]
    }
}