import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Node {
    int pos;
    int sector;
    int t;

    public Node(int pos, int sector, int t) {
        this.pos = pos;
        this.sector = sector;
        this.t = t;
    }
}


public class Main {
    static char[][] map;
    static int N, k, flag, visited[][];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 칸의 수
        k = Integer.parseInt(st.nextToken()); // 다른 칸 이동시 추가 이동
        flag = 0;

        map = new char[2][N];
        visited = new int[2][N];

        String s = br.readLine();
        map[0] = s.toCharArray();

        s = br.readLine();
        map[1] = s.toCharArray();

        int pos = 0;
        int sector = 0;

        visited[pos][sector] = 1;
        bfs(pos, sector, 0);

        if (flag == 1) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }

    static void bfs(int a, int b, int c) {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(a, b, c));

        while (!q.isEmpty()) {
            Node now_state = q.poll();

            int pos = now_state.pos;
            int sector = now_state.sector;
            int t = now_state.t;

            if (pos + k >= N || pos + 1 >= N) {
                flag = 1;
                break;
            }

            if (now_state.pos < now_state.t) {
                continue;
            }

            // 한 칸 앞으로 이동한다. 예를 들어, 현재 있는 칸이 i번 칸이면, i+1번 칸으로 이동한다.
            if (visited[sector][pos + 1] == 0 && map[sector][pos + 1] == '1') {
                visited[sector][pos + 1] = 1;
                q.offer(new Node(pos + 1, sector, t + 1));
            }

            // 한 칸 뒤로 이동한다. 예를 들어, 현재 있는 칸이 i번 칸이면, i-1번 칸으로 이동한다.
            if (pos - 1 >= 0 && visited[sector][pos - 1] == 0 && map[sector][pos - 1] == '1') {
                visited[sector][pos - 1] = 1;
                q.offer(new Node(pos - 1, sector, t + 1));
            }

            // 반대편 줄로 점프한다. 이때, 원래 있던 칸보다 k칸 앞의 칸으로 이동해야 한다. 예를 들어, 현재 있는 칸이 왼쪽 줄의 i번 칸이면, 오른쪽 줄의 i+k번 칸으로 이동해야 한다.
            if (sector == 1) {
                if (visited[0][pos + k] == 0 && map[0][pos + k] == '1') {
                    visited[0][pos + k] = 1;
                    q.offer(new Node(pos + k, 0, t + 1));
                }
            } else {
                if (visited[1][pos + k] == 0 && map[1][pos + k] == '1') {
                    visited[1][pos + k] = 1;
                    q.offer(new Node(pos + k, 1, t + 1));
                }
            }

        }
    }

//    static void dfs(int pos, int sector, int t) {
//        if (pos < t) {
//            return;
//        }
//
//        if (pos + k >= N || pos + 1 >= N) {
//            flag = 1;
//            return;
//        }
//
//        // 한 칸 앞으로 이동한다. 예를 들어, 현재 있는 칸이 i번 칸이면, i+1번 칸으로 이동한다.
//        if (pos + 1 >= t - 1 && visited[sector][pos + 1] == 0 && map[sector][pos + 1] == '1') {
//            visited[sector][pos + 1] = 1;
//            dfs(pos + 1, sector, t + 1);
//            visited[sector][pos + 1] = 0;
//        }
//
//        // 한 칸 뒤로 이동한다. 예를 들어, 현재 있는 칸이 i번 칸이면, i-1번 칸으로 이동한다.
//        if (pos - 1 >= 0 && pos - 1 >= t - 1 && visited[sector][pos - 1] == 0 && map[sector][pos - 1] == '1') {
//            visited[sector][pos - 1] = 1;
//            dfs(pos - 1, sector, t + 1);
//            visited[sector][pos - 1] = 0;
//        }
//
//        // 반대편 줄로 점프한다. 이때, 원래 있던 칸보다 k칸 앞의 칸으로 이동해야 한다. 예를 들어, 현재 있는 칸이 왼쪽 줄의 i번 칸이면, 오른쪽 줄의 i+k번 칸으로 이동해야 한다.
//        if (sector == 1) {
//            if (pos + k >= t - 1 && visited[0][pos + k] == 0 && map[0][pos + k] == '1') {
//                visited[0][pos + k] = 1;
//                dfs(pos + k, 0, t + 1);
//                visited[0][pos + k] = 0;
//            }
//        } else {
//            if (pos + k >= t - 1 && visited[1][pos + k] == 0 && map[1][pos + k] == '1') {
//                visited[1][pos + k] = 1;
//                dfs(pos + k, 1, t + 1);
//                visited[1][pos + k] = 0;
//            }
//        }
//    }
}


// 유저가 먼저 움직이고, 칸이 사라진다


//2 1
//10
//10