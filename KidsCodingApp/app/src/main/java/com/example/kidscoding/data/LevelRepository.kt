package com.example.kidscoding.data

import com.example.kidscoding.models.*

/**
 * 关卡数据仓库
 * 提供所有主题和关卡的数据
 */
object LevelRepository {

    /**
     * 获取所有主题
     */
    fun getAllThemes(): List<Theme> {
        return listOf(
            createPonyTheme(),
            createSuperJetTheme(),
            createPeppaPigTheme(),
            createUltramanTheme(),
            createPrincessLingTheme()
        )
    }

    /**
     * 根据ID获取主题
     */
    fun getThemeById(themeId: String): Theme? {
        return getAllThemes().find { it.id == themeId }
    }

    /**
     * 根据主题ID和关卡ID获取关卡
     */
    fun getLevelById(themeId: String, levelId: Int): Level? {
        val theme = getThemeById(themeId)
        return theme?.levels?.find { it.id == levelId }
    }

    // ==================== 主题1：小马宝莉 ====================

    private fun createPonyTheme(): Theme {
        return Theme(
            id = "pony",
            name = "小马宝莉",
            storyBackground = "魔法森林里发生了奇怪的事情，小马们需要帮助！",
            colorScheme = "pink_purple",
            levels = listOf(
                createPonyLevel1(),
                createPonyLevel2(),
                createPonyLevel3(),
                createPonyLevel4(),
                createPonyLevel5()
            )
        )
    }

    private fun createPonyLevel1(): Level {
        val map = arrayOf(
            arrayOf(0, 0, 0, 0, 3)
        )
        return Level(
            id = 1,
            themeId = "pony",
            title = "第1关：初遇魔法森林",
            story = "小马宝莉刚来到魔法森林的入口，她需要走到森林深处去找朋友们。",
            task = "让小马宝莉向前走4步，到达终点！",
            map = map,
            startPos = Position(0, 0),
            itemPositions = emptyList(),
            availableBlocks = listOf(BlockType.MOVE_FORWARD),
            learnConcept = "顺序执行：按顺序一步一步走"
        )
    }

    private fun createPonyLevel2(): Level {
        val map = arrayOf(
            arrayOf(0, 2, 0, 2, 3)
        )
        return Level(
            id = 2,
            themeId = "pony",
            title = "第2关：收集魔法花朵",
            story = "魔法森林里有神奇的花朵，小马宝莉想收集2朵送给朋友。",
            task = "向前走，收集2朵花，到达终点！数数要走几步？",
            map = map,
            startPos = Position(0, 0),
            itemPositions = listOf(Position(0, 1), Position(0, 3)),
            availableBlocks = listOf(BlockType.MOVE_FORWARD, BlockType.LOOP),
            learnConcept = "循环：用重复来简化多次相同的动作"
        )
    }

    private fun createPonyLevel3(): Level {
        val map = arrayOf(
            arrayOf(3, 0, 0),
            arrayOf(0, 0, 0),
            arrayOf(0, 2, 0)
        )
        return Level(
            id = 3,
            themeId = "pony",
            title = "第3关：穿过神秘花园",
            story = "花园里有神秘的迷宫，小马宝莉需要找到正确的路。",
            task = "先向上走，再收集花朵，最后到达终点！",
            map = map,
            startPos = Position(2, 0),
            itemPositions = listOf(Position(2, 1)),
            availableBlocks = listOf(BlockType.MOVE_FORWARD, BlockType.MOVE_UP),
            learnConcept = "方向控制：学会向上移动"
        )
    }

    private fun createPonyLevel4(): Level {
        val map = arrayOf(
            arrayOf(2, 0, 3),
            arrayOf(0, 0, 0),
            arrayOf(0, 2, 0)
        )
        return Level(
            id = 4,
            themeId = "pony",
            title = "第4关：魔法树的秘密",
            story = "魔法树下藏着宝藏，小马宝莉要绕路去收集。",
            task = "收集2朵花后到达终点！需要向上、向下走。",
            map = map,
            startPos = Position(1, 0),
            itemPositions = listOf(Position(0, 0), Position(2, 1)),
            availableBlocks = listOf(BlockType.MOVE_FORWARD, BlockType.MOVE_UP, BlockType.MOVE_DOWN),
            learnConcept = "多方向移动：学会向上和向下"
        )
    }

