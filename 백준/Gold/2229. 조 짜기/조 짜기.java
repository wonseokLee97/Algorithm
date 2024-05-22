import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 유형: 다익스트라
 */

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N + 1];
        int[] dp = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // i번째 사람을 기준으로
        for (int i = 1; i <= N; i++) {
            int max_val = Integer.MIN_VALUE;
            int min_val = Integer.MAX_VALUE;

            // i부터 1번 사람까지
            for (int j = i; j > 0; j--) {
                max_val = Math.max(max_val, arr[j]); // 7
                min_val = Math.min(min_val, arr[j]); // 2

                // 이전 조의 최대 값 + 현재 범위에서의 최대값과 최소값의 차
                dp[i] = Math.max(dp[i], dp[j - 1] + max_val - min_val);
            }
        }

        System.out.println(dp[N]);
    }
}

// d[i] = d[i - 1] + m - n;

// 10
// age: 0 1 2 3 4 5 6 7 8 9
// sco: [2 5] [7 1] [3 4 8] [6 9 3] - 3 6 5 6


// 각각의 조가 잘 짜여진 정도는
// 그 조에 속해있는 가장 점수가 높은 학생의 점수와 가장 점수가 낮은 학생의 점수의 차이가 된다.

// 또한 전체적으로 조가 잘 짜여진 정도는,
// 각각의 조가 잘 짜여진 정도의 합으로 나타난다.
// 한 명으로 조가 구성되는 경우에는 그 조의 잘 짜여진 정도가 0이 된다
// (가장 높은 점수와 가장 낮은 점수가 같으므로).