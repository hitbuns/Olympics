package com.Olympics;

import java.util.ArrayList;
import java.util.List;

public class Country {

    private final List<Athlete> athletes = new ArrayList<>();
    private static final int maxContestants = 10;
    private final CountryType countryType;

    public Country(CountryType countryType) {
        this.countryType = countryType;
    }

    public CountryType getCountryType() {
        return countryType;
    }

    public List<Athlete> getAthletes() {
        return athletes;
    }

    public void register(Athlete athlete) {
        if (athletes.size() < 10)
        this.athletes.add(athlete);
    }

    public static int getMaxContestants() {
        return maxContestants;
    }

}
