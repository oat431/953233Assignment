package controller.draw;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import model.Currency;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.concurrent.Callable;

public class DrawCurrencyInfoTask implements Callable<Pane> {
    public Currency currency;
    public DrawCurrencyInfoTask(Currency currency){
        this.currency = currency;
    }
    @Override
    public Pane call(){
        System.out.println("Calling CurrencyInfo");
        VBox currencyInfoPane = new VBox(10);
        currencyInfoPane.setPadding(new Insets(5,25,5,25));
        currencyInfoPane.setAlignment(Pos.CENTER);
        Label exchangeString = new Label("");
        Label watchString = new Label("");
        exchangeString.setStyle("-fx-font-size:20");
        watchString.setStyle("-fx-font-size:14");
        if(this.currency != null){
            exchangeString.setText(String.format("%s: %.4f",currency.getShortCode(),currency.getCurrent().getRate()));
            if(this.currency.getWatch()){
                watchString.setText(String.format("(Watch @%.4f)",currency.getWatchRate()));
            }
        }
        currencyInfoPane.getChildren().addAll(exchangeString,watchString);
        System.out.println("Ended Info");
        return currencyInfoPane;
    }

}
