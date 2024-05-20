import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 유형: 플로이드와샬, Dijikstra
 */

public class Main {
    static List<List<Integer>> graph;
    static int N, dist[], ans = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        graph = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 1; i <= N; i++) {
            char[] c_arr = br.readLine().toCharArray();

            for (int j = i + 1; j <= N; j++) {
                if (c_arr[j - 1] == 'Y') {
                    graph.get(i).add(j);
                    graph.get(j).add(i);
                }
            }
        }

        dist = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            Arrays.fill(dist, Integer.MAX_VALUE);
            dijikstra(i);
        }

        System.out.println(ans);
    }

    static void dijikstra(int start) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.offer(start);
        dist[start] = 0;

        while (!pq.isEmpty()) {
            int now_node = pq.poll();

            List<Integer> next_nodes = graph.get(now_node);
            for (int next_node : next_nodes) {
                if (dist[next_node] > dist[now_node] + 1) {
                    dist[next_node] = dist[now_node] + 1;
                    pq.offer(next_node);
                }
            }
        }

        int cnt = 0;
        for (int i = 1; i <= N; i++) {
            if (i == start) {
                continue;
            }

            // 두 사람이 친구이거나, A-B
            // A와 친구이고, B와 친구인 C가 존재해야 된다, A-C, B-C
            // 건너건너 최대 2
            if (dist[i] <= 2) {
                cnt++;
            }
        }

        ans = Math.max(ans, cnt);
    }
}

// 어떤 사람 A가 또다른 사람 B의 2-친구가 되기 위해선,
// 두 사람이 친구이거나, A-B
// A와 친구이고, B와 친구인 C가 존재해야 된다, A-C, B-C


//     A B C D E
// A   N Y N N N
// B   Y N Y N N
// C   N Y N Y N
// D   N N Y N Y
// E   N N N Y N

// A: A-B,
// B: B-A, B-C
// C: C-B, C-D
// D: D-C, D-E
// E: E-D
