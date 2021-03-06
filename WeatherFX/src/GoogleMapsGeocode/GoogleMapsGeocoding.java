package GoogleMapsGeocode;

import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import utility.Utility;
import weatherfx.City;
import weatherfx.Coordinates;

/**
 * The GoogleMapsGeocoding class contains methods to use APIs of geocoding service offered by Google Maps.
 * 
 * @author Andrea Antonioni -
 * <a href="mailto:andreaantonioni97@gmail.com">andreaantonioni97@gmail.com</a>
 */
public class GoogleMapsGeocoding {

    private static final String xmlRequest = "http://maps.googleapis.com/maps/api/geocode/xml?address=";

    /**
     * Returns a City object obtained using the geocoding service offered by Google Maps to geocode a place.
     * @param address A String which represents the place to geocode
     * @return A City object
     * @see weatherfx.City
     * @throws IOException If the internet connection doesn't work it throws this exception.
     * @throws NoResultsException If Google Maps doens't find the address.
     */
    public static City getCity(String address) throws IOException, NoResultsException {

        String addressEncoded = URLEncoder.encode(address, "UTF-8");

        Document doc = Utility.importXML(new URL(xmlRequest + addressEncoded));

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

    /**
     * Returns a Coordinates object obtained using XPath to detect information inside Document object.
     * @param xpath A XPath object
     * @param doc A Documentation object which contains the XML
     * @return A new Coordinates object
     * @see weatherfx.Coordinates#this
     */
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

    /**
     * Checks if the Documents objects contains information or it's empty. 
     * If it's empty means that the user described a place which doesn't exist on Earth.
     * @param xpath A XPath object
     * @param doc A Documentation object which contains the XML
     * @return true if there's a result
     */
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
