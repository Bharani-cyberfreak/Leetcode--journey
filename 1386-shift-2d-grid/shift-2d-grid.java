class Solution {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {

        int m = grid.length;
        int n = grid[0].length;
        int total = m * n;

        Integer[][] result = new Integer[m][n];

        for (int i = 0; i < m; i++) {

            for (int j = 0; j < n; j++) {

                int index = i * n + j;

                int newIndex = (index + k) % total;

                int newRow = newIndex / n;

                int newCol = newIndex % n;

                result[newRow][newCol] = grid[i][j];
            }
        }

        List<List<Integer>> ans = new ArrayList<>();

        for (Integer[] row : result) {
            ans.add(Arrays.asList(row));
        }

        return ans;
    }
}