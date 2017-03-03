package main.java.esiea.labadi_behra;

import java.util.HashMap;
import main.java.esiea.labadi_behra.Move;
import main.java.esiea.labadi_behra.Pair;
import main.java.esiea.labadi_behra.Game;


public class Human extends Player {
	public Human(String name, String letter) {
		super(name, letter);
	}
	
	

	public boolean needsConsole() {
		return true;
	}
	

	public int chooseMoveOption(HashMap<Integer, Pair<Move, Integer>> options) {
		do {
			System.out.print("Choose option: ");
			String choice = System.console().readLine();
			if (choice == null || (choice = choice.trim()).isEmpty())
				continue;
			try {
				Integer idx = Integer.valueOf(choice);
				if (options.containsKey(idx))
					return idx.intValue();
			}
			catch (Exception e) {}
		} while (true);
	}
	


	public String play(Game game) {
		System.out.print("Please enter a word: ");

		return System.console().readLine();
	}
}

