package com.example.kidscoding.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kidscoding.R
import com.example.kidscoding.models.Block
import com.example.kidscoding.models.BlockType

class BlockAdapter(
    private val blocks: MutableList<Block>,
    private val isDraggable: Boolean = false
) : RecyclerView.Adapter<BlockAdapter.BlockViewHolder>() {

    private var itemClickListener: ((Block) -> Unit)? = null

    class BlockViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val llBlock: LinearLayout = view.findViewById(R.id.llBlock)
        val tvLabel: TextView = view.findViewById(R.id.tvLabel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlockViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_block, parent, false)
        return BlockViewHolder(view)
    }

    override fun onBindViewHolder(holder: BlockViewHolder, position: Int) {
        val block = blocks[position]

        holder.tvLabel.text = block.label

        val backgroundRes = when (block.type) {
            BlockType.MOVE_FORWARD, BlockType.MOVE_UP, BlockType.MOVE_DOWN -> R.drawable.bg_block
            BlockType.LOOP -> R.drawable.bg_block_loop
            BlockType.CONDITION -> R.drawable.bg_block_condition
            else -> R.drawable.bg_block
        }
        holder.llBlock.setBackgroundResource(backgroundRes)

        holder.itemView.setOnClickListener {
            itemClickListener?.invoke(block)
        }
    }

    override fun getItemCount(): Int = blocks.size

    fun setOnItemClickListener(listener: (Block) -> Unit) {
        itemClickListener = listener
    }

    fun moveItem(fromPosition: Int, toPosition: Int) {
        val item = blocks.removeAt(fromPosition)
        blocks.add(toPosition, item)
        notifyItemMoved(fromPosition, toPosition)
    }

    fun addBlock(block: Block) {
        blocks.add(block)
        notifyItemInserted(blocks.size - 1)
    }

    fun removeBlock(position: Int) {
        if (position in 0 until blocks.size) {
            blocks.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    fun clearAll() {
        blocks.clear()
        notifyDataSetChanged()
    }

    fun getBlocks(): List<Block> = blocks.toList()

    fun updateBlocks(newBlocks: List<Block>) {
        blocks.clear()
        blocks.addAll(newBlocks)
        notifyDataSetChanged()
    }
}