package org.example;

import java.util.*;

public class PhoneLetter {

    private Map<Character, String[]> relationMap;

    public PhoneLetter() {
        relationMap = new HashMap<>();
        relationMap.put('2', new String[]{"a", "b", "c"});
        relationMap.put('3', new String[]{"d", "e", "f"});
        relationMap.put('4', new String[]{"g", "h", "i"});
        relationMap.put('5', new String[]{"j", "k", "l"});
        relationMap.put('6', new String[]{"m", "n", "o"});
        relationMap.put('7', new String[]{"p", "q", "r", "s"});
        relationMap.put('8', new String[]{"t", "u", "v"});
        relationMap.put('9', new String[]{"w", "x", "y", "z"});
    }


    public Map<String, List<String>> cache = new HashMap<>();

    public List<String> getAllLetters(String number, int index) {

//        if (cache.containsKey(number.substring(index))) {
//            return cache.get(number.substring(index));
//        }
        List<String> results = new ArrayList<>();
        if (index == number.length() - 1) {
            String[] strings = relationMap.get(number.charAt(index));
            for (int i = 0; i < strings.length; i++)
                results.add(strings[i]);
            return results;
        }
        char item = number.charAt(index);
        if (!relationMap.containsKey(item)) {
            throw new RuntimeException("input error");
        }
        String[] temp = relationMap.get(item);

            /*
                TODO：加缓存
             */

        List<String> nextAllletters = getAllLetters(number, index + 1);
        results = generateStringFromArray(temp, nextAllletters);

//        cache.put(number.substring(index), results);
        return results;
    }

    private List<String> generateStringFromArray(String[] temp, List<String> subStringList) {
        List<String> results = new ArrayList<>();
        for (int i = 0; i < temp.length; i++) {
            results.addAll(generateStringFromSubArray(temp[i], subStringList));
        }
        return results;
    }

    private List<String> generateStringFromSubArray(String current, List<String> nextAllletters) {
        List<String> results = new ArrayList<>();
        for (int j = 0; j < nextAllletters.size(); j++) {
            results.add(current + nextAllletters.get(j));
        }
        return results;
    }

    public static void main(String[] args) {
        PhoneLetter phoneLetter = new PhoneLetter();
        List<String> allLetters = phoneLetter.getAllLetters("234", 0);
        for (int i = 0; i < allLetters.size(); i++) {
            System.out.println(allLetters.get(i));
        }
    }

}
