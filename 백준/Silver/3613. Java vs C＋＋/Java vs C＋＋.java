import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();


        // C++인 경우
        if (s.contains("_")) {
            System.out.println(CppToJava(s));
        } else { // JAVA인 경우
            System.out.println(JavaToCpp(s));
        }
    }

    static String JavaToCpp(String s) {
        char[] c = s.toCharArray();

        if (c[0] + 0 < 97 || c[0] + 0 > 122) {
            return "Error!";
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < c.length; i++) {
            if (c[i] + 0 >= 97 && c[i] + 0 <= 122) {
                sb.append(c[i]);
            } else {
                sb.append("_");
                sb.append((char) (c[i] + 32));
            }
        }

        return sb.toString();
    }

    static String CppToJava(String s) {
        char[] cT = s.toCharArray();

        char start = cT[0];
        char end = cT[cT.length - 1];

        if (start == '_' || end == '_') {
            return "Error!";
        }


        String[] sArr = s.split("_");

        for (int i = 0; i < sArr.length; i++) {
            if (sArr[i] == "") {
                return "Error!";
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < sArr.length; i++) {
            char[] c = sArr[i].toCharArray();
            for (int j = 0; j < c.length; j++) {
                if (c[j] + 0 < 97 || c[j] + 0 > 122) {
                    return "Error!";
                }

                if (j == 0) {
                    if (i == 0) {
                        sb.append(c[j]);
                    } else {
                        sb.append((char) (c[j] - 32));
                    }
                } else {
                    sb.append(c[j]);
                }
            }
        }

        return sb.toString();
    }
}

// 1. 재원이의 프로그램은 가장 먼저 변수명을 입력으로 받은 뒤,
// 이 변수명이 어떤 언어 형식인지를 알아내야 한다.
// 2. 그 다음, C++형식이라면 Java형식으로, Java형식이라면 C++형식으로 바꾸면 된다.
// 3. 만약 C++형식과 Java형식 둘 다 아니라면, 에러를 발생시킨다.
// !. 변수명을 변환할 때, 단어의 순서는 유지되어야 한다.
// c__A
// c___a
// C_A
// JAVAIDEN
// jAvA -> j_av_a