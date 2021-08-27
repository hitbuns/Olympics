package com.Olympics;

import java.io.File;
import java.io.IOException;

public class Main {

    private final Country[] countries = new Country[CountryType.values().length];
    private static Main Instance;
    private final File f = new File(System.getProperty("user.dir"),"athletes.txt");

    public static void main(String[] args) {
        Instance = new Main();
        File f = getInstance().getFile();
        if (!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            loadDefaults();
        } else
            Athlete.load();
        for (Country country : getInstance().getCountries()) {
            country.getAthletes().forEach(athlete -> {
                System.out.println(athlete.toString());
            });
        }
    }

    public File getFile() {
        return f;
    }

    public Main() {
        int i = 0;
        for (CountryType value : CountryType.values()) {
            countries[i] = new Country(value);
            i++;
        }
    }

    public static void loadDefaults() {
        Country australia = getCountry(CountryType.AUSTRALIA);
        Country japan = getCountry(CountryType.JAPAN);
        Country britain = getCountry(CountryType.BRITAIN);
        australia.register(new Athlete("Ian",australia));
        australia.register(new Athlete("Thorpe",australia));
        japan.register(new Athlete("Kazuma",japan));
        britain.register(new Athlete("Blake",britain));
        System.out.println(australia.getAthletes().size());
        System.out.println(japan.getAthletes().size());
        System.out.println(britain.getAthletes().size());
        australia.getAthletes().forEach(Athlete::save);
        japan.getAthletes().forEach(Athlete::save);
        britain.getAthletes().forEach(Athlete::save);
    }

    public Country[] getCountries() {
        return countries;
    }

    public static Main getInstance() {
        return Instance;
    }

    public static Country getCountry(CountryType countryType) {
        for (Country country : getInstance().getCountries()) {
            if (country.getCountryType() == countryType) return country;
        }
        return null;
    }
}
