import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static List<List<Integer>> graph;
    static List<Integer> list1, list2;
    static int depth[], parent[], ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        graph = new ArrayList<>();
        depth = new int[N + 1];
        parent = new int[N + 1];

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        // 간선의 정보
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            graph.get(from).add(to);
            graph.get(to).add(from);
        }

        depthCheck(1, 1, 0);

        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            ans = -1;
            LCA(a, b);

            System.out.println(ans);
        }
    }


    static void LCA(int a, int b) {
        if (a == b) {
            ans = a;
            return;
        }

        int d_a = depth[a];
        int d_b = depth[b];

        // a가 depth 가 더 깊은경우, a만 depth 를 위로 올린다.
        if (d_a > d_b) {
            LCA(parent[a], b);
        } else if (d_a < d_b) {
            LCA(a, parent[b]);
        } else {
            LCA(parent[a], parent[b]);
        }
    }

    static void depthCheck(int cnt, int now_node, int parent_node) {
        depth[now_node] = cnt;
        parent[now_node] = parent_node;

        List<Integer> next_nodes = graph.get(now_node);
        for (int next_node : next_nodes) {
            if (next_node != parent_node) {
                // depth 증가, 다음노드가 현재 노드, 현재 노드가 다음노드의 부모
                depthCheck(cnt + 1, next_node, now_node);
            }
        }
    }
}

//1 2
//1 3
//2 4
//3 7
//6 2
//3 8
//4 9
//2 5
//5 11
//7 13
//10 4
//11 15
//12 5
//14 7
//15

//15
//1 2
//1 3
//2 4
//3 7
//6 2
//3 8
//4 9
//2 5
//5 11
//7 13
//10 4
//11 15
//12 5
//14 7
//1
//6 11
// 10 9
// 2 6
// 7 6
// 8 13
// 8 15

//11
//1 4
//1 5
//4 6
//5 7
//6 8
//7 9
//8 10
//9 11
//10 2
//11 3
//1
//2 3