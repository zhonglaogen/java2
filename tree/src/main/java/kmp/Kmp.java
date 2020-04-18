package kmp;

public class Kmp {
    /**
     * 生成前缀数组
     *
     * @param pattern 比较的串
     * @param prefix  前缀表
     * @param n       表长度
     */
    static void prefixTbale(char[] pattern, int[] prefix, int n) {
//        index位置的串的公共前缀长度len(此时len - 1为当前串index的位置)
        prefix[0] = 0;
        //比较的长度
        int len = 0;
        //开始比较的下标
        int i = 1;
        while (i < n) {
            //比较最后一位和前缀的下一位
            if (pattern[i] == pattern[len]) {
                len++;
                prefix[i] = len;
                i++;
            } else {
                //不一致就kmp找小一位比较
                //当len=0时，就无法再找更小的一位了
                if (len > 0) {
//                    此刻为长度是公共长度-1 的串 的长度
//                    例如串 AAAAB,当b不符合时,会将AAAA分成AAA,AAA(s1,s2),
//                     此刻为了第三个字母和B进行比较,就必须s1的前缀等于s2的后缀(因为s1和s2是相等的,所以s1找到s1的前后缀共串
//                          就找到了s1和s2的前串和后串)当len=0时表示前面没有共串(所以没有办法比较下一位)
                    len = prefix[len - 1];
                    //此时前缀为len也就是0，然后在比较下一个i
                } else {
                    prefix[i] = len;
                    i++;
                }

            }
        }
    }
    //整体将前缀数组后移一位，第一位补-1,
    static void move(int[] prefix, int n) {
        for (int i = n - 1; i > 0; i--) {
//            index当前正在比较的下标,如果比较不相等,要找到当前公共串的位置, v代表index-1长度串的公共长度(用v做下标就是j应该的位置)
            prefix[i] = prefix[i - 1];
        }
        prefix[0] = -1;
    }

    static void kmpSearch(char[] text, char[] pattern) {
        int n = pattern.length;
        int[] prefix = new int[n];
        prefixTbale(pattern, prefix, n);
        move(prefix, n);

        //text[i],pattern[j]
        //len[m]      len[n]
        int i = 0;
        int j = 0;
        int m = text.length;
        while (i < m) {
            if (j == n - 1 && text[i] == pattern[j]) {
                System.out.println("location is:" + (i - j));
                //找到后继续往后匹配
                j = prefix[j];
            }
            //i和j相等，比较下一个元素
            if (text[i] == pattern[j]) {
                i++;
                j++;
            } else {
                //不相等根据kmp通过前缀表移动j的位置
                j = prefix[j];
                //若果j=-1了，就没有这个下表了，不能继续比较了，直接比较表示都不符合
                //继续比较下一个i和下一个j
                if (j == -1) {
                    i++;
                    j++;
                }
            }
        }

    }

    public static void main(String[] args) {
        String str = "ABABCABAA";
        String str1 = "ABABABCABAABABCABAAWQDSAA";
        //{'A', 'B', 'A', 'B', 'C', 'A', 'B', 'A', 'A'};
        char[] pattern = str.toCharArray();
        char[] text=str1.toCharArray();
        kmpSearch(text,pattern);


    }
}
