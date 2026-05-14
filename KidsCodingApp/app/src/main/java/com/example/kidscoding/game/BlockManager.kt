package com.example.kidscoding.game

import com.example.kidscoding.models.Block
import com.example.kidscoding.models.BlockType

/**
 * 积木序列管理器
 * 管理用户拖拽的积木序列
 */
class BlockManager {

    // 当前已拖入的积木序列
    private val blockSequence: MutableList<Block> = mutableListOf()

    /**
     * 添加积木到序列末尾
     */
    fun addBlock(block: Block) {
        blockSequence.add(block)
    }

    /**
     * 在指定位置插入积木
     */
    fun insertBlock(position: Int, block: Block) {
        if (position in 0..blockSequence.size) {
            blockSequence.add(position, block)
        }
    }

    /**
     * 移除指定位置的积木
     */
    fun removeBlock(position: Int): Block? {
        if (position in 0 until blockSequence.size) {
            return blockSequence.removeAt(position)
        }
        return null
    }

    /**
     * 移动积木位置（拖拽排序）
     */
    fun moveBlock(fromPosition: Int, toPosition: Int) {
        if (fromPosition in 0 until blockSequence.size && toPosition in 0 until blockSequence.size) {
            val block = blockSequence.removeAt(fromPosition)
            blockSequence.add(toPosition, block)
        }
    }

    /**
     * 清空所有积木
     */
    fun clearAll() {
        blockSequence.clear()
    }

    /**
     * 获取当前积木序列
     */
    fun getBlockSequence(): List<Block> = blockSequence.toList()

    /**
     * 获取积木数量
     */
    fun getBlockCount(): Int = blockSequence.size

    /**
     * 生成展开后的执行序列
     * 将循环积木展开为实际的移动指令
     */
    fun expandBlocks(): List<Block> {
        val expandedList = mutableListOf<Block>()
        var i = 0

        while (i < blockSequence.size) {
            val block = blockSequence[i]

            if (block.type == BlockType.LOOP) {
                // 找到循环体内的积木（下一个积木）
                if (i + 1 < blockSequence.size) {
                    val innerBlock = blockSequence[i + 1]
                    // 重复执行循环体内的积木
                    repeat(block.param) {
                        expandedList.add(innerBlock)
                    }
                    i += 2 // 跳过循环积木和循环体积木
                } else {
                    i++
                }
            } else {
                expandedList.add(block)
                i++
            }
        }

        return expandedList
    }

    /**
     * 生成唯一积木ID
     */
    fun generateBlockId(): String = "block_${System.currentTimeMillis()}_${blockSequence.size}"
}