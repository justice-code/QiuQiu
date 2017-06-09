package org.eddy.ognl;

import org.apache.ibatis.ognl.Ognl;
import org.apache.ibatis.ognl.OgnlException;
import org.eddy.xml.context.XmlDataContext;
import org.eddy.xml.data.RuleNode;
import org.junit.Test;

import java.util.List;

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
}
