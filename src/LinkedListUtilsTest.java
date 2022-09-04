import static org.junit.jupiter.api.Assertions.*;

import java.util.Collection;
import java.util.LinkedList;

class LinkedListUtilsTest {

    @org.junit.jupiter.api.Test
    void insertSorted() {

        // Instantiate empty and test lists
        LinkedList<Integer> list = new LinkedList<>();
        LinkedList<Integer> testList = new LinkedList<>();

        // Add one element to empty list
        LinkedListUtils.insertSorted(list, 11);
        assertEquals(1, list.size());

        // Add one at start
        LinkedListUtils.insertSorted(list, 1);
        testList.add(1);
        testList.add(11);
        assertEquals(testList, list);

        // Add one at end
        LinkedListUtils.insertSorted(list, 22);
        testList.add(22);
        assertEquals(testList, list);

        // Add one in the middle
        LinkedListUtils.insertSorted(list, 15);
        testList.clear();
        testList.add(1);
        testList.add(11);
        testList.add(15);
        testList.add(22);
        assertEquals(testList, list);

    }

    @org.junit.jupiter.api.Test
    void removeMaximumValues() {
    }

    @org.junit.jupiter.api.Test
    void containsSubsequence() {
    }
}