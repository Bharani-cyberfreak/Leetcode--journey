class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int[] cnt = new int[26];

        for (char c : s.toCharArray()) {
            cnt[c - 'a']++;
        }

        int odd = 0;
        char mid = 0;

        for (int i = 0; i < 26; i++) {
            if ((cnt[i] & 1) == 1) {
                odd++;
                mid = (char) ('a' + i);
            }
        }

        if (odd > 1) {
            return "";
        }

        int[] half = new int[26];
        for (int i = 0; i < 26; i++) {
            half[i] = cnt[i] / 2;
        }

        char[] left = new char[n / 2];

        for (int i = 0; i < n / 2; i++) {
            boolean found = false;

            for (int c = 0; c < 26; c++) {
                if (half[c] == 0) {
                    continue;
                }

                half[c]--;
                left[i] = (char) ('a' + c);

                if (canMakeGreater(left, i + 1, half, mid, target)) {
                    found = true;
                    break;
                }

                half[c]++;
            }

            if (!found) {
                return "";
            }
        }

        StringBuilder ans = new StringBuilder();

        for (char c : left) {
            ans.append(c);
        }

        if (n % 2 == 1) {
            ans.append(mid);
        }

        for (int i = left.length - 1; i >= 0; i--) {
            ans.append(left[i]);
        }

        String result = ans.toString();

        return result.compareTo(target) > 0 ? result : "";
    }

    private boolean canMakeGreater(
        char[] left,
        int len,
        int[] half,
        char mid,
        String target
    ) {
        StringBuilder first = new StringBuilder();

        for (int i = 0; i < len; i++) {
            first.append(left[i]);
        }

        for (int c = 25; c >= 0; c--) {
            for (int j = 0; j < half[c]; j++) {
                first.append((char) ('a' + c));
            }
        }

        StringBuilder candidate = new StringBuilder(first);

        if (target.length() % 2 == 1) {
            candidate.append(mid);
        }

        for (int i = first.length() - 1; i >= 0; i--) {
            candidate.append(first.charAt(i));
        }

        return candidate.toString().compareTo(target) > 0;
    }
}