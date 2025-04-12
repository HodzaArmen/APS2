import java.util.*;

class AlphabeticalComparator implements Comparator<String> {
    @Override
    public int compare(String s1, String s2) {
        return s1.compareTo(s2);
    }
}

public class TabelaTabelTest {
    public static void main(String[] args) {
        AlphabeticalComparator comparator = new AlphabeticalComparator();

        TabelaTabel tabelaTabel = new TabelaTabel(comparator);

        tabelaTabel.izpisi();

        tabelaTabel.vstavi("1");
        tabelaTabel.vstavi("12");
        tabelaTabel.vstavi("123");

        tabelaTabel.izpisi();

        tabelaTabel.vstavi("321");
        tabelaTabel.vstavi("6383");
        tabelaTabel.vstavi("43");
        tabelaTabel.vstavi("023");

        tabelaTabel.izpisi();
    }
}
