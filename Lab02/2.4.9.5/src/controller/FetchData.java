package controller;

import model.CurrencyEntity;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;


public class FetchData {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static ArrayList<CurrencyEntity> fetch_range(String src, int N) {
        String apiKey = "fe3b25b7bb9d1bd469ca";
        String dateEnd = LocalDate.now().format(formatter);
        String dateStart = LocalDate.now().minusDays(N).format(formatter);
        String url_str = String.format("https://free.currconv.com/api/v7/convert?q=%s_THB&compact=ultra&date=%s&endDate=%s&apiKey=%s",src,dateStart,dateEnd,apiKey);
        String pastDateEnd = LocalDate.now().minusDays(N).format(formatter);
        String pastDateStart = LocalDate.now().minusDays(N+N+1).format(formatter);
        String url_str2 = String.format("https://free.currconv.com/api/v7/convert?q=%s_THB&compact=ultra&date=%s&endDate=%s&apiKey=%s",src,pastDateStart,pastDateEnd,apiKey);
        ArrayList<CurrencyEntity> hisList = new ArrayList<>();
        String retrievedJson = null;
        String retrievedJson2 = null;
        try{
            retrievedJson = IOUtils.toString(new URL(url_str), Charset.defaultCharset());
            retrievedJson2 = IOUtils.toString(new URL(url_str2), Charset.defaultCharset());
        } catch(MalformedURLException e){
            System.out.println("URL error");
        } catch(IOException e){
            System.out.println("File error");
        }
        JSONObject jsonOBJ = new JSONObject(retrievedJson).getJSONObject(String.format("%s_THB",src));
        JSONObject jsonOBJ2 = new JSONObject(retrievedJson2).getJSONObject(String.format("%s_THB",src));
        Iterator keyToCopyIterator = jsonOBJ.keys();
        Iterator keyToCopyIterator2 = jsonOBJ2.keys();

        while(keyToCopyIterator.hasNext()){
            String key = (String) keyToCopyIterator.next();
            double rate = Double.parseDouble(jsonOBJ.get(key).toString());
            hisList.add(new CurrencyEntity(rate,key));
        }

        while(keyToCopyIterator2.hasNext()){
            String key = (String) keyToCopyIterator2.next();
            double rate = Double.parseDouble(jsonOBJ2.get(key).toString());
            hisList.add(new CurrencyEntity(rate,key));
        }

        hisList.sort(Comparator.comparing(CurrencyEntity::getTimestamp));
        return hisList;
    }
}
