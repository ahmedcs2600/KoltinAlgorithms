package HeapPriorityQueue.PriorityQueue

import java.util.*

/*
    You are given an array of strings nums and an integer k. Each string in nums represents an integer without leading zeros.

    Return the string that represents the kth largest integer in nums.

    Note: Duplicate numbers should be counted distinctly. For example, if nums is ["1","2","2"], "2" is the first largest integer, "2" is the second-largest integer, and "1" is the third-largest integer.

    https://leetcode.com/problems/find-the-kth-largest-integer-in-the-array/

* */


fun kthLargestNumber(nums: Array<String>, k: Int): String {
    val minHeap = PriorityQueue<String> { a, b -> compare(a, b) }

    for(num in nums) {
        minHeap.offer(num)
        if(minHeap.size > k) minHeap.poll()
    }

    return minHeap.peek()
}

private fun compare(a: String, b: String): Int {
    if(a.length > b.length) return 1
    else if(a.length < b.length) return -1
    else {
        var index = 0
        for(index in 0 until Math.min(a.length, b.length)) {
            if(a[index] == b[index]) continue
            if(a[index] > b[index]) return 1
            else return -1
        }
        return 0
    }
}
