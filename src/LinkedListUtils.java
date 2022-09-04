

import java.util.LinkedList;
import java.util.ListIterator;

/*
 * SD2x Homework #1
 * Implement the methods below according to the specification in the assignment description.
 * Please be sure not to change the signature of any of the methods!
 */

public class LinkedListUtils {
	
	public static void insertSorted(LinkedList<Integer> list, int value) {

		// Early return if list is null
		if (list == null) {
			return;
		}

		// Get the current value in the head
		Integer current = list.peekFirst();

		// Early return if ll is empty
		if (current == null) {
			list.addFirst(value);
			return;
		}

		// Insert in front of head if appropriate
		if (value < current) {
			list.addFirst(value);
			return;
		}

		// Create iterator for the ll
		ListIterator<Integer> intIterator = list.listIterator();

		// Proceed through list
		while(value > current & intIterator.hasNext()) {
			current = intIterator.next();
		}

		// Insert after tail if appropriate (reached end of the iterator without value < current, meaning value is the
		// largest element in the list and therefore should be added to the end.)
		if(!intIterator.hasNext() & value > current) {
			list.addLast(value);
			return;
		}

		// Insert value in appropriate spot (just before the current element)
		intIterator.previous();
		intIterator.add(value);

	}
	

	public static void removeMaximumValues(LinkedList<String> list, int N) {

		/* IMPLEMENT THIS METHOD! */

	}
	
	public static boolean containsSubsequence(LinkedList<Integer> one, LinkedList<Integer> two) {

		/* IMPLEMENT THIS METHOD! */
		
		return true; // this line is here only so this code will compile if you don't modify it
	}
}
