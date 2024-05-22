import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node {
    int x;
    int y;
    int cost;

    public Node(int x, int y, int cost) {
        this.x = x;
        this.y = y;
        this.cost = cost;
    }
}

/**
 * 유형: 다이나믹 프로그래밍(누적합)
 */


public class Main {
    static int dx[] = {-1, 1, 0, 0}, dy[] = {0, 0, -1, 1}, ver[][], N, M, ans, hor[][];
    static long dp[][];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        hor = new int[N][M + 1];
        ver = new int[N + 1][M];

        dp = new long[N + 1][M + 1];

        ans = 0;

        int K = Integer.parseInt(br.readLine());
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            // 가로가 같을 때
            if (b == d) {
                hor[Math.min(a, c)][b] = 1;
            } else {
                ver[a][Math.min(b, d)] = 1;
            }
        }

//         누적합 초기화
//         가로
        for (int i = 1; i < N + 1; i++) {
            if (hor[i - 1][0] == 1) {
                break;
            }

            dp[i][0] = 1;
        }

        // 세로
        for (int i = 1; i < M + 1; i++) {
            if (ver[0][i - 1] == 1) {
                break;
            }

            dp[0][i] = 1;
        }


        // 세로
        for (int i = 1; i <= N; i++) {
            // 가로
            for (int j = 1; j <= M; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];

                if (hor[i - 1][j] == 1) {
                    dp[i][j] -= dp[i - 1][j];
                }

                if (ver[i][j - 1] == 1) {
                    dp[i][j] -= dp[i][j - 1];
                }
            }
        }

        System.out.println(dp[N][M]);
    }
}
