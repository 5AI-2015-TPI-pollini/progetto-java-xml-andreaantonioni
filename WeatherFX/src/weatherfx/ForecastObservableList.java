package weatherfx;

import GoogleMapsGeocode.GoogleMapsGeocoding;
import GoogleMapsGeocode.NoResultsException;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import utility.Utility;

/**
 * Represents an ObservableList object which contains Forecast objects.
 * 
 * @author Andrea Antonioni -
 * <a href="mailto:andreaantonioni97@gmail.com">andreaantonioni97@gmail.com</a>
 */
public class ForecastObservableList {

    /**
     * It's an ArrayList object which contains all Forecast objects searched.
     * When a Forecast object is added, it's graphically added to the ListView using ForecastCellRenderer.
     * @see ForecastCellRender#this
     * @see graphic.GUIController#list
     */
    public static ObservableList<Forecast> observableList = FXCollections.observableArrayList();

    /**
     * Reads an XML file containing various tags referred to a City object, then sends an http request for the weather conditions.
     * @param inputXML A File objects which represents an XML ("config.xml") which contains City objects.
     * @see #exportXML() 
     */
    public static void importXML(File inputXML) {
        if(!Utility.checkInternetConnection())
            return;
        
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputXML);

            doc.getDocumentElement().normalize();

            NodeList list = doc.getElementsByTagName("forecast");

            for (int i = 0; i < list.getLength(); i++) 
            {
                NodeList forecastList = list.item(i).getChildNodes();

                //City
                Node cityList = forecastList.item(0);

                Node coordinatesList = cityList.getChildNodes().item(0);

                String lat = coordinatesList.getChildNodes().item(0).getTextContent();
                String lng = coordinatesList.getChildNodes().item(1).getTextContent();

                String name = cityList.getChildNodes().item(1).getTextContent();

                City city = new City(new Coordinates(lat, lng), name);

                observableList.add(Forecast.getInstance(city));
            }

        } catch (ParserConfigurationException | SAXException ex) {
            Logger.getLogger(ForecastObservableList.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
        }
    }

    /**
     * Adds a new Forecast object created using the address param
     * @param address A String object which contains a description which identies a place on Earth
     * @see GoogleMapsGeocoding#getCity(java.lang.String) 
     * @see weatherfx.Forecast#getInstance(weatherfx.City) 
     * @throws IOException If the internet connection doesn't work
     * @throws NoResultsException If the address doesn't decribe a valid place on Earth
     */
    public static void add(String address) throws IOException, NoResultsException {

        City city = GoogleMapsGeocoding.getCity(address);
        Forecast forecast = Forecast.getInstance(city);
        
        observableList.add(forecast);

        System.out.println(forecast);

    }

    /**
     * Removes a City object using its name.
     * @param city A String which represents the name of the City object to remove
     * @see ObservableList#remove(java.lang.Object) 
     */
    public static void remove(String city) {
        for (Forecast forecast : ForecastObservableList.observableList) 
        {
            if (forecast.getCity().getName().equals(city)) 
            {
                ForecastObservableList.observableList.remove(forecast);
                return;
            }
        }
    }

    /**
     * Saves the ObservableList as an XML file called "config.xml". If the ObservableList is empty it doesn't work.
     * @see #observableList
     */
    public static void exportXML() {

        //If the list is empty, it doesn't create the file
        if (observableList.size() <= 0) 
        {
            return;
        }

        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("forecast_array");
            doc.appendChild(rootElement);

            for (Forecast forecast : observableList) {
                Element forecastElement = doc.createElement("forecast");
                rootElement.appendChild(forecastElement);

                //City
                Element cityElement = doc.createElement("city");
                forecastElement.appendChild(cityElement);

                Element coordinatesElement = doc.createElement("coordinates");
                cityElement.appendChild(coordinatesElement);

                Element latitudeElement = doc.createElement("lat");
                latitudeElement.appendChild(doc.createTextNode(forecast.getCity().getCoordinates().getLatitude()));
                coordinatesElement.appendChild(latitudeElement);

                Element longitudeElement = doc.createElement("lng");
                longitudeElement.appendChild(doc.createTextNode(forecast.getCity().getCoordinates().getLongitude()));
                coordinatesElement.appendChild(longitudeElement);

                Element nameElement = doc.createElement("name");
                nameElement.appendChild(doc.createTextNode(forecast.getCity().getName()));
                cityElement.appendChild(nameElement);

            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("config.xml"));

            transformer.transform(source, result);
            System.out.println("XML File Done");

        } catch (ParserConfigurationException ex) {
            Logger.getLogger(ForecastObservableList.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(ForecastObservableList.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(ForecastObservableList.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
