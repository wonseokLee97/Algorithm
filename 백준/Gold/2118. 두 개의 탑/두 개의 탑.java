import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        int total = 0;

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            total += arr[i];
        }

        int s = 0;
        int e = 1;

        // s, e 사이에서 s기준 오른쪽 -> e 까지의 거리가 rD
        // e부터 s 까지의 거리가 lD
        int rD = arr[s];
        int lD = total - rD;

        int ans = 0;
        while (s < N) {
            // rD, lD중 최솟값 중 최대값이 정답.
            ans = Math.max(ans, Math.min(rD, lD));

            // e를 증가시켜 rD를 증가시키자.
            if (rD < lD) {
                rD += arr[e];
                lD -= arr[e];
                e++;
                e %= N;
            } else { // rD와 lD가 같아지거나 rD가 더 커지는 경우 앞으로 lD는 작아질 일 밖에 없기 때문에 s를 움직여야함.
                rD -= arr[s];
                lD += arr[s];
                s++;
            }
        }

        System.out.println(ans);
    }
}


// 1 2 3 4 5
// 0 1 2 3 4 5
// 0 1 3 6 10 15

// 누적합
// 1~3: total 3 = dp[3 - 1] - dp[1 - 1] / total 12 = dp[5] - (dp[2] - dp[0])
// 1~4: total 6 = dp[4 - 1] - dp[0] / total 9 = dp[15] - (dp[3] - dp[0])
// 2~4: total 5 = dp[4 - 1] - dp[2 - 1] / total 10 = dp[5] - (dp[3] - dp[1])
// i~j: total = dp[j - 1] - dp[i - 1] / total = dp[N] - (dp[j - 1] - dp[i - 1])

// 투 포인터
// s
//       e
// 1 2 3 4 5  1 2 3 4 5  1 2 3 4 5 ...
//  1 2 3 4 5  1 2 3 4 5  1 2 3 4 ...

// 1. s ~ e 사이의 누적 중 최소값을 구한다.
// 2. 최소값이 최대값보다 작거나 같다면