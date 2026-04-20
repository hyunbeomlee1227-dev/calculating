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

        return Calc();
    }

    public static int Calc() {

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

