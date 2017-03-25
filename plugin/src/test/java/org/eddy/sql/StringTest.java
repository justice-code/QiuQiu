package org.eddy.sql;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

/**
 * Created by eddy on 2017/3/25.
 */
public class StringTest {

    @Test
    public void test() {
        String statementId = "org.eddy.dao.mapper.stock.BoughtStockMapper.insertOne";
        System.out.println(StringUtils.substring(statementId, 0, StringUtils.lastIndexOf(statementId, ".")));
        System.out.println(StringUtils.substring(statementId, StringUtils.lastIndexOf(statementId, ".") + 1));
    }
}
