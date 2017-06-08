package org.eddy.xml;

import org.eddy.xml.context.XmlDataContext;
import org.eddy.xml.data.DataNode;
import org.eddy.xml.data.RuleNode;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Created by eddy on 2017/5/3.
 */
public class XmlTest {

    @Test
    public void test() {
        List<RuleNode> dataNodes = XmlDataContext.getContext().getNodes();
        Assert.assertEquals(1, dataNodes.size());
        Assert.assertEquals(4, dataNodes.get(0).getDataNodes().size());
    }

}
