import java.io.*;
/*[문제정의]
 * 피보나치 함수에 대해 이해하고, fib(0), fib(1)이 호출될 때마다 횟수를 기록한다.
 * 0 1 형식으로 호출된 숫자를 출력하는 프로그램을 작성하라.
 * 임의의 정수 N은 40이하의 크기이다.
 * */

/*[해결방법]
 * 피보나치 함수를 정의하고, 0과 1이 호출 될 경우 카운팅을 할 클래스 내 변수 2개를 선언한다.
 * 출력을 위한 클래스 내의 두 변수를 출력하는 메소드를 정의한다.
 * -------------------------------------------------------------------------
 * 위의 방식으로 하면 실행속도가 현저히 느려진다. 최대한 실행속도를 높이는 방향으로 방향을 바꾼다.
 * */

/*[해결하는데 처음 사용해 본 것]
 * BufferedReader
 * BufferedWriter
 * */

/*[추가 내용 2018.03.27. PM7:32]
 * 실행시간이 오버되어서 틀림.
 * 해결 방법.
 * 1. 호출되는 클래스, 메소드의 횟수를 줄인다.
 * 2. static dynamic. 동적보단 정적이 실행시간을 단축할 수 있다.
 *
 *[추가 내용 2018.03.27. PM9:50]
 * 다른 부수적인 함수들을 호출하는 시간을 줄이거나, 정적으로 코드를 변경시켜도
 * 문제가 풀리지 않는다...
 * 그래서 fib()를 다시 살펴보기로 했다.
 * 인터넷 검색 결과. 동적 프로그래밍(동적 계획법) 방식이라고 한다. 실행시간을 최대한 줄이는 방법.
 * */


public class Main {
    static int zero_count = 0;
    static int one_count = 0;

    static int fibonacci(int n) {

        if (0 == n) {
            return 0;
        } else if (1 == n) {
            return 1;
        } else {
            return fibonacci(n - 1) + fibonacci(n - 2);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int count;
        count = Integer.parseInt(reader.readLine());

        while (count > 0) {
            count--;
            Main.fibonacci(Integer.parseInt(reader.readLine()));
            writer.write(zero_count + " " + one_count);
            writer.flush();
            zero_count = 0;
            one_count = 0;
        }
        reader.close();
        writer.close();
    }
}
