package HeapPriorityQueue.tree_map_pattern

import java.util.*


/*
    There is a car with capacity empty seats. The vehicle only drives east (i.e., it cannot turn around and drive west).

    You are given the integer capacity and an array trips where trips[i] = [numPassengersi, fromi, toi] indicates that the ith trip has numPassengersi passengers and the locations to pick them up and drop them off are fromi and toi respectively. The locations are given as the number of kilometers due east from the car's initial location.

    Return true if it is possible to pick up and drop off all passengers for all the given trips, or false otherwise.

    https://leetcode.com/problems/car-pooling/
* */


class Solution {
    fun carPooling(trips: Array<IntArray>, capacity: Int): Boolean {
        val sortedMap = TreeMap<Int, Int>()

        for((numPassengers, from, to) in trips) {
            sortedMap[from] = sortedMap.getOrDefault(from, 0) - numPassengers
            sortedMap[to] = sortedMap.getOrDefault(to, 0) + numPassengers
        }


        var remain = capacity

        for(passengerCount in sortedMap.values) {
            remain += passengerCount
            if(remain < 0) return false
        }

        return true
    }
}