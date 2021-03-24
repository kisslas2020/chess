package com.codecool.chessopen;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class ChessResults {

    public List<String> getCompetitorsNamesFromFile(String fileName){

        Map<String, Integer> results = new HashMap<>();

        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader reader = new BufferedReader(fileReader);
            String line = "";
            while((line = reader.readLine()) != null) {
                String[] record = line.split(",");
                String name = record[0];
                int totalPoints = totalPoints(record);
                results.put(name, totalPoints);
            }
            fileReader.close();
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        } catch (IOException e) {
            e.printStackTrace();
        }


        return sortNamesByResults(results);
    }

    private List<String> sortNamesByResults(Map<String, Integer> results) {
        return results.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .map(map -> map.getKey())
                .collect(Collectors.toList());
    }

    private int totalPoints(String[] record) {
        int totalPoints = 0;
        for(int i = 1; i < record.length; i++) {
            totalPoints += Integer.parseInt(record[i]);
        }
        return totalPoints;
    }



}
