package exercises.operators;

import java.util.Random;

class Coin {
    int side;

    boolean coinFlipping() {
        Random rand = new Random(47);
        return rand.nextBoolean();
    }
}

public class E07 {
    public static void main(String[] args) {
        System.out.println(new Coin().coinFlipping());
    }
}
