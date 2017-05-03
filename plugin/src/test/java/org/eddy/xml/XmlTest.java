package org.eddy.xml;

import org.eddy.xml.context.XmlDataContext;
import org.eddy.xml.data.DataNode;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Created by eddy on 2017/5/3.
 */
public class XmlTest {

    @Test
    public void test() {
        List<DataNode> dataNodes = XmlDataContext.getContext().getNodes();
        Assert.assertEquals(4, dataNodes.size());
    }

}
