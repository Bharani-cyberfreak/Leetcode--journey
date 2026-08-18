class Solution {
    public int largestInteger(int[] nums, int k) {
        int n = nums.length;

        if (k == 1) {
            int[] count = new int[51];

            for (int x : nums) {
                count[x]++;
            }

            int ans = -1;

            for (int x : nums) {
                if (count[x] == 1) {
                    ans = Math.max(ans, x);
                }
            }

            return ans;
        }

        if (k == n) {
            int ans = 0;

            for (int x : nums) {
                ans = Math.max(ans, x);
            }

            return ans;
        }

        int ans = -1;

        if (isUnique(nums, 0)) {
            ans = Math.max(ans, nums[0]);
        }

        if (isUnique(nums, n - 1)) {
            ans = Math.max(ans, nums[n - 1]);
        }

        return ans;
    }

    private boolean isUnique(int[] nums, int index) {
        for (int i = 0; i < nums.length; i++) {
            if (i != index && nums[i] == nums[index]) {
                return false;
            }
        }

        return true;
    }
}