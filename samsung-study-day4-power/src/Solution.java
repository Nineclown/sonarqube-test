import java.io.*;

public class Solution {
    BufferedReader reader;
    BufferedWriter writer;
    int base;
    int tag;
    String[] s;

    public Solution() {
        reader = new BufferedReader(new InputStreamReader(System.in));
        writer = new BufferedWriter(new OutputStreamWriter(System.out));
        tag = 1;
        base = 1;
    }

    private int calcPower(int exp) {
        if (0 == exp)
            return 1;
        if (1 == exp)
            return base;
        else {
            return base * calcPower(exp - 1);
        }
    }

    private void printTag(int exp) throws IOException {
        writer.write("#" + tag + " " + calcPower(exp) + "\n");
        writer.flush();
    }

    private void start() throws IOException {
        tag = Integer.parseInt(reader.readLine());
        s = reader.readLine().split(" ");
        base = Integer.parseInt(s[0]);
        printTag(Integer.parseInt(s[1]));
    }


    public static void main(String[] args) throws IOException {
        Solution sol = new Solution();
        for (int i = 0; i < 10; i++) {
            sol.start();
        }

    }
}
