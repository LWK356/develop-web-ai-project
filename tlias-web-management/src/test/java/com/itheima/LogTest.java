package com.itheima;

import org.junit.jupiter.api.Test;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

public class LogTest {


    private static final Logger log= LoggerFactory.getLogger(LogTest.class);
//LogTest.class 字节码对象
    @Test
    public void testLog(){
//        System.out.println(LocalDateTime.now() + " : 开始计算...");

        log.debug("start");
        int sum = 0;
        int[] nums = {1, 5, 3, 2, 1, 4, 5, 4, 6, 7, 4, 34, 2, 23};
        for (int num : nums) {
            sum += num;
        }

        log.info("result="+sum);
        
//        System.out.println("计算结果为: "+sum);
//        System.out.println(LocalDateTime.now() + "结束计算...");
        log.debug("end");
    }

}
