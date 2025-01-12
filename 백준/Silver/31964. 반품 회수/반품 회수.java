import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];
        int[] time = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int L = arr[N - 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            time[i] = Integer.parseInt(st.nextToken());
        }

        int idx = 0;
        int min_wait = 0;
        for (int i = 0; i < L; i++) {
            // 택배 수거 위치
            if (arr[idx] == i) {
                int t1 = i; // 갈 때,
                int t2 = L * 2 - i; // 돌아올 때,

                // 수거 불가능
                if (time[idx] > t1 + min_wait && time[idx] > t2 + min_wait) {
                    min_wait = Math.max(min_wait, Math.abs(time[idx] - t2));
                }

                idx++;
            }
        }

        System.out.println(L * 2 + min_wait);
    }
}

// min_wait = 2;
// min_wait = 3;
// 100,000,000

//         20           4      16           11
//  0   1   2   3   4   5   6   7   8   9   10
//  0   1   2   3   4   5   6   7   8   9   10
// 20  19  18  17  16  15  14  13  12  11