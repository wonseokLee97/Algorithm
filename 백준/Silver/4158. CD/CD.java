import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            if (N == 0 && M == 0) {
                break;
            }

            int[] arr1 = new int[N];
            for (int i = 0; i < N; i++) {
                arr1[i] = Integer.parseInt(br.readLine());
            }

            int[] arr2 = new int[M];
            for (int i = 0; i < M; i++) {
                arr2[i] = Integer.parseInt(br.readLine());
            }

            int p1 = 0;
            int p2 = 0;
            int cnt = 0;


            while (p1 != N && p2 != M) {
                if (arr1[p1] == arr2[p2]) {
                    p1++;
                    p2++;
                    cnt++;
                } else if (arr1[p1] < arr2[p2]) {
                    p1++;
                } else if (arr1[p1] > arr2[p2]){
                    p2++;
                }
            }

            System.out.println(cnt);
        }
    }
}


// 더 작은놈의 s를 움직여주자.
//           s
// 1 2 3 4 5
// 1 2 4 6
//       s

//5 3
//1
//2
//3
//4
//5
//1
//2
//4
//0 0