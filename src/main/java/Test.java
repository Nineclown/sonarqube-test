import java.io.*;

/* [system.out.println 은 되도록 사용하지 말자.]
 * [해독하는 프로그램]
 * String 형태로 값을 읽어와서.
 * 어떻게 해독할까?
 * 1. 우선 받은 데이터를 56자리만 유효한 데이터이므로 잘라내야한다.
 *  어떻게 자르지?
 *  시작부터 1을 찾아. 그래서 마지막으로 찾은 것 까지만 기록하고 위치를 반환하면 사이즈가 나온다.
 *  그러면 거기서 56자리만 사용할꺼니까.
 *  1. 마지막 1을 찾는다. 그리고 그 위치를 반환한다.
 * */
/*
 * [BufferedReader와 System.in?]
 * 그러니까 저 객체로 만들게 되면, 사용자의 키보드로 입력을 받든, 파일에서 읽어오든
 * 그 값이 BufferedReader의 버퍼에 저장이 된다. 그리고 BufferedReader의 readLine()이나 read() 같은
 * 메소드는 그 버퍼에서 한줄씩, 또는 한 글자(byte)씩 읽어오게 된다. 다 읽어오기 전에
 * 또 다른 데이터가 입력되면 버퍼의 다음 줄에 계속 Queue 형태로 저장이 된다.
 * ㅈ같다.
 * */

public class Test {
    //  입출력을 위한 instance.
    BufferedReader reader;
    BufferedWriter writer;

    int row, column;
    int i, size;
    int[] decryption;
    StringBuffer totalEncryption;
    StringBuffer encryption;
    StringBuffer temp;
    boolean is_setup;
    int count, iterator;

    private void start() throws IOException {
        reader = new BufferedReader(new InputStreamReader(System.in));   //  사용자로부터 데이터를 입력 받는다.
        writer = new BufferedWriter(new OutputStreamWriter(System.out)); //  입력한 데이터를 출력한다.
        iterator = Integer.parseInt(reader.readLine());
        count = 1;
        int i;
        for (i = 0;i < iterator; i++) {
            run();
        }

        reader.close();
        writer.close();
    }
    private void run() throws IOException {
        String[] s = reader.readLine().split(" ");
        row = Integer.parseInt(s[0]);
        column = Integer.parseInt(s[1]);
        is_setup = false;

        totalEncryption = new StringBuffer(column);
        for (i = 0; i < row - 1; i++) {
            if (!is_setup) {
                if (-1 != (reader.readLine()).indexOf("1")) {
                    totalEncryption.append(reader.readLine());
                    is_setup = true;
                }
            } else {
                reader.readLine();
            }
        }
        for (i = 0; i < column; i++) {
            if ('1' == totalEncryption.charAt(i)) {
                size = i;
            }
        }
        size++; //  끊을 때, size 보다 1크게 해야 원하는 size 만큼 자르기 때문에.
        encryption = new StringBuffer(totalEncryption.substring(size - 56, size));
        //System.out.println("encryption: " + encryption);
        decryptData();
        printResult();
    }

    private void decryptData() {
        decryption = new int[2];
        for (i = 0; i < 8; i++) {
            temp = new StringBuffer(encryption.substring(i * 7, (i + 1) * 7));
            //System.out.println("temp: " + temp);
            if ("0111101".equals(temp.toString())) {
                decryption[i % 2] += 3;
            } else if ("0110001".equals(temp.toString())) {
                decryption[i % 2] += 5;
            } else if ("0111011".equals(temp.toString())) {
                decryption[i % 2] += 7;
            } else if ("0110111".equals(temp.toString())) {
                decryption[i % 2] += 8;
            } else if ("0001101".equals(temp.toString())) {
                decryption[i % 2] += 0;
            } else if ("0001011".equals(temp.toString())) {
                decryption[i % 2] += 9;
            } else if ("0011001".equals(temp.toString())) {
                decryption[i % 2] += 1;
            } else if ("0010011".equals(temp.toString())) {
                decryption[i % 2] += 2;
            } else if ("0100011".equals(temp.toString())) {
                decryption[i % 2] += 4;
            } else if ("0101111".equals(temp.toString())) {
                decryption[i % 2] += 6;
            } else {
                System.out.println("error()\n");
                return;
            }
        }
    }

    private int verifyCode() {
        int result, sum = 0;
        result = decryption[0] * 3 + decryption[1];

        if (0 == result % 10) {
            for (int data : decryption) {
                sum += data;
            }
            return sum;
        } else return 0;
    }

    private void printResult() throws IOException {
        writer.write("#" + count++ + " " + verifyCode() + "\n");
        writer.flush();
    }

    public static void main(String[] args) throws IOException {
        Test m1 = new Test();
        m1.start();
    }
}