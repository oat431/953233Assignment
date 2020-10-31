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
        String dateEnd = LocalDate.now().format(formatter);
        String dateStart = LocalDate.now().minusDays(N).format(formatter);
        String apiKey = "fe3b25b7bb9d1bd469ca";
        String url_str = String.format("https://free.currconv.com/api/v7/convert?q=%s_THB&compact=ultra&date=%s&endDate=%s&apiKey=%s",src,dateStart,dateEnd,apiKey);
        ArrayList<CurrencyEntity> hisList = new ArrayList<>();
        String retrievedJson = null;
        try{
            retrievedJson = IOUtils.toString(new URL(url_str), Charset.defaultCharset());
        } catch(MalformedURLException e){
            System.out.println("URL error");
        } catch(IOException e){
            System.out.println("File error");
        }
        JSONObject jsonOBJ = new JSONObject(retrievedJson).getJSONObject(String.format("%s_THB",src));
        Iterator keyToCopyIterator = jsonOBJ.keys();

        while(keyToCopyIterator.hasNext()){
            String key = (String) keyToCopyIterator.next();
            double rate = Double.parseDouble(jsonOBJ.get(key).toString());
            hisList.add(new CurrencyEntity(rate,key));
        }

        hisList.sort(new Comparator<CurrencyEntity>() {
            @Override
            public int compare(CurrencyEntity o1, CurrencyEntity o2) {
                return o1.getTimestamp().compareTo(o2.getTimestamp());
            }
        });
        return hisList;
    }
}
