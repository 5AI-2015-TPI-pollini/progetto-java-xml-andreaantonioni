/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weatherfx;

/**
 * The Coordinates class represents the couple latitude and longitude which can identify a point in the Earth.
 * 
 * @author Andrea Antonioni -
 * <a href="mailto:andreaantonioni97@gmail.com">andreaantonioni97@gmail.com</a>
 */
public class Coordinates {
    private String longitude;
    private String latitude;

    /**
     * Constructs a new Coordinates using the input latitude and longitude.
     * 
     * @param latitude it's the number which represents the number of parallels.
     * @param longitude it's the number which represents the number of meridians.
     */
    public Coordinates(String latitude, String longitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }
    
    /**
     * Returns a String which represents the longitude.
     * @return longitude
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     * Sets a new values for the longitude.
     * @param longitude it's the number which represents the number of meridians.
     */
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    /**
     * Returns a String which represents the latitude.
     * @return latitude
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     * Sets a new values for the latitude.
     * @param latitude it's the number which represents the number of parallels.
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
    
    /**
     * Returns a description of this object. 
     * It's like:
     * "lat: [value]
     * lng: [value]"
     * 
     * @return A new String which contains the description
     * @see String#toString() 
     */
    @Override
    public String toString()
    {
        return "lat: " + latitude + "\n" + "lng: " + longitude ;
    }
    
}
