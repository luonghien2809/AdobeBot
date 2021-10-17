package Dijkstra;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class MiceNMaze {

    public static class Node implements Comparable<Node>{
        Integer id;
        Integer dist;

        public Node(int id, int dist) {
            this.id = id;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node other) {
            return this.dist.compareTo(other.dist);
        }

    }

    public static ArrayList<ArrayList<Node>> maze;
    public static int[] path;
    public static int[] dist;

    public static void dijkstra(int s) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int n = maze.size();
        for(int i = 0; i< n; i++) {
            path[i]= -1;
            dist[i] = Integer.MAX_VALUE;
        }

        pq.add(new Node(s, 0));
        dist[s] = 0;
        while (!pq.isEmpty()) {
            Node temp = pq.poll();
            int u = temp.id;
            int w = temp.dist;
            for (Node neighbor: maze.get(u)) {
                if (w + neighbor.dist < dist[neighbor.id]) {
                    dist[neighbor.id] = w + neighbor.dist;
                    pq.add(new Node(neighbor.id, dist[neighbor.id]));
                    path[neighbor.id] = u;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int exit = in.nextInt() - 1;
        int limit = in.nextInt();
        int m = in.nextInt();

        path = new int[n];
        dist = new int[n];
        maze = new ArrayList<>(n);

        for (int i = 0; i < n; i++) {
            maze.add(new ArrayList<Node>());
        }

        for (int i = 0; i < m; i++) {
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;
            int d = in.nextInt();

            maze.get(v).add(new Node(u, d)); // reverse path

        }
        dijkstra(exit);

        int count = 0;
        for (int i = 0; i < n; i++) {
            if (dist[i] <= limit)
                count += 1;
        }

        System.out.println(count);
    }
}
