package com.example.kidscoding.ui.adapters

import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.kidscoding.R
import com.example.kidscoding.data.ProgressManager
import com.example.kidscoding.models.Level

class LevelAdapter(
    private val levels: List<Level>,
    private val progressManager: ProgressManager,
    private val themeId: String,
    private val onItemClick: (Level, Boolean) -> Unit
) : RecyclerView.Adapter<LevelAdapter.LevelViewHolder>() {

    class LevelViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val cardLevel: CardView = view.findViewById(R.id.cardLevel)
        val tvStatusIcon: TextView = view.findViewById(R.id.tvStatusIcon)
        val tvTitle: TextView = view.findViewById(R.id.tvTitle)
        val tvConcept: TextView = view.findViewById(R.id.tvConcept)
        val tvStatus: TextView = view.findViewById(R.id.tvStatus)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LevelViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_level, parent, false)
        return LevelViewHolder(view)
    }

    override fun onBindViewHolder(holder: LevelViewHolder, position: Int) {
        val level = levels[position]
        val levelId = level.id

        val isUnlocked = progressManager.loadProgress().isLevelUnlocked(themeId, levelId)
        val progress = progressManager.loadProgress().getLevelProgress(themeId, levelId)
        val isCompleted = progress?.completed ?: false

        holder.tvTitle.text = level.title
        holder.tvConcept.text = "学习：${level.learnConcept}"

        if (isCompleted) {
            holder.tvStatusIcon.text = "✓"
            holder.tvStatusIcon.setBackgroundResource(R.drawable.bg_level_completed)
            holder.tvStatus.text = "已完成"
            holder.tvStatus.setBackgroundResource(R.drawable.bg_status_completed)
            holder.tvStatus.visibility = View.VISIBLE
            holder.cardLevel.alpha = 1.0f
        } else if (isUnlocked) {
            holder.tvStatusIcon.text = "${levelId}"
            holder.tvStatusIcon.setBackgroundResource(R.drawable.bg_level_current)
            holder.tvStatus.text = "开始!"
            holder.tvStatus.setBackgroundResource(R.drawable.bg_status_current)
            holder.tvStatus.visibility = View.VISIBLE
            holder.cardLevel.alpha = 1.0f
        } else {
            holder.tvStatusIcon.text = "🔒"
            holder.tvStatusIcon.setBackgroundResource(R.drawable.bg_level_locked)
            holder.tvStatus.visibility = View.GONE
            holder.cardLevel.alpha = 0.5f
        }

        holder.itemView.setOnClickListener {
            if (isUnlocked) {
                onItemClick(level, true)
            } else {
                onItemClick(level, false)
            }
        }
    }

    override fun getItemCount(): Int = levels.size
}