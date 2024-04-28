import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            int[][] dp = new int[N + 1][M + 1];


            for (int i = 1; i < N + 1; i++) {
                for (int j = 1; j < M + 1; j++) {
                    if (i == 1) {
                        dp[i][j] = j;
                    } else if (i == j) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = dp[i - 1][j - 1] + dp[i][j - 1];
                    }
                }
            }

//        for (int k = 0; k < N + 1; k++) {
//            System.out.println(Arrays.toString(dp[k]));
//        }
//        System.out.println();

            System.out.println(dp[N][M]);
        }
    }
}



        // 왼쪽에 4개, 오른쪽에 5개일 경우,
        // t1 -> 7
        // t2 -> 6 + 5 + 4 + 3 + 2 + 1 -> 21
        // t3 -> 5 + 4 + 3 + 2 + 1 ->


// 3, 5
// 1 -> 1

// dp
// 1 1 1 1 1 1 1
// 0
// 0 0 5 0 0 0 0
// 0 0 0 0 0 0 0

// dp
// 1 2 3
// 0 1 3

// dp / 3, 4
// 1 2 3 4
// 0 2 4 6
// 0 0 6 12