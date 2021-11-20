

package com.xzy.study.recyclerview.test006.recyclerview.data

import kotlin.random.Random

/**
 *
 * @author ：created by xzy.
 * @date ：2021/11/9
 */
object RvData {

    private var stringArrayList: ArrayList<String> = ArrayList()
    private var stringArrayList2: ArrayList<String> = ArrayList()
    private var expressRoutingList: ArrayList<ExpressRoutingItem> = ArrayList()

    fun testData(): ArrayList<String> {
        stringArrayList2 = arrayListOf()
        stringArrayList2.add("AAA")
        stringArrayList2.add("BBB")
        stringArrayList2.add("CCC")
        stringArrayList2.add("DDD")
        stringArrayList2.add("EEE")
        return stringArrayList2
    }

    fun initData(): ArrayList<String> {
        stringArrayList = arrayListOf()
        stringArrayList.add("1111111")
        stringArrayList.add("2222222")
        stringArrayList.add("3333333")
        stringArrayList.add("4444444")
        stringArrayList.add("5555555")
        stringArrayList.add("6666666")
        stringArrayList.add("7777777")
        stringArrayList.add("8888888")
        stringArrayList.add("9999999")
        stringArrayList.add("10101010")
        return stringArrayList
    }

    fun refreshData(): ArrayList<String> {
        stringArrayList.clear()
        stringArrayList.add("AAA")
        stringArrayList.add("BBB")
        stringArrayList.add("CCC")
        stringArrayList.add("DDD")
        stringArrayList.add("EEE")
        stringArrayList.add("1111111")
        stringArrayList.add("2222222")
        stringArrayList.add("3333333")
        stringArrayList.add("4444444")
        stringArrayList.add("5555555")
        return stringArrayList
    }

    fun loadMoreData(): ArrayList<String> {
        stringArrayList.add("Java" + System.currentTimeMillis() + Random(1000).nextInt())
        stringArrayList.add("Python" + System.currentTimeMillis() + Random(1000).nextInt())
        stringArrayList.add("Kotlin" + System.currentTimeMillis() + Random(1000).nextInt())
        stringArrayList.add("Dart" + System.currentTimeMillis() + Random(1000).nextInt())
        stringArrayList.add("C++" + System.currentTimeMillis() + Random(1000).nextInt())
        return stringArrayList
    }
}
