import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 총 게임 횟수
        int P = Integer.parseInt(st.nextToken()); // 플레이어 정보의 수

        st = new StringTokenizer(br.readLine());
        int W = Integer.parseInt(st.nextToken()); // 이긴 경우 획득 점수
        int L = Integer.parseInt(st.nextToken()); // 졌을 때 떨어지는 점수
        int G = Integer.parseInt(st.nextToken()); // IRON 티어에서 벗어나기 위한 점수

        HashMap<String, String> map = new HashMap<>();
        for (int i = 0; i < P; i++) {
            st = new StringTokenizer(br.readLine());
            String player = st.nextToken();
            String type = st.nextToken();
            map.put(player, type);
        }

        int score = 0;
        for (int i = 0; i < N; i++) {
            if (score >= G) {
                System.out.println("I AM NOT IRONMAN!!");
                return;
            }

            String playWith = br.readLine();
            if (map.get(playWith) != null) {
                String t = map.get(playWith);

                if (t.equals("W")) {
                    score += W;
                } else {
                    if (score - L <= 0) {
                        score = 0;
                    } else {
                        score -= L;
                    }
                }
            } else {
                if (score - L <= 0) {
                    score = 0;
                } else {
                    score -= L;
                }
            }
        }

        System.out.println("I AM IRONMAN!!");
    }
}


// 하지만 해킹을 통하여 알아내지 못한 플레이어와 같이 게임을 하는 경우
// 형동이가 매우 못하기 때문에 같은 팀원이 아무리 잘해도 반드시 진다.

//