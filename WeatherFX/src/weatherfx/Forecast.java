package weatherfx;

import GoogleMapsGeocode.GoogleMapsGeocoding;
import java.io.IOException;
import java.net.URL;
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
 * This class represents the forecast getted using Wunderground APIs.
 * Wunderground is an internet service which gets the weather forecast of a place using its coordinates.
 * @author Andrea Antonioni -
 * <a href="mailto:andreaantonioni97@gmail.com">andreaantonioni97@gmail.com</a>
 */
public class Forecast {
    private City city;
    private Weather weather;
    
    private static final String wunderground = "http://api.wunderground.com/api/3a361d678e2fb458/conditions/forecast/q/";
    
    Forecast(City city, Weather weather)
    {
        this.city = city;
        this.weather = weather;
    }

    /**
     * Returns the city of this forecast
     * @return A City object
     */
    public City getCity() {
        return city;
    }

    /**
     * Returns the weather of this forecast
     * @return A Weather object
     */
    public Weather getWeather() {
        return weather;
    }
    
    /**
     * It uses the Wunderground APIs to get the weather today of the City object.
     * @param city A City object that represents the city you want to get the weather forecast
     * @return A Forecast object
     * @throws IOException if the internet connection doesn't work
     */
    public static Forecast getInstance(City city) throws IOException {
        Document doc = Utility.importXML(new URL(wunderground + city.getCoordinates().getLatitude() + "," + 
                city.getCoordinates().getLongitude() + ".xml"));

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
    
    /**
     * Returns a description of this object. 
     * @return A String object which contains a escription of this object.
     * @see City#toString() 
     * @see Weather#toString() 
     */
    @Override
    public String toString()
    {
        return city + "\n" + "weather: " + weather;
    }

}
