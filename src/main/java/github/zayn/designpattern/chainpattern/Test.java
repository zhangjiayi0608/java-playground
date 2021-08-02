package github.zayn.designpattern.chainpattern;

import java.util.HashSet;
import java.util.Set;

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

    @Autowired
    ChainEngine chainEngine;

    @org.junit.Test
    public void test() {
        InputDTO inputDTO = new InputDTO();
        inputDTO.setCount(100);
        Set<String> promotionTypes = new HashSet<>();
        promotionTypes.add("FIRST");
        promotionTypes.add("SECOND");
        inputDTO.setPromotionTypes(promotionTypes);
        long l = chainEngine.execute(inputDTO);
        System.out.println(l);
    }
}
