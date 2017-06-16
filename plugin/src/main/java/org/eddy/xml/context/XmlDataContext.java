package org.eddy.xml.context;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.eddy.xml.UrlDtdPathResolver;
import org.eddy.xml.data.DataNode;
import org.eddy.xml.data.KeyColumn;
import org.eddy.xml.data.RuleNode;
import org.eddy.xml.rule.Comparator;
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
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by eddy on 2017/5/2.
 */
@Getter
public class XmlDataContext {

    private static final Logger logger = LoggerFactory.getLogger(XmlDataContext.class);

    private List<RuleNode> nodes;

    private static XmlDataContext context = new XmlDataContext("rule/rule.xml");


    public static XmlDataContext getContext() {
        return context;
    }

    //**********************私有构造函数*************************

    private XmlDataContext(String path) {
        try {
            this.nodes = loadXml(path);
        } catch (Exception e) {
            logger.error("parse xml error", e);
            throw new RuntimeException("parse xml error", e);
        }
    }

    //**********************解析xml*****************************

    private List<RuleNode> loadXml(String path) throws Exception{
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = documentBuilderFactory.newDocumentBuilder();
        builder.setEntityResolver(new UrlDtdPathResolver());
        Document document = builder.parse(Thread.currentThread().getContextClassLoader().getResourceAsStream(path));

        List<RuleNode> results = new ArrayList<>();

        NodeList rootChildren = document.getDocumentElement().getChildNodes();
        for (int i = 0; i < rootChildren.getLength(); i++) {

            Optional.ofNullable(parseNode(rootChildren.item(i))).ifPresent(node -> results.add(node));

        }

        return results.stream().filter(Objects::nonNull).collect(Collectors.toList());
    }

    private RuleNode parseNode(Node item) throws Exception{

        // 节点非element节点 或 element节点name非rule, 则直接返回
        if (item.getNodeType() != Node.ELEMENT_NODE || !StringUtils.equals(item.getNodeName(), RuleNode.RULE_NODE_NAME)) {
            return null;
        }

        Element ruleNode = (Element) item;

        String table = ruleNode.getAttribute("table");
        String column = ruleNode.getAttribute("column");
        String javaType = ruleNode.getAttribute("javaType");
        KeyColumn keyColumn = new KeyColumn(table, column, javaType);

        String comparator = ruleNode.getAttribute("class");
        Comparator comparatorClass = (Comparator) Class.forName(comparator).newInstance();

        RuleNode node = new RuleNode(keyColumn, comparatorClass);

        NodeList ruleChildren = item.getChildNodes();
        for (int i = 0; i < ruleChildren.getLength(); i++) {

            Optional.ofNullable(parseData(ruleChildren.item(i))).ifPresent(dataNode -> {
                node.add(dataNode);
                dataNode.setRuleNode(node);
            });

        }

        return node;
    }

    private DataNode parseData(Node item) throws Exception {
        // 节点非element节点 或 element节点name非data, 则直接返回
        if (item.getNodeType() != Node.ELEMENT_NODE || !StringUtils.equals(item.getNodeName(), DataNode.DATA_NODE_NAME)) {
            return null;
        }

        Element dataElement = (Element) item;
        String schema = dataElement.getAttribute("schema");
        String table = dataElement.getAttribute("table");
        String script = dataElement.getAttribute("script");

        return new DataNode(schema, table, script);
    }
}
