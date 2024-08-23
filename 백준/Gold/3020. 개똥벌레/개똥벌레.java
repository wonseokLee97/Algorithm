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
        int H = Integer.parseInt(st.nextToken());

        int[] down = new int[N / 2];
        int[] up = new int[N / 2];

        // 1 3
        int idxD = 0;
        int idxU = 0;
        for (int i = 0; i < N; i++) {
            // 석순
            if (i % 2 == 0) {
                down[idxD++] = Integer.parseInt(br.readLine());
            } else {
                up[idxU++] = Integer.parseInt(br.readLine());
            }
        }
        Arrays.sort(down);
        Arrays.sort(up);

        // N의 최대값은 200,000
        // O(N*H)는 10억이므로 이분탐색 사용
        // 높이가 i로 개똥벌레가 지나갈 때 부서지는 장애물의 총 합
        int minCrack = Integer.MAX_VALUE;
        int[] visited = new int[N];
        for (int i = 1; i <= H; i++) {
            int d = binarySearch(i, down);
            int u = binarySearch(H - i + 1, up);

            int crack = d + u;
            minCrack = Math.min(minCrack, crack);
            visited[crack]++;
        }

        System.out.println(minCrack + " " + visited[minCrack]);
    }

    //     1  2  3  4  5  6
    //    ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
    // 7 |    1     1     1
    // 6 |    1     1
    // 5 |    1     1  1
    // 4 |    1        1
    // 3 |    1  1     1
    // 2 |       1     1
    // 1 | 1     1     1

    // arrA 2 2 3 3 3 4 4
    // arrB 3 3 3 2 2 4 4
    // 3일 때 (3, 4, 5)면 부심, 5 - (3 - 1) | 3
    // 2일 때 (4, 5)면 부심, 5 - (2 - 1) | 4
    // 4일 때 (2, 3, 4, 5)면 부심 5 - (4 - 1) | 2
    // arrB 3 3 3 4 4 2 2
    // arrB

    // ex) 장애물이 다음과 같이 있고, 높이가 m(3)일 때
    //     x 여기서 부터 뒤에는 모두 장애물이므로 박살낸다. (Lower Bound - s)
    // arr 1 2 3 3 3 3 4
    // H가 3일 때, 3 뒤로부터는 다 부셔버려.
    // 그렇기에 3이 시작하는 부분을 찾아야 함.
    static int binarySearch(int H, int[] arr) {
        int s = 0;
        int e = arr.length - 1;

        while (s <= e) {
            int m = (s + e) / 2;

            // 해당 idx 의 값이 H보다 같거나 클 때
            // m의 값을 줄여야함.
            if (arr[m] >= H) {
                e = m - 1;
            } else {
                s = m + 1;
            }
        }

        return arr.length - s;
    }
}
