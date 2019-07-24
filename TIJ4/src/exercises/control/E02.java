package exercises.control;

import java.util.Random;

public class E02 {
    public static void main(String[] args) {
        Random rand = new Random(47);
        int i = 1;
        int randInt1 = rand.nextInt(100);
        while (i < 25) {
            int randInt2 = rand.nextInt(100);
            if (randInt1 < randInt2)
                System.out.println(randInt1 + " < " + randInt2);
            else if (randInt1 > randInt2)
                System.out.println(randInt1 + " > " + randInt2);
            else
                System.out.println(randInt1 + " = " + randInt2);
            ++i;
            randInt1 = randInt2;
        }
    }
}
