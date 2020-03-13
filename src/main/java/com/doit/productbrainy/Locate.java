package com.doit.productbrainy;

import java.util.Arrays;
import java.util.Locale;

public class Locate {
	
	public static void main(String[] args) {
        
        String rd = "United States";
        
        String[] countryCodes = Locale.getISOCountries();
        Locale iso3 = Arrays.asList(countryCodes).stream().map(element -> new Locale("", element)).filter(locate -> locate.getDisplayCountry().equalsIgnoreCase(rd)).findFirst().orElse(null);
        if (iso3 != null) {
        	System.out.println(iso3.getISO3Country());
        }
	}
}
