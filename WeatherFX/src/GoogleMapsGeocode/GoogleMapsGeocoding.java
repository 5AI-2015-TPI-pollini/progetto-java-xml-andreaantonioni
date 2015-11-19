/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GoogleMapsGeocode;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import utility.Utility;
import weatherwidgetfx.City;
import weatherwidgetfx.Coordinates;

/**
 *
 * @author Andrea Antonioni -
 * <a href="mailto:andreaantonioni97@gmail.com">andreaantonioni97@gmail.com</a>
 */
public class GoogleMapsGeocoding {

    public static final String xmlRequest = "http://maps.googleapis.com/maps/api/geocode/xml?address=";

    public static City getCity(String address) throws IOException, NoResultsException {

        String addressEncoded = URLEncoder.encode(address, "UTF-8");

        Document doc = Utility.importXML(xmlRequest + addressEncoded);

        XPathFactory xpathFactory = XPathFactory.newInstance();
        XPath xpath = xpathFactory.newXPath();

        if (!checkResults(xpath, doc)) {
            throw new NoResultsException(address);
        }

        Coordinates coordinates = getCoordinates(xpath, doc);

        XPathExpression statusExpression;
        String locality = null;
        try {
            statusExpression = xpath.compile("/GeocodeResponse/result/address_component[type='locality']/long_name/text()");
            locality = (String) statusExpression.evaluate(doc, XPathConstants.STRING);
        } catch (XPathExpressionException ex) {
            Logger.getLogger(GoogleMapsGeocoding.class.getName()).log(Level.SEVERE, null, ex);
        }

        return new City(coordinates, locality);

    }

    private static Coordinates getCoordinates(XPath xpath, Document doc) {
        XPathExpression coordinatesExpression;
        NodeList coordinatesList = null;
        try {
            coordinatesExpression = xpath.compile("/GeocodeResponse/result/geometry/location[1]/lat/text() | "
                    + "/GeocodeResponse/result/geometry/location[1]/lng/text()");

            coordinatesList = (NodeList) coordinatesExpression.evaluate(doc, XPathConstants.NODESET);
        } catch (XPathExpressionException ex) {
            Logger.getLogger(GoogleMapsGeocoding.class.getName()).log(Level.SEVERE, null, ex);
        }

        return new Coordinates(coordinatesList.item(0).getNodeValue(), coordinatesList.item(1).getNodeValue());
    }

    private static boolean checkResults(XPath xpath, Document doc) {
        try {
            XPathExpression statusExpression = xpath.compile("/GeocodeResponse/status/text()");
            String status = (String) statusExpression.evaluate(doc, XPathConstants.STRING);

            //Controllo che sia stati trovati dei risultati
            if (status.equals("ZERO_RESULTS")) {
                return false;
            }
            return true;
        } catch (XPathExpressionException ex) {
            Logger.getLogger(GoogleMapsGeocoding.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    
}
