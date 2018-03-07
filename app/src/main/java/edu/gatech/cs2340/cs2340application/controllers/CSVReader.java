package edu.gatech.cs2340.cs2340application.controllers;

import java.util.Scanner;

import java.io.File;
import java.util.Scanner;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.gatech.cs2340.cs2340application.model.Shelter;
import edu.gatech.cs2340.cs2340application.model.ShelterModel;

public class CSVReader {

        private static final char DEFAULT_SEPARATOR = ',';
        private static final char DEFAULT_QUOTE = '"';

        public static void parseCSV() throws Exception {
            ShelterModel model = ShelterModel.INSTANCE;

            String csvFile = "homeless_shelter_database.csv";

            Scanner scanner = new Scanner(new File(csvFile));
            while (scanner.hasNext()) {
                List<String> line = parseLine(scanner.nextLine());
                int key = Integer.parseInt(line.get(0));
                String name = line.get(1);
                int capacity = Integer.parseInt(line.get(2));
                //restrictions is 3 (string)
                double longitude = Double.parseDouble(line.get(4));
                double latitude = Double.parseDouble(line.get(5));
                //address is 6
                String address = line.get(6);
                String phoneNumber = line.get(7);
                String notes = line.get(8);
                //special notes is 7
                //phone number is 8
                Shelter rick = new Shelter(key, name, capacity, latitude, longitude, address, phoneNumber, notes);
                model.addItem(rick);

                //System.out.println("Country [id= " + line.get(0) + ", code= " + line.get(1) + " , name=" + line.get(2) + "]");
            }
            scanner.close();

        }

        public static List<String> parseLine(String cvsLine) {
            return parseLine(cvsLine, DEFAULT_SEPARATOR, DEFAULT_QUOTE);
        }

        public static List<String> parseLine(String cvsLine, char separators) {
            return parseLine(cvsLine, separators, DEFAULT_QUOTE);
        }

        public static List<String> parseLine(String cvsLine, char separators, char customQuote) {

            List<String> result = new ArrayList<>();

            //if empty, return!
            if (cvsLine == null && cvsLine.isEmpty()) {
                return result;
            }

            if (customQuote == ' ') {
                customQuote = DEFAULT_QUOTE;
            }

            if (separators == ' ') {
                separators = DEFAULT_SEPARATOR;
            }

            StringBuffer curVal = new StringBuffer();
            boolean inQuotes = false;
            boolean startCollectChar = false;
            boolean doubleQuotesInColumn = false;

            char[] chars = cvsLine.toCharArray();

            for (char ch : chars) {

                if (inQuotes) {
                    startCollectChar = true;
                    if (ch == customQuote) {
                        inQuotes = false;
                        doubleQuotesInColumn = false;
                    } else {

                        //Fixed : allow "" in custom quote enclosed
                        if (ch == '\"') {
                            if (!doubleQuotesInColumn) {
                                curVal.append(ch);
                                doubleQuotesInColumn = true;
                            }
                        } else {
                            curVal.append(ch);
                        }

                    }
                } else {
                    if (ch == customQuote) {

                        inQuotes = true;

                        //Fixed : allow "" in empty quote enclosed
                        if (chars[0] != '"' && customQuote == '\"') {
                            curVal.append('"');
                        }

                        //double quotes in column will hit this!
                        if (startCollectChar) {
                            curVal.append('"');
                        }

                    } else if (ch == separators) {

                        result.add(curVal.toString());

                        curVal = new StringBuffer();
                        startCollectChar = false;

                    } else if (ch == '\r') {
                        //ignore LF characters
                        continue;
                    } else if (ch == '\n') {
                        //the end, break!
                        break;
                    } else {
                        curVal.append(ch);
                    }
                }

            }

            result.add(curVal.toString());

            return result;
        }


}
