package com.kj.memoryhelper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val imageView = findViewById<ImageView>(R.id.iv)
        imageView.setImageResource(R.mipmap.xiaoyu)


        val sp = getSharedPreferences("memory_helper", MODE_PRIVATE)
        sp.edit().putString(
            "test",
            "kl;;;;;;;jkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk"
        )
            .apply()

        imageView.setOnClickListener {
            sp.getString("test", "")
        }


        val rv = findViewById<RecyclerView>(R.id.rv)
        val imgArr = mutableListOf(
            "https://t7.baidu.com/it/u=4162611394,4275913936&fm=193&f=GIF",
            "https://t7.baidu.com/it/u=1951548898,3927145&fm=193&f=GIF",
            "https://t7.baidu.com/it/u=1831997705,836992814&fm=193&f=GIF",
            "https://t7.baidu.com/it/u=2405382010,1555992666&fm=193&f=GIF",
            "https://t7.baidu.com/it/u=2582370511,530426427&fm=193&f=GIF",
            "https://t7.baidu.com/it/u=2851687453,2321283050&fm=193&f=GIF",
            "https://t7.baidu.com/it/u=3601447414,1764260638&fm=193&f=GIF",
            "https://t7.baidu.com/it/u=4069854689,43753836&fm=193&f=GIF",
            "https://t7.baidu.com/it/u=2141219545,3103086273&fm=193&f=GIF",
            "https://img2.baidu.com/it/u=792809847,4192450812&fm=253&fmt=auto&app=138&f=JPEG?w=800&h=500",
            "https://img.zcool.cn/community/0117ee60d19e4511013f47208bcee8.jpg?x-oss-process=image/auto-orient,1/resize,m_lfit,w_1280,limit_1/sharpen,100",
            "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fsafe-img.xhscdn.com%2Fbw1%2Fe7d4c9c3-fbe5-4f50-9ed8-90add1b1793a%3FimageView2%2F2%2Fw%2F1080%2Fformat%2Fjpg&refer=http%3A%2F%2Fsafe-img.xhscdn.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1680760301&t=47f6465b847e77ef37a4ce005e4ac6b6",
            "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fsafe-img.xhscdn.com%2Fbw1%2F587ecc0f-8d77-41d0-a17f-03dd5b985763%3FimageView2%2F2%2Fw%2F1080%2Fformat%2Fjpg&refer=http%3A%2F%2Fsafe-img.xhscdn.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1680760301&t=b4a7d14c5ae1d773a62815d80bec5d5d"
        )

        rv.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = object : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
                override fun onCreateViewHolder(
                    parent: ViewGroup,
                    viewType: Int
                ): RecyclerView.ViewHolder {
                    val view = LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_rv_main, parent, false)
                    return VH(view)
                }

                override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
                    val iv = (holder as VH).iv
                    Glide.with(iv).load(imgArr[position]).into(iv)
                }

                override fun getItemCount(): Int {
                    return imgArr.size
                }

                override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
                    super.onViewRecycled(holder)
                }
            }
        }
    }

    open class VH(root: View) : RecyclerView.ViewHolder(root) {
        val iv: ImageView

        init {
            iv = root.findViewById(R.id.item_iv)
        }
    }
}