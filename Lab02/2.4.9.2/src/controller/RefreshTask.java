package controller;


import javafx.application.Platform;
import javafx.concurrent.Task;

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
