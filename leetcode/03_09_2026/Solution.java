class Solution {
    public boolean uniformArray(int[] nums1) {
        int minOdd = Integer.MAX_VALUE;
        int minEven = Integer.MAX_VALUE;

        for (int x : nums1) {
            if (x % 2 == 0) {
                minEven = Math.min(minEven, x);
            } else {
                minOdd = Math.min(minOdd, x);
            }
        }

        // Make everything odd:
        // Even x needs a smaller odd number.
        boolean canBeOdd = true;
        for (int x : nums1) {
            if (x % 2 == 0 && minOdd >= x) {
                canBeOdd = false;
                break;
            }
        }

        if (canBeOdd) {
            return true;
        }

        // Make everything even:
        // Odd x needs a smaller odd number.
        boolean canBeEven = true;
        for (int x : nums1) {
            if (x % 2 == 1 && minOdd >= x) {
                canBeEven = false;
                break;
            }
        }

        return canBeEven;
    }
}