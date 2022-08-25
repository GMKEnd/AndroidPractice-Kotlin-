package com.example.testproject

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // linear
        val testList = DataSource(this).getFlowerList()
        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.adapter = Adapter(testList)

        // grid
        var count = 2
        var canscroll = true
        val recyclerViewGrid: RecyclerView = findViewById(R.id.recycler_view_grid)
        // reverselayout 的T/F 控制从右到左/从左到右
        val gridLayoutManager = object : GridLayoutManager(this, count, VERTICAL, false) {
            override fun canScrollHorizontally(): Boolean {
                return canscroll
            }

            override fun canScrollVertically(): Boolean {
                return canscroll
            }
        }

        recyclerViewGrid.layoutManager = gridLayoutManager
        recyclerViewGrid.adapter = AdapterGrid(testList)

        gridLayoutManager.spanSizeLookup = object : SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                // 第一行
                if (position == 0) {
                    return 2
                }
                // 最后一行
                return if (position == AdapterGrid(testList).itemCount - 1) {
                    1
                } else 1 // 其他
            }
        }

        val btn: Button = findViewById(R.id.button)
        btn.setOnClickListener {
            // 变动 GridLayout 的 Span 值
            gridLayoutManager.spanCount = ++count
            canscroll = false
            Log.i(TAG, "列数：${gridLayoutManager.spanCount}")
        }

        // Staggered
        val RVstag: RecyclerView = findViewById(R.id.recycler_view_stag)
        val stagLayoutManager = object : StaggeredGridLayoutManager(2, VERTICAL) {

        }

        RVstag.layoutManager = stagLayoutManager
        RVstag.adapter = AdapterGrid(testList)
    }
}