    private fun createPonyLevel5(): Level {
        val map = arrayOf(
            arrayOf(2, 0, 2, 0, 3),
            arrayOf(0, 0, 0, 0, 0),
            arrayOf(0, 2, 0, 2, 0)
        )
        return Level(
            id = 5,
            themeId = "pony",
            title = "第5关：拯救被困的朋友",
            story = "朋友们被困在森林深处，小马宝莉需要收集所有花朵才能救出他们！",
            task = "收集4朵花并到达终点！试试用循环来简化。",
            map = map,
            startPos = Position(1, 0),
            itemPositions = listOf(Position(0, 0), Position(0, 2), Position(2, 1), Position(2, 3)),
            availableBlocks = listOf(BlockType.MOVE_FORWARD, BlockType.MOVE_UP, BlockType.MOVE_DOWN, BlockType.LOOP),
            learnConcept = "综合应用：循环+方向移动"
        )
    }

    // ==================== 主题2：超级飞侠 ====================

    private fun createSuperJetTheme(): Theme {
        return Theme(
            id = "superjet",
            name = "超级飞侠",
            storyBackground = "超级飞侠们接到救援任务，需要飞往世界各地！",
            colorScheme = "blue_sky",
            levels = listOf(
                createSuperJetLevel1(),
                createSuperJetLevel2(),
                createSuperJetLevel3(),
                createSuperJetLevel4(),
                createSuperJetLevel5()
            )
        )
    }

    private fun createSuperJetLevel1(): Level {
        val map = arrayOf(
            arrayOf(0, 0, 0, 3)
        )
        return Level(
            id = 1,
            themeId = "superjet",
            title = "第1关：起飞任务",
            story = "超级飞侠接到第一个任务，需要飞到目的地！",
            task = "向前飞3步到达目的地！",
            map = map,
            startPos = Position(0, 0),
            itemPositions = emptyList(),
            availableBlocks = listOf(BlockType.MOVE_FORWARD),
            learnConcept = "顺序执行：一步步完成任务"
        )
    }

    private fun createSuperJetLevel2(): Level {
        val map = arrayOf(
            arrayOf(0, 2, 0, 2, 3)
        )
        return Level(
            id = 2,
            themeId = "superjet",
            title = "第2关：运送包裹",
            story = "超级飞侠需要运送2个包裹到目的地！",
            task = "向前飞，运送2个包裹到达终点！",
            map = map,
            startPos = Position(0, 0),
            itemPositions = listOf(Position(0, 1), Position(0, 3)),
            availableBlocks = listOf(BlockType.MOVE_FORWARD, BlockType.LOOP),
            learnConcept = "循环：重复运送包裹"
        )
    }

    private fun createSuperJetLevel3(): Level {
        val map = arrayOf(
            arrayOf(3, 0, 0),
            arrayOf(0, 0, 2),
            arrayOf(0, 0, 0)
        )
        return Level(
            id = 3,
            themeId = "superjet",
            title = "第3关：穿越云层",
            story = "天空中有很多云层，超级飞侠需要调整飞行高度！",
            task = "先向上飞，再收集包裹，到达目的地！",
            map = map,
            startPos = Position(2, 0),
            itemPositions = listOf(Position(1, 2)),
            availableBlocks = listOf(BlockType.MOVE_FORWARD, BlockType.MOVE_UP),
            learnConcept = "向上飞行：学会上升"
        )
    }

    private fun createSuperJetLevel4(): Level {
        val map = arrayOf(
            arrayOf(0, 0, 3),
            arrayOf(2, 0, 0),
            arrayOf(0, 0, 2)
        )
        return Level(
            id = 4,
            themeId = "superjet",
            title = "第4关：环球救援",
            story = "超级飞侠接到紧急救援，需要在不同高度运送物资！",
            task = "收集2个物资并到达目的地！需要向上和向下飞行。",
            map = map,
            startPos = Position(1, 0),
            itemPositions = listOf(Position(1, 0), Position(2, 2)),
            availableBlocks = listOf(BlockType.MOVE_FORWARD, BlockType.MOVE_UP, BlockType.MOVE_DOWN),
            learnConcept = "高度调整：上升和下降"
        )
    }

