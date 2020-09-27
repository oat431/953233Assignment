package controller.draw;

import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import model.Currency;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class DrawTopPane implements Callable<Void> {
    private Currency currency;

    public DrawTopPane(Currency currency){
        this.currency = currency;
    }

    @Override
    public Void call() throws Exception{
        FutureTask<Void> topPane = new FutureTask<Void>(new DrawTopAreaTask(currency));
        return null;
    }
}
