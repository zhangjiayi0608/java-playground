package github.zayn.list;

import java.util.List;
import java.util.Map;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhangjiayi
 * @Description
 * @date 2024/7/8 11:23
 */
@Data
@NoArgsConstructor
public class TcpSceneRule {
    /**
     * 场景规则ID
     */
    private long tcpSceneRuleId;

    /**
     * 跟踪&计佣优先级
     */
    private int priority;
    /**
     * 跟踪有效期(毫秒)
     */
    private long traceDurationInMillis;

    /**
     * 规则影响范围: 跟踪、计费 { SceneRuleScopeEnum}
     */
    private Set<Integer> sceneRuleScopeSet;

    /**
     * tcp子业务场景: { BusinessProject}
     */
    private Integer bizType;




    /**
     * 计费相关配置
     */
    private RatingConfigGroup ratingConfigGroup;


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RatingConfigGroup {
        /**
         * 场景规则扣佣类型, { SceneRuleTypeEnum}
         */
        private Integer sceneRuleType;

        /**
         * 是否依赖计划标
         * 只有值为true才会使用推广标映射配置来筛选场景策略和规则
         */
        private Boolean needOrderTag;

        /**
         * 推广标和订单标的配置映射
         *
         * map的key:planType
         * map的value:推广计划标
         */
        private Map<Integer, List<String>> planTagMap;

        /**
         * 佣金计佣范围 0:单品交易结算, 1:同卖家交易结算 { DealScopeEnum}
         */
        private int dealScope;

        /**
         * 仲裁类型, { ArbitrateCenterEnum}
         */
        private Integer arbitrateCenter;

        /**
         * 是否需要分佣，{ DivideCommissionEnum}
         */
        private Integer divideCommission;

        /**
         * 是否自动同步账房, { PayCenterTypeEnum}
         */
        private Integer payCenterType;

        /**
         * 扣佣单结算模式 1:自营， 2:代收代付
         */
        private String settleMode;
    }

}
