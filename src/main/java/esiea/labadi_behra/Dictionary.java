package main.java.esiea.labadi_behra;

import java.util.Set;
import java.util.Vector;
import main.java.esiea.labadi_behra.Util;
import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.HashSet;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public abstract class Dictionary {
	protected String langage;
	protected Set<String> dico;
	

	protected Dictionary(String language) {
		langage = language;
		dico = new HashSet<String>();
		String name = "/dico." + langage + ".txt";
		InputStream in = Util.openResource(name);
		fillDictionary(in);
	}
	

	public String getLongestWordFor(String word) {
		String wordFixed = removeAccents(word).toLowerCase();

		for (String subword : Util.getAllSubstrings(wordFixed, 2)) {
			int len = subword.length();

			for (String test : dico) {

				if (test.length() == len && Util.hasSameLetters(subword, test))
				{
					return test;
				}
			}
		}
		
		return "";
	}
	

	public Vector<String> getPossibleExtensionsFor(String word) {
		Vector<String> res = new Vector<String>();
		
		if (word == null || word.length() < 2)
			return res;
		
		String wordFixed = removeAccents(word).toLowerCase();
		int len = wordFixed.length();
		for (String test : dico)
			if (test.length() > len && Util.hasSameLetters(wordFixed, test))
				res.add(test);
		
		return res;
	}
	
	protected String removeAccents(String word) {
		
		if (word == null || word.isEmpty())
			return "";
		
		return Normalizer.normalize(word, Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
	}	

	public boolean exists(String word) {
		if (word == null || word.isEmpty())
			return false;
		
		return dico.contains(removeAccents(word).toLowerCase());
	}


	private void fillDictionary(InputStream in) {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
			String line;
			while ((line = reader.readLine()) != null)
				dico.add(removeAccents(line).toLowerCase());
		}
		catch (Exception e) {
			System.out.println("Error while reading the dictionary: " + e.getMessage());
			System.exit(0);
		}
	}

}
