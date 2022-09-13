import java.util.*;


public class MostCommonWord {
    public String mostCommonWord(String paragraph, String[] banned) {
        Set<String> set = new HashSet<>();
        for(String s: banned){
            set.add(s);
        }
        String normalizedStr = paragraph.replaceAll("[^a-zA-Z0-9 ]", " ").toLowerCase();
        String[] p = normalizedStr.split("\\s+");
        Map<String, Integer> map = new HashMap<>();
        for(String c :p){
            if(!set.contains(c)) {
                if (!map.containsKey(c)) {
                    map.put(c, 1);
                } else {
                    map.put(c, map.get(c) + 1);
                }
            }
        }
        List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return (o2.getValue() - o1.getValue());
            }
        });
        return list.get(0).getKey();
    }

    public static void main(String[] args) {
        MostCommonWord mcw =new MostCommonWord();
        String pa= "Bob hit a ball, the hit BALL flew far after it was hit";
        String[] ban ={"hit"};
        System.out.println(mcw.mostCommonWord(pa,ban));
    }
}
