import java.util.*;

class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {
        int n = img1.length;
        int[][] a = new int[n * n][2];
        int[][] b = new int[n * n][2];

        int x = 0, y = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (img1[i][j] == 1) {
                    a[x++] = new int[]{i, j};
                }

                if (img2[i][j] == 1) {
                    b[y++] = new int[]{i, j};
                }
            }
        }

        Map<String, Integer> map = new HashMap<>();

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                int dx = a[i][0] - b[j][0];
                int dy = a[i][1] - b[j][1];

                String key = dx + "," + dy;
                map.put(key, map.getOrDefault(key, 0) + 1);
            }
        }

        int ans = 0;

        for (int value : map.values()) {
            ans = Math.max(ans, value);
        }

        return ans;
    }
}