import java.util.*;
//76. Minimum Window Substring

class Pair {
    public char first;
    public int second;
    public Pair(char v,int e){
        first=v;
        second=e;
    }
    public void setFirst(char v){
        first=v;
    }
    public void setSecond(int e){
        second=e;
    }
    public char getFirst(Pair p){
        return p.first;
    }
    public int getSecond(Pair p){
        return  p.second;
    }

}

public class MinimumWindowSubstring {
    public String minWindow(String s, String t) {
        if (s.length() == 0 || t.length() == 0 || t.length() > s.length()) return "";
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            map.put(t.charAt(i), map.getOrDefault(t.charAt(i), 0) + 1);
        }
        int require = map.size();

        List<Pair> filters = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                filters.add(new Pair(c, i));
            }
        }

        int left = 0;
        int right = 0;
        int formed = 0;
        Map<Character, Integer> windowCounts = new HashMap<Character, Integer>();
        int[] ans = {-1, 0, 0}; //record size, start and end
        while (right < filters.size()) {
            char c = filters.get(right).first;
            int count = windowCounts.getOrDefault(c, 0);
            windowCounts.put(c, count + 1);
            if (map.containsKey(c) && windowCounts.get(c).intValue() == map.get(c).intValue()) {
                formed++;
            }

            while (left <= right && formed == require) {
                c = filters.get(left).first;
                int end = filters.get(right).second;
                int start = filters.get(left).second;
                if (ans[0] == -1 || end - start + 1 < ans[0]) {
                    ans[0] = end - start + 1;
                    ans[1] = start;
                    ans[2] = end;
                }
                windowCounts.put(c, windowCounts.get(c) - 1);
                if (map.containsKey(c) && windowCounts.get(c).intValue() < map.get(c).intValue()) {
                    formed--;
                }
                left++;
            }
            right++;
        }
        return ans[0] == -1 ? "" : s.substring(ans[1], ans[2] + 1);
    }
}


