package HeapPriorityQueue.PriorityQueue

import java.util.*

/*
 https://leetcode.com/problems/last-stone-weight/description/
 * */
fun lastStoneWeight(stones: IntArray): Int {
    val pQueue = PriorityQueue<Int> { a, b -> b - a }
    for(stone in stones) pQueue.add(stone)

    while(pQueue.size >= 2) {
        val x = pQueue.poll()
        val y = pQueue.poll()

        if(x != y) {
            val max = Math.max(x, y)
            val min = Math.min(x, y)
            pQueue.add(max - min)
        }
    }


    return if(pQueue.isEmpty()) 0 else pQueue.peek()
}