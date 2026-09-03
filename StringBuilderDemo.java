/**
 * Demonstrates the use of String and StringBuilder in Java.
 *
 * Key differences:
 * - String is immutable: every modification creates a new object in memory.
 * - StringBuilder is mutable: modifications happen in-place, making it
 *   more efficient for repeated concatenation or manipulation.
 */
public class StringBuilderDemo {

    public static void main(String[] args) {
        System.out.println("===== String Demo =====");
        stringDemo();

        System.out.println("\n===== StringBuilder Demo =====");
        stringBuilderDemo();

        System.out.println("\n===== Performance Comparison =====");
        performanceComparison();
    }

    // -------------------------------------------------------
    // String (immutable) operations
    // -------------------------------------------------------
    public static void stringDemo() {
        // Creation
        String greeting = "Hello";
        String name = "World";

        // Concatenation — each '+' produces a NEW String object
        String message = greeting + ", " + name + "!";
        System.out.println("Concatenation : " + message);

        // Length
        System.out.println("Length         : " + message.length());

        // Character access
        System.out.println("charAt(0)      : " + message.charAt(0));

        // Substring
        System.out.println("substring(0,5) : " + message.substring(0, 5));

        // Searching
        System.out.println("indexOf('World'): " + message.indexOf("World"));
        System.out.println("contains('Hello'): " + message.contains("Hello"));

        // Case conversion
        System.out.println("toUpperCase    : " + message.toUpperCase());
        System.out.println("toLowerCase    : " + message.toLowerCase());

        // Trim whitespace
        String padded = "   spaces   ";
        System.out.println("trim()         : \"" + padded.trim() + "\"");

        // Replace
        System.out.println("replace        : " + message.replace("World", "Java"));

        // Split
        String csv = "apple,banana,cherry";
        String[] fruits = csv.split(",");
        System.out.print("split(',')     : ");
        for (String fruit : fruits) {
            System.out.print("[" + fruit + "] ");
        }
        System.out.println();

        // Equality — always use .equals(), never ==
        String a = new String("test");
        String b = new String("test");
        System.out.println("a == b         : " + (a == b));        // false (different objects)
        System.out.println("a.equals(b)    : " + a.equals(b));     // true  (same content)
    }

    // -------------------------------------------------------
    // StringBuilder (mutable) operations
    // -------------------------------------------------------
    public static void stringBuilderDemo() {
        // Creation
        StringBuilder sb = new StringBuilder("Hello");
        System.out.println("Initial        : " + sb);

        // append — adds to the end
        sb.append(", ").append("World!");
        System.out.println("After append   : " + sb);

        // insert — inserts at a given index
        sb.insert(5, " Beautiful");
        System.out.println("After insert   : " + sb);

        // replace — replaces characters between start and end indices
        int start = sb.indexOf("Beautiful");
        int end = start + "Beautiful".length();
        sb.replace(start, end, "Wonderful");
        System.out.println("After replace  : " + sb);

        // delete — removes characters between start and end indices
        sb.delete(5, 15); // removes " Wonderful"
        System.out.println("After delete   : " + sb);

        // deleteCharAt — removes a single character
        sb.deleteCharAt(sb.length() - 1); // remove trailing '!'
        System.out.println("After deleteAt : " + sb);

        // reverse
        sb.reverse();
        System.out.println("After reverse  : " + sb);
        sb.reverse(); // reverse back to normal
        System.out.println("Reversed back  : " + sb);

        // capacity vs length
        StringBuilder fresh = new StringBuilder();  // default capacity = 16
        System.out.println("Capacity       : " + fresh.capacity());
        System.out.println("Length         : " + fresh.length());

        // Convert StringBuilder to String
        String result = sb.toString();
        System.out.println("toString()     : " + result);

        // Method chaining — the real power of StringBuilder
        String chained = new StringBuilder()
                .append("Method")
                .append(" chaining")
                .append(" is")
                .append(" elegant!")
                .toString();
        System.out.println("Chained        : " + chained);
    }

    // -------------------------------------------------------
    // Performance: String concatenation vs StringBuilder
    // -------------------------------------------------------
    public static void performanceComparison() {
        int iterations = 50_000;

        // String concatenation in a loop (slow — creates many objects)
        long startTime = System.currentTimeMillis();
        String s = "";
        for (int i = 0; i < iterations; i++) {
            s = s + "a";  // new String object every iteration!
        }
        long stringTime = System.currentTimeMillis() - startTime;

        // StringBuilder in a loop (fast — modifies in place)
        startTime = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < iterations; i++) {
            sb.append("a");  // same object, no extra allocation
        }
        String result = sb.toString();
        long builderTime = System.currentTimeMillis() - startTime;

        System.out.println("Iterations     : " + iterations);
        System.out.println("String time    : " + stringTime + " ms");
        System.out.println("StringBuilder  : " + builderTime + " ms");
        System.out.println("Both same len? : " + (s.length() == result.length()));
    }
}
