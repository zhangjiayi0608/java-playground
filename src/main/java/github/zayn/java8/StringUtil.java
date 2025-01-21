package github.zayn.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

/**
 * @author zhangjiayi
 * @Description
 * @date 2024/2/27 11:23
 */
public class StringUtil {

    private static final String QUERY_FORMAT = "item_title:%s|cate_name:%s|item_id:%s";

    public static void main(String[] args) {

    }

    private static String buildQuery(long sellerId, List<String> tmcTags, List<String> icTags, List<Long> itemIds,
        boolean onlyRecommend) {
        String sellerMatch = String.format("user_id:\"%s\"", sellerId);
        String tmcMatch = CollectionUtils.isEmpty(tmcTags) ? null : tmcTags.stream().map(
            tag -> String.format("tmc_tags:\"%s\"", tag)).collect(Collectors.joining(" OR "));
        String icMatch = CollectionUtils.isEmpty(icTags) ? null : icTags.stream().map(
            tag -> String.format("ic_tags:\"%s\"", tag)).collect(Collectors.joining(" OR "));
        String tagMatch = Stream.of(tmcMatch, icMatch).filter(StringUtils::isNotEmpty).collect(
            Collectors.joining(" OR "));
        String itemMatch = CollectionUtils.isEmpty(itemIds) ? null : itemIds.stream().map(
            itemId -> String.format("id:\"%s\"", itemId)).collect(Collectors.joining(" OR "));
        String recommendMatch = onlyRecommend ? "has_activity:\"1\"" : null;
        return Stream.of(sellerMatch, tagMatch, itemMatch, recommendMatch).filter(StringUtils::isNotEmpty).map(
            match -> "( " + match + " )").collect(Collectors.joining(" AND "));
    }

    private static String build() {
        String itemId = "1|2|3|4|5|6";
        List<String> list = Arrays.asList(itemId.split("\\|"));
        String encode = String.format(QUERY_FORMAT, "1", "2", "3");
        encode = StringUtils.replace(encode, "+", "%20");
        return encode;
    }

    public static List<String> getColumnsFromSQL(String sql) {
        if (sql != null && sql.length() != 0) {
            sql = sql.trim().replaceAll("\\s+", " ");
            int start = sql.toLowerCase().indexOf("select ") + "select ".length();
            int end = sql.toLowerCase().indexOf(" from ");
            String sColumns = sql.substring(start, end).trim();
            if ("*".equals(sColumns)) {
                List<String> columns = new ArrayList();
                columns.add("*");
                return columns;
            } else {
                List<String> columns = splitColumns(sColumns);
                List<String> labels = new ArrayList(columns.size());
                Iterator var6 = columns.iterator();

                while (var6.hasNext()) {
                    String column = (String)var6.next();
                    column = column.trim();
                    int index = column.lastIndexOf(32);
                    if (index == -1) {
                        int pointIndex = column.lastIndexOf(46);
                        if (pointIndex == -1) {
                            labels.add(column);
                        } else {
                            labels.add(column.substring(pointIndex + 1));
                        }
                    } else {
                        labels.add(column.substring(index + 1));
                    }
                }

                return labels;
            }
        } else {
            return new ArrayList(0);
        }
    }

    private static List<String> splitColumns(String sColumns) {
        List<String> columns = new ArrayList();
        sColumns = sColumns + ",";
        int leftBracketCount = 0;
        int start = 0;

        for (int i = 0; i < sColumns.length(); ++i) {
            char c = sColumns.charAt(i);
            if (c == '(') {
                ++leftBracketCount;
            } else if (c == ')') {
                --leftBracketCount;
            } else if (c == ',' && leftBracketCount == 0) {
                columns.add(sColumns.substring(start, i));
                start = i + 1;
            }
        }

        return columns;
    }

}
