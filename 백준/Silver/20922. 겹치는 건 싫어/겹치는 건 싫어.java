import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, K, arr[], cnt_arr[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[N];
        cnt_arr = new int[100001];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int s = 0;
        int e = 0;
        int max_len = Integer.MIN_VALUE;

        while (s < N) {
            if (e == N || verify(arr[e])) {
                cnt_arr[arr[s++]]--;
            } else {
                cnt_arr[arr[e++]]++;
            }

            max_len = Math.max(max_len, e - s);
        }

        System.out.println(max_len);
    }

    static boolean verify(int n) {
        if (n == N) {
            return false;
        }

        // 지금 추가하려는 원소의 개수가 K 개보다 적은가?
        if (cnt_arr[n] < K) {
            return false;
        }

        return true;
    }
}

// 최장 연속 부분 수열의 길이