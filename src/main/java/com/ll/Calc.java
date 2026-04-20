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

        ChangeMinus();

        return Calc(0, expression.length() - 1);
    }

    public static int Calc(int start, int end) {
        if (tokens.size() == 1)
            return Integer.parseInt(tokens.getFirst());

        int parseStartIndex = tokens.lastIndexOf("(");
        if (parseStartIndex != -1 && start > parseStartIndex)
            parseCalc();

        for (int i = start; i < end; ++i)
        {

        }
    }

    public static void parseCalc(){
        int parseStartIndex = tokens.lastIndexOf("(");

        if (parseStartIndex == -1)
            return;

        int parseEndIndex = tokens.indexOf(")");
        Calc(parseStartIndex, parseEndIndex);
        tokens.remove(tokens.lastIndexOf("("));
        tokens.remove(")");
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

