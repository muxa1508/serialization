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
//        System.out.println("Корневой элемент: " + root.getNodeName());

        read(root);

//        load(loadEnabled, loadFilename, loadFormat);
//        save(saveEnabled, saveFilename, saveFormat);
//        log(logEnabled, logFilename);

    }

    private void read(Node node) {
        NodeList nodeList = node.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node currentNode = nodeList.item(i);
            if (currentNode.getNodeType() != Node.TEXT_NODE) {
                NodeList props = currentNode.getChildNodes();
//                System.out.println("Второй элемент" + " " + currentNode.getNodeName());
                for (int j = 0; j < props.getLength(); j++) {
                    Node prop = props.item(j);
                    if (prop.getNodeType() != Node.TEXT_NODE) {
//                        System.out.println(currentNode.getNodeName() + " "
//                                + prop.getNodeName() + " "
//                                + prop.getTextContent());
//                    условия
                        if (currentNode.getNodeName().equals("load")) {
//                            System.out.println("Это была загрузка");
                            if (prop.getNodeName().equals("enabled")) {
                                loadEnabled = prop.getTextContent();
//                                System.out.println(loadEnabled);
                            }
                            if (prop.getNodeName().equals("fileName")) {
                                loadFilename = prop.getTextContent();
//                                System.out.println(loadFilename);
                            }
                            if (prop.getNodeName().equals("format")) {
                                loadFormat = prop.getTextContent();
//                                System.out.println(loadFormat);
                            }
                        }
                        if (currentNode.getNodeName().equals("save")) {
//                            System.out.println("Это было сохранение");
                            if (prop.getNodeName().equals("enabled")) {
                                saveEnabled = prop.getTextContent();
//                                System.out.println(saveEnabled);
                            }
                            if (prop.getNodeName().equals("fileName")) {
                                saveFilename = prop.getTextContent();
//                                System.out.println(saveFilename);
                            }
                            if (prop.getNodeName().equals("format")) {
                                saveFormat = prop.getTextContent();
//                                System.out.println(saveFormat);
                            }
                        }
                        if (currentNode.getNodeName().equals("log")) {
//                            System.out.println("Это был лог");
                            if (prop.getNodeName().equals("enabled")) {
                                logEnabled = prop.getTextContent();
//                                System.out.println(logEnabled);
                            }
                            if (prop.getNodeName().equals("fileName")) {
                                logFilename = prop.getTextContent();
//                                System.out.println(logFilename);
                            }
                        }
                    }
                }
            }
        }
//        System.out.println(loadEnabled + " " + loadFilename + " " + loadFormat);
//        System.out.println(saveEnabled + " " + saveFilename + " " + saveFormat);
//        System.out.println(logEnabled + " " + logFilename);
    }

//    protected void load(String loadEnabled, String loadFilename, String loadFormat) {
//        if (loadEnabled.equals("true")) {
//            this.loadFilename = loadFilename;
//        } else {
//            this.loadFilename = null;
//        }
//    }
//
//    protected void save(String saveEnabled, String saveFilename, String saveFormat) {
//        if (saveEnabled.equals("true")) {
//            this.saveFilename = saveFilename;
//        } else {
//            this.saveFilename = null;
//        }
//    }
//
//    protected void log(String logEnabled, String logFilename) {
//        if (logEnabled.equals("true")) {
//            this.logFilename = logFilename;
//        } else {
//            this.logFilename = null;
//        }
//    }

}

