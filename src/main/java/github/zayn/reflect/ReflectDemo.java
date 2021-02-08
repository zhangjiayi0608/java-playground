package github.zayn.reflect;

import java.lang.reflect.Field;
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

        How zjy2 = new How();
        Base base = new Base();
        base.setName("zhangjiayi1");
        zjy2.setAge(18);
        zjy2.setBase(base);
        Field[] declaredFields = zjy2.getClass().getDeclaredFields();
        System.out.println(declaredFields);
    }
}
