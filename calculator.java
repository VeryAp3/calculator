
import java.util.Scanner;

public class calculator {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите два операнда и один оператор (+, -, /, *)");
        String str = sc.nextLine();
        System.out.println(Calculate(str));

    }

    static String Calculate(String str) throws Exception {
        int a;
        int b;
        String[] split = str.split("[+\\-*/!%:№?)(_~]");
        String operator;
        boolean isRoman;
        operator = Operator(str);
        if (operator == null) throw new Exception("т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        if (split.length != 2) {throw new Exception("т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");}
        String result;

        if (Rom.isRoman(split[0]) && Rom.isRoman(split[1])) {
            a = Rom.toArabic(split[0]);
            b = Rom.toArabic(split[1]);
            isRoman = true;

        } else if (!Rom.isRoman(split[0]) && !Rom.isRoman(split[1])) {
            a = Integer.parseInt(split[0]);
            b = Integer.parseInt(split[1]);
            isRoman = false;
        } else {
            throw new Exception("т.к. используются одновременно разные системы счисления");
        }
        if (a > 10 || b > 10) {
            throw new Exception("т.к. число больше 10");
        }
        int arabic = calculation(a, b, operator);
        if (isRoman) {
            if (arabic <= 0) {
                throw new Exception("т.к. в римской системе нет отрицательных чисел");
            }
            result = Rom.toRoman(arabic);
        } else {
            result = String.valueOf(arabic);
        }
        return result;

    }

    static String Operator(String str) {
        if (str.contains("+")) return "+";
        else if (str.contains("-")) return "-";
        else if (str.contains("*")) return "*";
        else if (str.contains("/")) return "/";
        else return null;


    }

    static int calculation(int a, int b, String operator) {
        return switch (operator) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            case "/" -> a / b;
            default -> 0;
        };

    }

    class Rom {

        static String[] romanArr = new String[]{"0", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
                "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
                "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
                "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
                "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
                "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
                "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"};



        static boolean isRoman(String val) {
            for (int i = 0; i < romanArr.length; i++) {
                if (val.equals(romanArr[i])) {
                    return true;
                }
            }
            return false;
        }
        static int toArabic(String romNum) {
            for (int i = 0; i < romanArr.length; i++) {
                if (romNum.equals(romanArr[i])) {
                    return i;
                }
            }
            return -1;
        }

        static String toRoman(int arabic) {
            return romanArr[arabic];
        }
    }


}
