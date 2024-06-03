import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = i + 1;
        }

        int s = 0;
        int e = 0;
        int total = 0;
        int cnt = 0;

        // 더하고 옮겨라
        while (s < N) {
            // 총합이 N보다 크거나 같다면 s를 오른쪽으로 움직여 총합을 줄이다.
            // e가 가장 배열의 크기를 벗어났다면 s만 움직이자.
            if (total >= N || e == N) {
                total -= arr[s++];
            } else {
                total += arr[e++];
            }

            if (total == N) {
                cnt++;
            }
        }

        System.out.println(cnt);
    }
}