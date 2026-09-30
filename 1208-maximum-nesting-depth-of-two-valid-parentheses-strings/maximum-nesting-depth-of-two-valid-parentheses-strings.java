class Solution {
    public int[] maxDepthAfterSplit(String s) {
        int n = s.length();
        int[] ans = new int[n];
        int depth = 0;

        for(int i = 0; i < n; i++){
            char ch = s.charAt(i);

            if(ch == '('){
                depth++;
                ans[i] = depth % 2;
            }
            else if(ch == ')'){
                ans[i] = depth % 2;
                depth--;
            }
        }

        return ans;
    }
}