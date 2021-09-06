package digital.number.scanner.service.impl;

import digital.number.scanner.service.SymbolMatcher;

import java.util.*;

public class DefaultSymbolMatcher implements SymbolMatcher {
    private static final int MATRIX_SIZE = 3;
    private final Map<String, Character> stringToDigitMap = new HashMap<>();

    public DefaultSymbolMatcher() {
        stringToDigitMap.put(
                "..." +
                "..|" +
                "..|", '1');
        stringToDigitMap.put(
                "._." +
                "._|" +
                "|_.", '2');
        stringToDigitMap.put(
                "._." +
                "._|" +
                "._|", '3');
        stringToDigitMap.put(
                "..." +
                "|_|" +
                "..|", '4');
        stringToDigitMap.put(
                "._." +
                "|_." +
                "._|", '5');
        stringToDigitMap.put(
                "._." +
                "|_." +
                "|_|", '6');
        stringToDigitMap.put(
                "._." +
                "..|" +
                "..|", '7');
        stringToDigitMap.put(
                "._." +
                "|_|" +
                "|_|", '8');
        stringToDigitMap.put(
                "._." +
                "|_|" +
                "._|", '9');
        stringToDigitMap.put(
                "._." +
                "|.|" +
                "|_|", '0');
    }

    @Override
    public char match(char[][] matrix) {
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < MATRIX_SIZE; i++){
            for (int j = 0; j < MATRIX_SIZE; j++) {
                char c = matrix[i][j];
                if (c == '|' || c =='_'){
                    builder.append(c);
                } else if (Character.isWhitespace(c)){
                    builder.append('.');
                } else {
                    return '?';
                }
            }
        }

        return stringToDigitMap.getOrDefault(builder.toString(), '?');
    }
}
