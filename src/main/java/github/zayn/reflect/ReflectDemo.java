package github.zayn.reflect;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;
import com.google.gson.Gson;

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
                        String value = (String) field.get(param);
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
    public static void main(String[] args) {
        List<What> dataList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            What what = new What();
            what.setHeight(i);
            what.setWeight(i);
            what.setAge(i);
            what.setName("name" + i);
            dataList.add(what);
        }
        List<ExcelColumnInfo> excelColumnInfos = new ArrayList<>();
        ExcelColumnInfo excelColumnInfo1 = new ExcelColumnInfo();
        excelColumnInfo1.setColumnChineseName("身高");
        excelColumnInfo1.setColumnEnglishName("height");
        excelColumnInfos.add(excelColumnInfo1);
        ExcelColumnInfo excelColumnInfo2 = new ExcelColumnInfo();
        excelColumnInfo2.setColumnChineseName("体重");
        excelColumnInfo2.setColumnEnglishName("weight");
        excelColumnInfos.add(excelColumnInfo2);
        List<List<String>> lists = buildDynamicData(excelColumnInfos, dataList);
        System.out.println(lists);
    }

    private static List<List<String>> buildDynamicData(List<ExcelColumnInfo> excelColumnInfos,
            List<?> dataList) {
        List<List<String>> rowDataList = new ArrayList<>();
        for (Object data : dataList) {
            List<String> eachRowData = new ArrayList<>();
            for (ExcelColumnInfo excelColumnInfo : excelColumnInfos) {
                try {
                    Field field = data.getClass().getDeclaredField(excelColumnInfo.getColumnEnglishName());
                    field.setAccessible(true);
                    if (field.getType() == Long.class || field.getType() == Integer.class) {
                        eachRowData.add(String.valueOf(field.get(data)));
                    } else {
                        eachRowData.add((String) (field.get(data)));
                    }
                } catch (Exception e) {
                    System.out.println("err!");
                }
            }
            rowDataList.add(eachRowData);
        }
        return rowDataList;
    }
}
