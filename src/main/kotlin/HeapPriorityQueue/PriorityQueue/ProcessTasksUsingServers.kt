package HeapPriorityQueue.PriorityQueue

import java.util.*
/*
https://leetcode.com/problems/process-tasks-using-servers/description/

* */
fun assignTasks(servers: IntArray, tasks: IntArray): IntArray {
    // System.out.println(servers.length);
    // System.out.println(servers[36] + " ; " + servers[56]);
    val N = tasks.size
    val res = IntArray(N)
    val available = PriorityQueue(java.util.Comparator { s1: IntArray, s2: IntArray ->
        if (s1[1] == s2[1]) return@Comparator s1[0] - s2[0]
        s1[1] - s2[1]
    }) // (index, weight), sort by weight first then index, increasingly
    val running = PriorityQueue { s1: IntArray, s2: IntArray ->
        s1[1] - s2[1]
    } // (index, finishing time), sort by finishing time increasingly
    for (i in servers.indices) {
        available.add(intArrayOf(i, servers[i]))
    }
    var i = 0
    var time = 0
    while (i < N) {
        while (!running.isEmpty() && running.peek()[1] <= time) {
            val freeServer = running.poll()
            val index = freeServer[0]
            available.offer(intArrayOf(index, servers[index]))
        }
        while (!available.isEmpty() && time >= i && i < N) {
            val freeServer = available.poll()
            running.offer(intArrayOf(freeServer[0], time + tasks[i]))
            res[i++] = freeServer[0]
        }
        time = if (available.isEmpty()) {
            running.peek()[1]
        } else {
            i
        }
    }
    return res
}