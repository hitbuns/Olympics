package com.Olympics;


import java.io.*;
import java.util.Scanner;

public class Athlete {

    private int bronze,silver,gold;
    private String name;
    private Country country;

    public Athlete(String name,Country country) {
        this(name,country,0,0,0);
    }

    public Athlete(String name,Country country,int bronze,int silver,int gold) {
        this.name = name;
        this.country = country;
        this.bronze = Math.max(0,bronze);
        this.silver = Math.max(0,silver);
        this.gold = Math.max(0,gold);
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void awardBronze() {
        bronze++;
    }

    public void awardSilver() {
        silver++;
    }

    public void awardGold() {
        gold++;
    }

    public int getBronze() {
        return bronze;
    }

    public int getSilver() {
        return silver;
    }

    public int getGold() {
        return gold;
    }

    public static void save(Athlete athlete) {
        File f = Main.getInstance().getFile();
        try {
            if (!f.exists()) f.createNewFile();
            FileWriter writer = new FileWriter(f,true);
            writer.append(athlete.toString()+"\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void load() {
        File f = Main.getInstance().getFile();
        try {
            Scanner scanner = new Scanner(f);
            while (scanner.hasNextLine()) {
                int bronze = 0,silver = 0,gold = 0;
                String name = null;
                Country country = null;
                String[] split = scanner.nextLine().split(",");
                int i = 0;
                for (String s : split) {
                    String[] sp = s.split("=");
                    i++;
                    switch (i) {
                        case 1:
                            bronze = Integer.parseInt(sp[1]);
                            break;
                        case 2:
                            silver = Integer.parseInt(sp[1]);
                            break;
                        case 3:
                            gold = Integer.parseInt(sp[1]);
                            break;
                        case 4:
                            name = sp[1];
                            break;
                        case 5:
                            country = Main.getCountry(CountryType.valueOf(sp[1].toUpperCase()));
                            break;
                        default:
                            break;
                    }
                    if (i >= 5) {
                        country.register(new Athlete(name,country,bronze,silver,gold));
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return
                "bronze=" + bronze +
                ",silver=" + silver +
                ",gold=" + gold +
                ",name=" + name +
                ",country=" + country.getCountryType().name();
    }
}
