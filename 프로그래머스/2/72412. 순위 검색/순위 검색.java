import java.util.*;

class Solution {
    static int visited[];
    static HashMap<String, List<Integer>> map;
    public int[] solution(String[] info, String[] query) {
        map = new HashMap<>();
        
        for (String i : info) {
            String[] iArr = i.split(" ");
            String l = iArr[0];
            String p = iArr[1];
            String e = iArr[2];
            String f = iArr[3];
            String s = iArr[4];
            
            String[] combArr = new String[] {l, p, e, f, s};
            
            visited = new int[4];
            for (int j = 0; j <= 4; j++) {
                comb(0, 0, j, combArr);
            }
        }
        
        for (String mk : map.keySet()) {
            Collections.sort(map.get(mk), new Comparator<Integer>() {
                @Override
                public int compare(Integer a, Integer b) {
                    return a - b;
                }
            });
        }
        
        int[] result = new int[query.length];
        int idx = 0;
        
        for (String q : query) {
            StringBuilder sb = new StringBuilder();
            String[] sArr = q.split(" and ");
            String l = sArr[0];
            String p = sArr[1];
            String e = sArr[2];
            
            String[] sArr2 = sArr[3].split(" ");
            String f = sArr2[0];
            String score = sArr2[1];
            int target = Integer.parseInt(score);
            
            sb.append(l).append(p).append(e).append(f);
            String key = sb.toString();
            
            // query에 해당되는 info가 없다면
            if (!map.containsKey(key)) {
                result[idx++] = 0;
                continue;
            }
            
            List<Integer> valueList = map.get(key);
            
            int st = 0;
            int ed = valueList.size() - 1;
                        
            
            // score 이상이 몇개 있는가?
            // 그렇다면 score 최소 위치를 이분탐색으로 찾야한다. (lowerBound)
            // 50, 80, 150, 150, 210, 260
            // m       150   
            // t
            
            int ans = 0;
            while (st <= ed) {
                int m = (st + ed) / 2;
                
                // target 보다 작을 때
                if (valueList.get(m) < target) {
                    st = m + 1;
                } 
                // target 보다 같거나 클 때
                else {
                    ed = m - 1; 
                    ans = m;
                }
            }
            
            // System.out.println(valueList);
            // System.out.println(valueList.get(st) + ", " + st);
            // System.out.println(valueList.size() - st);
            result[idx++] = valueList.size() - st;
        }

        
        return result;
    }
    
    static void comb(int start, int cnt, int N, String[] combArr) {
        if (cnt == N) {
            // System.out.println(Arrays.toString(visited));
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 4; i++) {
                // 와일드 카드
                if (visited[i] == 0) {
                    sb.append("-");
                    continue;
                }
                
                sb.append(combArr[i]);
            }
            String comb = sb.toString();
            int score = Integer.parseInt(combArr[4]);
            
            // key가 있다면?
            if (map.containsKey(comb)) {
                map.get(comb).add(score);
            } 
            // key가 없다면
            else {
                map.put(comb, new ArrayList<>());
                map.get(comb).add(score);
            }

            return;
        }
        
        for (int i = start; i < 4; i++) {
            if (visited[i] == 1) {
                continue;
            }
            
            visited[i] = 1;
            comb(i, cnt + 1, N, combArr);
            visited[i] = 0;
        }
    }
}

//  "java backend junior pizza 150",
// - - - - 
// java - - -
// java backend - -
// java backend junior - 
// java backend junior pizza
// - backend - -
// - backend junior - 

// info
// [
//  "java backend junior pizza 150",
//  "python frontend senior chicken 210",
//  "python frontend senior chicken 150",
//  "cpp backend senior pizza 260",
//  "java backend junior chicken 80",
//  "python backend senior chicken 50"
// ]

// 50 80 150 150 210 260

// query
// [
//  "java and backend and junior and pizza 100",
//  "python and frontend and senior and chicken 200",
//  "cpp and - and senior and pizza 250",
//  "- and backend and senior and - 150",
//  "- and - and - and chicken 100",
//  "- and - and - and - 150"
// ]

// 