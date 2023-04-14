package com.birariro.vkestrel.etc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SpringTest {

    @Value("${setting.document.max-sizetest:40}")
    private int key;

    @Value("${setting.schedule.cron:0 0 9 * * ?}")
    private String cron;

    @Test
    public void key(){
        System.out.println("key = " + key);
    }

    @Test
    public void cron(){
        System.out.println("cron = " + cron);
    }

}
