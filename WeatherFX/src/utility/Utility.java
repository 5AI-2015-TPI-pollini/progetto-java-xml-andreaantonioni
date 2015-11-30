package utility;

import GoogleMapsGeocode.GoogleMapsGeocoding;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 * This class contains utility methods.
 * 
 * @author Andrea Antonioni -
 * <a href="mailto:andreaantonioni97@gmail.com">andreaantonioni97@gmail.com</a>
 */
public class Utility {

    /**
     * It sends an HTTP request to an internet service and it imports the XML like answer.
     * 
     * @param url it's an URL object which represents the url to use for the HTTP request.
     * @return A Document object which contains the XML.
     * @throws IOException If the internet connection doesn't work.
     */
    public static Document importXML(URL url) throws IOException {

        URLConnection urlConnection = url.openConnection();
        InputStream inStream = new BufferedInputStream(urlConnection.getInputStream());

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        Document doc = null;
        try {
            builder = factory.newDocumentBuilder();
            doc = builder.parse(inStream);
        } catch (ParserConfigurationException | SAXException ex) {
            Logger.getLogger(GoogleMapsGeocoding.class.getName()).log(Level.SEVERE, null, ex);
        }

        return doc;
    }
    
    /**
     * It sets the proxy settings for the internet connection.
     * @param server A String which represents the server ip.
     * @param porta A String which represents the number of the port.
     */
    public static void setProxy(String server, String porta) {
        System.setProperty("proxySet", "true");
        System.setProperty("http.proxyHost", server);
        System.setProperty("http.proxyPort", porta);
    }

    /**
     * It sets the proxy settings with authentication for the internet connection.
     * @param server A String which represents the server ip.
     * @param porta A String which represents the number of the port.
     * @param username A String which represents the username.
     * @param password A String which represents the password.
     */
    public static void setProxy(String server, String porta, String username, String password) {
        System.setProperty("proxySet", "true");
        System.setProperty("http.proxyHost", server);
        System.setProperty("http.proxyPort", porta);

        Authenticator.setDefault(new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication(username, password.toCharArray());
            }
        });

    }
    
}