    private fun createSuperJetLevel5(): Level {
        val map = arrayOf(
            arrayOf(2, 0, 2, 0, 3),
            arrayOf(0, 0, 0, 0, 0),
            arrayOf(0, 2, 0, 0, 0)
        )
        return Level(
            id = 5,
            themeId = "superjet",
            title = "第5关：终极任务",
            story = "超级飞侠接到最艰巨的任务，需要在全球运送所有物资！",
            task = "收集3个物资并到达目的地！试试用循环简化。",
            map = map,
            startPos = Position(1, 0),
            itemPositions = listOf(Position(0, 0), Position(0, 2), Position(2, 1)),
            availableBlocks = listOf(BlockType.MOVE_FORWARD, BlockType.MOVE_UP, BlockType.MOVE_DOWN, BlockType.LOOP),
            learnConcept = "综合应用：循环+多方向"
        )
    }

    // ==================== 主题3：小猪佩奇 ====================

    private fun createPeppaPigTheme(): Theme {
        return Theme(
            id = "peppa",
            name = "小猪佩奇",
            storyBackground = "佩奇和家人的一天，充满了温馨的小日常！",
            colorScheme = "pink_soft",
            levels = listOf(
                createPeppaLevel1(),
                createPeppaLevel2(),
                createPeppaLevel3(),
                createPeppaLevel4(),
                createPeppaLevel5()
            )
        )
    }

    private fun createPeppaLevel1(): Level {
        val map = arrayOf(
            arrayOf(0, 0, 0, 3)
        )
        return Level(
            id = 1,
            themeId = "peppa",
            title = "第1关：散步时间",
            story = "佩奇想出门散步，走到公园去玩！",
            task = "让佩奇向前走3步到达公园！",
            map = map,
            startPos = Position(0, 0),
            itemPositions = emptyList(),
            availableBlocks = listOf(BlockType.MOVE_FORWARD),
            learnConcept = "顺序执行：一步一步走"
        )
    }

    private fun createPeppaLevel2(): Level {
        val map = arrayOf(
            arrayOf(0, 2, 0, 2, 3)
        )
        return Level(
            id = 2,
            themeId = "peppa",
            title = "第2关：捡起玩具",
            story = "佩奇在公园里发现了2个玩具，她想都捡起来！",
            task = "向前走，捡起2个玩具，到达终点！",
            map = map,
            startPos = Position(0, 0),
            itemPositions = listOf(Position(0, 1), Position(0, 3)),
            availableBlocks = listOf(BlockType.MOVE_FORWARD, BlockType.LOOP),
            learnConcept = "循环：重复捡玩具"
        )
    }

    private fun createPeppaLevel3(): Level {
        val map = arrayOf(
            arrayOf(0, 0, 3),
            arrayOf(2, 0, 0),
            arrayOf(0, 0, 0)
        )
        return Level(
            id = 3,
            themeId = "peppa",
            title = "第3关：爬山游戏",
            story = "佩奇和家人去爬山，她想爬到山顶！",
            task = "先捡起玩具，再向上爬到山顶！",
            map = map,
            startPos = Position(2, 0),
            itemPositions = listOf(Position(1, 0)),
            availableBlocks = listOf(BlockType.MOVE_FORWARD, BlockType.MOVE_UP),
            learnConcept = "向上移动：学会爬坡"
        )
    }

    private fun createPeppaLevel4(): Level {
        val map = arrayOf(
            arrayOf(0, 2, 3),
            arrayOf(0, 0, 0),
            arrayOf(2, 0, 0)
        )
        return Level(
            id = 4,
            themeId = "peppa",
            title = "第4关：花园探险",
            story = "佩奇在花园里探险，需要在不同高度捡起玩具！",
            task = "捡起2个玩具并到达终点！需要向上和向下走。",
            map = map,
            startPos = Position(1, 0),
            itemPositions = listOf(Position(0, 1), Position(2, 0)),
            availableBlocks = listOf(BlockType.MOVE_FORWARD, BlockType.MOVE_UP, BlockType.MOVE_DOWN),
            learnConcept = "多方向移动：上下探索"
        )
    }

