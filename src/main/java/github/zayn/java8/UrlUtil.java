package github.zayn.java8;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.apache.commons.lang3.StringUtils;

/**
 * @author duxuan.yjj
 * @date 2020/09/15
 */
public class UrlUtil {

    public static String encode(String query) {
        String result = "";
        if (query == null) {
            return result;
        }
        try {
            result = URLEncoder.encode(query, StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
        }
        return result;
    }

    /**
     * url encode之后将+替换为%20
     *
     * @param query
     * @return
     */
    public static String encodeWithReplacePlus(String query) {
        String result = "";
        if (query == null) {
            return result;
        }
        try {
            result = URLEncoder.encode(query, StandardCharsets.UTF_8.name());
            result = StringUtils.replace(result, "+", "%20");
        } catch (UnsupportedEncodingException e) {
        }
        return result;
    }
}
