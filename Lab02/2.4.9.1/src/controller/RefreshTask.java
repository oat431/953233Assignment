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
                Thread.sleep((long)((long)60 * 1e3));
            }catch (InterruptedException e){
                System.out.println("Interrupted occur");
            }
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    try{
                        //some code awsome eiei
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
