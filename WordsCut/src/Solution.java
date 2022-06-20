public class Solution {

    public String solution(String message, int K) {
        if (K > message.length()) {
            return message.trim();
        }

        StringBuilder result = new StringBuilder();
        String[] words = message.split(" ");
        int currentRemaining = K;
        for (String word : words) {
            if (word.length() <= currentRemaining) {
                result.append(word);
                result.append(" ");
                currentRemaining--;
            }
            currentRemaining = currentRemaining - word.length();
        }

        return result.toString();
    }
}
