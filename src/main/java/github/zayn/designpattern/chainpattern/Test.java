package github.zayn.designpattern.chainpattern;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import github.zayn.conf.SpringConfig;
import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName Test
 * @DESCRIPTION TODO
 * @Author zhangjiayi07
 * @Date 2021/7/29 4:00 下午
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringConfig.class})
@Slf4j
public class Test {
    private static ExecutorService pool = new ThreadPoolExecutor(10, 18,
            5000L, TimeUnit.MILLISECONDS,
            new SynchronousQueue<>());

    @Autowired
    ChainEngine chainEngine;

    @org.junit.Test
    public void test() {
        CompletableFuture.runAsync(() -> {
            for (int i = 0; i < 1000; i++) {
                InputDTO inputDTO = new InputDTO();
                inputDTO.setCount(i * 10);
                Set<String> promotionTypes = new HashSet<>();
                promotionTypes.add("FIRST");
                promotionTypes.add("SECOND");
                inputDTO.setPromotionTypes(promotionTypes);
                long l = chainEngine.execute(inputDTO);
                System.out.println("i is " + i + "price is " + l);
            }
        }, pool);
    }
}
