package com.example.lottoeuromillions.lotto

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.lottoeuromillions.R
import com.example.lottoeuromillions.ViewType
import kotlinx.android.synthetic.main.item_lotto.view.*
import kotlinx.android.synthetic.main.item_result.view.*

class LottoAdapter(
    private val context: Context,
    val MyViewType: ViewType,
    private val doOnItemClick: (Int) -> Unit = {},
    private val doOnSubClick: (Int) -> Unit = { }
) :
    ListAdapter<Lotto, RecyclerView.ViewHolder>(
        LottoDiffCallback()
    ) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (MyViewType == ViewType.MAIN) {
            val view = LayoutInflater.from(context).inflate(R.layout.item_lotto, parent, false)
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

    inner class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        init {
            itemView.btnLotto.setOnClickListener(this)
        }

        fun bind(item: Lotto?) {
            itemView.btnLotto.text = item?.number.toString()
        }

        override fun onClick(view: View?) {
            when (view?.id) {
                R.id.btnLotto -> {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION)
                        doOnItemClick(getItem(position).number)
                }
            }
        }
    }

    inner class ResultViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        fun bind(item: Lotto?) {
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
                        doOnSubClick(getItem(position).number)
                }
            }
        }
    }
}

class LottoDiffCallback : DiffUtil.ItemCallback<Lotto>() {
    override fun areItemsTheSame(oldItem: Lotto, newItem: Lotto): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Lotto, newItem: Lotto): Boolean {
        return oldItem == newItem
    }
}