package main.java.esiea.labadi_behra;

import java.util.Vector;
import main.java.esiea.labadi_behra.Game;
import main.java.esiea.labadi_behra.Util;
import main.java.esiea.labadi_behra.Player;


public class CompleteOwnWord extends Move {

	public void playOption(Game game, String word, int idx) {
		assertOption(idx);
		
		String ownWord = (String)options.elementAt(idx);
		String remaining = Util.remainingChars(word, ownWord);
		game.getPurse().useLetter(remaining);
		System.out.println("Extending own word '" + ownWord + "' and using letters '" + remaining + "' from the purse");
	}

	public void showAvailableOption(int idxGlobal, int idxOpt) {
		assertOption(idxOpt);
		
		showOptionIndex(idxGlobal);
		System.out.println("Extend own word '" + (String)options.elementAt(idxOpt) + "'");
	}
	

	public boolean attempt(Game game, String word) {
		Vector<String> purseLetters = game.getPurse().getLetter();
		
		Player self = game.getCurrentPlayer();
		for (String ownWord : self.getWords())
			if (word.length() > ownWord.length() && Util.hasSameLetters(ownWord, word) && Util.hasSameLetters(word, Util.addCharsToVector(purseLetters, ownWord)))
				options.add(ownWord);
		
		return !options.isEmpty();
	}


}