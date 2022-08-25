package com.example.testproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adapter(val testList: Array<String>) : RecyclerView.Adapter<Adapter.TestVH>() {

    class TestVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val testTextView: TextView = itemView.findViewById(R.id.test_text)

        fun bind(text: String) {
            testTextView.text = text
        }
    }

    // viewHolder 创建时调用
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestVH {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.test_item, parent, false)
        return TestVH(view)
    }

    // 把 VH 放置到指定位置
    override fun onBindViewHolder(holder: TestVH, position: Int) {
        holder.bind(testList[position])
    }

    // RecyclerView 是个列表，testList 为数据源，返回其长度
    override fun getItemCount(): Int {
        return testList.size
    }
}