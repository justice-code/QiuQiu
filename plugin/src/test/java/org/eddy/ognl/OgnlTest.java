package org.eddy.ognl;

import org.apache.ibatis.ognl.Ognl;
import org.apache.ibatis.ognl.OgnlException;
import org.apache.ibatis.parsing.PropertyParser;
import org.eddy.xml.context.XmlDataContext;
import org.eddy.xml.data.RuleNode;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Created by eddy on 2017/6/9.
 */
public class OgnlTest {

    @Test
    public void test() throws OgnlException {
        List<RuleNode> dataNodes = XmlDataContext.getContext().getNodes();
        Object value = Ognl.getValue("[0].keyColumn.table", dataNodes);
        System.out.println(value);
    }

    @Test
    public void test2() throws OgnlException {
        List<RuleNode> dataNodes = XmlDataContext.getContext().getNodes();
        Map<String, RuleNode> map = new HashMap<>();
        map.put("key1", dataNodes.get(0));
        map.put("key2", dataNodes.get(1));
        Object value = Ognl.getValue("key1.keyColumn.table", map);
        System.out.println(value);

        Object value2 = Ognl.getValue("key2.keyColumn.table", map);
        System.out.println(value2);
    }

    @Test
    public void test3() throws OgnlException {
        List<RuleNode> dataNodes = XmlDataContext.getContext().getNodes();
        Map<String, List<RuleNode>> map = new HashMap<>();
        map.put("key", dataNodes);
        Object value = Ognl.getValue("key[1].keyColumn.table", map);
        System.out.println(value);

    }

    @Test
    public void test4() {
        Properties properties = new Properties();
        properties.put("today", "now");
        properties.put("m", "l");
        String content = "today is ${today}, ${m)";
        String result = PropertyParser.parse(content, properties);
        System.out.println(result);
    }

    @Test
    public void test5() throws OgnlException {
        Object value = Ognl.getValue("value", "value");
        System.out.println(value);
    }
}
