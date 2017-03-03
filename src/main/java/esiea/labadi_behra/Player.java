package main.java.esiea.labadi_behra;

import main.java.esiea.labadi_behra.Game;
import java.util.Vector;
import main.java.esiea.labadi_behra.Move;
import main.java.esiea.labadi_behra.Pair;
import java.util.HashMap;


public abstract class Player {
	protected String name;
	protected String letter;
	protected Vector<String> words = new Vector<String>();
	
	
	public Player(String name, String letter) {
		this.name = name;
		this.letter = letter;
	}
	

	public final void addWord(String word) {
		words.add(word);
	}
	

	public final void removeWord(String word) {
		words.remove(word);
	}
	
	abstract public boolean needsConsole();
	
	abstract public String play(Game game);

	abstract public int chooseMoveOption(HashMap<Integer, Pair<Move, Integer>> options);


	public final int getScore() {
		return words.size();
	}

	public final String getName() {
		return name;
	}
	

	public final Vector<String> getWords() {
		return words;
	}

	public final boolean hasName(String test) {
		return name.equalsIgnoreCase(test);
	}
	
	public final String getWordsImploded() {
		String ret = "";
		for (String word : words)
			ret += (ret.isEmpty() ? "" : ", ") + word;
		
		return ret;
	}

	public final String getLetter() {
		return letter;
	}

}

