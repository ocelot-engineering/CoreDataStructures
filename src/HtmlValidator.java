import java.util.Queue;
import java.util.Stack;

/*
 * SD2x Homework #2
 * Implement the method below according to the specification in the assignment description.
 * Please be sure not to change the method signature!
 */

public class HtmlValidator {

    public static Stack<HtmlTag> isValidHtml(Queue<HtmlTag> tags) {

        HtmlTag currentTag;
        Stack<HtmlTag> openStack = new Stack<>();

        // Build stack and pop all matching closed tags.
        // Return stack or null if closed tags don't match open ones
        while (!tags.isEmpty()) {
            currentTag = tags.poll();

            if (currentTag.isSelfClosing()) {
                continue;
            }
            if (currentTag.isOpenTag()) {
                // currentTag is open, add to stack
                openStack.add(currentTag);
            } else {
                // currentTag is closed, remove from stack or return depending on issue.

                // Check if stack is empty (i.e. not open tag to match the current closed one)
                if (openStack.empty()) {
                    return null; // closed tag has no open, return null according to brief
                }

                // Check if closed tag matches
                if (openStack.peek().matches(currentTag)) {
                    openStack.pop();  // closed tag matches, pop from stack and continue
                } else {
                    return openStack; // closed tag doesn't match, returning stack
                }
            }
        }

        // If HTML file is formatted correctly, shown by returning an empty stack
        return openStack;
    }
}
