public class NajvecjePodzaporedje {
    private int[] elementi;
    private int zacetek;
    private int konec;
    private int maxVsota;
    private int tempZacetek;
    private int tempKonec;

    public NajvecjePodzaporedje(int[] elementi) {
        this.elementi = elementi;
    }

    public void grobaSila() {
        System.out.println("groba sila");
        int n = elementi.length;
        maxVsota = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int vsota = 0;
            for (int j = i; j < n; j++) {
                vsota += elementi[j];
                if (vsota > maxVsota) {
                    maxVsota = vsota;
                    zacetek = i;
                    konec = j;
                }
            }
        }
    }

    public int najvecjeZaporedje(int min, int max) {
        System.out.println("najvecje zaporedje");
        if (min == max) {
            if (elementi[min] > maxVsota) {
                maxVsota = elementi[min];
                zacetek = min;
                konec = min;
            }
            return elementi[min];
        }
        int meja = (min + max) / 2;
        int l = najvecjeZaporedje(min, meja);
        int d = najvecjeZaporedje(meja + 1, max);
        int c = najvecjeSredinskoZaporedje(min, meja, max);
        int najvecji = Math.max(Math.max(l, d), c);
        if (najvecji == l) {
            zacetek = min;
            konec = meja;
        } else if (najvecji == d || (najvecji == l && l == d)) {
            zacetek = meja + 1;
            konec = max;
        } else {
            zacetek = tempZacetek;
            konec = tempKonec;
        }
        maxVsota = najvecji;
        return najvecji;
    }

    public int najvecjeSredinskoZaporedje(int min, int meja, int max) {
        System.out.println("najvecje sredinsko");
        int vsota = 0;
        int maxLevo = Integer.MIN_VALUE;
        int maxDesno = Integer.MIN_VALUE;
        for (int i = meja + 1; i <= max; i++) {
            vsota += elementi[i];
            if (vsota > maxDesno) {
                maxDesno = vsota;
                tempKonec = i;
            }

        }
        vsota = 0;
        for (int i = meja; i >= min; i--) {
            vsota += elementi[i];
            if (vsota > maxLevo) {
                maxLevo = vsota;
                tempZacetek = i;
            }
        }
        return maxLevo + maxDesno;
    }

    public void deliVladaj() {
        System.out.println("deli vladaj");
        maxVsota = najvecjeZaporedje(0, elementi.length - 1);
    }

    public int kadanovAlgoritem() {
        System.out.println("kadanov algoritem");
        int vsota = 0, zacetniIndex = 0;
        maxVsota = Integer.MIN_VALUE;

        for (int i = 0; i < elementi.length; i++) {
            if (vsota <= 0) {
                vsota = elementi[i];
                zacetniIndex = i;
            } else {
                vsota += elementi[i];
            }

            if (vsota >= maxVsota) {
                maxVsota = vsota;
                zacetek = zacetniIndex;
                konec = i;
            }
        }
        return maxVsota;
    }

    public void izpisiNajvecjePodzaporedje() {
        System.out.println(zacetek + "," + konec + "," + maxVsota);
    }
}