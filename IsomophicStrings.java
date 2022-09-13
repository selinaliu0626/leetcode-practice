

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//205. Isomorphic Strings
public class IsomophicStrings {
    // method1: creat 2 array, since the character could be max 256,
    // we could use char in s as the index array to store the char in string t
    //and use char in string t to be the index to store the char in string s

    public boolean isIsomorphic1(String s, String t) {
        //if(s.length()!= t.length()) return false;
        int[] smap =new int[256];
        Arrays.fill(smap,-1);
        int[] tmap =new int[256];
        Arrays.fill(tmap,-1);
        for(int i =0;i<s.length();i++){
            char c1 =s.charAt(i);
            char c2 =t.charAt(i);
            if(smap[c2]==-1 && tmap[c1]==-1){
                smap[c2]=c1;
                tmap[c1]=c2;
            }else if(smap[c2]!= c1 ||tmap[c1]!=c2){
                return false;
            }
        }
        return true;
    }


    //method 2
    // convert add to" 0 1 1", use index as the char in string , and put space to distinguish  "1 1" and "11 "
    public boolean isIsomorphic2(String s, String t) {
        return transToString(s).equals(transToString(t));
    }

    private String transToString(String s) {
        Map<Character,Integer> map = new HashMap<>();
            StringBuilder sb =new StringBuilder();
            for(int i =0;i<s.length();i++){
                char c1 =s.charAt(i);
                if(!map.containsKey(c1)){
                    map.put(c1,i);
                }
                //if already has in map
                sb.append(Integer.toString(map.get(c1)));
                sb.append(" ");
            }
            return sb.toString();
    }
}
