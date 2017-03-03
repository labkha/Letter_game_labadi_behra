package main.java.esiea.labadi_behra;

import main.java.esiea.labadi_behra.Util;
import main.java.esiea.labadi_behra.Game;


public class FromPurse extends Move {

	public void playOption(Game game, String word, int idx) {
		assertOption(idx);
		
		game.getPurse().useLetter((String)options.elementAt(idx));
		System.out.println("Use only the Purse");
	}

	public void showAvailableOption(int idxGlobal, int idxOpt) {
		assertOption(idxOpt);
		
		showOptionIndex(idxGlobal);
		System.out.println("Use only the Purse");
	}	

	public boolean attempt(Game game, String word) {
		if (Util.hasSameLetters(word, game.getPurse().getLetter()))
			options.add(word);
		
		return !options.isEmpty();
	}
	

}