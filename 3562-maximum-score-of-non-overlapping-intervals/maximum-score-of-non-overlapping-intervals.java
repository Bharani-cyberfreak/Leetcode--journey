import java.util.*;

class Solution {
    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();

        int[][] a = new int[n][4];
        for (int i = 0; i < n; i++) {
            a[i][0] = intervals.get(i).get(0);
            a[i][1] = intervals.get(i).get(1);
            a[i][2] = intervals.get(i).get(2);
            a[i][3] = i;
        }

        Arrays.sort(a, (x, y) -> {
            if (x[0] != y[0]) return Integer.compare(x[0], y[0]);
            return Integer.compare(x[1], y[1]);
        });

        int[] starts = new int[n];
        for (int i = 0; i < n; i++) {
            starts[i] = a[i][0];
        }

        int[] next = new int[n];
        for (int i = 0; i < n; i++) {
            next[i] = upperBound(starts, a[i][1]);
        }

        long[][] dp = new long[5][n + 1];
        int[][][] best = new int[5][n + 1][4];
        int[][] count = new int[5][n + 1];

        for (int i = n - 1; i >= 0; i--) {
            for (int k = 1; k <= 4; k++) {
                dp[k][i] = dp[k][i + 1];
                count[k][i] = count[k][i + 1];

                for (int j = 0; j < 4; j++) {
                    best[k][i][j] = best[k][i + 1][j];
                }

                long takeValue = a[i][2] + dp[k - 1][next[i]];

                if (takeValue > dp[k][i]) {
                    dp[k][i] = takeValue;
                    count[k][i] = 1 + count[k - 1][next[i]];

                    best[k][i][0] = a[i][3];

                    for (int j = 0; j < count[k - 1][next[i]]; j++) {
                        best[k][i][j + 1] = best[k - 1][next[i]][j];
                    }

                    Arrays.sort(best[k][i], 0, count[k][i]);
                } else if (takeValue == dp[k][i]) {
                    int[] candidate = new int[4];
                    int candidateCount = 1 + count[k - 1][next[i]];

                    candidate[0] = a[i][3];

                    for (int j = 0; j < count[k - 1][next[i]]; j++) {
                        candidate[j + 1] = best[k - 1][next[i]][j];
                    }

                    Arrays.sort(candidate, 0, candidateCount);

                    if (compare(candidate, candidateCount,
                            best[k][i], count[k][i]) < 0) {
                        count[k][i] = candidateCount;

                        for (int j = 0; j < 4; j++) {
                            best[k][i][j] = candidate[j];
                        }
                    }
                }
            }
        }

        return Arrays.copyOf(best[4][0], count[4][0]);
    }

    private int upperBound(int[] arr, int target) {
        int left = 0;
        int right = arr.length;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }

    private int compare(int[] a, int sizeA, int[] b, int sizeB) {
        int len = Math.min(sizeA, sizeB);

        for (int i = 0; i < len; i++) {
            if (a[i] != b[i]) {
                return Integer.compare(a[i], b[i]);
            }
        }

        return Integer.compare(sizeA, sizeB);
    }
}