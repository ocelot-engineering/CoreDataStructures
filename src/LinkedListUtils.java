

import java.util.ArrayList;
import java.util.Collections;
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

        // Early return in inputs are invalid
        if (N <= 0 || list == null || list.size() == 0) {
            return;
        }

        /* Generate a list of the unique largest values */
        // Deep copy list and order it
        ArrayList<String> nLargestValues = new ArrayList<>();  // used to store largest unique values
        ArrayList<String> orderedList = new ArrayList<>(list); // add all elements to array list, then order it
        orderedList.sort(String::compareTo);                   // reverse sort the list
        Collections.reverse(orderedList);

        // Build N Largest values list
        for (String value : orderedList) {
            if (!nLargestValues.contains(value)) {
                nLargestValues.add(value);
            }
            if (nLargestValues.size() >= N) {
                break;
            }
        }

        /* Remove N largest values */
        // Loop through N largest values and remove them
        for (String value : nLargestValues) {
            while (list.removeFirstOccurrence(value)) ; // while loop returns false once there is nothing to remove
        }

    }

    public static boolean containsSubsequence(LinkedList<Integer> one, LinkedList<Integer> two) {

		/*
		   Terminology:
		   one is main-sequence
		   two is sub-sequence
		*/

        // Early return from empty or null
        if (one == null || one.isEmpty() || two == null || two.isEmpty()) {
            return false;
        }

        // Take the first element of the subsequence, then see if it's contained in the main sequence, if it
        // is, will return the index of this element and therefore give a starting point for checking the
        // rest of the sequence.
        int startIndex = one.indexOf(two.peekFirst());
        if (startIndex == -1) { // doesn't contain first element
            return false;
        }

        // Iterate through the subsequence and see if the entire subsequence is present in the main sequence.
        ListIterator<Integer> oneIter = one.listIterator(startIndex);

        for (Integer element : two) {
            // easier to read with it split up
            if (!oneIter.hasNext()) {
                return false;
            }
            if (oneIter.next().compareTo(element) != 0) {
                return false;
            }
        }

        // Entire sequence of sub is present in main, no more checks required so return true
        return true;
    }
}
