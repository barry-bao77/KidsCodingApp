package com.example.kidscoding.game

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.kidscoding.models.Level
import com.example.kidscoding.models.Position

/**
 * 游戏场景绘制器
 * 使用 Canvas 绘制格子地图、角色和物品
 */
class SceneRenderer(context: Context, attrs: AttributeSet? = null) : View(context, attrs) {

    private var level: Level? = null
    private var characterPosition: Position? = null
    private var collectedPositions: Set<Position> = emptySet()

    // 绘制配置
    private var cellSize: Float = 60f
    private var padding: Float = 20f

    // 颜色定义
    private val emptyCellColor = Color.parseColor("#F0F0F0")
    private val obstacleColor = Color.parseColor("#8B4513")
    private val itemColor = Color.parseColor("#FFE66D")
    private val endColor = Color.parseColor("#4CAF50")
    private val characterColor = Color.parseColor("#FFB6C1")
    private val gridLineColor = Color.parseColor("#CCCCCC")

    // 画笔
    private val cellPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val characterPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val gridPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    init {
        textPaint.color = Color.WHITE
        textPaint.textSize = 24f
        textPaint.textAlign = Paint.Align.CENTER

        characterPaint.color = characterColor
        characterPaint.style = Paint.Style.FILL

        gridPaint.color = gridLineColor
        gridPaint.strokeWidth = 1f
        gridPaint.style = Paint.Style.STROKE
    }

    /**
     * 设置关卡数据
     */
    fun setLevel(level: Level) {
        this.level = level
        this.characterPosition = level.startPos
        this.collectedPositions = emptySet()
        requestLayout()
        invalidate()
    }

    /**
     * 更新角色位置
     */
    fun updateCharacterPosition(position: Position) {
        this.characterPosition = position
        invalidate()
    }

    /**
     * 更新已收集物品位置
     */
    fun updateCollectedPositions(positions: Set<Position>) {
        this.collectedPositions = positions
        invalidate()
    }

    /**
     * 计算合适的格子大小
     */
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = MeasureSpec.getSize(widthMeasureSpec)
        val height = MeasureSpec.getSize(heightMeasureSpec)

        level?.let { lv ->
            val cols = lv.getCols()
            val rows = lv.getRows()

            // 计算格子大小，确保地图完整显示
            cellSize = minOf(
                (width - padding * 2) / cols,
                (height - padding * 2) / rows
            ).toFloat()
        }

        setMeasuredDimension(width, height)
    }

    /**
     * 绘制场景
     */
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        level?.let { lv ->
            drawGrid(canvas, lv)
            drawCells(canvas, lv)
            drawCharacter(canvas)
        }
    }

    /**
     * 绘制网格线
     */
    private fun drawGrid(canvas: Canvas, level: Level) {
        val cols = level.getCols()
        val rows = level.getRows()

        for (row in 0..rows) {
            val y = padding + row * cellSize
            canvas.drawLine(padding, y, padding + cols * cellSize, y, gridPaint)
        }

        for (col in 0..cols) {
            val x = padding + col * cellSize
            canvas.drawLine(x, padding, x, padding + rows * cellSize, gridPaint)
        }
    }

    /**
     * 绘制格子内容
     */
    private fun drawCells(canvas: Canvas, level: Level) {
        for (row in 0 until level.getRows()) {
            for (col in 0 until level.getCols()) {
                val cellType = level.map[row][col]
                val left = padding + col * cellSize
                val top = padding + row * cellSize

                // 选择颜色
                var color = when (cellType) {
                    0 -> emptyCellColor      // 空格
                    1 -> obstacleColor       // 障碍
                    2 -> itemColor           // 物品
                    3 -> endColor            // 终点
                    else -> emptyCellColor
                }

                // 检查物品是否已被收集
                val pos = Position(row, col)
                if (cellType == 2 && pos in collectedPositions) {
                    color = emptyCellColor  // 已收集的物品显示为空格
                }

                cellPaint.color = color
                canvas.drawRect(left, top, left + cellSize, top + cellSize, cellPaint)

                // 绘制特殊格子的标识
                when (cellType) {
                    2 -> {
                        if (pos !in collectedPositions) {
                            // 绘制物品图标（简化为文字）
                            textPaint.color = Color.parseColor("#FF6B00")
                            canvas.drawText("🌸", left + cellSize / 2, top + cellSize / 2 + textPaint.textSize / 3, textPaint)
                        }
                    }
                    3 -> {
                        textPaint.color = Color.WHITE
                        canvas.drawText("终点", left + cellSize / 2, top + cellSize / 2 + textPaint.textSize / 3, textPaint)
                    }
                }
            }
        }
    }

    /**
     * 绘制角色
     */
    private fun drawCharacter(canvas: Canvas) {
        characterPosition?.let { pos ->
            val left = padding + pos.col * cellSize + cellSize * 0.1f
            val top = padding + pos.row * cellSize + cellSize * 0.1f
            val size = cellSize * 0.8f

            // 绘制圆形角色（简化）
            val centerX = left + size / 2
            val centerY = top + size / 2
            val radius = size / 2

            characterPaint.color = characterColor
            canvas.drawCircle(centerX, centerY, radius, characterPaint)

            // 绘制角色标识文字
            textPaint.color = Color.WHITE
            textPaint.textSize = cellSize * 0.3f
            canvas.drawText("角色", centerX, centerY + textPaint.textSize / 3, textPaint)
        }
    }

    /**
     * 清除场景
     */
    fun clear() {
        level = null
        characterPosition = null
        collectedPositions = emptySet()
        invalidate()
    }
}