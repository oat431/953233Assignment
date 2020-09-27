package view;

import javafx.geometry.Insets;
import javafx.scene.layout.FlowPane;
import model.Currency;

import java.util.ArrayList;
import java.util.concurrent.*;

public class CurrencyParentPane extends FlowPane {
    public CurrencyParentPane(ArrayList<Currency> currencyList) throws ExecutionException, InterruptedException{
        this.setPadding(new Insets(0));
        refreshPane(currencyList);
    }
    public void refreshPane(ArrayList<Currency> currencyList) throws ExecutionException,InterruptedException{
        this.getChildren().clear();
        for(int i=0;i<currencyList.size();i++){
            CurrencyPane cp = new CurrencyPane(currencyList.get(i));
            this.getChildren().add(cp);
        }
    }
}
