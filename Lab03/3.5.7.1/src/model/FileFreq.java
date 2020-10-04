package model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;

public class FileFreq {
    private final String name;
    private final String path;
    private final Integer freq;

    public FileFreq(String name,String path, Integer freq){
        this.name = name;
        this.path = path;
        this.freq = freq;
    }

    public String getPath() {
        return path;
    }

    @Override
    public String toString(){
        return String.format("{%s:%d}",name,freq);
    }

    public Integer getFreq() {
        return freq;
    }
}
