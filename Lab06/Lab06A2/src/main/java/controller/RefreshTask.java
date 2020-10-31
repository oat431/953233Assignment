package controller;

import javafx.scene.control.Alert;
import model.Currency;

import javafx.application.Platform;
import javafx.concurrent.Task;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class RefreshTask extends Task<Void> {
    protected Void call() throws Exception {
        for(;;){
            try{
                Thread.sleep((long)((long)5 * 1e3));
            }catch (InterruptedException e){
                System.out.println("Interrupted occur");
            }
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    try{
                        /*ArrayList<Currency> allCurrency = Launcher.getCurrency();
                        String found = "";
                        for(int i=0;i<allCurrency.size();i++){
                            if(allCurrency.get(i).getWatchRate() != 0 && allCurrency.get(i).getWatchRate()<allCurrency.get(i).getCurrent().getRate()){
                                if(found.equals("")){
                                    found = allCurrency.get(i).getShortCode();
                                }else{
                                    found = found + " add " + allCurrency.get(i).getShortCode();
                                }
                            }
                        }
                        if(!found.equals("")) {
                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setTitle(null);
                            alert.setHeaderText(null);
                            if (found.length() > 3) {
                                alert.setContentText(String.format("%s have become lower than the watch rate!", found));
                            } else {
                                alert.setContentText(String.format("%s has become higher than the watch rates!", found));
                                alert.showAndWait();
                            }
                        }*/
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });
            FutureTask futureTask = new FutureTask(new WatchTask());
            Platform.runLater(futureTask);
            try{
                futureTask.get();
            }catch (InterruptedException e){
                System.out.println("Interrupted occur");
            }catch (ExecutionException e){
                System.out.println("Execution error");
            }
        }
    }
}
