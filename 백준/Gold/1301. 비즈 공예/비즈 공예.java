import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;

public class Main {
    static long dp[][][][][][][];
    static int total, N, visited[], arr[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 구슬의 종류
        N = Integer.parseInt(br.readLine());
        arr = new int[6];
        total = 0;

        for (int i = 1; i < N + 1; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            total += arr[i];
        }

        // 전전구슬의 색, 전구슬의 색, x, y, z, p, q의 남은 개수
        dp = new long[6][6][11][11][11][11][11];
        for (long[][][][][][] ints : dp) {
            for (long[][][][][] anInt : ints) {
                for (long[][][][] ints1 : anInt) {
                    for (long[][][] ints2 : ints1) {
                        for (long[][] ints3 : ints2) {
                            for (long[] ints4 : ints3) {
                                Arrays.fill(ints4, -1L);
                            }
                        }
                    }
                }
            }
        }

        System.out.println(perm(0, 0, 0));
    }

    static long perm(int cnt, int pprev, int prev) {
        if (cnt == total) {
            return 1;
        }

        // 전전, 전 구슬의 경우의 수
        long val = dp[pprev][prev][arr[1]][arr[2]][arr[3]][arr[4]][arr[5]];

        // 초기화가 이미 된 경우 return
        if (val != -1) {
            return val;
        }

        long c = 0;
        // 구슬의 종류 선택
        for (int i = 1; i <= N; i++) {
            if (i == pprev || i == prev || arr[i] == 0) {
                continue;
            }

            arr[i]--;
            c += perm(cnt + 1, prev, i);
            arr[i]++;
        }

        dp[pprev][prev][arr[1]][arr[2]][arr[3]][arr[4]][arr[5]] = c;

        return c;
    }
}


// 임의의 연속된 3개의 구슬의 색을 모두 다르게 하려고 한다.
// 1번 구슬을 2개, 2번 구슬을 1개, 3번 구슬을 1개 가지고 있다
// 1-2-3-1, 1-3-2-1,

// 1-2
// 2-1
// 3-1

// x\y 0 1 2 3 4
// 0   0 0 0 0 0
// 1   0 1 1 1 1
// 2   0 1 1 1 1
// 3   0

// y번째 구슬을 놓을 때, 이전의 x구슬과 3칸의 간격이 존재하는가?