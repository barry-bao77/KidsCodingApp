package com.example.kidscoding.ui

import android.content.Intent
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kidscoding.R
import com.example.kidscoding.data.LevelRepository
import com.example.kidscoding.data.ProgressManager
import com.example.kidscoding.game.BlockAdapter
import com.example.kidscoding.game.BlockManager
import com.example.kidscoding.game.GameEngine
import com.example.kidscoding.game.SceneRenderer
import com.example.kidscoding.models.Block
import com.example.kidscoding.models.BlockType
import com.example.kidscoding.models.Level

class GameActivity : AppCompatActivity() {

    private lateinit var tvTask: TextView
    private lateinit var sceneView: SceneRenderer
    private lateinit var tvBlockAreaTitle: TextView
    private lateinit var rvBlockSequence: RecyclerView
    private lateinit var rvAvailableBlocks: RecyclerView
    private lateinit var tvRun: TextView
    private lateinit var tvReset: TextView
    private lateinit var tvHint: TextView
    private lateinit var flBlockSequence: FrameLayout

    private lateinit var blockManager: BlockManager
    private lateinit var gameEngine: GameEngine
    private lateinit var progressManager: ProgressManager

    private lateinit var sequenceAdapter: BlockAdapter
    private lateinit var availableAdapter: BlockAdapter

    private var themeId: String = ""
    private var themeName: String = ""
    private var levelId: Int = 1
    private var currentLevel: Level? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        blockManager = BlockManager()
        gameEngine = GameEngine()
        progressManager = ProgressManager(this)

        // 获取传递的关卡信息
        themeId = intent.getStringExtra("themeId") ?: ""
        themeName = intent.getStringExtra("themeName") ?: ""
        levelId = intent.getIntExtra("levelId", 1)

        setupViews()
        loadLevel()
    }

    private fun setupViews() {
        tvTask = findViewById(R.id.tvTask)
        sceneView = findViewById(R.id.sceneView)
        tvBlockAreaTitle = findViewById(R.id.tvBlockAreaTitle)
        rvBlockSequence = findViewById(R.id.rvBlockSequence)
        rvAvailableBlocks = findViewById(R.id.rvAvailableBlocks)
        tvRun = findViewById(R.id.tvRun)
        tvReset = findViewById(R.id.tvReset)
        tvHint = findViewById(R.id.tvHint)
        flBlockSequence = findViewById(R.id.flBlockSequence)

        // 设置积木序列列表
        rvBlockSequence.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        sequenceAdapter = BlockAdapter(mutableListOf())
        rvBlockSequence.adapter = sequenceAdapter

        // 设置可选积木列表
        rvAvailableBlocks.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        availableAdapter = BlockAdapter(mutableListOf(), isDraggable = false)
        rvAvailableBlocks.adapter = availableAdapter

        // 点击可选积木，添加到序列
        availableAdapter.setOnItemClickListener { block ->
            addBlockToSequence(block)
        }

        // 运行按钮
        tvRun.setOnClickListener {
            runProgram()
        }

        // 重置按钮
        tvReset.setOnClickListener {
            resetGame()
        }
    }

    private fun loadLevel() {
        currentLevel = LevelRepository.getLevelById(themeId, levelId)

        currentLevel?.let { level ->
            tvTask.text = "🌸 ${level.task}"
            tvBlockAreaTitle.text = "🧩 拖拽积木让${themeName}完成任务："

            // 设置场景
            sceneView.setLevel(level)

            // 设置可选积木
            val availableBlocks = generateAvailableBlocks(level.availableBlocks)
            availableAdapter.updateBlocks(availableBlocks)
        }
    }

    private fun generateAvailableBlocks(types: List<BlockType>): List<Block> {
        return types.mapIndexed { index, type ->
            when (type) {
                BlockType.MOVE_FORWARD -> Block.createMoveBlock(BlockType.MOVE_FORWARD, "forward_$index")
                BlockType.MOVE_UP -> Block.createMoveBlock(BlockType.MOVE_UP, "up_$index")
                BlockType.MOVE_DOWN -> Block.createMoveBlock(BlockType.MOVE_DOWN, "down_$index")
                BlockType.LOOP -> Block.createLoopBlock("loop_$index", 2)
                BlockType.CONDITION -> Block.createConditionBlock("condition_$index")
                else -> Block("unknown_$index", type, "未知", R.color.block_move, 0)
            }
        }
    }

    private fun addBlockToSequence(block: Block) {
        val newBlock = Block(
            id = blockManager.generateBlockId(),
            type = block.type,
            label = block.label,
            colorRes = block.colorRes,
            param = block.param
        )
        blockManager.addBlock(newBlock)
        sequenceAdapter.addBlock(newBlock)
        tvHint.visibility = if (sequenceAdapter.itemCount > 0) TextView.GONE else TextView.VISIBLE
    }

    private fun runProgram() {
        currentLevel?.let { level ->
            val expandedBlocks = blockManager.expandBlocks()
            val result = gameEngine.execute(expandedBlocks, level)

            // 显示结果界面
            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra("themeId", themeId)
            intent.putExtra("themeName", themeName)
            intent.putExtra("levelId", levelId)
            intent.putExtra("success", result.success)
            intent.putExtra("stars", result.getStars())
            intent.putExtra("learnConcept", level.learnConcept)
            startActivity(intent)
            finish()
        }
    }

    private fun resetGame() {
        blockManager.clearAll()
        sequenceAdapter.clearAll()
        tvHint.visibility = TextView.VISIBLE

        // 重置场景
        currentLevel?.let { level ->
            sceneView.setLevel(level)
        }
    }
}