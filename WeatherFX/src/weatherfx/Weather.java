package weatherfx;

/**
 * Represents a weather forecast.
 * @author Andrea Antonioni -
 * <a href="mailto:andreaantonioni97@gmail.com">andreaantonioni97@gmail.com</a>
 */
public class Weather {
    private String description;
    private String urlIcon;
    private String temperature;

    /**
     * Creates a new Weather object
     * @param description A String object which represents the description of the weather
     * @param urlIcon A String object which contains the URL for the icon
     * @param temperature A String object which represents the temperature
     */
    public Weather(String description, String urlIcon, String temperature) {
        this.description = description;
        this.urlIcon = urlIcon;
        if(temperature.indexOf(".")<0)
            this.temperature = temperature;
        else this.temperature = temperature.substring(0, temperature.indexOf("."));
        this.temperature+="°";
    }

    /**
     * Returns a String object which contains the URL for the icon
     * @return A String object which contains the URL for the icon
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns a String object which contains the URL
     * @return A String object which contains the URL
     */
    public String getUrlIcon() {
        return urlIcon;
    }

    /**
     * Returns a String object which represents the temperature
     * @return A String object which represents the temperature
     */
    public String getTemperature() {
        return temperature;
    }

    /**
     * Returns a description of this Weather object.
     * @return A String object which contains the description of the weather and the temperature
     */
    @Override
    public String toString()
    {
        return description + " - " + temperature;
    }
}
