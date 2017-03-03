package main.java.esiea.labadi_behra;

import java.util.Vector;
import main.java.esiea.labadi_behra.Util;
import main.java.esiea.labadi_behra.Game;

public abstract class Bot extends Player {
	protected static int counter = 0;
	
	
	public Bot(String name, String letter) {
		super(name, letter);
		counter++;
		this.name = "BOT_" + counter;
	}
	

	public boolean console() {
		return false;
	}
	protected final String getLongestExtension(Game game, Player player) {
		Vector<String> purseLetters = game.getPurse().getLetter();
		String res = "";
		
		for (String word : player.getWords())
			for (String possibleWord : game.getDictionary().getPossibleExtensionsFor(word))
				if (possibleWord.length() > res.length() && Util.hasSameLetters(Util.remainingChars(possibleWord, word), purseLetters))
					res = possibleWord;
		
		return res;
	}

	public String playGame(Game game) {
		String res;
		
		res = findWordFromSelf(game);
		if (res != null && !res.isEmpty())
			return res;

		res = game.getPurse().findLongestWord(game.getDictionary());
		if (res != null && !res.isEmpty())

		return findWordFromSelf(game);
		return res;
	}
	
	protected final String findWordFromSelf(Game game) {
		return getLongestExtension(game, this);
	}
	
}

