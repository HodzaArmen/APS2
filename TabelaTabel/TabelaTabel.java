import java.util.Comparator;

public class TabelaTabel {
    private Comparator<String> comparator;
    private Vnos[][] tabele;
    private int[] velikosti;

    public TabelaTabel(Comparator<String> comparator) {
        this.comparator = comparator;
        this.tabele = new Vnos[1][];
        this.velikosti = new int[1];
    }

    public void vstavi(String element) {
        for (int i = 0; i < tabele.length; i++) {
            if (tabele[i] == null)
                continue;
            int index = binarySearch(tabele[i], element);
            if (index >= 0 && !tabele[i][index].izbrisan) {
                tabele[i][index].steviloPojavitev++;
                return;
            }
        }

        Vnos[] novaTabela = { new Vnos(element, 1) };
        int index = 0;
        while (index < tabele.length && tabele[index] != null) {
            novaTabela = zdruziTabeli(novaTabela, tabele[index]);
            tabele[index] = null;
            index++;
        }

        if (index >= tabele.length) {
            tabele = povecajTabelo(tabele, tabele.length + 1);
            velikosti = povecajTabelo(velikosti, velikosti.length + 1);
        }
        tabele[index] = novaTabela;
        velikosti[index] = novaTabela.length;
    }

    private Vnos[] zdruziTabeli(Vnos[] a, Vnos[] b) {
        Vnos[] rezultat = new Vnos[a.length + b.length];
        int i = 0, j = 0, k = 0;
        while (i < a.length && j < b.length) {
            if (comparator.compare(a[i].vrednost, b[j].vrednost) < 0) {
                rezultat[k++] = a[i++];
            } else if (comparator.compare(a[i].vrednost, b[j].vrednost) > 0) {
                rezultat[k++] = b[j++];
            } else {
                rezultat[k++] = new Vnos(a[i].vrednost, a[i].steviloPojavitev + b[j].steviloPojavitev);
                i++;
                j++;
            }
        }
        while (i < a.length)
            rezultat[k++] = a[i++];
        while (j < b.length)
            rezultat[k++] = b[j++];

        Vnos[] skrajsan = new Vnos[k];
        for (int m = 0; m < k; m++) {
            skrajsan[m] = rezultat[m];
        }
        return skrajsan;
    }

    private int binarySearch(Vnos[] tabela, String element) {
        int low = 0, high = tabela.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            int cmp = comparator.compare(element, tabela[mid].vrednost);
            if (cmp == 0)
                return mid;
            else if (cmp < 0)
                high = mid - 1;
            else
                low = mid + 1;
        }
        return -1;
    }

    public boolean najdi(String element) {
        for (int i = 0; i < tabele.length; i++) {
            if (tabele[i] == null)
                continue;
            int index = binarySearch(tabele[i], element);
            if (index >= 0 && !tabele[i][index].izbrisan && tabele[i][index].steviloPojavitev > 0) {
                System.out.println("true");
                return true;
            }
        }
        System.out.println("false");
        return false;
    }

    public boolean izbrisi(String element) {
        for (int i = 0; i < tabele.length; i++) {
            if (tabele[i] == null)
                continue;
            int index = binarySearch(tabele[i], element);
            if (index >= 0 && !tabele[i][index].izbrisan && tabele[i][index].steviloPojavitev > 0) {
                tabele[i][index].steviloPojavitev--;
                if (tabele[i][index].steviloPojavitev == 0) {
                    tabele[i][index].izbrisan = true;
                }
                System.out.println("true");
                return true;
            }
        }
        System.out.println("false");
        return false;
    }

    public void izpisi() {
        boolean vsePrazno = true;
        String niz = "";

        for (int i = 0; i < tabele.length; i++) {
            if (tabele[i] == null) {
                niz += "...\n";
                continue;
            }

            StringBuilder sb = new StringBuilder();
            boolean vrsticaPrazna = true;

            for (int j = 0; j < tabele[i].length; j++) {
                Vnos v = tabele[i][j];
                if (v.izbrisan) {
                    sb.append("x, ");
                    vrsticaPrazna = false;
                } else {
                    sb.append(v.vrednost).append("/").append(v.steviloPojavitev).append(", ");
                    vrsticaPrazna = false;
                    vsePrazno = false;
                }
            }

            if (!vrsticaPrazna) {
                niz += sb.substring(0, sb.length() - 2) + "\n";
            }
        }

        if (vsePrazno) {
            System.out.println("prazen");
        } else {
            System.out.print(niz);
        }
    }

    private Vnos[][] povecajTabelo(Vnos[][] original, int novaVelikost) {
        Vnos[][] nova = new Vnos[novaVelikost][];
        for (int i = 0; i < original.length; i++) {
            nova[i] = original[i];
        }
        return nova;
    }

    private int[] povecajTabelo(int[] original, int novaVelikost) {
        int[] nova = new int[novaVelikost];
        for (int i = 0; i < original.length; i++) {
            nova[i] = original[i];
        }
        return nova;
    }
}

class Vnos {
    String vrednost;
    int steviloPojavitev;
    boolean izbrisan;

    Vnos(String vrednost, int steviloPojavitev) {
        this.vrednost = vrednost;
        this.steviloPojavitev = steviloPojavitev;
        this.izbrisan = false;
    }
}
