import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();

        String[] s_arr = new String[]{"pi", "ka", "chu"};

        char[] c_arr = s.toCharArray();

        int flag = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < c_arr.length; i++) {
            sb.append(c_arr[i]);

            for (String pikachu : s_arr) {
                if (sb.toString().equals(pikachu)) {
                    flag = 1;
                    break;
                }
            }

            if (flag == 1) {
                sb = new StringBuilder();
                flag = 0;
            }
        }

        if (sb.toString().length() == 0) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}
