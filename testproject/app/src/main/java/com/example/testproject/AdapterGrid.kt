package com.example.testproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdapterGrid(private val testList: Array<String>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TYPE_TEXT = 0
    private val TYPE_EDIT = 1

    class TestVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val testTextView: TextView = itemView.findViewById(R.id.test_text)
        fun bind(text: String, position: Int) {
            if (position % 2 == 0) {
                testTextView.textSize = 10F
            }
            testTextView.text = text
        }
    }

    class TestEditVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val testEditView: EditText = itemView.findViewById(R.id.test_editText)
        fun bind(position: Int) {
            if (position % 3 == 0) {
                testEditView.textSize = 10F
            }
            testEditView.hint = "Hello"
        }
    }

    // viewHolder 创建时调用
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == TYPE_TEXT) {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.test_item, parent, false)
            TestVH(view)
        } else {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.test_item_editext, parent, false)
            TestEditVH(view)
        }
    }

    // 把 VH 放置到指定位置
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is TestVH) {
            holder.bind(testList[position], position)
        }
        if (holder is TestEditVH) {
            holder.bind(position)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position in 5..8) {
            TYPE_EDIT
        } else {
            TYPE_TEXT
        }
    }

    // RecyclerView 是个列表，testList 为数据源，返回其长度
    override fun getItemCount(): Int {
        return testList.size
    }
}