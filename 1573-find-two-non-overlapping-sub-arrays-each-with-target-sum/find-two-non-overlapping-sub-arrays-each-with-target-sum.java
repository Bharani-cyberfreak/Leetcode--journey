import java.util.*;

class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        int INF = 1000000000;
        int[] best = new int[n + 1];
        Arrays.fill(best, INF);

        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 0);

        int sum = 0;
        int ans = INF;

        for (int i = 1; i <= n; i++) {
            sum += arr[i - 1];

            best[i] = best[i - 1];

            if (map.containsKey(sum - target)) {
                int start = map.get(sum - target);
                int len = i - start;

                if (best[start] != INF) {
                    ans = Math.min(ans, len + best[start]);
                }

                best[i] = Math.min(best[i], len);
            }

            map.put(sum, i);
        }

        return ans == INF ? -1 : ans;
    }
}