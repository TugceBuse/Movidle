package com.example.filmlistesi;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class ReadText {

    private static final ArrayList<Movie> MovieList = new ArrayList<>();

    public static void readcsv() {

        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("imdb_top_250.csv"), StandardCharsets.ISO_8859_1))) {
            String line;
            String[] groups;
            int k = -1;

            while ((line = br.readLine()) != null) {
                k++;
                if (k != 0) {
                    groups = line.split(";");
                    MovieList.add(new Movie(groups[1], groups[2], groups[3], groups[4], groups[5], groups[6], groups[7], k + ".jpg"));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index Out Of Bounds");
        }
    }

    public static ArrayList<Movie> getMovieList() {
        return MovieList;
    }
}

