package github.zayn.reflect;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @ClassName ReceiveEventNdimCondition
 * @DESCRIPTION TODO
 * @Author zhangjiayi07
 * @Date 2023/4/20 2:44 PM
 **/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReceiveEventNdimCondition4LiveInfrastructure extends ReceiveEventNdimBaseCondition {
    //直播消耗
    private Long costLiveCost;
    //直播时长
    private Double costLiveDuration;
}
