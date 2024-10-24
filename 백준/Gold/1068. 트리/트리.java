import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static List<List<Integer>> graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        graph = new ArrayList<>();
        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        Queue<Integer> q = new LinkedList<>();


        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int parent = Integer.parseInt(st.nextToken());

            if (parent == -1) {
                q.offer(i);
            } else {
                graph.get(parent).add(i);
            }
        }
        int del_node = Integer.parseInt(br.readLine());
        int ans = 0;

        if (!q.isEmpty() && q.peek() == del_node) {
            System.out.println(0);
            return;
        }

        while (!q.isEmpty()) {
            int now_node = q.poll();
            int flag = 0;

            List<Integer> next_nodes = graph.get(now_node);
            if (next_nodes.isEmpty()) { // 리프노드인 경우
                ans++;
            }

            for (int nextNode : next_nodes) {
                if (nextNode == del_node) { // 삭제한 노드인 경우
                    continue;
                }

                flag = 1;
                q.offer(nextNode);
            }

            // 다음 노드가 있는데, 그것이 삭제한 노드인 경우
            // 지금 노드는 리프노드이다.
            if (!next_nodes.isEmpty() && flag == 0) {
                ans++;
            }
        }

        System.out.println(ans);
    }
}
