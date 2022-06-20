import java.util.Arrays;

public class Solution {

    public int solution(int[] P, int[] S) {
        Arrays.sort(S);
        int length = S.length;
        boolean targetAchieved = false;
        int numberOfCars = 0;
        int pointer = 0;
        int currentSum = 0;
        for (int num = length - 1; num >= 0; num--) {
            currentSum = currentSum + S[num];
            while (currentSum > 0) {
                currentSum = currentSum - P[pointer];
                pointer++;
                if (pointer == length) {
                    currentSum = 0;
                    targetAchieved = true;
                }
            }
            numberOfCars++;
            if (targetAchieved) {
                break;
            }
        }

        return numberOfCars;
    }
}
