import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Node {
    int x;
    int y;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < 200001; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            graph.get(y).add(x);
        }

        for (int i = 0; i < 200001; i++) {
            Collections.sort(graph.get(i));
        }


        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(0, 0));
        int cnt = 0;

        while (!q.isEmpty()) {
            int size = q.size();

            // 현재 이동할 수 있는 다음 좌표를 모두 털자.
            for (int s = 0; s < size; s++) {
                Node now_node = q.poll();

                // 정산에 도달했다면 탈출
                if (now_node.y == T) {
                    System.out.println(cnt);
                    return;
                }

                // 갈 수 있는 노드, y를 기준으로 +-2를 탐색
                for (int y = now_node.y - 2; y <= now_node.y + 2; y++) {
                    if (y < 0 || y >= 200001) {
                        continue;
                    }

                    // 기준을 충족한 y들 중,
                    for (int i = 0; i < graph.get(y).size(); i++) {
                        int x = graph.get(y).get(i);

                        // x 좌표를 정렬했기 때문에 현재 좌표보다 2초과로 크면 그 뒤는 볼 필요가 없다.
                        if (now_node.x + 2 < x) break;
                        else if (now_node.x - 2 > x) {
                            continue;
                        }

                        graph.get(y).remove(i);
                        q.offer(new Node(x, y));
                        i--;
                    }
                }
            }

            cnt++;
        }
        
        System.out.println(-1);
    }
}

// [x, y] -> [a, b]로 이동하기 위한 조건
// 1. 각 좌표의 차이가 2이하이면 이동 가능.
// 높이가 T 일때까지 오르려고 한다.


//5 3 : 5개의 좌표와 목표 높이는 3
//시작 위치는 0, 0
//1 2 (1)
//6 3 (2)
//4 1 (3)
//3 2 (4)
//0 2 (5)

// 0 - 0
// 1 - 4
// 2 - 0, 3
// 3 - 2


// 1. 시작점 기준으로 갈 수 있는 좌표를 탐색하자.
// 2. 갈 수 있는 좌표들을 큐에 넣는다.
// 3. 다음 좌표들을 순회하며 방문하지 않은 곳 중 갈 수 있는 좌표들을 큐에 넣는다.
// 4. y가 T라면 저장.

// 0. [0, 0, 0] -> [1, 2, 1], [0, 2, 1]
// 1. [1, 2, 1] -> [3, 2, 2], [4, 1, 1]

// 우선순위 큐, 각 좌표의 차가 2 이하, 그 중 가장 간격이 큰
// [0, 0]
// [1, 2]
// [3, 2]
// [4, 1]
// [6, 3]