import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int arr[][], N, M, max_val;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        max_val = Integer.MIN_VALUE;


        for (int i = 0; i < N; i++) {
            char[] c = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                arr[i][j] = c[j] - '0';
            }
        }

        if (N == 1 && M == 1) {
            int sqrt = (int) Math.sqrt(arr[0][0]);

//            System.out.println(ans + ", " + sqrt);
            if (sqrt * sqrt == arr[0][0]) {
                max_val = arr[0][0];
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int k = 0; k < N; k++) {
                    for (int l = 0; l < M; l++) {
                        StringBuilder sb = new StringBuilder();
                        play(i, j, k, l, sb, 0);
                        sb = new StringBuilder();
                        play(i, j, k, l, sb, 1);
                        sb = new StringBuilder();
                        play(i, j, k, l, sb, 2);
                        sb = new StringBuilder();
                        play(i, j, k, l, sb, 3);
                    }
                }
            }
        }

        if (max_val == Integer.MIN_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(max_val);
        }
    }

    static void play(int s, int e, int i, int j, StringBuilder sb, int c) {
        if (i == 0 && j == 0) {
            return;
        }

        if (!sb.toString().equals("")) {
            int ans = Integer.parseInt(sb.toString());
            int sqrt = (int) Math.sqrt(ans);

//            System.out.println(ans + ", " + sqrt);
            if (sqrt * sqrt == ans) {
                max_val = Math.max(max_val, ans);
            }
        }


        if (s < 0 || s >= N || e < 0 || e >= M) {
            return;
        }

        switch (c) {
            case 0:
                play(s + i, e + j, i, j, sb.append(arr[s][e]), c);
                break;

            case 1:
                play(s - i, e + j, i, j, sb.append(arr[s][e]), c);
                break;

            case 2:
                play(s + i, e - j, i, j, sb.append(arr[s][e]), c);
                break;

            case 3:
                play(s - i, e - j, i, j, sb.append(arr[s][e]), c);
                break;
        }


    }
}


// 좌우상하는 1, 2, 3 .. 씩 증가
// 1. 00, 01, 02 처럼 행이나 열이 0씩 증가한다면 ㄱㅊ
// 2. 00, 11, 22 처럼 행이나 열이 동시에 증가한다면 ㄱㅊ
// 3. 00, 12, 24

// 00,
// 01, 02 ...
// 10, 20, ...

// 00 01 02
// 10 11 12
// 20 21 22

// [1, 2, 3]
// [4, 5, 6]
// [7, 8, 9]
