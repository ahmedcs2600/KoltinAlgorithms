package HeapPriorityQueue.PriorityQueue

import java.util.*

/*
    https://leetcode.com/problems/single-threaded-cpu/description/
* */

fun getOrder(tasks: Array<IntArray>): IntArray {
    val modifiedTasks = tasks.mapIndexed { index, item -> intArrayOf(item[0], item[1], index) }
    Collections.sort(modifiedTasks) { a, b -> a[0] - b[0] }
    val n = modifiedTasks.size
    var index = 0
    var time = 1
    val minHeap = PriorityQueue<Pair<Int, Int>> { a, b -> if(a.first == b.first) a.second - b.second else a.first - b.first }
    var res = IntArray(n)
    var resIdx = 0

    while(!minHeap.isEmpty() || index < n) {

        while(index < n && time >= modifiedTasks[index][0]) {
            minHeap.offer(Pair(modifiedTasks[index][1], modifiedTasks[index][2]))
            index++
        }

        if(minHeap.isEmpty()) {
            time = modifiedTasks[index][0]
        } else {
            val (t, idx) = minHeap.poll()
            res[resIdx++] = idx
            time += t
        }
    }

    return res
}