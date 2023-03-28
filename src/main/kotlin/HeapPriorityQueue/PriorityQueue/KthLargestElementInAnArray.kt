package HeapPriorityQueue.PriorityQueue

fun findKthLargest(nums: IntArray, k: Int): Int {
    return quickSelect(nums,0, nums.size - 1, nums.size - k)
}

//Quick Select Implementation
private fun quickSelect(nums: IntArray, l: Int, r: Int, k: Int): Int {
    val pivot = nums[r]
    var p = l

    for(index in l until r) {
        if(nums[index] < pivot) {
            swap(nums, index, p)
            p+=1
        }
    }
    swap(nums, p, r)

    if(p > k) return quickSelect(nums, l, p - 1, k)
    else if(p < k) return quickSelect(nums, p + 1, r, k)
    else return nums[p]
}

private fun swap(nums: IntArray, a: Int, b: Int) {
    val temp = nums[a]
    nums[a] = nums[b]
    nums[b] = temp
}