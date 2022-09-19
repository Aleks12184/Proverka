import java.util.InputMismatchException;
import java.util.Scanner;

public class Kalk {


    static final String[] RIM_NUMBERS = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
            "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
            "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
            "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
            "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
            "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
            "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите выражение (1+2) или (I+V) + Enter");
        String userString = scanner.nextLine();
        int operationIndex = -1;
        char operation = '@';
        for (int i = 0; i < userString.length(); i++) {
            char c = userString.charAt(i);
            if (c == '+' || c == '-' || c == '/' || c == '*') {
                operation = c;
                operationIndex = i;
            }
        }
        if (operationIndex == -1) {
            throw new InputMismatchException("Нет знака операции");
        }
        String first = userString.substring(0, operationIndex).trim();
        String second = userString.substring(operationIndex + 1).trim();
        calculate(first, second, operation);
        scanner.close();
    }

    private static String arabicToRim(int numArab) {
        return RIM_NUMBERS[numArab - 1];
    }

    private static int rimToArabic(String rimNumber) {
        for (int i = 0; i < RIM_NUMBERS.length; ++i) {
            if (RIM_NUMBERS[i].equals(rimNumber)) {
                return i + 1;
            }
        }
        return -1;
    }

    private static void calculate(String first, String second, char operation) {
        if (!(ifArabic(first) && ifArabic(second) || ifRim(first) && ifRim(second))) {
            throw new InputMismatchException("Неправильный формат чисел");
        }
        boolean ifBothArabic = ifArabic(first) && ifArabic(second);
        int num1;
        int num2;
        if (ifBothArabic) {
            num1 = Integer.parseInt(first);
            num2 = Integer.parseInt(second);
        } else {
            num1 = rimToArabic(first);
            num2 = rimToArabic(second);
        }
        if (!(1 <= num1 && num1 <= 10 && 1 <= num2 && num2 <= 10)) {
            throw new IllegalArgumentException("Числа должны быть от 1 до 10 включительно");
        }
        int result;
        switch (operation) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                result = num1 / num2;
                break;
            default:
                throw new IllegalArgumentException("Invalid operation");
        }
        if (ifBothArabic) {
            System.out.println("Результат вычисления:\n" + result);
        } else {
            if (result <= 0) {
                throw new ArithmeticException("Римские числа должны быть положительные");
            }
            System.out.println("Результат вычисления:\n" + arabicToRim(result));
        }
    }

    private static boolean ifArabic(String num) {
        num = num.trim();
        for (int i = 0; i < num.length(); i++) {
            if (!Character.isDigit(num.charAt(i))) {
                return false;
            }
        }
        return true;

    }

    private static boolean ifRim(String num) {
        num = num.trim();
        for (String x : RIM_NUMBERS) {
            if (x.equals(num)) {
                return true;
            }
        }
        return false;
    }

}
