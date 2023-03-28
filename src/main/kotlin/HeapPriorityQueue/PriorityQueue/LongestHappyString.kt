package HeapPriorityQueue.PriorityQueue

import java.util.*

/*
*
    A string s is called happy if it satisfies the following conditions:

    s only contains the letters 'a', 'b', and 'c'.
    s does not contain any of "aaa", "bbb", or "ccc" as a substring.
    s contains at most a occurrences of the letter 'a'.
    s contains at most b occurrences of the letter 'b'.
    s contains at most c occurrences of the letter 'c'.
    Given three integers a, b, and c, return the longest possible happy string. If there are multiple longest happy strings, return any of them. If there is no such string, return the empty string "".

    A substring is a contiguous sequence of characters within a string

    https://leetcode.com/problems/longest-happy-string/
* */

class Solution {
    fun longestDiverseString(a: Int, b: Int, c: Int): String {
        val maxHeap = PriorityQueue<Pair<Char, Int>> { a, b -> b.second - a.second }
        if(a > 0) maxHeap.offer(Pair('a', a))
        if(b > 0) maxHeap.offer(Pair('b', b))
        if(c > 0) maxHeap.offer(Pair('c', c))
        val characters = StringBuilder()

        while(!maxHeap.isEmpty()) {
            val (character, count) = maxHeap.poll()
            if(characters.length > 1 && characters[characters.length - 1] == character && characters[characters.length - 2] == character) {
                if (maxHeap.isEmpty()) break
                val (character1, count1) = maxHeap.poll()
                characters.append(character1)
                if(count1 - 1 > 0) {
                    maxHeap.offer(Pair(character1, count1 - 1))
                }
                maxHeap.offer(Pair(character, count))
            } else {
                characters.append(character)
                characters.append(character)
                if(count - 2 > 0) {
                    maxHeap.offer(Pair(character, count - 2))
                }
            }
        }

        return characters.toString()
    }
}