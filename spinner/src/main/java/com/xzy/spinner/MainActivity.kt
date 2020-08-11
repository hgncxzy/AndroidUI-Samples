package com.xzy.spinner

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import com.xzy.spinner.R


/**
 * 测试 spinner 的级联效果
 * https://www.cnblogs.com/all88/p/4097494.html
 * */


class MainActivity : Activity() {
    private var city // 一级菜单
            : Spinner? = null
    private var area // 二级菜单
            : Spinner? = null
    private var cityTextView: TextView? = null
    private var areTextView: TextView? = null
    private val areaData = arrayOf(arrayOf("请选择："), arrayOf("朝阳区", "海淀区", "西城区", "大兴区", "怀柔区"), arrayOf("闵行区"), arrayOf("洪山区", "武昌区", "东西湖区"))
    private var adapterArea: ArrayAdapter<CharSequence>? = null
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        cityTextView = findViewById<View>(R.id.tex_city) as TextView
        areTextView = findViewById<View>(R.id.tex_area) as TextView
        city = findViewById<View>(R.id.city) as Spinner // 取得下拉列表框
        // 由于一个城市有多个市区，为了简单起见下面我们定义一个数组，通过数组保存若干个城市，而实际中需要单独的数据
        area = findViewById<View>(R.id.area) as Spinner // 取得下拉列表框
        city!!.onItemSelectedListener = OnItemSelectedListenerImpl()
        area!!.onItemSelectedListener = OnItemSelectedListenerImpl()
        city!!.prompt = "选择你喜欢的城市"
        city!!.prompt = "选择你喜欢的城区"
    }

    private inner class OnItemSelectedListenerImpl : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(parent: AdapterView<*>, view: View,
                                    position: Int, id: Long) { // 选项改变的时候触发
            when (parent.id) {
                R.id.city -> {
                    val value = parent.getItemAtPosition(position) as String // 得到选中的选项
                    cityTextView!!.text = value
                    adapterArea = ArrayAdapter(this@MainActivity, R.layout.support_simple_spinner_dropdown_item,  // 显示风格
                            areaData[position]) // 在列表视图中所代表的对象。
                    area!!.adapter = adapterArea // 把adapterArea添加到area
                }
                R.id.area -> {
                    val value2 = parent.getItemAtPosition(position) as String // 得到选中的选项
                    areTextView!!.text = value2
                }
            }
        }

        override fun onNothingSelected(arg0: AdapterView<*>?) { // 没有选项的时候触发
            // 一般不关心
        }
    }
}