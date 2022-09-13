
import java.util.*;
public class BusRoutes {
    static class Pair {
        int x;
        int y;

        Pair(int a, int b) {
            x = a;
            y = b;
        }
    }

    public int numBusesToDestination(int[][] routes, int s, int t) {
        if (s == t) return 0;
        int n = routes.length; //number of buses
        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            Arrays.sort(routes[i]);
            graph.add(new ArrayList<>());
        }

        //build graph
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (interaction(routes[i], routes[j])) {
                    graph.get(i).add(j);
                    graph.get(j).add(i);
                }
            }
        }
        Set<Integer> seen = new HashSet<>();
        Set<Integer> targets = new HashSet<>();
        Queue<Pair> queue = new ArrayDeque<>();

        //find the start and the destination, add the bus id in set

        for (int i = 0; i < n; i++) {
            if (Arrays.binarySearch(routes[i], s) >= 0) {
                seen.add(i);
                queue.offer(new Pair(i, 0));
            }
            if (Arrays.binarySearch(routes[i], t) >= 0) {
                targets.add(i);
            }
        }
        while (!queue.isEmpty()) {
            Pair a = queue.poll();
            int stop = a.x, depth = a.y;
            if (targets.contains(stop)) return depth + 1;
            for (Integer nei : graph.get(stop)) {
                if (!seen.contains(nei)) {
                    seen.add(nei);
                    queue.offer(new Pair(nei, depth + 1));
                }
            }
        }
        return -1;
    }


    private boolean interaction(int[] x, int[] y) {
        int i = 0, j = 0;
        while (i < x.length && j < y.length) {
            if (x[i] == y[j]) return true;
            else if (x[i] < y[j]) {
                i++;
            } else {
                j++;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        BusRoutes br = new BusRoutes();
        int[][] routes = {{1, 2, 5}, {5, 8}, {3, 6, 8}};
        System.out.print(br.numBusesToDestination(routes, 1, 6));
    }
}


