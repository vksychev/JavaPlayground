import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.EmptyStackException;
import java.util.Stack;

/**
 * 1. пустая строка — правильная скобочная последовательность;
 * 2. правильная скобочная последовательность,
 *      взятая в скобки одного типа — правильная скобочная последовательность;
 * 3. правильная скобочная последовательность,
 *      к которой приписана слева или справа правильная скобочная последовательность
 *      — тоже правильная скобочная последовательность.
 */
public class ParenthesesSequenceExt {

    private static final String QUIT = "q";

    private static final char LEFT_PAREN     = '(';
    private static final char RIGHT_PAREN    = ')';
    private static final char LEFT_BRACE     = '{';
    private static final char RIGHT_BRACE    = '}';
    private static final char LEFT_BRACKET   = '[';
    private static final char RIGHT_BRACKET  = ']';

    // sequence = "()()" | "(({}[]))[[[" | "{}" | ...
    private static boolean isBalanced(String sequence) {
        Stack<Character> chacha = new Stack<Character>();
        for (int i = 0 ; i < sequence.length();i++){
            switch (sequence.charAt(i)){
            case RIGHT_BRACE:{
                chacha.push(RIGHT_BRACE);
                while(!chacha.pop().equals(LEFT_BRACE)){
                    if (chacha.isEmpty()) return false;
                }
                break;
            }
            case RIGHT_BRACKET:{
                chacha.push(RIGHT_BRACKET);
                while(!chacha.pop().equals(LEFT_BRACKET)){
                    if (chacha.isEmpty()) return false;
                }
                break;
            }
            case RIGHT_PAREN:{
                chacha.push(RIGHT_PAREN);
                while(!chacha.pop().equals(LEFT_PAREN)){
                    if (chacha.isEmpty()) return false;
                }
                break;
            }
            default:{
                chacha.push(sequence.charAt(i));
            }
            }
        }
        if (chacha.isEmpty())return true;
        return false;
    }

    public static void main(String[] args) {
        try (BufferedReader lineReader = new BufferedReader(new InputStreamReader(System.in))) {
            String sequence;
            while (!QUIT.equals(sequence = lineReader.readLine())) {
                System.out.println(isBalanced(sequence) ? "YES" : "NO");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}