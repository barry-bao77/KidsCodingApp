package com.example.kidscoding.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.kidscoding.R
import com.example.kidscoding.models.Theme

class ThemeAdapter(
    private val themes: List<Theme>,
    private val onItemClick: (Theme) -> Unit
) : RecyclerView.Adapter<ThemeAdapter.ThemeViewHolder>() {

    class ThemeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val cardView: CardView = view.findViewById(R.id.cardView)
        val ivCharacter: ImageView = view.findViewById(R.id.ivCharacter)
        val tvName: TextView = view.findViewById(R.id.tvName)
        val tvStory: TextView = view.findViewById(R.id.tvStory)
        val tvLevels: TextView = view.findViewById(R.id.tvLevels)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThemeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_theme_card, parent, false)
        return ThemeViewHolder(view)
    }

    override fun onBindViewHolder(holder: ThemeViewHolder, position: Int) {
        val theme = themes[position]

        holder.tvName.text = theme.name
        holder.tvStory.text = theme.storyBackground
        holder.tvLevels.text = "${theme.getPlayableLevelCount()} 关可玩"

        holder.itemView.setOnClickListener {
            onItemClick(theme)
        }

        holder.ivCharacter.setImageResource(R.drawable.ic_placeholder)
    }

    override fun getItemCount(): Int = themes.size
}