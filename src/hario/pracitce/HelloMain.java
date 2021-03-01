package hario.pracitce;


import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class HelloMain {
    private final static String[] hexDigits = { "0", "1", "2", "3", "4",
            "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

    public abstract static interface ABClass {
        abstract void test();
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {

//        List<Map<String, Integer>> l = new ArrayList<>();
//        int sum = l.stream().mapToInt(x -> {
//            Integer xx = x.getOrDefault("xx", 0);
//            return xx;
//        }).sum();
//        System.out.println(sum);
//
//        Calendar c = Calendar.getInstance();
//        c.set(Calendar.DATE, -3);
//        System.out.println(c.getTime().getTime());
//        System.out.println(System.currentTimeMillis());

        int ss = (int) (System.currentTimeMillis() / 1000L);
        StringBuilder sb = new StringBuilder();
//        sb.append("game_key").append("=").append("1007876");
//        sb.append("time").append("=").append(ss);
//        sb.append("brGVuZnghEw$%hOu");


//        sb.append(ss)
//                .append(1)
//                .append(1007876)
////                .append("gmmx_android_20001")
//                .append(1751768697)
//                .append("h!bZNj-6dlfOU1R5eBwG9X4iTPYSkp0a");

        // 详细信息
        sb.append(1)
                .append(1007876)
                .append(ss)
                .append(1763634458)
                .append("gmmx_android_20001")
                .append(175933928735301632L)
                .append(0)
                .append("h!bZNj-6dlfOU1R5eBwG9X4iTPYSkp0a");

//        sb.append("h!bZNj-6dlfOU1R5eBwG9X4iTPYSkp0a")
//                .append(1)
//                .append(1007876)
//                .append("gmmx_android_20001")
//                .append("hello")
//                .append(175933927810324480L)
//                .append("1782498588")
//                .append("2FVMBBB5CBGK")
//                .append(ss)
//                .append("hello");


        Calendar c = Calendar.getInstance();
        c.set(Calendar.DATE, -1);

        System.out.println("__________" + c.getTime().getTime() / 1000L);
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


        Date date = new Date(1582277944 * 1000L);

        String format = fmt.format(date);

        System.out.println(format);
        Date end = new Date(1597980234 * 1000L);
        String ends = fmt.format(end);
        System.out.println(format +"-----" + ends);


        System.out.println(ss);

        String s = MD5(sb);
        System.out.println(s);

        System.out.println("123456".toCharArray()[1]);

//        System.out.println(System.currentTimeMillis() / 1000L);
       String content = "";

//       int aa = (int) 175933928668178432L;
//        System.out.println(
//                aa
//        );

        int a = 10 / 5 * 100;
        System.out.println(Math.round(12.221133));

        BigDecimal bg = new BigDecimal(1.123456);

        double v = bg.setScale(4, BigDecimal.ROUND_HALF_DOWN).doubleValue();
        System.out.println(v);

        List<Integer> res = new ArrayList<>();
        res.add(0);
        res.add(1);
        res.add(2);
        res.add(3);
        res.set(1, 99);

        System.out.println(res);
//        return (n + a - 1) &^ (a - 1);

        


    }

    public static String MD5(StringBuilder sb) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("MD5");
        try {
            digest.update(sb.toString().getBytes());
            byte[] digest1 = digest.digest();

            return byteArrayToHexString(digest1);
        } catch (Exception e) {}
        return "";
    }

    private static String byteArrayToHexString(byte[] b) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0)
            n = 256 + n;
        int d1 = n / 16;
        int d2 = n % 16;

        return hexDigits[d1] + hexDigits[d2];
    }



    public String test(int i, String k){
        return "xx";
    }

//#define N 6
//
//    void RecurMatrixChain(int* p,int** m){
//        for(int r=2;r<N+1;r++){
//            for(int i=1;i<=N-r+1;i++){
//                int j=i+r-1;
//                m[i][j]=m[i][i]+m[i+1][j]+p[i-1]*p[i]*p[j];                  //先设个初始值，在i和i+1间分开
//                for(int k=i+1;k<j;k++){
//                    int t=m[i][k]+m[k+1][j]+p[i-1]*p[k]*p[j];
//                    if(t<m[i][j]){
//                        m[i][j]=t;
//                    }
//                }
//            }
//        }
//    }
//
//    int main(){
//        int p[N+1]={30,35,15,5,10,20,25};
//
//        int **m=new int*[N+1];       //m数组记录i到j的最优（最少）计算次数
//        for(int i=0;i<N+1;i++){
//            m[i]=new int[N+1];
//        }
//        for(int i=1;i<N+1;i++){
//            m[i][i]=0;
//        }
//        RecurMatrixChain(p,m);
//        printf("少次数%d\n",m[1][N]);
//        return 0;
//    }
    /*
     *
     *
package main

import (
	"fmt"
)

func round(n, a uintptr) uintptr {
	return (n + a - 1) &^ (a - 1)
}

func main() {
	fmt.Println(round(3, 8))
}
打印结果：

8
 分析，如上述代码，假设有一块内存要求按8字节对齐，现在已经分配的偏移地址是3，
* 那么下一个元素内存分配的偏移地址就是8。这个在golang源码中经常出现。
*/

}
