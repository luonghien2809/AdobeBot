package Dijkstra;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class TheShortestPath {

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
    public static String[] cities;

    public static void dijkstra(int s) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for(int i= 0;i <maze.size(); i++) {
            path[i] = -1;
            dist[i] = Integer.MAX_VALUE;
        }
        dist[s] = 0;
        pq.add(new Node(s, 0));
        while(!pq.isEmpty()) {
            Node temp = pq.poll();
            int w = temp.dist;
            int id = temp.id;
            for(Node node : maze.get(id)) {
                if (w + node.dist < dist[node.id]) {
                    dist[node.id] = w + node.dist;
                    pq.add(new Node(node.id, dist[node.id]));
                    path[node.id] = id;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int s = in.nextInt();

        for(int i=0; i < s; i++) {
            int n = in.nextInt();
            cities =  new String[n];
            path = new int[n];
            dist = new int[n];
            maze = new ArrayList<>(n);
            for (int j = 0; j < n; j ++) {
                maze.add(new ArrayList<>(n));
                String name = in.next();
                cities[j] = name;
                int p = in.nextInt();
                for (int k = 0; k < p; k++) {
                    int v = in.nextInt() - 1;
                    int d = in.nextInt();
                    maze.get(j).add(new Node(v, d));
                }
            }
            int r = in.nextInt();
            for (int j = 0 ; j < r; j ++) {
                String source = in.next();
                String des = in.next();
                int sourceIndx = - 1;
                int desIndx = -1;
                for (int k = 0; k < n; k++) {
                    if (source.compareTo(cities[k]) == 0)
                        sourceIndx = k;
                    if (des.compareTo(cities[k]) == 0)
                        desIndx = k;
                }

                dijkstra(sourceIndx);
                System.out.println(dist[desIndx]);
            }
            in.nextLine();
        }
    }
}
