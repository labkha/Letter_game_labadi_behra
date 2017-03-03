package main.java.esiea.labadi_behra;

public class Pair<E1, E2> {
	private final E1 first;
	private final E2 second;

	public Pair(E1 first, E2 second) {
		this.first = first;
		this.second = second;
	}

	public E1 getFirst() {
		return first;
	}
	
	public E2 getSecond() {
		return second;
	}

	@Override
	public int hashCode() {
		return first.hashCode() ^ second.hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Pair))
			return false;

		try {
			@SuppressWarnings("unchecked")
			Pair<E1, E2> pair = (Pair<E1, E2>)o;
			
			return this.first.equals(pair.getFirst()) && this.second.equals(pair.getSecond());
		}
		catch (ClassCastException e) {}
		
		return false;
	}
}

