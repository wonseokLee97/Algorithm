import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int cnt, arr[][], r, c, ans, flag;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        int n = (int) Math.pow(2, N);
        cnt = 0;
        flag = 0;

        // 1, 2, 3, 4
        divide(0, 0, n);
    }

    static void divide(int x, int y, int n) {
        if (n == 1) {
            System.out.println(cnt);
            return;
        }
        

        // 0, 0, 8
        // 3, 1
        int divN = n / 2;

        // 1사분면
        if (r < x + divN && c < y + divN) {
            divide(x, y, divN);
        }
        // 2사분면
        else if (r < x + divN && c >= y + divN) {
            cnt += (n * n) / 4;
            divide(x, y + divN, divN);
        }
        // 3사분면
        else if (r >= x + divN && c < y + divN) {
            cnt += ((n * n) / 4) * 2;
            divide(x + divN, y, divN);
        }
        // 4사분면
        else {
            cnt += ((n * n) / 4) * 3;
            divide(x + divN, y + divN, divN);
        }
    }
}



// 2x2 행렬에서
// 1 2
// 3 4 순으로 순회하며 탐색한다.

// N = 3 , 8일때
// 8 -> 4 -> 2 까지 Divide
// 0,0 / 0,1 / 0,2 / 0,3
// 1,0 / 1,1 / 1,2 / 1,3