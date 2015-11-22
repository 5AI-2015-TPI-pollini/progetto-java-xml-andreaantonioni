/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weatherwidgetfx;

import GoogleMapsGeocode.GoogleMapsGeocoding;
import GoogleMapsGeocode.NoResultsException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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

/**
 *
 * @author Andrea Antonioni -
 * <a href="mailto:andreaantonioni97@gmail.com">andreaantonioni97@gmail.com</a>
 */
public class ForecastArray extends ArrayList<Forecast> {

    public ForecastArray() {

    }
    
    /*public ForecastArray(File inputXML) throws IOException {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputXML);
            
            doc.getDocumentElement().normalize();
            
            NodeList list = doc.getElementsByTagName("forecast");
            
            for(int i=0; i<list.getLength(); i++)
            {
                NodeList forecastList = list.item(i).getChildNodes();
                
                Element forecast = (Element) forecastList.item(0);
                
                //City
                Element cityList = (Element) forecast.getElementsByTagName("city");
                
                Element coordinatesList = (Element) cityList.getElementsByTagName("coordinates");
                
                String lat = coordinatesList.getElementsByTagName("lat").item(0).getTextContent();
                String lng = coordinatesList.getElementsByTagName("lng").item(0).getTextContent();
                
                String name = cityList.getElementsByTagName("name").item(0).getTextContent();
                
                City city = new City(new Coordinates(lat, lng), name);
                
                //Weather
                NodeList weatherList = forecastList.item(1).getChildNodes();
                
                Weather weather = new Weather(weatherList.item(0).getTextContent(), 
                        weatherList.item(1).getTextContent());
                
                this.add(new Forecast(city, weather));
            }
            
            
            
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(ForecastArray.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(ForecastArray.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/

    public void add(String address) throws IOException, NoResultsException {
        City city = GoogleMapsGeocoding.getCity(address);
        Forecast forecast = Forecast.getInstance(city);
        this.add(forecast);

        System.out.println(forecast);
    }

    public void exportXML() {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("forecast_array");
            doc.appendChild(rootElement);

            for (Forecast forecast : this) {
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
                
                //Weather
                Element weatherElement = doc.createElement("weather");
                forecastElement.appendChild(weatherElement);

                Element descriptionElement = doc.createElement("description");
                descriptionElement.appendChild(doc.createTextNode(forecast.getWeather().getDescription()));
                weatherElement.appendChild(descriptionElement);

                Element urlIconElement = doc.createElement("url_icon");
                urlIconElement.appendChild(doc.createTextNode(forecast.getWeather().getUrlIcon()));
                weatherElement.appendChild(urlIconElement);

            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("forecast_array.xml"));
            
            transformer.transform(source, result);
            System.out.println("XML File Done");

        } catch (ParserConfigurationException ex) {
            Logger.getLogger(ForecastArray.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(ForecastArray.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(ForecastArray.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
