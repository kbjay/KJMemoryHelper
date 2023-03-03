package com.kj.memory_helper.suggest_board

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kj.memory_helper.R
import com.kj.memory_helper.db.WarningMsg


class BoardAdapter(
    private val dataSet: List<WarningMsg>,
    private val itemClickListener: OnItemClickListener
) :
    RecyclerView.Adapter<BoardAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    open class ViewHolder(view: View, val itemClickListener: OnItemClickListener) :
        RecyclerView.ViewHolder(view) {
        val tvTitle: TextView
        val tvContent: TextView
        val root: LinearLayout

        init {
            root = view.findViewById(R.id.root)
            tvTitle = view.findViewById(R.id.tv_title)
            tvContent = view.findViewById(R.id.tv_content)

            root.setOnClickListener {
                itemClickListener.onItemClick(it, root.getTag(R.id.board_item_pos) as Int)
            }
        }
    }

    open interface OnItemClickListener {
        fun onItemClick(view: View, position: Int)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_rv, viewGroup, false)

        return ViewHolder(view, itemClickListener)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.tvTitle.text = dataSet[position].type
        viewHolder.tvContent.text = dataSet[position].title
        viewHolder.root.setTag(R.id.board_item_pos, position)
    }

    override fun getItemCount() = dataSet.size

}