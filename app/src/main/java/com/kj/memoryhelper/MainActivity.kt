package com.kj.memoryhelper

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    fun testImageMonitor(view: View) {
        val imageView = findViewById<ImageView>(R.id.iv)
        imageView.setImageResource(R.mipmap.xiaoyu)
    }

    fun testViewBgMonitor(view: View) {
        val rl = findViewById<RelativeLayout>(R.id.rl)
        rl.setBackgroundResource(R.mipmap.xiaoyu)
    }

    fun testSPGetMonitor(view: View) {
        val sp = getSharedPreferences("memory_helper", MODE_PRIVATE)
        sp.getString("test", "")
    }

    fun testSPSetMonitor(view: View) {
        val sp = getSharedPreferences("memory_helper", MODE_PRIVATE)
        sp.edit().putString(
            "test",
            "kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk"
        )
            .apply()
    }

    fun testRVMonitor(view: View) {
        startActivity(Intent(this, MainActivity2::class.java))
    }
}