package HeapPriorityQueue.PriorityQueue

import java.util.*
import kotlin.collections.HashMap

/*
    Given a string s, rearrange the characters of s so that any two adjacent characters are not the same.

    Return any possible rearrangement of s or return "" if not possible.

    https://leetcode.com/problems/reorganize-string/
*/

fun reorganizeString(s: String): String {
    val charactersCount = HashMap<Char, Int>()
    val maxHeap = PriorityQueue<Char> { a, b -> charactersCount.getOrDefault(b, 0) - charactersCount.getOrDefault(a, 0) }

    for(ch in s) {
        charactersCount[ch] = (charactersCount[ch] ?: 0) + 1
    }
    val res = StringBuilder()
    maxHeap.addAll(charactersCount.keys)

    while(maxHeap.size > 1) {
        val a = maxHeap.poll()
        val b = maxHeap.poll()
        res.append(a)
        res.append(b)

        if(charactersCount.getOrDefault(a, 0) - 1 > 0) {
            charactersCount[a] = charactersCount.getOrDefault(a, 0) - 1
            maxHeap.offer(a)
        }

        if(charactersCount.getOrDefault(b, 0) - 1 > 0) {
            charactersCount[b] = charactersCount.getOrDefault(b, 0) - 1
            maxHeap.offer(b)
        }
    }

    if(maxHeap.isNotEmpty()) {
        if(charactersCount.getOrDefault(maxHeap.peek(), 0) > 1) {
            return ""
        }
        res.append(maxHeap.poll())
    }

    return res.toString()
}