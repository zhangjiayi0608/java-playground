package github.zayn.reflect;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSONObject;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import github.zayn.java8.StringUtil;
import org.apache.commons.lang3.StringUtils;

/**
 * @ClassName ReflactDemo
 * @DESCRIPTION TODO
 * @Author zhangjiayi07
 * @Date 2020/12/24 下午2:58
 **/
public class ReflectDemo2 {

    public static void main(String[] args) {
        String input = "CONSUME_PV_30D";
        String camelCaseOutput = toCamelCase(input);
        System.out.println(camelCaseOutput); // 输出: contentId
    }

    public static String toCamelCase(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }

        StringBuilder camelCaseString = new StringBuilder();
        boolean convertNext = false;

        // 将字符串转换为小写并按下划线分割
        String[] parts = input.toLowerCase().split("_");

        for (String part : parts) {
            if (convertNext) {
                // 将单词的第一个字母转换为大写
                camelCaseString.append(Character.toUpperCase(part.charAt(0)));
                camelCaseString.append(part.substring(1));
            } else {
                camelCaseString.append(part);
                convertNext = true;
            }
        }

        return camelCaseString.toString();
    }

    public static String camelToSnake(String str) {
        // 第一步：在大写字母前面添加下划线
        String regexForUpperCase = "([a-z])([A-Z]+)";
        String replacementForUpperCase = "$1_$2";

        // 第二步：确保数字前面是小写字母，并在它们之间插入下划线
        String regexForNumbers = "([a-z])(\\d+)";
        String replacementForNumbers = "$1_$2";

        // 应用两个正则表达式
        String intermediate = str.replaceAll(regexForUpperCase, replacementForUpperCase);
        String snakeCase = intermediate.replaceAll(regexForNumbers, replacementForNumbers).toLowerCase();

        return snakeCase;
    }

    private static <T> JSONObject buildCondition(T condition) {
        JSONObject conditions = new JSONObject();
        Class<?> clazz = condition.getClass();
        try {
            for (java.lang.reflect.Field field : clazz.getDeclaredFields()) {
                field.setAccessible(true);
                String fieldName = field.getName();
                Object value = field.get(condition);
                String underscoreKey = camelCaseToUnderscore(fieldName);
                conditions.put(underscoreKey, value);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return conditions;
    }

    private static String camelCaseToUnderscore(String fieldName) {

        return fieldName.replaceAll("([a-z])([A-Z])", "$1_$2").replaceAll("(\\d+)([a-zA-Z])", "$1_$2").toLowerCase();
    }
}
