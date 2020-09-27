package controller.draw;

import controller.AllEventHandlers;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import model.Currency;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class DrawTopAreaTask extends BorderPane implements Callable<Void> {
    private Currency currency;
    private Button watch;
    private Button delete;

    public DrawTopAreaTask(Currency currency){
        this.currency = currency;
        this.watch = new Button("Watch");
        this.delete = new Button("Delete");

        this.delete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                AllEventHandlers.onDelete(currency.getShortCode());
            }
        });

        this.watch.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                AllEventHandlers.onWatch(currency.getShortCode());
            }
        });


    }

    @Override
    public Void call() throws Exception{
        System.out.println("Calling TopArea");
        FutureTask<Pane> currInfo = new FutureTask<Pane>(new DrawCurrencyInfoTask(currency));
        FutureTask<VBox> graph = new FutureTask<VBox>(new DrawGrapTask(currency));
        ExecutorService exe = Executors.newSingleThreadExecutor();
        exe.execute(graph);
        exe.execute(currInfo);
        Pane currencyInfo = (Pane)currInfo.get();
        VBox currencyGraph = (VBox)graph.get();
        Pane topArea = genTopArea();
        this.setTop(topArea);
        this.setLeft(currencyInfo);
        this.setCenter(currencyGraph);
        System.out.println("Ended top area");
        return null;
    }

    private HBox genTopArea(){
        HBox topArea = new HBox(10);
        topArea.setPadding(new Insets(5));
        topArea.getChildren().addAll(watch,delete);
        ((HBox)topArea).setAlignment(Pos.CENTER_RIGHT);
        return topArea;
    }
}
