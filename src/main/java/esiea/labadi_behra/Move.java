package main.java.esiea.labadi_behra;

import main.java.esiea.labadi_behra.Game;
import java.util.Vector;


public abstract class Move {

	protected Vector<Object> options = new Vector<Object>();
	
	
	

	abstract public void playOption(Game game, String word, int idx);
	

	abstract public boolean attempt(Game game, String word);
	

	public final void showAvailableOptions(int idx) {
		if (options.isEmpty())
			return;
		
		for (int opt = 0, len = options.size(); opt < len; opt++)
			showAvailableOption(idx, opt);
	}


	abstract public void showAvailableOption(int idxGlobal, int idxOpt);


	protected final void showOptionIndex(int idx) {
		System.out.print(idx + ". ");
	}
	
	public static final Move factory(String type) {
		try {
			Class<?> moveClass = Class.forName("esiea.labadi_behra.move." + type);
			
			if (moveClass != Move.class && Move.class.isAssignableFrom(moveClass))
				return (Move)moveClass.newInstance();
		}
		catch (Exception e) {
		}
		
		throw new RuntimeException(type + " is not an available move");
	}
	protected final void assertOption(int idx) {
		if (options.size() < (idx + 1) || idx < 0)
			throw new RuntimeException("Should never happen");
	}
	

	public final int getNbOptions() {
		return options.size();
	}
	
	
}
