import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;


public class SettingsChecker {

    protected String loadEnabled;
    protected String loadFilename;
    protected String loadFormat;
    protected String saveEnabled;
    protected String saveFilename;
    protected String saveFormat;
    protected String logEnabled;
    protected String logFilename;


    public void settingscheck() throws IOException, SAXException, ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new File("shop.xml"));
        Node root = doc.getDocumentElement();
        read(root);
    }

    private void read(Node node) {
        NodeList nodeList = node.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node currentNode = nodeList.item(i);
            if (currentNode.getNodeType() != Node.TEXT_NODE) {
                NodeList props = currentNode.getChildNodes();
                for (int j = 0; j < props.getLength(); j++) {
                    Node prop = props.item(j);
                    if (prop.getNodeType() != Node.TEXT_NODE) {
                        if (currentNode.getNodeName().equals("load")) {
                            if (prop.getNodeName().equals("enabled")) {
                                loadEnabled = prop.getTextContent();
                            }
                            if (prop.getNodeName().equals("fileName")) {
                                loadFilename = prop.getTextContent();
                            }
                            if (prop.getNodeName().equals("format")) {
                                loadFormat = prop.getTextContent();
                            }
                        }
                        if (currentNode.getNodeName().equals("save")) {
                            if (prop.getNodeName().equals("enabled")) {
                                saveEnabled = prop.getTextContent();
                            }
                            if (prop.getNodeName().equals("fileName")) {
                                saveFilename = prop.getTextContent();
                            }
                            if (prop.getNodeName().equals("format")) {
                                saveFormat = prop.getTextContent();
                            }
                        }
                        if (currentNode.getNodeName().equals("log")) {
                            if (prop.getNodeName().equals("enabled")) {
                                logEnabled = prop.getTextContent();
                            }
                            if (prop.getNodeName().equals("fileName")) {
                                logFilename = prop.getTextContent();
                            }
                        }
                    }
                }
            }
        }
    }
}

