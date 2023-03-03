package com.kj.memory_helper.suggest_board

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kj.memory_helper.R
import com.kj.memory_helper.db.WarningMsg
import com.kj.memory_helper.detail.DetailDialogFragment

class BoardActivity : AppCompatActivity() {

    private var boardAdapter: BoardAdapter? = null
    private val dataSet: ArrayList<WarningMsg> = ArrayList()
    val boardVm by lazy {
        ViewModelProvider(this)[BoardVM::class.java]
    }
    private lateinit var recyclerView: RecyclerView
    private lateinit var tv: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board)

        tv = findViewById(R.id.tv)
        tv.text = "${dataSet.size}个优化建议"
        initRv()

        boardVm.warnings.observe(this, ::updateData)
    }

    private fun initRv() {
        recyclerView = findViewById(R.id.rv)
        recyclerView.layoutManager = LinearLayoutManager(this)
        boardAdapter = BoardAdapter(dataSet, object : BoardAdapter.OnItemClickListener {
            override fun onItemClick(view: View, position: Int) {
                showDetail(dataSet[position])
            }

        })
        recyclerView.adapter = boardAdapter
    }

    private fun showDetail(warningMsg: WarningMsg) {
        DetailDialogFragment.newInstance(warningMsg).show(supportFragmentManager, "detail")
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun updateData(msgs: List<WarningMsg>) {
        dataSet.clear()
        dataSet.addAll(msgs)
        boardAdapter?.notifyDataSetChanged()
        tv.text = "${dataSet.size}个优化建议"
    }
}