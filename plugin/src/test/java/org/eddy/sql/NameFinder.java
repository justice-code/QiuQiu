package org.eddy.sql;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.Select;
import org.eddy.sql.split.ReplaceTablesNamesFinder;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Created by eddy on 2017/6/8.
 */
public class NameFinder {

    @Test
    public void test() throws JSQLParserException {
        String sql = "select max(id) from (\n" +
                "            select distinct(t.id) as id\n" +
                "            from (select i.sale_order_item_id as id,o.order_id as orderId,o.marketplace as marketplace,\n" +
                "            replace(json_extract(o.others,'$.warehouse_upload'),'\"','') as swname\n" +
                "            from fantasia.sale_order_item i\n" +
                "            left join fantasia.sale_order o on o.order_id=i.order_id\n" +
                "            where o.type in ('7','8') and o.state in ('3','4') and o.upload_date>DATE_SUB(CURDATE(), INTERVAL 3 MONTH) and i.sale_order_item_id not in (\n" +
                "            select order_item_id from fantasia.customs_declaration_order_item di\n" +
                "            left join fantasia.customs_declaration_form f on di.form_id=f.id where f.removed=0)) t\n" +
                "            left join fantasia.`storage_warehouse` sw on t.swname = sw.name\n" +
                "            left join fantasia.`company` c on replace(json_extract(sw.others,'$.company'),'\"','') = c.id where c.`inland` = '1') as a";

        ReplaceTablesNamesFinder tablesNamesFinder = new ReplaceTablesNamesFinder();
        Statement statement = CCJSqlParserUtil.parse(sql);
        List<String> tables = tablesNamesFinder.getTableList((Select) statement);
        Assert.assertTrue(tables.size() == 6);
    }

    @Test
    public void test2() throws JSQLParserException {
        String sql = "select max(id) from (\n" +
                "            select distinct(t.id) as id\n" +
                "            from (select i.sale_order_item_id as id,o.order_id as orderId,o.marketplace as marketplace,\n" +
                "            replace(json_extract(o.others,'$.warehouse_upload'),'\"','') as swname\n" +
                "            from fantasia.sale_order_item i\n" +
                "            left join fantasia.sale_order o on o.order_id=i.order_id\n" +
                "            where o.type in ('7','8') and o.state in ('3','4') and o.upload_date>DATE_SUB(CURDATE(), INTERVAL 3 MONTH) and i.sale_order_item_id not in (\n" +
                "            select order_item_id from fantasia.customs_declaration_order_item di\n" +
                "            left join fantasia.customs_declaration_form f on di.form_id=f.id where f.removed=0)) t\n" +
                "            left join fantasia.`storage_warehouse` sw on t.swname = sw.name\n" +
                "            left join fantasia.`company` c on replace(json_extract(sw.others,'$.company'),'\"','') = c.id where c.`inland` = '1') as a";

        ReplaceTablesNamesFinder tablesNamesFinder = new ReplaceTablesNamesFinder();
        Statement statement = CCJSqlParserUtil.parse(sql);
        tablesNamesFinder.getTableList((Select) statement);
        System.out.println(statement.toString());
    }
}
