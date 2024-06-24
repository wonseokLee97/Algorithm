import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        long[] arr = new long[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }

        long s = 1;
        long e = 1000000000;

        long max_val = Long.MIN_VALUE;

        while (s <= e) {
            long mid = (s + e) / 2;
            long sum = 0;

            for (long snack : arr) {
                sum += snack / mid;
            }


            // 과자의 개수가 조카의 수 보다 많다면 (길이가 너무 짧다)
            if (sum >= M) {
                max_val = Math.max(max_val, mid);
                s = mid + 1;
            } else if (sum < M) { // (길이가 너무 길다)
                e = mid - 1;
            }
        }

        if (max_val == Long.MIN_VALUE) {
            System.out.println(0);
        } else {
            System.out.println(max_val);
        }
        
    }
}
