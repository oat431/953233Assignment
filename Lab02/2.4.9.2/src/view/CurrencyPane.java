package view;

import controller.AllEventHandlers;
import controller.draw.DrawCurrencyInfoTask;
import controller.draw.DrawGrapTask;
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

import java.util.concurrent.*;

public class CurrencyPane extends BorderPane {
    private Currency currency;
    private Button watch;
    private Button delete;
    public CurrencyPane(Currency currency) throws ExecutionException, InterruptedException {
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
        this.setPadding(new Insets(0));
        this.setPrefSize(640,300);
        this.setStyle("-fx-background-color: white");

    }

    private HBox genTopArea(){
        HBox topArea = new HBox(10);
        topArea.setPadding(new Insets(5));
        topArea.getChildren().addAll(watch,delete);
        ((HBox)topArea).setAlignment(Pos.CENTER_RIGHT);
        return topArea;
    }
}
