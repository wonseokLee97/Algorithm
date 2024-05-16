import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        // 듣도 못한 사람 수
        int N = Integer.parseInt(st.nextToken());
        // 보도 못한 사람 수
        int M = Integer.parseInt(st.nextToken());

        HashMap<String, Integer> map = new HashMap<>();

        List<String> list = new ArrayList<>();


        for (int i = 0; i < N; i++) {
            String d = br.readLine();

            if (map.containsKey(d)) {
                map.put(d, map.get(d) + 1);
            } else {
                map.put(d, 0);
            }
        }

        for (int i = 0; i < M; i++) {
            String b = br.readLine();

            if (map.containsKey(b)) {
                list.add(b);
            }
        }

        Collections.sort(list, new Comparator<String>(){
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });

        System.out.println(list.size());
        for (String s : list) {
            System.out.println(s);
        }
    }
}
