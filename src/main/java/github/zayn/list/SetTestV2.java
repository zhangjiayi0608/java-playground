package github.zayn.list;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

/**
 * @author zhangjiayi
 * @Description
 * @date 2024/7/8 11:21
 */
public class SetTestV2 {

    public static Set<String> set =Sets.newHashSet();


    public static void main(String[] args) {
        Set<Long> validRuleIdSet = Sets.newHashSet(280L,282L);
        Set<Long> lowPriceSceneRuleIdSet =Sets.newHashSet(280L,282L);
        Set<Long> difference = Sets.newHashSet(validRuleIdSet);
        difference.removeAll(lowPriceSceneRuleIdSet);
        System.out.println(difference);
    }
}
