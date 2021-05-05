package org.campus02.ecom;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class BasketDataLoader {

    public static ArrayList<BasketData> load(String path) throws DataFileException {

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            ArrayList<BasketData> list = new ArrayList<>();

            String line;
            while ((line = br.readLine()) != null) {
                BasketData basketData = new Gson().fromJson(line, BasketData.class);
                list.add(basketData);
            }

            return list;
        } catch (FileNotFoundException e) {
            throw new DataFileException(e);
        } catch (IOException e) {
            throw new DataFileException(e);
        }
    }

    public static ArrayList<BasketData> load(String path, Comparator<BasketData> comparator) throws DataFileException {
        ArrayList<BasketData> entries = load(path);
        Collections.sort(entries, comparator);
        return entries;
    }
}
