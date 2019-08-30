package exercises.initialization;

public class E22 {
    cheapestCurrency paper;

    public E22(cheapestCurrency currency) {
        paper = currency;
    }

    private void describe() {
        switch (paper) {
        case IRANIAN_RIAL:
            System.out.println(paper + ": The Iranian Rial is officially the least valued currency in the world.");
            break;
        case VIETNAMESE_DONG:
            System.out
                .println(paper + ": The Vietnamese Dong is the currency with the second lowest value in the world.");
            break;
        case INDONESIAN_RUPIAH:
            System.out.println(
                paper + ": Indonesia is an economically stable and quite developed country in Southeast Asia.");
            break;
        case GUINEAN_FRANC:
            System.out.println(paper + ": Guinea – the African country with one of the most inflated currencies.");
            break;
        case LAOTIAN_KIP:
            System.out.println(paper
                + ": The Lao is the only currency on this list which did not devalue but was originally issued with very low rate.");
            break;
        case SIERRA_LEONEAN_LEONE:
            System.out.println(paper
                + ": Sierra Leone is a very poor African country, which handled out many serious tests which caused the local money to devalue.");
            break;
        default:
            throw new IllegalArgumentException("Unexpected value: " + paper);
        }
    }

    public static void main(String[] args) {
        for (cheapestCurrency currency : cheapestCurrency.values()) {
            E22 p = new E22(currency);
            p.describe();
        }
    }
}
