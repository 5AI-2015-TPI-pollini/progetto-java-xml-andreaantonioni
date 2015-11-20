/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import GoogleMapsGeocode.GoogleMapsGeocoding;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Authenticator;
import java.net.MalformedURLException;
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
 *
 * @author Andrea Antonioni -
 * <a href="mailto:andreaantonioni97@gmail.com">andreaantonioni97@gmail.com</a>
 */
public class Utility {

    public static Document importXML(String addressEncoded) throws MalformedURLException, IOException {

        System.out.println(addressEncoded);
        //Apro InputStream con XML
        URL url = new URL(addressEncoded);

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
