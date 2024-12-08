import java.util.*;

class Player {
    int health;
    int combo;
    
    Player (int health, int combo) {
        this.health = health;
        this.combo = combo;
    }
}

class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int answer = 0;
        
        int t = bandage[0];
        int x = bandage[1];
        int y = bandage[2];
        int endTime = attacks[attacks.length - 1][0];
        
        int[] attackArr = new int[endTime + 1];
        for (int[] a : attacks) {
            attackArr[a[0]] = a[1];
        }
        
        
        Player p = new Player(health, 0);
        
        System.out.println(Arrays.toString(attackArr));
        for (int i = 1; i <= endTime; i++) {
            if (p.health <= 0) {
                return -1;
            }
            
            // 몬스터의 공격이 우선
            if (attackArr[i] != 0) {
                // 연속 공격이 초기화
                p.health -= attackArr[i];
                p.combo = 0;
                continue;
            }
            
            // 붕대를 감았을 때 
            // 체력이 health 이하인 경우,
            heal(p, x, health);
            
            p.combo += 1;
            
            // t초 연속으로 붕대를 감았다면
            if (p.combo == t) {
                p.combo = 0;
                heal(p, y, health);
            }
        } 
        
        if (p.health <= 0) {
            return -1;
        } else {
            answer = p.health;
        }

        return answer;
    }
    
    static void heal(Player p, int n, int health) {
        if (p.health + n <= health) {
                p.health = p.health + n;
            } 
        // 체력이 health 초과인 경우.
        else {
            p.health = health;
        }
    }
}

// t초 동안 붕대를 감으며, 1초마다 x만큼의 체력을 회복.
// t초 연속으로 붕대를 감는데 성공하면 y의 체력을 추가 회복

// [t, x, y] = bandage
// 최대 체력 = health
// [공격 시간, 피해량] = attacks