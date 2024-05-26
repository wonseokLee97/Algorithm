import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 분류: 최소 스패닝 트리(MST)
 */

class Node {
    int from;
    int to;
    int cost;

    public Node(int from, int to, int cost) {
        this.from = from;
        this.to = to;
        this.cost = cost;
    }
}

public class Main {
    static List<Node> graph;
    static int N, M, parent[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        parent = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        long total = 0L;
        graph = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            total += cost;

            graph.add(new Node(from, to, cost));
        }

        Collections.sort(graph, new Comparator<Node>() {
            // 비용 오름차순 정렬 (가장 작은 소요값 부터 연결)
            @Override
            public int compare(Node o1, Node o2) {
                return o1.cost - o2.cost;
            }
        });

        long min = 0L;
        int cnt = 0;
        int flag = 0;
        for (Node node : graph) {
            // 같은 부모인 경우 사이클이다.
            if (union(node.from, node.to)) {
                min += node.cost;
                cnt++;
            }

            if (cnt == N - 1) {
                flag = 1;
                break;
            }
        }

        if (flag == 1) System.out.println(total - min);
        else System.out.println(-1);

    }

    static boolean union(int a, int b) {
        int a_parent = find(a);
        int b_parent = find(b);

        if (a_parent == b_parent) {
            return false;
        }

        if (a_parent > b_parent) {
            parent[a_parent] = b_parent;
        } else {
            parent[b_parent] = a_parent;
        }

        return true;
    }

//    static int find(int node) {
//        // 현재 노드와 찾고자 하는 부모노드가 같다면 가장 마지막
//        if (parent[node] == node) {
//            return node;
//        }
//
//        // 찾고자 하는 노드의 부모 노드로 들어가보자!
//        return parent[node] = find(parent[node]);
//    }
    static int find(int node) {
        // 현재 노드와 찾고자 하는 부모노드가 같다면 가장 마지막
        if (parent[node] == node) {
            return node;
        }

        // 찾고자 하는 노드의 부모 노드로 들어가보자!
        return find(parent[node]);
    }
}


// Union-find 하는데, 가장 적은 비용부터 ㄱ
// A to B일 때, A와 B는 부모가 같으면 안된다.