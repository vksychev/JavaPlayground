import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * ( 1 + ( ( 2 + 3 ) * ( 4 * 5 ) ) ) = 101
 * ( 1 + ( 5 * ( 4 * 5 ) ) )
 * ( 1 + ( 5 * 20 ) ) = 101
 * ( 1 + 100 ) = 101
 * <p>
 * 1 + ( 2 + 3 ) * 4 * 5 = 101
 * 1 + 5 * 4 * 5 = 101
 * 1 + 5 * 20 = 101
 * 1 + 100 = 101
 * 20 / 4 = 5
 * (101 - 1) / 5 = 20
 * <p>
 * Считаем, что операции деления на ноль отсутствуют
 */
public class SolverExt {

    private static final String QUIT = "q";

    private static final char LEFT_PAREN = '(';
    private static final char RIGHT_PAREN = ')';
    private static final char PLUS = '+';
    private static final char MINUS = '-';
    private static final char TIMES = '*';
    private static final char DIVISION = '/';

    private static final byte PAREN_PRIORITY = -1;
    private static final byte TIMES_PRIORITY = 1;
    private static final byte PLUS_PRIORITY = 2;


    private static byte getPriority(char o) {
        switch (o) {
        case PLUS:
        case MINUS: {
            return PLUS_PRIORITY;
        }
        case TIMES:
        case DIVISION: {
            return TIMES_PRIORITY;
        }
        case LEFT_PAREN: {
            return PAREN_PRIORITY;
        }
        default: {
            return 100;
        }
        }
    }

    private static boolean canPop(char p1, char p2) {
        return getPriority(p1) >= 0 && getPriority(p2) >= 0 && getPriority(p1) >= getPriority(p2);
    }

    private static void solve(char last, IStack<Double> numbers) {
        switch (last) {
        case PLUS: {
            numbers.push(numbers.pop() + numbers.pop());
            break;
        }
        case MINUS: {
            double a = numbers.pop();
            numbers.push(numbers.pop() - a);
            break;
        }
        case TIMES: {
            double a = numbers.pop();
            numbers.push(numbers.pop() * a);
            break;
        }
        case DIVISION: {
            double a = numbers.pop();
            numbers.push(numbers.pop() / a);
            break;
        }
        }
    }

    // 1+1+1=3
    private static double evaluate(String[] values) {
        IStack<Double> numbers = new LinkedStack<>();
        IStack<Character> operators = new LinkedStack<>();
        for (int i = 0; i < values.length; i++) {
            switch (values[i].charAt(0)) {
            case LEFT_PAREN: {
                operators.push(LEFT_PAREN);
                break;
            }
            case RIGHT_PAREN: {
                char last = '0';
                if (i == values.length - 1) {
                    while (!operators.isEmpty()) {
                        last = operators.pop();
                        solve(last, numbers);
                    }
                }else {
                    while (last != LEFT_PAREN) {
                        last = operators.pop();
                        solve(last, numbers);
                    }
                }

                break;
            }
            case PLUS:
            case MINUS:
            case TIMES:
            case DIVISION: {
                if (operators.isEmpty()) {
                    operators.push(values[i].charAt(0));
                } else {
                    while (!operators.isEmpty()) {
                        char last = operators.pop();
                        if (canPop(values[i].charAt(0), last)) {
                            solve(last, numbers);
                        } else {
                            operators.push(last);
                            break;
                        }
                    }
                    operators.push(values[i].charAt(0));
                }
                break;
            }

            default: {
                try {
                    numbers.push(Double.parseDouble(values[i]));
                } catch (NumberFormatException e) {
                    System.err.println("Неверный формат выражения");
                    throw e;
                }
                if (i == values.length - 1) {
                    while (!operators.isEmpty()) {
                        char last = operators.pop();
                        solve(last, numbers);
                    }
                }
            }
            }
        }
        return numbers.pop();
    }

    public static void main(String[] args) {
        try (BufferedReader lineReader = new BufferedReader(new InputStreamReader(System.in))) {
            String sequence;
            while (!QUIT.equals(sequence = lineReader.readLine())) {
                System.out.println(evaluate(sequence.split(" ")));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}