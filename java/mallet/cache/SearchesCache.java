package mallet.cache;

import java.util.ArrayList;
import java.util.List;

public class SearchesCache {
	private static List<String> searchesHolder = new ArrayList<String>();
	
	public static void addSearchEntry(String entry) {
		if (searchesHolder.size() == 0) {
			
			searchesHolder.add(entry);
		} else {
			addToHolderValidated(entry);
		}
	}
	
	private static void addToHolderValidated(String entry) {
		boolean stringIsAlreadyInHolder = checkIsInHolder( entry );
		
		if ( stringIsAlreadyInHolder == false ) {
			searchesHolder.add(entry);
		}
		
	}
	
	private static boolean checkIsInHolder(String entry) {
		int isIn = searchesHolder.indexOf(entry);
		
		return ( isIn != -1 ) ? true : false;

	}
	
	public static List<String> getAllSearchEntries() {
		return searchesHolder;
	}
}