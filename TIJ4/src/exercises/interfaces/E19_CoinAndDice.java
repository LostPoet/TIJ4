package exercises.interfaces;

import java.util.Random;

interface TossingGame {
    void play();
}

interface TossingGameFactory {
    TossingGame create();
}

class Coin implements TossingGame {
    private Random rand = new Random();
    private boolean side = rand.nextBoolean();

    public void play() {
	System.out.println((side == true) ? "facade" : "obverse");
    }
}

class Dice implements TossingGame {
    private Random rand = new Random();
    private int point = rand.nextInt(6);

    public void play() {
	System.out.println("point " + ++point);
    }
}

class CoinFactory implements TossingGameFactory {
    public TossingGame create() {
	return new Coin();
    }
}

class DiceFactory implements TossingGameFactory {
    public TossingGame create() {
	return new Dice();
    }
}

public class E19_CoinAndDice {
    static void tossing(TossingGameFactory tf) {
	TossingGame tossingGame = tf.create();
	tossingGame.play();
    }

    public static void main(String[] args) {
	tossing(new CoinFactory());
	tossing(new DiceFactory());
    }
}
