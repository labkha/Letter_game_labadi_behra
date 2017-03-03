package main.java.esiea.labadi_behra;

import java.util.NavigableMap;
import java.util.Random;
import java.io.BufferedReader;
import java.util.TreeMap;
import java.io.InputStreamReader;
import main.java.esiea.labadi_behra.Util;

public class Bag {
protected static Bag instance;
	
	protected NavigableMap<Double, String> letter = new TreeMap<Double, String>();
	protected Random randome;
	protected double total_Weight = 0;
	
	
	/**
	 * Singleton
	 */
	protected Bag() {
		randome = new Random();
		initialLetter();
	}
	
	protected void initialLetter() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(Util.openResource("/letter.txt")));
		String line;
		String[] parts;
		try {
			while ((line = reader.readLine()) != null) {
				parts = line.split(":");
				if (parts.length != 2)
					continue;
				total_Weight += Double.parseDouble(parts[1]);
				letter.put(total_Weight, parts[0]);
			}
		}
		catch (Exception e) {
			throw new RuntimeException("Error while reading: " + e.getMessage());
		}
	}

	/**
	 * @return A letter
	 */
	public String chooseLetter() {
		double rand = randome.nextDouble() * total_Weight;
		
        return letter.ceilingEntry(rand).getValue().toUpperCase();
	}

/* Singleton*/

	public static final Bag getInstance() {
		if (instance == null)
			instance = new Bag();
		
		return instance;
	}
}
