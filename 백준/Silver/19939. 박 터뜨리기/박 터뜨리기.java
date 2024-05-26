import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int total = 0;

        for (int i = 1; i <= K; i++) {
            total += i;
        }

        if (total > N) {
            System.out.println(-1);
        } else {
            if ((N - total) % K == 0) {
                System.out.println(K - 1);
            } else {
                System.out.println(K);
            }
        }
    }
}

// N개의 공을 K개의 바구니에 나눠 담아야 한다.
// 이때, 게임의 재미를 위해서 바구니에 담기는 공의 개수를 모두 다르게 하고 싶다.
// 즉, N개의 공을 K개의 바구니에 빠짐없이 나누어 담는데,
// 각 바구니에는 1개 이상의 공이 있어야 하고, 바구니에 담긴 공의 개수가 모두 달라야 한다.

// 5, 3

// 0 1 2
// 1 1 1
// 5개 중에 3개는 이미 담겨있음. 2개만 분할하면 됨.
// 홀수개(3)에 짝수개(2) 만큼 분할해야 함.
// 2 % 3 = 2

// 0 1 2
// 1 1 1
// 1 2 3
// 2 3 4

// 9개 중에 3개는 이미 담겨있음.
// 1 2 3
// 남은 3개만 분할하면 됨
// 남은 공의 개수 % 바구니 개수 = 3 % 3 = 0
// 바구니 개수 - 1
// 2 3 4

// 10개 중 3개가 담겨있다면
// 남은 4개만 분할하면됨
// 남은 공의 개수 % 바구니 개수 = 4 % 3 = 1
// 2 3 5


// 이를 토대로
// 1 1 1에서
// 1 2 3으로 분할할 수 있는지 판단 -> 바구니 k개의 1 ~ k-1 sum 을 구해라.
// 그 중, 1 ... k라고 했을 때 k개 만큼 더해라. n - k개를 분할해야지.
// (n-k)/k - 1
// 처음과 마지막을 빼줘!! 2 다음은 4 다음은 6
// 1 2 3
// 2 3 4
// 3 4 5
// 1 2 4


// 8개라고 치자..
// 0 1 2 3 4
// 1 1 1 1 1
// 8개 중에 5개는 이미 담겨있음. 3개만 분할하면 됨
// 4개에 대해 분할해야 됨. 1+2+3+4 10개 있어야지 됨

// 1 1 1 1 1
// 1