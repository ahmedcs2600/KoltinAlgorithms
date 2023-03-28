package HeapPriorityQueue.PriorityQueue

import java.util.*

/*
 https://leetcode.com/problems/seat-reservation-manager/
 * */
class SeatManager(n: Int) {
    val minHeap = PriorityQueue<Int>()

    init {
        for(seat in 1..n) minHeap.offer(seat)
    }

    fun reserve(): Int {
        return minHeap.poll()
    }

    fun unreserve(seatNumber: Int) {
        minHeap.offer(seatNumber)
    }
}