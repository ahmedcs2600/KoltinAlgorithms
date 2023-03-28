package HeapPriorityQueue.design

import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.HashSet

/*
    https://leetcode.com/problems/design-twitter/description/
* */

class Twitter() {
    var timeStamp = 0
    val users = HashMap<Int, User>()

    fun postTweet(userId: Int, tweetId: Int) {
        val tweet = Tweet(tweetId, ++timeStamp)
        createUser(userId)
        users[userId]?.postTweet(tweet)
    }

    private fun createUser(userId: Int) {
        users.putIfAbsent(userId, User(userId))
    }

    fun getNewsFeed(userId: Int): List<Int> {
        val followedUsers = users[userId]?.followed ?: return listOf()
        val maxHeap = PriorityQueue<Tweet> { a, b -> b.timeStamp - a.timeStamp }

        for(user in followedUsers) {
            val tweet = users[user]?.tweetHead ?: continue
            maxHeap.offer(tweet)
        }

        var n = 0
        val res = LinkedList<Int>()

        while(!maxHeap.isEmpty() && n++ < 10) {
            val tweet = maxHeap.poll()
            res.add(tweet.id)
            if(tweet.next != null) {
                maxHeap.offer(tweet.next)
            }
        }
        return res
    }

    fun follow(followerId: Int, followeeId: Int) {
        createUser(followerId)
        createUser(followeeId)
        users[followerId]?.follow(followeeId)
    }

    fun unfollow(followerId: Int, followeeId: Int) {
        createUser(followerId)
        createUser(followeeId)
        users[followerId]?.unFollow(followeeId)
    }
}

class User(
    val id: Int
) {
    val followed = HashSet<Int>()
    var tweetHead: Tweet? = null

    init {
        followed.add(id)
    }

    fun follow(id: Int) {
        followed.add(id)
    }

    fun unFollow(id: Int) {
        followed.remove(id)
    }

    fun postTweet(tweet: Tweet) {
        tweet.next = tweetHead
        tweetHead = tweet
    }
}

data class Tweet(
    val id: Int,
    val timeStamp: Int,
    var next: Tweet? = null
)

/**
 * Your Twitter object will be instantiated and called as such:
 * var obj = Twitter()
 * obj.postTweet(userId,tweetId)
 * var param_2 = obj.getNewsFeed(userId)
 * obj.follow(followerId,followeeId)
 * obj.unfollow(followerId,followeeId)
 */