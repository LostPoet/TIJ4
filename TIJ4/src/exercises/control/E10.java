package exercises.control;

public class E10 {
    public static void main(String[] args) {
        for (int i = 1000; i < 10000; ++i) {
            for (int j = 1000; j < 10000; ++j) {
                int left = j / 100;
                int right = j % 100;
                if (left * right == i && identicalNumbers(i, j)) {
                    System.out.println(i + " = " + left + " * " + right);
                    break;
                }
            }
        }
    }

    static boolean identicalNumbers(Integer x, Integer y) {
        char xa[] = x.toString().toCharArray();
        char ya[] = y.toString().toCharArray();
        char xnum[] = new char[10];
        char ynum[] = new char[10];
        for (char xelem : xa) {
            xnum[xelem - '0']++;
        }
        for (char yelem : ya) {
            ynum[yelem - '0']++;
        }
        for (int i = 0; i < 10; ++i) {
            if (xnum[i] != ynum[i]) {
                return false;
            }
        }
        return true;
    }
}
