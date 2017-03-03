package main.java.esiea.labadi_behra;

import java.util.Vector;
import main.java.esiea.labadi_behra.Dictionary;
import main.java.esiea.labadi_behra.Bag;

public class Purse {
	protected static Purse instance;
	
	protected Vector<String> letter = new Vector<String>();
	

	public String findLongestWord(Dictionary dico) {
		return dico.getLongestWordFor(getLetterAsString());
	}
	

	public void setLetter(String word) {
		letter.clear();
		addLetter(word);
	}
	

	public void addLetter(String word) {
		for (char c : word.toUpperCase().toCharArray())
			letter.add("" + c);
	}
	

	public void useLetter(String word) {
		int idx;
		for (char c : word.toUpperCase().toCharArray()) {
			if ((idx = letter.indexOf("" + c)) != -1)
				letter.remove(idx);
		}
	}
	

	public void pickLetter(Bag bag, int amount) {
		for (int i = 0; i < amount; i++)
			letter.add(bag.chooseLetter());
	}
	

/*	public String pickLetter(Bag bag) {
		String letter = bag.pickLetter();
		letter.add(letter);
		
		return letter;
	}*/

	public final Vector<String> getLetter() {
		return letter;
	}
	

	public final String getLetterAsString() {
		String ret = "";
		for (String letter : letter)
			ret += letter;
		
		return ret;
	}
	

	public final String getLetterImploded() {
		String ret = "";
		for (String letter : letter)
			ret += (ret.isEmpty() ? "" : ", ") + letter;
		
		return ret;
	}
	
	

	public static final Purse getInstance() {
		if (instance == null)
			instance = new Purse();
		
		return instance;
	}
}

