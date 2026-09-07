class Solution {
    public int distinctSubseqII(String s) {
        long[] dp=new long[26];
        long total =0;
        long MOD=1_000_000_007L;

        for(char c : s.toCharArray()){
            int i=c-'a';
            long add=(total+1)%MOD;
            total=(total+add-dp[i]+MOD)%MOD;
            dp[i]=add;
        }
        return(int) total;
        
    }
}