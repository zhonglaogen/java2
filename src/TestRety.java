/**
 * @Description:
 * @author: zhonglianxi
 * @date: 2020-02-27
 */
public class TestRety {
    public static void main(String[] args) throws Exception {
        retry:
        for (int i = 0; i < 5; i++) {
            System.out.println(" i = " + i);
            for (int j = 10; j < 20; j++) {
                if (j == 15) {
                    continue retry;
                }
                System.out.println(" j = " + j);
            }
        }
    }

}
