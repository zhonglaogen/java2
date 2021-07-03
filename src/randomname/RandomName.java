package randomname;


import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.*;

/**
 * RandomName
 *
 * @author zhonglx
 * @version 1.0.0 2021/7/2 15:16
 */
public class RandomName {
    
    List<String> list1 = new ArrayList<>();
    List<String> list2 = new ArrayList<>();

    public List<String> getList1(String name) throws IOException {
        List<String> cur  = new ArrayList<>();
        File file = new File(name);
        RandomAccessFile rs = new RandomAccessFile(file, "rw");
        String s = new String(rs.readLine().getBytes("ISO-8859-1"), "utf-8");
        while (s != null) {
            if (s != null && !"".equals(s)) {
                cur.add(s.replaceAll("[a-z,A-Z]|[//(]{1}.*[//)]{1}", "").replaceAll("", ""));
            }
            System.out.println(cur.get(cur.size() - 1));
            String curr = rs.readLine();
            if (curr != null) {
                s = new String(curr.getBytes("ISO-8859-1"), "utf-8");
            } else {
                s = null;
            }
        }
        rs.close();

        return cur;
    }

    public List<String> getList2() {
        return list2;
    }

    public static void main(String[] args) throws IOException {
        Set<String> res = new HashSet<>();
        RandomName rn = new RandomName();
        rn.list1 = rn.getList1("D:\\name\\a.txt");
        rn.list1.addAll(rn.getList1("D:\\name\\b.txt"));
        String pre = "俏皮 敏捷 乐观 调皮 爽脆 爽朗 豪爽 正直 直率 直爽 干脆 直言 爽直 刚直 憨直 率直 耿直 公正 公道 公平 公允 正派 爽快 简捷 开阔 豁达 明朗 率真";
        String[] s = pre.split(" ");
        rn.list2 = Arrays.asList(s);
        System.out.println();
        Random r = new Random();
        int count = 0;
        while (count < 500) {
            int index1 = r.nextInt(rn.list1.size());
            int index2 = r.nextInt(rn.list2.size());
            String s1 = rn.list1.get(index1);
            String s2 = rn.list2.get(index2);
            res.add(s2 + "的" + s1);
            count++;
        }
        System.out.println(res);
        RandomAccessFile rs = new RandomAccessFile("D:\\name\\c.txt", "rw");
        for (String re : res) {
            re += "\r\n";
            rs.write(re.getBytes());
        }
        rs.close();

    }
}
