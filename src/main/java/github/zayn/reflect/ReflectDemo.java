package github.zayn.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.google.common.base.Splitter;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import org.aspectj.weaver.SourceContextImpl;
import org.aspectj.weaver.ast.Var;
import org.springframework.util.StringUtils;

/**
 * @ClassName ReflactDemo
 * @DESCRIPTION TODO
 * @Author zhangjiayi07
 * @Date 2020/12/24 下午2:58
 **/
public class ReflectDemo {
    protected static Map<String, String> buildDimen(Object param) {
        Gson gson = new Gson();
        if (param == null) {
            return Maps.newHashMap();
        }
        Class beanClass = param.getClass();
        Field[] fields = beanClass.getDeclaredFields();

        Map<String, String> result = Maps.newHashMap();
        for (Field field : fields) {
            field.setAccessible(true);

            try {
                if (field.get(param) != null) {
                    if (field.getType() == String.class) {
                        String value = (String)field.get(param);
                        if (value != null) {
                            result.put(field.getName(), value);
                        }
                    } else {
                        result.put(field.getName(), gson.toJson(field.get(param)));
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return result;
    }

    @SuppressWarnings("checkstyle:MagicNumber")
    //    public static void main(String[] args) {
    //        List<What> dataList = new ArrayList<>();
    //        for (int i = 0; i < 10; i++) {
    //            What what = new What();
    //            what.setHeight(i);
    //            what.setWeight(i);
    //            what.setAge(i);
    //            what.setName("name" + i);
    //            dataList.add(what);
    //        }
    //        List<ExcelColumnInfo> excelColumnInfos = new ArrayList<>();
    //        ExcelColumnInfo excelColumnInfo1 = new ExcelColumnInfo();
    //        excelColumnInfo1.setColumnChineseName("身高");
    //        excelColumnInfo1.setColumnEnglishName("height");
    //        excelColumnInfos.add(excelColumnInfo1);
    //        ExcelColumnInfo excelColumnInfo2 = new ExcelColumnInfo();
    //        excelColumnInfo2.setColumnChineseName("体重");
    //        excelColumnInfo2.setColumnEnglishName("weight");
    //        excelColumnInfos.add(excelColumnInfo2);
    //        ExcelColumnInfo excelColumnInfo3 = new ExcelColumnInfo();
    //        excelColumnInfo3.setColumnChineseName("名字");
    //        excelColumnInfo3.setColumnEnglishName("name");
    //        excelColumnInfos.add(excelColumnInfo3);
    //        List<List<String>> lists = buildDynamicData(excelColumnInfos, dataList);
    //        System.out.println(lists);
    //    }

    //    public static void main(String[] args) throws IllegalAccessException {
    //        What what = new What();
    //        what.setHeight(186);
    //        what.setWeight(100);
    //        what.setAge(18);
    //        what.setName("zhangjiayi");
    //        List<String> strings = buildParams(what);
    //
    //        String str = "${duration}天直播次数满${target}次，单个时长满${costLiveDuration}小时，单次直播花费满${costLiveCost}元";
    //        ReceiveEventNdimCondition4LiveInfrastructure condition =
    //                new ReceiveEventNdimCondition4LiveInfrastructure();
    //        condition.setCostLiveCost(10L);
    //        condition.setCostLiveDuration(100D);
    //        String s = temTaskDeal(str, condition);
    //        System.out.println(s);
    //    }

    public static String temTaskDeal(String contextValue, ReceiveEventNdimBaseCondition condition) {
        try {
            contextValue = contextValue
                .replace("${duration}", "1")
                .replace("${target}", "5");
            Map<String, String> conditionMap = getCondition(condition);
            for (String name : conditionMap.keySet()) {
                String targetStr = "${" + name + "}";
                contextValue = contextValue.replace(targetStr, conditionMap.get(name));
            }
            return contextValue;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contextValue;
    }

    private static Map<String, String> getCondition(ReceiveEventNdimBaseCondition condition)
        throws IllegalAccessException {
        Map<String, String> conditionMap = new HashMap<>();
        Field[] fields = condition.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Object o = field.get(condition);
            conditionMap.put(field.getName(), o.toString());
        }
        return conditionMap;
    }

    private static List<String> buildParams(Base condition) throws IllegalAccessException {
        List<String> strings = new ArrayList<>();
        Field[] fields = condition.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            strings.add(field.getName());
        }
        return strings;
    }

    private static List<List<String>> buildDynamicData
        (List<ExcelColumnInfo> excelColumnInfos,
            List<?> dataList) {
        List<List<String>> rowDataList = new ArrayList<>();
        for (Object data : dataList) {
            List<String> eachRowData = new ArrayList<>();
            for (ExcelColumnInfo excelColumnInfo : excelColumnInfos) {
                try {
                    Field field = getField(excelColumnInfo.getColumnEnglishName(), data.getClass());
                    field.setAccessible(true);
                    if (field.getType() == Long.class || field.getType() == Integer.class) {
                        eachRowData.add(String.valueOf(field.get(data)));
                    } else {
                        eachRowData.add((String)(field.get(data)));
                    }
                } catch (Exception e) {
                    System.out.println("err!");
                }
            }
            rowDataList.add(eachRowData);
        }
        return rowDataList;
    }

    public static Field getField(String tar, Class clazz) {
        String error = null;
        Field field = null;
        while (clazz != null) {
            try {
                field = clazz.getDeclaredField(tar);
                error = null;
                break;
            } catch (Exception e) {
                clazz = clazz.getSuperclass();
                error = e.getMessage();
            }
        }
        if (error != null || field == null) {
            throw new RuntimeException("无法获取源字段:" + tar);
        }
        return field;
    }

    public static void main(String[] args) {
        Set<Long> ignoreUserSet = new HashSet<>();
        String ignoreUsers = "11，,22，,33,2216936455462";
        List<String> stringList = Arrays.asList(ignoreUsers.split(","));
        List<Long> numberList = stringList.stream()
            .filter(s -> s.matches("\\d+")).map(Long::parseLong)
            .collect(Collectors.toList());
        System.out.println(numberList);

    }
}
