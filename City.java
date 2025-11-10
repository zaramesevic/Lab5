public class City {
    private final String city;
    private final String country;
    private final String size;
    private final boolean isCapitol;

    public City(String city, String country, String size, boolean isCapitol) {
        this.city = city;
        this.country = country;
        this.size = size;
        this.isCapitol = isCapitol;
    }

    public Boolean isCapitol() {    return this.isCapitol;}

    @Override
    public String toString() {
        return city + ", " + country + ", " + size + ", " + isCapitol;
    }

    public String getSize() {
        return this.size;
    }
}