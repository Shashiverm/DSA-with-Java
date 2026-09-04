class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length;
        int maxLeft = 0;

        for (int i = 0; i < n; i++) {
            maxLeft = Math.max(maxLeft, nums[i]);

            int minRight = nums[i];
            for (int j = i + 1; j < n; j++) {
                minRight = Math.min(minRight, nums[j]);
            }

            if (maxLeft - minRight <= k) {
                return i;
            }
        }

        return -1;
    }
}