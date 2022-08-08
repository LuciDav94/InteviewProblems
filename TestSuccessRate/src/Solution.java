import java.util.HashMap;
import java.util.Map;

public class Solution {

    public int solution(String[] T, String[] R) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < T.length; i++) {
            String testEntry =
                    T[i].split("(?<=\\d)(?=\\D)")[0];
            if (!R[i].equals("OK")) {
                map.put(testEntry, 1);
            } else if (map.get(testEntry) == null) {
                map.put(testEntry, 0);
            }
        }

        int okFounds = 0;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 0) {
                okFounds++;
            }
        }
        int testCases = map.size();
        return (okFounds * 100 / testCases);
    }
}
