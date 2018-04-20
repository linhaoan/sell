package com.linhao.sell.Utils;

import java.util.Random;

public class KeyUtils {

    /**
     * 生成String类主键ID
     * 格式 时间+随机数
     *
     * @return 随机String 主键
     */
    public static synchronized String getUniqueKey() {
        Random random = new Random();
        Integer number = random.nextInt(900000) + 100000;
        return System.currentTimeMillis() + String.valueOf(number);
    }
}
