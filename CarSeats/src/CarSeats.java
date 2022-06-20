


public class CarSeats {

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] P = new int[]{1,4,1};
        int[] S = new int[]{1,5,1};
        System.out.println(solution.solution(P,S));

        int[] P1 = new int[]{4,4,2,4};
        int[] S1 = new int[]{5,5,2,5};
        System.out.println(solution.solution(P1,S1));

        int[] P2 = new int[]{2,3,4,2};
        int[] S2 = new int[]{2,5,7,2};
        System.out.println(solution.solution(P2,S2));
    }
}
