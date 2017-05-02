package org.eddy.xml.context;

import org.eddy.xml.UrlDtdPathResolver;
import org.eddy.xml.data.DataNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by eddy on 2017/5/2.
 */
public class XmlDataContext {

    private static final Logger logger = LoggerFactory.getLogger(XmlDataContext.class);

    private List<DataNode> nodes;

    public XmlDataContext(String path) {
        try {
            this.nodes = loadXml(path);
        } catch (Exception e) {
            logger.error("parse xml error", e);
            throw new RuntimeException("parse xml error", e);
        }
    }

    private List<DataNode> loadXml(String path) throws Exception{
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = documentBuilderFactory.newDocumentBuilder();
        builder.setEntityResolver(new UrlDtdPathResolver());
        Document document = builder.parse(Thread.currentThread().getContextClassLoader().getResourceAsStream(path));

        List<DataNode> results = new ArrayList<>();

        NodeList rootChildren = document.getDocumentElement().getChildNodes();
        for (int i = 0; i < rootChildren.getLength(); i++) {
            DataNode node = parseNode(rootChildren.item(i));
            results.add(node);
        }

        return results.stream().filter(Objects::nonNull).collect(Collectors.toList());
    }

    private DataNode parseNode(Node item) {
        return null;
    }
}
