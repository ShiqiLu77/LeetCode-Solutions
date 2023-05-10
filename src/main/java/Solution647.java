class Solution647{
    public int countSubstrings(String s) {
        StringBuilder t = new StringBuilder(s.length() * 2 + 3);
        t.append("$");
        for (int i = 0 ; i < s.length() ; i++){
            t.append('#');
            t.append(s.charAt(i));
        }
        t.append("#!");

        int count = 0;
        int im = 0, rm = 0;
        int[] dp = new int[t.length()];

        for (int i = 1; i < t.length()-1 ; i++ ){
            if (i <= rm){
                int j = im * 2 - i;
                int r = rm - i + 1;
                if (dp[j] < r)        dp[i] = dp[j];
                else if (dp[j] > r)   dp[i] = r;
                else{
                    dp[i] = r;
                    while (t.charAt(i + dp[i]) == t.charAt(i - dp[i])) {
                        dp[i]++;
                    }
                }
            }else{
                dp[i] = 1;
                while (t.charAt(i + dp[i]) == t.charAt(i - dp[i])) {
                    dp[i]++;
                }
            }

            count += dp[i]/2;
            if (i + dp[i] - 1 > rm) {
                im = i;
                rm = i + dp[i] - 1;
            }
        }
        return count;
    }
}
