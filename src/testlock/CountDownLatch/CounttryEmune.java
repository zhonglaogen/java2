package testlock.CountDownLatch;

/**
 *枚举充当简易的数据库
 */
public enum CounttryEmune {
    ONE(1, "齐"), TWO(2, "楚"), THREE(3, "燕"), FOUR(4, "韩"), FIEVE(5, "赵"), SIX(6, "魏");

    private String retMessage;
    private Integer retCode;

    CounttryEmune(Integer retCode, String retMessage) {
        this.retCode = retCode;
        this.retMessage = retMessage;
    }

    public static CounttryEmune for_each_countryEmun(int index) {
        CounttryEmune[] values = CounttryEmune.values();
        for (CounttryEmune emune : values) {
            if (index == emune.getRetCode()) {
                return emune;
            }
        }
        //不能为空，线程名不能为空
        return null;


    }


    public Integer getRetCode() {
        return retCode;
    }

    public String getRetMessage() {
        return retMessage;
    }


}
