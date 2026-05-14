# 少儿编程乐园 Android App

一个面向 6-8 岁儿童的编程启蒙教育应用，采用积木拼图式编程方式。

## 项目结构

```
KidsCodingApp/
├── app/
│   ├── src/main/
│   │   ├── java/com/example/kidscoding/
│   │   │   ├── models/        # 数据模型 (8个文件)
│   │   │   ├── data/          # 数据层 (2个文件)
│   │   │   ├── game/          # 游戏逻辑 (3个文件)
│   │   │   └── ui/            # 界面层
│   │   │       ├── adapters/  # 适配器 (3个文件)
│   │   │       └── *.kt       # Activity (5个文件)
│   │   ├── res/
│   │   │   ├── drawable/      # 图片资源 (21个文件)
│   │   │   ├── layout/        # 布局文件 (7个文件)
│   │   │   └── values/        # 配置文件
│   │   └── AndroidManifest.xml
│   ├── build.gradle.kts
│   └── proguard-rules.pro
│
├── gradle/wrapper/
│   └── gradle-wrapper.properties
├── build.gradle.kts
├── settings.gradle.kts
└── README.md
```

## 功能特点

- 5 个原创角色主题（每个主题 5 关）
- 积木拖拽式编程界面
- 网格地图场景展示
- 本地进度保存
- 离线运行（无需网络）

## 编程概念教学

- 基础：序列概念（积木顺序执行）
- 进阶：循环概念（重复执行）
- 高级：条件概念（判断执行）

## 安装步骤

### 1. 安装 Android Studio

下载并安装 [Android Studio](https://developer.android.com/studio)（推荐最新版本）。

### 2. 创建新项目并导入代码

**方法 A：新建项目后复制文件**

1. 打开 Android Studio
2. 选择 **New Project** → **Empty Activity**
3. 设置项目信息：
   - Name: `KidsCodingApp`
   - Package name: `com.example.kidscoding`
   - Language: Kotlin
   - Minimum SDK: API 24 (Android 7.0)
4. 点击 Finish 创建项目
5. 关闭 Android Studio
6. 将本文件夹中的以下内容复制到新项目：
   - `app/src/main/java/com/example/kidscoding/` → 复制所有 Kotlin 文件
   - `app/src/main/res/` → 复制 drawable、layout、values 文件夹
   - `app/src/main/AndroidManifest.xml` → 替换现有文件
   - `app/build.gradle.kts` → 替换现有文件
7. 重新打开 Android Studio 项目

**方法 B：直接导入（推荐）**

1. 打开 Android Studio
2. 选择 **File** → **Open**
3. 选择本 `KidsCodingApp` 文件夹
4. 等待 Gradle 同步完成

### 3. 等待 Gradle 同步

首次打开项目，Android Studio 会自动下载依赖包。等待同步完成（可能需要几分钟）。

如果同步失败，检查网络连接，或手动更新 Gradle 版本。

### 4. 添加应用图标

项目需要应用图标文件：
- `app/src/main/res/mipmap-hdpi/ic_launcher.png`
- `app/src/main/res/mipmap-mdpi/ic_launcher.png`
- `app/src/main/res/mipmap-xhdpi/ic_launcher.png`
- `app/src/main/res/mipmap-xxhdpi/ic_launcher.png`
- `app/src/main/res/mipmap-xxxhdpi/ic_launcher.png`
- 以及对应的 `ic_launcher_round.png` 圆形图标

可以在 Android Studio 中：
1. 右键点击 `res/mipmap` 目录
2. 选择 **New** → **Image Asset**
3. 选择或设计一个图标

### 5. 运行应用

1. 连接 Android 手机（开启 USB 调试）或启动模拟器
2. 点击 Android Studio 的 Run 按钮（绿色三角形）
3. 等待应用安装并启动

### 6. 验证功能

- 主题选择页面应显示 5 个角色主题
- 点击主题进入关卡列表
- 点击关卡进入游戏页面
- 拖拽积木测试游戏流程

## 常见问题

### Q: Gradle 同步失败
尝试修改 `gradle/wrapper/gradle-wrapper.properties` 中的版本：
```properties
distributionUrl=https\://services.gradle.org/distributions/gradle-8.0-bin.zip
```

### Q: 编译错误 - 缺少依赖
检查 `app/build.gradle.kts` 中的依赖版本是否正确，可尝试更新到最新版本。

### Q: 找不到 R 文件
确保 `app/src/main/res/values/colors.xml` 和 `strings.xml` 存在且格式正确。

### Q: 模拟器启动慢
建议使用真机测试，或在 Device Manager 中创建 x86 模拟器（启动更快）。

## 后续开发建议

1. **添加角色图片**：将实际卡通角色图片添加到 `res/drawable/`
2. **增加关卡内容**：在 `LevelRepository.kt` 中扩展更多关卡
3. **添加音效**：使用 SoundPool 或 MediaPlayer 添加反馈音效
4. **动画效果**：使用 Lottie 添加角色动画
5. **成就系统**：扩展 UserProgress 增加成就徽章

## 技术架构

- **开发语言**: Kotlin
- **最低 SDK**: Android 7.0 (API 24)
- **目标 SDK**: Android 14 (API 34)
- **主要依赖**:
  - AndroidX AppCompat
  - Material Design Components
  - RecyclerView + CardView
  - Lottie 动画库
  - Gson JSON 序列化

## 许可说明

本项目代码为教学示例，角色图片需用户自行添加（个人/家庭使用）。