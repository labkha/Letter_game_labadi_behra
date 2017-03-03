package main.java.esiea.labadi_behra;

import java.util.Set;
import java.util.Vector;
import java.io.InputStream;
import java.util.HashSet;
import java.util.ArrayList;


public abstract class Util {

	public static ArrayList<String> getAllSubstrings(String str, int minLen) {
		Set<String> perm = new HashSet<String>();
		String tmp;
		
		for (int i = 0; i < str.length(); i++) {
			for (int k = 0; k < i; k++)
				if ((tmp = str.substring(k, i) + str.substring(i + 1)).length() >= minLen)
					perm.add(tmp);
	        for (int j = i + 1; j <= str.length(); j++)
	        	if ((tmp = str.substring(i, j)).length() >= minLen)
	        		perm.add(tmp);
	    }
		return null;
		

	}


	public static Set<String> findPermutations(String str) {
		Set<String> perm = new HashSet<String>();
		
		findPermutations("", str, perm);
		
		return perm;
	}

	protected static Set<String> findPermutations(String pivot, String str, Set<String> list) {
		if (str.isEmpty())
			return list;

		for (int i = 0, len = str.length(); i < len; i++) {
			list.add(pivot + str);
			findPermutations(pivot + str.charAt(i), str.substring(0, i) + str.substring(i + 1, len), list);
		}

		return list;
	}
    

	public static boolean hasSameLetters(String word, String available) {
		Vector<String> res = new Vector<String>();
		for (char c : available.toCharArray())
			res.add("" + c);
		
		return hasSameLetters(word, res);
	}


	public static boolean hasSameLetters(String word, Vector<String> available) {
		Vector<String> copy = new Vector<String>(available);
		int idx;

		for (char c : word.toCharArray()) {
			if ((idx = copy.indexOf("" + c)) == -1)
				return false;
			
			copy.remove(idx);
		}
		
		return true;
	}
	

	public static String remainingChars(String origin, String subtract) {
		String res = new String(origin);
		for (char c : subtract.toCharArray())
			res = res.replaceFirst("" + c, "");
		
		return res;
	}
	

	public static Vector<String> addCharsToVector(Vector<String> list, String word) {
		Vector<String> res = new Vector<String>(list);
		for (char c : word.toCharArray())
			res.add("" + c);
		
		return res;
	}
	

	public static InputStream openResource(String name) {
		InputStream in = null;
		
		try {
			in = Util.class.getResourceAsStream(name);
			if (in == null)
				throw new Exception();
		}
		catch (Exception e) {
			throw new RuntimeException("Could not find the resource: '" + name + "'");
		}
		
		return in;
	}
}


