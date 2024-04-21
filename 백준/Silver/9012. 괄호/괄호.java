import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            Stack<Character> stack = new Stack<>();
            char[] chars = br.readLine().toCharArray();


            // )를 넣기 위해서는..
            // 스택을 pop 해야 한다.
            for (int j = 0; j < chars.length; j++) {
                if (chars[j] == '(') {
                    stack.push('(');
                } else {
                    if (stack.size() != 0 && stack.peek() == '(') {
                        stack.pop();
                    } else {
                        stack.push(')');
                    }
                }

//                System.out.println(stack);
            }

            if (stack.size() != 0) {
                System.out.println("NO");
            } else {
                System.out.println("YES");
            }
        }
    }
}