    private fun createPeppaLevel5(): Level {
        val map = arrayOf(
            arrayOf(0, 2, 0, 0, 3),
            arrayOf(0, 0, 0, 2, 0),
            arrayOf(2, 0, 0, 0, 0)
        )
        return Level(
            id = 5,
            themeId = "peppa",
            title = "第5关：家庭聚会",
            story = "佩奇要为家庭聚会准备，需要收集所有的玩具！",
            task = "收集3个玩具并到达终点！试试用循环。",
            map = map,
            startPos = Position(1, 0),
            itemPositions = listOf(Position(0, 1), Position(1, 3), Position(2, 0)),
            availableBlocks = listOf(BlockType.MOVE_FORWARD, BlockType.MOVE_UP, BlockType.MOVE_DOWN, BlockType.LOOP),
            learnConcept = "综合应用：循环+方向"
        )
    }

    // ==================== 主题4：奥特曼 ====================

    private fun createUltramanTheme(): Theme {
        return Theme(
            id = "ultraman",
            name = "奥特曼",
            storyBackground = "奥特曼守护地球的正义，需要打败怪兽！",
            colorScheme = "gold_red",
            levels = listOf(
                createUltramanLevel1(),
                createUltramanLevel2(),
                createUltramanLevel3(),
                createUltramanLevel4(),
                createUltramanLevel5()
            )
        )
    }

    private fun createUltramanLevel1(): Level {
        val map = arrayOf(
            arrayOf(0, 0, 0, 3)
        )
        return Level(
            id = 1,
            themeId = "ultraman",
            title = "第1关：出击！",
            story = "奥特曼接到信号，需要飞往战斗地点！",
            task = "向前飞3步到达战斗地点！",
            map = map,
            startPos = Position(0, 0),
            itemPositions = emptyList(),
            availableBlocks = listOf(BlockType.MOVE_FORWARD),
            learnConcept = "顺序执行：快速出击"
        )
    }

    private fun createUltramanLevel2(): Level {
        val map = arrayOf(
            arrayOf(0, 2, 0, 2, 3)
        )
        return Level(
            id = 2,
            themeId = "ultraman",
            title = "第2关：收集能量",
            story = "奥特曼需要收集能量晶体才能打败怪兽！",
            task = "向前飞，收集2个能量，到达目标！",
            map = map,
            startPos = Position(0, 0),
            itemPositions = listOf(Position(0, 1), Position(0, 3)),
            availableBlocks = listOf(BlockType.MOVE_FORWARD, BlockType.LOOP),
            learnConcept = "循环：重复收集能量"
        )
    }

    private fun createUltramanLevel3(): Level {
        val map = arrayOf(
            arrayOf(3, 0, 0),
            arrayOf(0, 0, 2),
            arrayOf(0, 0, 0)
        )
        return Level(
            id = 3,
            themeId = "ultraman",
            title = "第3关：空中战斗",
            story = "怪兽在天空中，奥特曼需要飞上去！",
            task = "先向上飞，收集能量，然后打败怪兽！",
            map = map,
            startPos = Position(2, 0),
            itemPositions = listOf(Position(1, 2)),
            availableBlocks = listOf(BlockType.MOVE_FORWARD, BlockType.MOVE_UP),
            learnConcept = "向上飞行：空中作战"
        )
    }

    private fun createUltramanLevel4(): Level {
        val map = arrayOf(
            arrayOf(0, 2, 3),
            arrayOf(0, 0, 0),
            arrayOf(2, 0, 0)
        )
        return Level(
            id = 4,
            themeId = "ultraman",
            title = "第4关：地面怪兽",
            story = "怪兽在地面和空中都有，奥特曼需要上下穿梭！",
            task = "收集2个能量并打败怪兽！需要上下飞行。",
            map = map,
            startPos = Position(1, 0),
            itemPositions = listOf(Position(0, 1), Position(2, 0)),
            availableBlocks = listOf(BlockType.MOVE_FORWARD, BlockType.MOVE_UP, BlockType.MOVE_DOWN),
            learnConcept = "高度调整：上下穿梭"
        )
    }

    private fun createUltramanLevel5(): Level {
        val map = arrayOf(
            arrayOf(2, 0, 2, 0, 3),
            arrayOf(0, 0, 0, 0, 0),
            arrayOf(0, 0, 2, 0, 0)
        )
        return Level(
            id = 5,
            themeId = "ultraman",
            title = "第5关：终极战斗",
            story = "大怪兽出现了！奥特曼需要收集所有能量才能打败它！",
            task = "收集3个能量并打败怪兽！试试用循环。",
            map = map,
            startPos = Position(1, 0),
            itemPositions = listOf(Position(0, 0), Position(0, 2), Position(2, 2)),
            availableBlocks = listOf(BlockType.MOVE_FORWARD, BlockType.MOVE_UP, BlockType.MOVE_DOWN, BlockType.LOOP),
            learnConcept = "综合应用：循环+多方向"
        )
    }

