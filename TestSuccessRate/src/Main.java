public class Main {

    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] T = new String[]{"test1a", "test2", "test1b", "test1c", "test3",
                "test1d", "test4", "test5", "test6"};
        String[] R = new String[]{"Wrong answer", "OK", "Runtime error", "OK",
                "Time limit exceeded", "OK", "OK", "OK", "OK"};

        System.out.println(solution.solution(T, R));
    }
}
