import java.util.*;

class Solution {
    static int n, visited[], dice[][], ans[], maxWin;
    static List<Integer> aList, bList;
    
    public int[] solution(int[][] dice) {
        n = dice.length;
        maxWin = Integer.MIN_VALUE;
        this.dice = dice;
        visited = new int[n];
        ans = new int[n / 2];
        
        comb(0, 0);
        
        return ans;
    }
    
    static void comb(int start, int cnt) {
        if (cnt == n / 2) {
            // System.out.println(Arrays.toString(visited));
            int[] arrA = new int[n / 2];
            int[] arrB = new int[n / 2];
            int idxA = 0;
            int idxB = 0;
            aList = new ArrayList<>();
            bList = new ArrayList<>();
            
            for (int i = 0; i < n; i++) {
                if (visited[i] == 0) {
                    arrA[idxA++] = i;
                } else {
                    arrB[idxB++] = i;
                }
            }
            
            play(arrA, 0, 0, 0);
            play(arrB, 1, 0, 0);

            // 결과값 정렬
            Collections.sort(aList);
            Collections.sort(bList);
            
            // aList보다 작은 bList의 원소값을 찾을거임. A가 이겨야하기 때문에
            // A: [3] 5 7 9 ...
            // B: 1 2 3 4 ...
            
            // 3을 기준으로 3보다 작은 친구들의 개수를 구해야됨. 따라서. 3보다 큰 숫자중 최소값! (Lower Bound) , s가 기준
            int win = 0;
            for (int i = 0; i < aList.size(); i++) {
                // b의 idx기준
                int s = 0;
                int e = bList.size() - 1;
                
                while (s <= e) {
                    int m = (s + e) / 2;
                    
                    if (aList.get(i) > bList.get(m)) {
                        s = m + 1;
                    } 
                    // aList의 기준보다 크거나 같으면.. 
                    else {
                        e = m - 1;
                    }
                }
                
                win += s;
            }
            
            // System.out.println(Arrays.toString(arrA) + ", " + win);
            
            if (maxWin < win) {
                maxWin = win;
               for (int i = 0; i < n / 2; i++) {
                    ans[i] = arrA[i] + 1;
                } 
            }
            
            
            return;
        }
        
        for (int i = start; i < n; i++) {
            if (visited[i] == 1) {
                continue;
            }
            
            visited[i] = 1;
            comb(i, cnt + 1);
            visited[i] = 0;
        }
    }
    
    // total -> 주사위의 수 합
    static void play(int[] arr, int type, int cnt, int total) {
        // n/2개 만큼 주사위 수를 골라야함.
        if (cnt == n / 2) {
            // A
            if (type == 0) {
                aList.add(total);
            }
            // B
            else {
                bList.add(total);
            }
            
            return;
        }
        
        for (int i = 0; i < 6; i++) {
            play(arr, type, cnt + 1, total + dice[arr[cnt]][i]);
        }
    }
}

// [1, 2, 3, 4, 5, 6],
// [3, 3, 3, 3, 4, 4]
// 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 6, 6, 6, 6, 6, 6, 7, 7, ...

// dice의 최대길이 10
// A, B가 dice의 반을 가져가서 굴린다.
// 10C2 

// 10*9 / 1*2