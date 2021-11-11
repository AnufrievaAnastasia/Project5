import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class Reader {

    public List<Currency> readFile(String filepath) throws ParserConfigurationException {
        List<Currency> valueList = new ArrayList<Currency>();
        File xmlFile = new File(filepath);
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

        try {
            Document document = builder.parse(xmlFile);
            NodeList nodeList = document.getDocumentElement().getElementsByTagName("Valute");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node currencyXML = nodeList.item(i);
                Currency currency = Reader.getCurrency(currencyXML);
                valueList.add(currency);
            }

        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return valueList;
    }

    private static String getTagValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = (Node) nodeList.item(0);
        return node.getNodeValue();
    }

    private static Currency getCurrency(Node node) {
        Currency valute = new Currency();
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            valute.setNumCode(getTagValue("NumCode", element));
            valute.setCharCode(getTagValue("CharCode", element));
            valute.setNominal(parseInt(getTagValue("Nominal", element)));
            valute.setName(getTagValue("Name", element));
            valute.setValue(Double.parseDouble(getTagValue("Value", element).replace(",", ".")));
        }

        return valute;
    }




}

