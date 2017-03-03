package main.java.esiea.labadi_behra;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import main.java.esiea.labadi_behra.Bag;
import main.java.esiea.labadi_behra.Purse;
import main.java.esiea.labadi_behra.Dictionary;
import main.java.esiea.labadi_behra.FrenchDictionary;
import main.java.esiea.labadi_behra.EnglishDictionary;
import main.java.esiea.labadi_behra.Move;
import main.java.esiea.labadi_behra.Player;


public abstract class Game {
	protected static Game instance = null;
	protected static final int WIN_SCORE = 5;
	
	protected FrenchDictionary dico = new FrenchDictionary();
	protected EnglishDictionary dico1 = new EnglishDictionary();
	protected Bag bag = Bag.getInstance();
	protected Purse purse = Purse.getInstance();
	protected Vector<Player> players = new Vector<Player>();
	protected int nbPlayers = 0;
	protected int curPlayerIdx = -1;
	protected Player curPlayer;
	protected final String[] moves = {"FromPurse", "StealFromOpponent", "CompleteOwnWord"};
	
		
	public final void init() {


		checkNbPlayers();
		play();
		showScores();
	}
	

	protected final void checkNbPlayers() {
		nbPlayers = players.size();
		if (nbPlayers < 2) {
			System.out.println("There must be at least 2 players to play !!");
			System.exit(0);
		}
	}
	

	protected final void play() {
		String word;
		
		while (true) {
			getNextPlayer();
			purse.pickLetter(bag, 2);
			showBoardStatus(true);
			word = curPlayer.play(this).trim().toUpperCase();
			if (assertWordExists(word)) {
				tryToPlaceWord(word);
				if (curPlayer.getScore() >= WIN_SCORE)
					break;
			}
		}
	}
	

	public final Purse getPurse() {
		return purse;
	}
	

	protected final void showScores() {
		System.out.println("");
		System.out.println("Player " + curPlayer.getName() + " won !");
		System.out.println("");
		System.out.println("Scores:");

		for (int i = 0, len = players.size(); i < len; i++)
			System.out.println((i + 1) + ". " + players.elementAt(i).getName() + " : " + players.elementAt(i).getScore());
	}

	protected final void tryToPlaceWord(String word) {
		HashMap<Integer, Pair<Move, Integer>> options = getPossibleMoveOptions(word);
		if (options.isEmpty()) {
			System.out.println("Cannot place the word '" + word + "'");
			return;
		}
		
		
		System.out.print("Placing the word '" + word + "'");
		
		showAllOptions(options, false);
		if (options.size() == 1)
			playOption(word, options.get(1));
		else
			playOption(word, options.get(curPlayer.chooseMoveOption(options)));
		
		curPlayer.addWord(word);
//		purse.pickLetter(bag);
	}

	protected final void showAllOptions(HashMap<Integer, Pair<Move, Integer>> options, boolean force) {
		if (!force && (options.size() < 2 || !curPlayer.needsConsole()))
			return;
		
		for (Map.Entry<Integer, Pair<Move, Integer>> entry : options.entrySet())
			entry.getValue().getFirst().showAvailableOption(entry.getKey(), entry.getValue().getSecond());
	}	

	protected final void showBoardStatus(boolean showCurrentPlayer) {
		System.out.println("");
		if (showCurrentPlayer)
			System.out.println("= " + curPlayer.getName() + "'s turn!\n");
		System.out.println("Please play an other word, this one is already in game:");
		for (Player player : players)
			System.out.println("   * " + player.getName() + ": " + player.getWordsImploded());
		
		System.out.println("Letters in the purse:");
		System.out.println(purse.getLetter());
	}

	protected final HashMap<Integer, Pair<Move, Integer>> getPossibleMoveOptions(String word) {
		HashMap<Integer, Pair<Move, Integer>> options = new HashMap<Integer, Pair<Move, Integer>>();
		int cur = 1;
		for (String method : moves) {
			Move move = Move.factory(method);
			if (move.attempt(this, word)) {
				for (int i = 0, len = move.getNbOptions(); i < len; i++) {
					options.put(cur, new Pair<Move, Integer>(move, i));
					cur++;
				}
			}
		}
		
		return options;
	}	


	protected final boolean assertWordExists(String word) {
		if (word == null || word.isEmpty()) {
			System.out.println("Skipping ");
			return false;
		}
		
		if (!dico.exists(word)) {
			System.out.println("This word does not exist");
			return false;
		}
		
		return true;
	}
	
	protected final void playOption(String word, Pair<Move, Integer> option) {
		option.getFirst().playOption(this, word, option.getSecond().intValue());
	}

	protected final Player getNextPlayer() {
		curPlayerIdx = (curPlayerIdx + 1) % nbPlayers;
		curPlayer = players.elementAt(curPlayerIdx);
		
		return curPlayer;
	}

	public static final Game factory(String type) {
		if (instance != null)
			return instance;
		
		try {
			Class<?> gameClass = Class.forName("esiea.labadi_behra.game." + type);
			
			if (gameClass != Game.class && Game.class.isAssignableFrom(gameClass)) {
				instance = (Game)gameClass.newInstance();
				return instance;
			}
		}
		catch (Exception e) {
		}
		
		throw new RuntimeException(type + " is not an available game mode");
	}	


	public final Player getCurrentPlayer() {
		return curPlayer;
	}

	public final Vector<Player> getOpponents() {
		Vector<Player> opponents = new Vector<Player>(players);
		opponents.remove(curPlayer);
		
		return opponents;
	}

	protected final boolean checkName(String name) {
		if (name == null || name.isEmpty())
			return false;
		
		for (Player player : players)
			if (player.hasName(name)) {
				System.out.println("PLease take an ohter, this name is already taken");
				return false;
			}
		
		return true;
	}

	public final Dictionary getDictionary() {
		return dico;
	}


}
