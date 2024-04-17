import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 유클리드 호재법

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            long lcm = ((long) a * b / Gcd(a, b));
            System.out.println(lcm);
        }
    }

    static long Gcd(int a, int b) {
        if (a % b == 0) {
            return b;
        }

        return Gcd(b, a % b);
    }
}