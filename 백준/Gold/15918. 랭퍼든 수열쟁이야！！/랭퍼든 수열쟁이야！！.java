import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n, x, y, arr[], visited[], total[], ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());

        ans = 0;
        arr = new int[n * 2 + 1];
        visited = new int[n * 2 + 1];

        arr[x] = arr[y] = y - x - 1;
        visited[y - x - 1] = 1;

        perm(1);
        System.out.println(ans);
    }

    // 1 2 3
    // 1 2 1 3 2 3

    // 1 3 2
    // 2 1 3
    // 2 3 1
    // 3 1 2
    // 3 1 2 1 3 2
    // 3 2 1
    // 3 2 x x 3 x

    static void perm(int cnt) {
        if (cnt == n * 2) {
//            System.out.println(Arrays.toString(arr));
            ans++;
            return;
        }

        if (arr[cnt] == 0) {
            for (int i = 1; i <= n; i++) {
                if (visited[i] == 1) {
                    continue;
                }

                if (i + cnt + 1 <= n * 2 && arr[i + cnt + 1] == 0) {
                    arr[cnt] = arr[i + cnt + 1] = i;
                    visited[i] = 1;
                    perm(cnt + 1);
                    arr[cnt] = arr[i + cnt + 1] = 0;
                    visited[i] = 0;
                }
            }
        } else {
            perm(cnt + 1);
        }
    }
}


// 랭퍼드 수열은 다음 조건을 만족하는 길이 2n의 수열이다.
// 1 이상 n 이하의 자연수가 각각 두 개씩 들어 있다.
// 두 개의 1 사이에는 정확히 1개의 수가 있다.
// 두 개의 2 사이에는 정확히 2개의 수가 있다.
// ...
// 두 개의 n 사이에는 정확히 n개의 수가 있다.

// 1 1 2 2 3 3
// 1 2 2 3 1 3