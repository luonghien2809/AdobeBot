package Dijkstra;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class SendingEmail {

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
    public static int n,m,s,t;
    public static int[] path;
    public static int[] dist;

    public static void dijkstra(int s) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        dist[s] = 0;
        pq.add(new Node(s, 0));
        while(!pq.isEmpty()) {
            Node temp = pq.poll();
            for(Node node: maze.get(temp.id)) {
                if (temp.dist + node.dist < dist[node.id]) {
                    dist[node.id] = temp.dist + node.dist;
                    pq.add(new Node(node.id, dist[node.id]));
                    path[node.id] = temp.id;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int cases = in.nextInt();
        for (int i = 0; i < cases; i++) {
            n = in.nextInt();
            m = in.nextInt();
            s = in.nextInt();
            t = in.nextInt();

            maze = new ArrayList<>();
            path = new int[n];
            dist = new int[n];
            for(int j = 0; j < n; j++) {
                maze.add(new ArrayList<>());
                path[j] = -1;
                dist[j] = Integer.MAX_VALUE;
            }

            for(int j = 0; j < m; j++) {
                int u = in.nextInt();
                int v = in.nextInt();
                int d = in.nextInt();

                maze.get(u).add(new Node(v, d));
                maze.get(v).add(new Node(u, d));
            }
            dijkstra(s);
            System.out.print("Case #" + (i + 1) + ": ");
            if (path[t] == -1)
                System.out.println("unreachable");
            else
                System.out.println(dist[t]);
        }

    }
}
