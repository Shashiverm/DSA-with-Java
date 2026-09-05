class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length;

        // suffix minimum
        int[] suffMin = new int[n];
        suffMin[n - 1] = nums[n - 1];

        for (int i = n - 2; i >= 0; i--) {
            suffMin[i] = Math.min(nums[i], suffMin[i + 1]);
        }

        // prefix maximum + find first stable index
        int prefMax = nums[0];

        for (int i = 0; i < n; i++) {
            prefMax = Math.max(prefMax, nums[i]);

            int instability = prefMax - suffMin[i];

            if (instability <= k) {
                return i;
            }
        }

        return -1;
    }
}