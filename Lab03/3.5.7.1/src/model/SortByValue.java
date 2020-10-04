package model;

import java.util.ArrayList;
import java.util.Comparator;

public class SortByValue implements Comparator<ArrayList<FileFreq>> {
    @Override
    public int compare(ArrayList<FileFreq> o1,ArrayList<FileFreq> o2){
        return o1.get(0).getFreq().compareTo(o2.get(0).getFreq());
    }
}
