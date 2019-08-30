package exercises.initialization;

enum cheapestCurrency {
    IRANIAN_RIAL, VIETNAMESE_DONG, INDONESIAN_RUPIAH, GUINEAN_FRANC, LAOTIAN_KIP, SIERRA_LEONEAN_LEONE
}

public class E21 {
    public static void main(String[] args) {
        for (cheapestCurrency paper : cheapestCurrency.values()) {
            System.out.println(paper + ", Ordinal:" + paper.ordinal());
        }
    }
}
