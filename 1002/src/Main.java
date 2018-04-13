import java.util.Scanner;

/*[문제정의]
 * 두점과 임의의 포인트가 주어진다. 각 점에서 포인트까지 거리 또한 주어진다.
 * 이 정보를 입력으호 하는 함수를 만들어내라. 출력 값은 생성가능한 포인트의 수.
 * 생성가능한 포인트의 숫자가 무한히 많을 경우, -1을 출력.
 * */
/*[해결방법]
 * 각 점에서 거리가 r인 모든 점을 좌표평면에 그린다.
 * 그러면 2개의 원이 생기고, 원 끼리 겹치는 부분이 총 3가지 경우가 나오게 된다.
 * 하나도 안겹치거나, 한점에서 만나거나 두 점에서 만나거나.
 * 두 원이 만나는 조건을 이용해 해결하려고 한다.
 * distance와 반지름의 관계를 통해 두 원의 접점 관계를 알 수 있다.
 */
/*[해결하는데 처음 사용해 본 것]
 * math class.
 * */
public class Main {
    public static void main(String[] args) {
        //todo 사용할 클래스, 변수 선언.
        Scanner scanner = new Scanner(System.in);
        int count; //수행할 횟수.
        int x1, x2, y1, y2, r1, r2;
        double distance;
        int pvalue; //반재름의 합
        int mvalue; //반지름의 차.

        count = scanner.nextInt();
        //todo 반복적으로 수행되어야 함.
        while (count > 0) {
            count--;
            //todo 입력을 받는다.
            x1 = scanner.nextInt();
            y1 = scanner.nextInt();
            r1 = scanner.nextInt();
            x2 = scanner.nextInt();
            y2 = scanner.nextInt();
            r2 = scanner.nextInt();

            //todo 사용할 변수들을 초기화 해준다.
            pvalue = r1 + r2;
            mvalue = Math.abs(r1 - r2);
            distance = Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2));

            //todo 두 원이 같은 점에 있을 때,
            if ((x1 == x2) && (y1 == y2)) {
                if (r1 == r2)
                    System.out.println("-1");
                else
                    System.out.println("0");
            }
            //todo 두 원이 다른 점에 있을 때.
            else {
                if ((distance > mvalue) && (distance < pvalue)) {
                    //System.out.println("distance: " + distance + "\nmvalue: " + mvalue + "\npvalue: " + pvalue);
                    System.out.println("2");
                } else if ((distance == pvalue) || (distance == mvalue)) {
                    System.out.println("1");
                } else
                    System.out.println("0");
            }
        }

    }
}
