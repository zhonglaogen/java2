package facemath;

/**
 * @Description: kmp算法
 * @author: zhonglianxi
 * @date: 2020-03-01
 */
public class Kmp {


    public static void main(String[] args) {
        String str = "ABABCABAA";
        String str1 = "ABABABCABAABABCABAAWQDSAA";
        //{'A', 'B', 'A', 'B', 'C', 'A', 'B', 'A', 'A'};
        char[] pattern = str.toCharArray();
        char[] text = str1.toCharArray();
        kmpSearch(text, pattern);


    }

    private static void kmpSearch(char[] text, char[] pattern) {
        int length = pattern.length;
        int[] prex = new int[length];
        construchtPreix(pattern, prex, length);


        //前缀数组偏移，省去了kmp匹配时寻找公共前缀-1的操作,找法就是比较不同时，
        // 通过前缀下标为len的值，可以替代上文的len-1操作，拿到prex【len】的值，为公共前缀的长度
//        又由于数组从0开始的原因，下一个比较的位置就是prex[len]的值
        move(prex);

//        开始寻找
        int i = 0;
        int j = 0;
        int tLen = text.length;

        while (j < tLen){
            if (i == length -1){
                System.out.println("有,开始的位置是" + (j - i));
                i = prex[i];
            }

            if (pattern[i] == text[j]){
                i++;
                j++;
            }else {
                i = prex[i];
                if (i == -1){
//                    表示当前位置为单个的字母，也就是len为0，比较了两个字母不一样，不存在前后缀，直接跳过
                    i++;
                    j++;
                }

            }



        }
    }

    private static void move(int[] prex) {
        int length = prex.length - 1;
        for (int i = length; i > 0; i--) {
            prex[i] = prex[i - 1];

        }
        prex[0] = -1;
    }


    /**
     * 构建前缀数组，pubLen的长度不合适了，就要根据此长度来找，kmp找法，
     * 通过两个相同串，来找最大公共串，找法为相同串
     * 因为“两个串相同”，只需要找其中单独一个串的最大前后缀
     *
     * @param pattern
     * @param prex
     * @param length
     */
    private static void construchtPreix(char[] pattern, int[] prex, int length) {
        int pubLen = 0;
        int index = 1;
        while (index < length) {
            if (pattern[index] == pattern[pubLen]) {
                pubLen++;
                prex[index] = pubLen;
                index++;
            } else {
                if (pubLen > 0) {
                    pubLen = prex[pubLen - 1];
                } else {
                    prex[index] = pubLen;
                    index++;
                }

            }


        }
    }
}

