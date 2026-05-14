package com.example.kidscoding.models

/**
 * 积木块数据模型
 */
data class Block(
    val id: String,              // 积木唯一标识
    val type: BlockType,         // 积木类型
    val label: String,           // 显示文字
    val colorRes: Int,           // 颜色资源ID
    val param: Int = 1           // 参数（如重复次数）
) {
    companion object {
        /**
         * 创建默认的移动积木
         */
        fun createMoveBlock(type: BlockType, id: String): Block {
            val label = when (type) {
                BlockType.MOVE_FORWARD -> "➡️ 向前走"
                BlockType.MOVE_UP -> "⬆️ 向上走"
                BlockType.MOVE_DOWN -> "⬇️ 向下走"
                else -> ""
            }
            val color = when (type) {
                BlockType.MOVE_FORWARD, BlockType.MOVE_UP, BlockType.MOVE_DOWN -> R.color.block_move
                else -> R.color.block_move
            }
            return Block(id, type, label, color, 1)
        }

        /**
         * 创建循环积木
         */
        fun createLoopBlock(id: String, repeatCount: Int): Block {
            return Block(
                id = id,
                type = BlockType.LOOP,
                label = "🔄 重复 $repeatCount 次",
                colorRes = R.color.block_loop,
                param = repeatCount
            )
        }

        /**
         * 创建条件积木
         */
        fun createConditionBlock(id: String): Block {
            return Block(
                id = id,
                type = BlockType.CONDITION,
                label = "❓ 如果...",
                colorRes = R.color.block_condition,
                param = 0
            )
        }
    }
}