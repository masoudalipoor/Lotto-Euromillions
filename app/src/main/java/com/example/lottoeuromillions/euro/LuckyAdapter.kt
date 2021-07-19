package com.example.lottoeuromillions.euro

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.lottoeuromillions.R
import com.example.lottoeuromillions.ViewType
import kotlinx.android.synthetic.main.item_lucky.view.*
import kotlinx.android.synthetic.main.item_result.view.*

class LuckyAdapter(
    private val context: Context,
    val MyViewType: ViewType,
    private val callback: (Lucky) -> Unit = {},
    private val doOnSubClick: (String, Int) -> Unit = { _, _ -> null }
) :
    ListAdapter<Lucky, RecyclerView.ViewHolder>(
        LuckyDiffCallback()
    ) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (MyViewType == ViewType.MAIN) {
            val view = LayoutInflater.from(context).inflate(R.layout.item_lucky, parent, false)
            return MainViewHolder(view)
        } else {
            val view =
                LayoutInflater.from(context).inflate(R.layout.item_result, parent, false)
            return ResultViewHolder(view)
        }
    }

    override fun onBindViewHolder(holderMain: RecyclerView.ViewHolder, position: Int) {
        if (holderMain is MainViewHolder)
            holderMain.bind(getItem(position))
        else if (holderMain is ResultViewHolder)
            holderMain.bind(getItem(position))
    }

    inner class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.btnLucky.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION)
                    callback(getItem(position))
            }
        }

        fun bind(item: Lucky?) {
            itemView.btnLucky.text = item?.number.toString()
        }
    }

    inner class ResultViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        fun bind(item: Lucky?) {
            itemView.txtIdItemResult.text = (adapterPosition + 1).toString()
            itemView.txtNumberItemResult.text = item?.number.toString()
            itemView.txtCountItemResult.text = item?.count.toString()
            itemView.btnSub.setOnClickListener(this)
        }

        override fun onClick(view: View?) {
            when (view?.id) {
                R.id.btnSub -> {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION)
                        doOnSubClick("Lucky", getItem(position).number)
                }
            }
        }
    }
}

class LuckyDiffCallback : DiffUtil.ItemCallback<Lucky>() {
    override fun areItemsTheSame(oldItem: Lucky, newItem: Lucky): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Lucky, newItem: Lucky): Boolean {
        return oldItem == newItem
    }
}