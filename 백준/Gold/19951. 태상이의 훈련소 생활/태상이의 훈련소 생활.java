import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
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

        int[] dig = new int[N + 1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            dig[a - 1] += k;
            dig[b] -= k;
        }


        int total = 0;
        for (int i = 0; i < N; i++) {
            total += dig[i];
            dig[i] = total;
        }

        for (int i = 0; i < N; i++) {
            arr[i] += dig[i];
        }

        StringBuilder sb = new StringBuilder();
        for (int i : arr) {
            sb.append(i + " ");
        }

        System.out.println(sb);
    }
}

// now = 0
// [-3, 5, 2]
//  1  2  3  4  5  6  7  8  9  10  11
// -3  2  0  0  0  8  0 -2  0   0   5
// -3 -1 -1 -1 -1  6  6  4  4   4   9
//  1  2  3  4  5 -1 -2 -3 -4  -5
// ====================================
// -2  1  2  3  4  5  4  1  0  -1

// -2 -1  0  1  2 -1 -2 -3 -4  -5
// -2 -1  0  1  2  4  3  2  1   0
// -2  1  2  3  4  6  5  2  1   0