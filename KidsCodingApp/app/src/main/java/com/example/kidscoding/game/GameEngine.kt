package com.example.kidscoding.game

import com.example.kidscoding.models.*

/**
 * 游戏执行引擎
 * 执行积木序列，计算角色移动路径和结果
 */
class GameEngine {

    /**
     * 执行积木序列
     * @param blocks 积木序列（已展开的）
     * @param level 关卡数据
     * @return 游戏执行结果
     */
    fun execute(blocks: List<Block>, level: Level): GameResult {
        var currentPosition = level.startPos
        val executionPath = mutableListOf(currentPosition)
        var collectedItems = 0
        val collectedPositions = mutableSetOf<Position>()

        // 逐个执行积木
        for (block in blocks) {
            val newPosition = executeBlock(block, currentPosition, level)

            // 检查新位置是否有效
            if (!level.isValidPosition(newPosition) || level.getCellType(newPosition) == 1) {
                // 碰到障碍或越界，保持原位置
                executionPath.add(currentPosition)
                continue
            }

            currentPosition = newPosition
            executionPath.add(currentPosition)

            // 检查是否收集到物品
            if (currentPosition in level.itemPositions && currentPosition !in collectedPositions) {
                collectedItems++
                collectedPositions.add(currentPosition)
            }
        }

        // 判断是否成功
        val reachedEnd = level.getCellType(currentPosition) == 3
        val success = reachedEnd && collectedItems == level.itemPositions.size

        return GameResult(
            success = success,
            finalPosition = currentPosition,
            collectedItems = collectedItems,
            totalItems = level.itemPositions.size,
            reachedEnd = reachedEnd,
            executionPath = executionPath
        )
    }

    /**
     * 执行单个积木
     */
    private fun executeBlock(block: Block, currentPos: Position, level: Level): Position {
        return when (block.type) {
            BlockType.MOVE_FORWARD -> currentPos.moveForward()
            BlockType.MOVE_UP -> currentPos.moveUp()
            BlockType.MOVE_DOWN -> currentPos.moveDown()
            BlockType.LOOP -> currentPos // 循环积木不移动，由 expandBlocks 处理
            BlockType.CONDITION -> currentPos // 条件积木暂不实现
            else -> currentPos
        }
    }

    /**
     * 检查关卡是否可通关
     * 用于验证关卡设计的合理性
     */
    fun isLevelSolvable(level: Level, solutionBlocks: List<Block>): Boolean {
        val result = execute(solutionBlocks, level)
        return result.success
    }
}