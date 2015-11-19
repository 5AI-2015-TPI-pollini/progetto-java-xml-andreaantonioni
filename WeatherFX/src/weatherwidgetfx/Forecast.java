/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weatherwidgetfx;

import GoogleMapsGeocode.GoogleMapsGeocoding;
import java.io.IOException;
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

/**
 *
 * @author Andrea Antonioni -
 * <a href="mailto:andreaantonioni97@gmail.com">andreaantonioni97@gmail.com</a>
 */
public class Forecast {
    private City city;
    private Weather weather;
    
    public static final String wunderground = "http://api.wunderground.com/api/3a361d678e2fb458/conditions/forecast/q/";
    
    private Forecast(City city, Weather weather)
    {
        this.city = city;
        this.weather = weather;
    }

    public static Forecast getInstance(City city) throws IOException {
        Document doc = Utility.importXML(wunderground + city.getCoordinates().getLatitude() + "," + 
                city.getCoordinates().getLongitude() + ".xml");

        XPathFactory xpathFactory = XPathFactory.newInstance();
        XPath xpath = xpathFactory.newXPath();
        
        return new Forecast(city, getWeather(xpath, doc));
    }

    private static Weather getWeather(XPath xpath, Document doc) {
        XPathExpression weatherExpression;
        NodeList weatherList = null;
        try {
            weatherExpression = xpath.compile("/response/current_observation/weather/text() | "
                    + "/response/current_observation/icon_url/text()");
            weatherList = (NodeList) weatherExpression.evaluate(doc, XPathConstants.NODESET);
            
        } catch (XPathExpressionException ex) {
            Logger.getLogger(GoogleMapsGeocoding.class.getName()).log(Level.SEVERE, null, ex);
        }

        return new Weather(weatherList.item(0).getNodeValue(), weatherList.item(1).getNodeValue());
    }
    
    @Override
    public String toString()
    {
        return city + "\n" + "weather: " + weather;
    }

}
