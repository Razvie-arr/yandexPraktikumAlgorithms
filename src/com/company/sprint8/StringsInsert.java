package com.company.sprint8;

import java.io.*;
import java.util.*;
class Pair implements Comparable<Pair> {
    private final String s;
    private final int index;
    public Pair(String s, int index) {
        this.s = s;
        this.index = index;
    }

    public String getS() {
        return s;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public int compareTo(Pair pair) {
        return Integer.compare(this.getIndex(), pair.getIndex());
    }
}

public class StringsInsert {
    public static void main(String[] args) throws IOException {
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader reader = new BufferedReader(new FileReader("/Users/byastinov/IdeaProjects/yandex_praktikum_algorithms/src/com/company/sprint8/testcopy.txt"));
        String s = reader.readLine();
        int n = Integer.parseInt(reader.readLine());
        List<Pair> giftedStrings = new ArrayList<>(n);
//        Map<String, Integer> giftedMap = new LinkedHashMap<>(n);
        long startFillingPairs = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            StringTokenizer giftTokenizer = new StringTokenizer(reader.readLine(), " ");
            giftedStrings.add(new Pair(giftTokenizer.nextToken(), Integer.parseInt(giftTokenizer.nextToken())));
        }
        long endFillingPairs = System.currentTimeMillis();
        System.out.println("fillingPairsTime: " + (endFillingPairs - startFillingPairs));
        printStringWithGifts(s, giftedStrings, n);
    }

    public static void printStringWithGifts(String string, List<Pair> gifts, int n) throws IOException {
        int insertedCounter = 0;
        long startSortTime = System.currentTimeMillis();
        Collections.sort(gifts);
        long endSortTime = System.currentTimeMillis();
        System.out.println("SortTime: " + (endSortTime - startSortTime));
        long startInsertTime = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            String giftedString = gifts.get(i).getS();
            string = insert(string, gifts.get(i).getIndex(), giftedString, insertedCounter);
            insertedCounter += giftedString.length();
        }
        long endInsertTime = System.currentTimeMillis();
        System.out.println("InsertTime: " + (endInsertTime - startInsertTime));
        long startOutTime = System.currentTimeMillis();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        writer.write(string);
        writer.flush();
        long endOutTime = System.currentTimeMillis();
        System.out.println("SOUT Time: " + (endOutTime - startOutTime));
    }

    public static String insert(String string, int index, String substring, int insertedCounter) {
        int length = string.length();
        int shift = substring.length();
        if (index > length) {
            return null;
        }
        StringBuilder stringSB = new StringBuilder(string);
        StringBuilder insertSB = new StringBuilder();
        for (int i = 0; i <= shift - 1; i++) {
            insertSB.append(substring.charAt(i));
        }
        stringSB.insert(index + insertedCounter, insertSB);
        return stringSB.toString();
    }
}
