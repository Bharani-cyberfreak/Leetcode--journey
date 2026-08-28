class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();
        int[] original = new int[26];

        for (char c : s.toCharArray()) {
            original[c - 'a']++;
        }

        for (int pivot = n - 1; pivot >= 0; pivot--) {
            int[] cnt = original.clone();
            boolean possible = true;

            for (int i = 0; i < pivot; i++) {
                int x = target.charAt(i) - 'a';

                if (cnt[x] == 0) {
                    possible = false;
                    break;
                }

                cnt[x]--;
            }

            if (!possible) {
                continue;
            }

            int x = target.charAt(pivot) - 'a';

            for (int c = x + 1; c < 26; c++) {
                if (cnt[c] > 0) {
                    StringBuilder ans = new StringBuilder();

                    ans.append(target, 0, pivot);
                    ans.append((char) ('a' + c));
                    cnt[c]--;

                    for (int j = 0; j < 26; j++) {
                        while (cnt[j] > 0) {
                            ans.append((char) ('a' + j));
                            cnt[j]--;
                        }
                    }

                    return ans.toString();
                }
            }
        }

        return "";
    }
}