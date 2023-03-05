package br.com.cwi.berk.factory;

import java.util.Random;

public class SimpleFactory {

    public static Long getRandomLong() {
        return new Random().nextLong();
    }

}