    // ==================== 主题5：小玲公主 ====================

    private fun createPrincessLingTheme(): Theme {
        return Theme(
            id = "princess",
            name = "小玲公主",
            storyBackground = "小玲公主在梦幻城堡里开始了冒险旅程！",
            colorScheme = "purple_lavender",
            levels = listOf(
                createPrincessLevel1(),
                createPrincessLevel2(),
                createPrincessLevel3(),
                createPrincessLevel4(),
                createPrincessLevel5()
            )
        )
    }

    private fun createPrincessLevel1(): Level {
        val map = arrayOf(
            arrayOf(0, 0, 0, 3)
        )
        return Level(
            id = 1,
            themeId = "princess",
            title = "第1关：城堡入口",
            story = "小玲公主来到城堡门口，需要走进城堡！",
            task = "让小玲公主向前走3步进入城堡！",
            map = map,
            startPos = Position(0, 0),
            itemPositions = emptyList(),
            availableBlocks = listOf(BlockType.MOVE_FORWARD),
            learnConcept = "顺序执行：一步步前进"
        )
    }

    private fun createPrincessLevel2(): Level {
        val map = arrayOf(
            arrayOf(0, 2, 0, 2, 3)
        )
        return Level(
            id = 2,
            themeId = "princess",
            title = "第2关：收集宝石",
            story = "城堡里有闪闪发光的宝石，小玲公主想收集它们！",
            task = "向前走，收集2颗宝石，到达终点！",
            map = map,
            startPos = Position(0, 0),
            itemPositions = listOf(Position(0, 1), Position(0, 3)),
            availableBlocks = listOf(BlockType.MOVE_FORWARD, BlockType.LOOP),
            learnConcept = "循环：重复收集宝石"
        )
    }

    private fun createPrincessLevel3(): Level {
        val map = arrayOf(
            arrayOf(0, 0, 3),
            arrayOf(2, 0, 0),
            arrayOf(0, 0, 0)
        )
        return Level(
            id = 3,
            themeId = "princess",
            title = "第3关：登上塔楼",
            story = "小玲公主想登上城堡的塔楼看看远方！",
            task = "先捡起宝石，再向上走到达塔楼！",
            map = map,
            startPos = Position(2, 0),
            itemPositions = listOf(Position(1, 0)),
            availableBlocks = listOf(BlockType.MOVE_FORWARD, BlockType.MOVE_UP),
            learnConcept = "向上移动：登塔"
        )
    }

    private fun createPrincessLevel4(): Level {
        val map = arrayOf(
            arrayOf(0, 2, 3),
            arrayOf(0, 0, 0),
            arrayOf(2, 0, 0)
        )
        return Level(
            id = 4,
            themeId = "princess",
            title = "第4关：城堡迷宫",
            story = "城堡里有迷宫，小玲公主需要在不同楼层寻找宝石！",
            task = "收集2颗宝石并到达终点！需要上下走动。",
            map = map,
            startPos = Position(1, 0),
            itemPositions = listOf(Position(0, 1), Position(2, 0)),
            availableBlocks = listOf(BlockType.MOVE_FORWARD, BlockType.MOVE_UP, BlockType.MOVE_DOWN),
            learnConcept = "多方向移动：上下探索"
        )
    }

    private fun createPrincessLevel5(): Level {
        val map = arrayOf(
            arrayOf(2, 0, 0, 0, 3),
            arrayOf(0, 0, 2, 0, 0),
            arrayOf(0, 2, 0, 0, 0)
        )
        return Level(
            id = 5,
            themeId = "princess",
            title = "第5关：梦幻宝藏",
            story = "城堡深处有传说中的宝藏，小玲公主要收集所有宝石找到它！",
            task = "收集3颗宝石并到达终点！试试用循环。",
            map = map,
            startPos = Position(1, 0),
            itemPositions = listOf(Position(0, 0), Position(1, 2), Position(2, 1)),
            availableBlocks = listOf(BlockType.MOVE_FORWARD, BlockType.MOVE_UP, BlockType.MOVE_DOWN, BlockType.LOOP),
            learnConcept = "综合应用：循环+方向"
        )
    }
}