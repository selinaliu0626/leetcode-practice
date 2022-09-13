import java.util.Arrays;
import java.util.TreeMap;

public class MyCalendarTwo {
    TreeMap<Integer, Integer> delta;

    public MyCalendarTwo() {
        delta = new TreeMap();
    }

    public boolean book(int start, int end) {
        delta.put(start, delta.getOrDefault(start, 0) + 1);
        delta.put(end, delta.getOrDefault(end, 0) - 1);

        int active = 0;
        for (int d: delta.values()) {
            active += d;
            if (active >= 3) {
                delta.put(start, delta.get(start) - 1);
                delta.put(end, delta.get(end) + 1);
                if (delta.get(start) == 0)
                    delta.remove(start);
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        MyCalendarTwo my2= new MyCalendarTwo();
        int[][] books ={{10,20},{50,60},{10,40},{5,15},{25,55}};
        boolean[] result = new boolean[5];
        for(int i =0;i< books.length;i++){
            int[] cur =books[i];
            boolean a =my2.book(cur[0],cur[1]);
            result[i]=a;
        }
        System.out.println(Arrays.toString(result));

    }
}
