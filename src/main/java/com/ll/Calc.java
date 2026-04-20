package com.ll;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calc {
    static List<String> tokens = new ArrayList<>();
    static int pos = 0;

    public static int run(String expression) {
        tokens.clear();
        pos = 0;

        Pattern pattern = Pattern.compile("\\d+|[+\\-*/()]");
        Matcher matcher = pattern.matcher(expression);

        while (matcher.find()) {
            tokens.add(matcher.group());
        }

        Calculate(0, tokens.size() - 1);

        return Integer.parseInt(tokens.get(0));
    }

    public static void Calculate(int start, int end) {
        ChangeMinus();

        if (tokens.size() == 1)
            return;

        int parseStartIndex = tokens.lastIndexOf("(");
        if (parseStartIndex != -1 && start <= parseStartIndex)
            parseCalc();

        CalcBasicOperate(start, end);
    }

    private static void CalcBasicOperate(int start, int end) {
        for (int i = start; i < tokens.size() && i < end; i++) {
            if (tokens.get(i).equals("*") || tokens.get(i).equals("/")) {

                int a = Integer.parseInt(tokens.get(i - 1));
                int b = Integer.parseInt(tokens.get(i + 1));

                int result = tokens.get(i).equals("*") ? a * b : a / b;

                tokens.set(i - 1, String.valueOf(result));

                tokens.remove(i);
                tokens.remove(i);

                --i;
                --end;
            }
        }

        for (int i = start; i < tokens.size() && i < end; i++) {

            if (tokens.get(i).equals("+") || tokens.get(i).equals("-")) {

                int a = Integer.parseInt(tokens.get(i - 1));
                int b = Integer.parseInt(tokens.get(i + 1));

                int result = tokens.get(i).equals("+") ? a + b : a - b;

                tokens.set(i - 1, String.valueOf(result));

                tokens.remove(i);
                tokens.remove(i);

                --i;
                --end;
            }
        }
    }

    public static void parseCalc() {
        int parseStartIndex = tokens.lastIndexOf("(");

        if (parseStartIndex == -1)
            return;

        int parseEndIndex = tokens.indexOf(")");
        Calculate(parseStartIndex + 1, parseEndIndex);
        tokens.remove(tokens.lastIndexOf("("));
        tokens.remove(")");

        parseCalc();
    }

    public static void ChangeMinus() {
        for (int i = 0; i < tokens.size(); ++i) {
            if (!tokens.get(i).equals("-")) continue;

            if (i == 0 || isOperator(tokens.get(i - 1))) {
                if (i + 1 < tokens.size()) {
                    tokens.set(i + 1, "-" + tokens.get(i + 1));
                    tokens.remove(i);
                    i--;
                }
            }
        }
    }

    static boolean isOperator(String minusCheck) {
        return minusCheck.equals("+")
                || minusCheck.equals("-")
                || minusCheck.equals("*")
                || minusCheck.equals("/")
                || minusCheck.equals("(");
    }
}

