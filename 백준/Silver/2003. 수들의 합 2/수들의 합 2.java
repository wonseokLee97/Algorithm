import java.util.*;
import java.io.*;


public class Main
{
    public static void main(String args[]) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int s_idx = -1;
        int e_idx = -1;
        int total = 0;
        int cnt = 0;

        while (s_idx < N - 1) {
            if (total >= M || e_idx == N - 1) {
                total -= arr[++s_idx];
            } else if (total < M) {
                total += arr[++e_idx];
            }

            if (total == M) {
                cnt++;
            }
            // System.out.println("s: " + s_idx + ", e: " + e_idx + ", total: " + total);

        }

        System.out.println(cnt);
    }
}

// 

// 시작, 끝 지점을 -1로 두고
// 끝 지점까지의 합이 M보다 작을경우?
// 끝점을 증가시킨다.
// 끝 지점까지의 합이 M보다 큰 경우?
// 시작 지점을 증가시킨다.
// 끝 지점까지의 합이 M과 같은 경우?
// 끝 점을 증가시킨다.