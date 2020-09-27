package controller;

import model.Currency;
import javafx.scene.control.TextInputDialog;
import model.CurrencyEntity;

import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

public class AllEventHandlers {
    public static void onRefresh(){
        try{
            Launcher.refreshPane();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void onAdd(){
        try{
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Add Currency");
            dialog.setContentText("Currency code:");
            dialog.setHeaderText(null);
            dialog.setGraphic(null);
            Optional<String> code = dialog.showAndWait();
            if(code.isPresent()){
                ArrayList<Currency> currency_list = Launcher.getCurrency();
                Currency c = new Currency(code.get());
                ArrayList<CurrencyEntity> c_list = FetchData.fetch_range(c.getShortCode(),6);
                c.setHistorical(c_list);
                c.setCurrent(c_list.get(c_list.size()-1));
                currency_list.add(c);
                Launcher.setCurrency(currency_list);
                Launcher.refreshPane();
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        } catch (ExecutionException e){
            e.printStackTrace();
        }
    }

    public static void onDelete(String code){
        try{
            ArrayList<Currency> currency_list = Launcher.getCurrency();
            int index = -1;
            for(int i=0;i<currency_list.size();i++){
                if(currency_list.get(i).getShortCode().equals(code)){
                    index = i;
                    break;
                }
            }
            if(index != -1){
                currency_list.remove(index);
                Launcher.setCurrency(currency_list);
                Launcher.refreshPane();
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        } catch (ExecutionException e ){
            e.printStackTrace();
        }
    }

    public static void onWatch(String code){
        try{
            ArrayList<Currency> currency_list = Launcher.getCurrency();
            int index = -1;
            System.out.println(code + " " + index);
            for(int i=0;i<currency_list.size();i++){
                if(currency_list.get(i).getShortCode().equals(code)){
                    index = i;
                    break;
                }
            }
            System.out.println(currency_list + " " + index);
            if(index != -1){
                TextInputDialog dialog = new TextInputDialog();
                dialog.setTitle("Add Watch");
                dialog.setContentText("Rate:");
                dialog.setHeaderText(null);
                dialog.setGraphic(null);
                Optional<String> retrievedRate = dialog.showAndWait();
                System.out.println(retrievedRate.get());
                if(retrievedRate.isPresent()){
                    double rate = Double.parseDouble(retrievedRate.get());
                    currency_list.get(index).setWatch(true);
                    currency_list.get(index).setWatchRate(rate);
                    Launcher.setCurrency(currency_list);
                    Launcher.refreshPane();
                }
                Launcher.setCurrency(currency_list);
                Launcher.refreshPane();
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }catch (ExecutionException e){
            e.printStackTrace();
        }
    }

    public static void onUnWatch(String code){
        try{
            ArrayList<Currency> currency_list = Launcher.getCurrency();
            int index = -1;
            for(int i=0;i<currency_list.size();i++){
                if(currency_list.get(i).getShortCode().equals(code)){
                    index = i;
                    break;
                }
            }
            if(index != -1){
                currency_list.get(index).setWatch(false);
                currency_list.get(index).setWatchRate(0.0);
                Launcher.setCurrency(currency_list);
                Launcher.refreshPane();
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }catch (ExecutionException e){
            e.printStackTrace();
        }
    }
}
