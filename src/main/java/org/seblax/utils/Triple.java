package org.seblax.utils;

import java.util.ArrayList;
import java.util.List;

public class Triple<E,F,G>{
    private E x;
    private F y;
    private G z;

    protected Triple(E x, F y, G z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static <E,F,G> Triple<E,F,G> of(E x,F y,G z){
        return new Triple<E,F,G>(x,y,z);
    }

    public E getFirst(){
        return x;
    }

    public F getSecond(){
        return y;
    }

    public G getThird(){
        return z;
    }

    public List<Object> toList() {
        return List.of(x, y, z);
    }

     @Override
    public String toString(){
        return "["
                + getFirst().toString() + ", "
                + getSecond().toString() + ", "
                + getThird().toString()
                + "]";
     }

}
