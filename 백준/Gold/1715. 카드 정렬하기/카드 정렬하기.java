import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });

        for (int i = 0; i < N; i++) {
            int card = Integer.parseInt(br.readLine());
            pq.offer(card);
        }

        int cnt = 0;
        int total = 0;
        int sum = 0;

        while (!pq.isEmpty()) {
            Integer card = pq.poll();
            cnt++;

            total += card;

            if (cnt % 2 == 0) {
                sum += total;
//                System.out.println(total + " 집어넣는다!");
                pq.offer(total);
                total = 0;
            }

//            System.out.println(card + ", " + total + ", " + cnt);

        }

        System.out.println(sum);
    }
}
