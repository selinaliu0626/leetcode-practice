import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithoutRepeatingCharacters {
    public int lengthOfLongestSubstring(String s) {
        if(s==null || s.length()==0) return 0;
        if(s.length()==1) return 1;
        int count=0, start=0;
        int max=0;
        Map<Character,Integer> map = new HashMap<>();
        for(int end=0;end<s.length();end++){
            char c = s.charAt(end);
            if(!map.containsKey(c)){
                map.put(c,end);
            }else{
                if(map.get(c)<start) map.put(c,end);
                else {
                    count = end - start;
                    max = Math.max(max, count);
                    start = map.get(c) + 1;
                    map.put(c, end);
                }
            }
            if(end== s.length()-1){
                count= end-start+1;
                max=Math.max(max,count);
            }
        }
        return max;

    }

    public static void main(String[] args) {
        LongestSubstringWithoutRepeatingCharacters lwrc= new LongestSubstringWithoutRepeatingCharacters();
        String s ="abba";
        System.out.println(lwrc.lengthOfLongestSubstring(s));

    }
}
