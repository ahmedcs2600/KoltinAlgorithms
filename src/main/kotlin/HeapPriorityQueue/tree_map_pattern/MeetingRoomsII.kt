package HeapPriorityQueue.tree_map_pattern

import java.util.*

/*
* Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.)
* https://www.lintcode.com/problem/919/description
* */
fun main() {

}

class Interval {
    var start: Int = 0
    var end: Int = 0

    constructor(start: Int, end: Int) {
        this.start = start
        this.end = end
    }
}

fun minMeetingRooms(intervals: List<Interval>): Int {
    var currentRunningMeetingRoomCount = 0
    var maxMeetingRoomsRequired = 0
    val meetingTimeToRoomsMap = TreeMap<Int, Int>()

    for (interval in intervals) {
        val start = interval.start
        val end = interval.end
        meetingTimeToRoomsMap[start] = meetingTimeToRoomsMap.getOrDefault(start, 0) + 1
        meetingTimeToRoomsMap[end] = meetingTimeToRoomsMap.getOrDefault(end, 0) - 1
    }

    for (meetingRoomsCount in meetingTimeToRoomsMap.values) {
        currentRunningMeetingRoomCount += meetingRoomsCount
        maxMeetingRoomsRequired = Math.max(currentRunningMeetingRoomCount, maxMeetingRoomsRequired)
    }

    return maxMeetingRoomsRequired
}