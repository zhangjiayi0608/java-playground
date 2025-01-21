package github.zayn.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import github.zayn.list.TcpSceneRule.RatingConfigGroup;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

/**
 * @author zhangjiayi
 * @Description
 * @date 2024/7/8 11:21
 */
public class SetTest {

    public static Map<String, String> orderTagToPromotionConfig = Maps.newHashMap();

    static {
        orderTagToPromotionConfig.put("plmf", "promotion:TXMF-74159199822;_F_live_mft:2");
        orderTagToPromotionConfig.put("bybt", "saleInfo:xbcjms");
    }

    public static void main(String[] args) {
        Map<String, String> attribute = Maps.newHashMap();
        Map<String, String> result = Maps.newHashMap();
        attribute.put("promotion", "TXMF-74159199822");
        attribute.put("_F_live_mft", "2");
        attribute.put("saleInfo", "xbcjms@127,100002321312");
        convertOrderTag(attribute, result);
        System.out.println(result);
    }

    private static void convertOrderTag(Map<String, String> attribute, Map<String, String> result) {
        Set<String> promotionTagSet = Sets.newHashSet();

        for (String promotionTag : orderTagToPromotionConfig.keySet()) {
            String orderTagConfig = orderTagToPromotionConfig.get(promotionTag);

            // 1.解析配置的orderTagConfig

            List<String> orderTagConfigList = Arrays.stream(StringUtils.split(orderTagConfig, ";")).collect(
                Collectors.toList());
            for (String orderTagConfigItem : orderTagConfigList) {
                List<String> orderTagConfigItemList = Arrays.stream(StringUtils.split(orderTagConfigItem, ":")).collect(
                    Collectors.toList());
                if (CollectionUtils.isEmpty(orderTagConfigItemList) || orderTagConfigItemList.size() < 2) {
                    continue;
                }
                String orderTagConfigKey = orderTagConfigItemList.get(0);
                String orderTagConfigStr = orderTagConfigItemList.get(1);
                Set<String> orderTagConfigSet = Arrays.stream(StringUtils.split(orderTagConfigStr, ","))
                    .collect(Collectors.toSet());
                // 2.解析订单上面的orderTag
                String orderTagStr = attribute.get(orderTagConfigKey);
                if (StringUtils.isBlank(orderTagStr)) {
                    continue;
                }
                Set<String> orderTagSet = Arrays.stream(StringUtils.split(orderTagStr, ","))
                    .collect(Collectors.toSet());
                for (String orderTag : orderTagSet) {
                    for (String key : orderTagConfigSet) {
                        if (orderTag.startsWith(key)) {
                            promotionTagSet.add(promotionTag);
                        }
                    }
                }
            }
        }

        result.put("PROMOTION_TAG", StringUtils.join(promotionTagSet, ","));
    }

}
