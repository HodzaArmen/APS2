package VertexCover;

public class Graf {
    private final int n; // število vozlišč
    private final int[][] sosedi; // seznam sosedov za vsako vozlišče
    private final int[] stSosedov; // število sosedov za vsako vozlišče
    private int resultLen = 0;

    public Graf(int stVozlisc) {
        this.n = stVozlisc;
        this.sosedi = new int[n][];
        this.stSosedov = new int[n];
        for (int i = 0; i < n; i++) {
            sosedi[i] = new int[1]; // začetna velikost 1, se bo povečala po potrebi
        }
    }

    public void povezi(int u, int v) {
        dodajSoseda(u, v);
        dodajSoseda(v, u);
    }

    private void dodajSoseda(int u, int v) {
        if (stSosedov[u] >= sosedi[u].length) {
            int[] noviSosedi = new int[sosedi[u].length * 2];
            System.arraycopy(sosedi[u], 0, noviSosedi, 0, sosedi[u].length);
            sosedi[u] = noviSosedi;
        }
        sosedi[u][stSosedov[u]++] = v;
    }

    public void grobaSila() {
        int[] bestCover = null;
        int minSize = Integer.MAX_VALUE;
        long minPowerSum = Long.MAX_VALUE;

        int powerSetSize = 1 << n;
        for (int mask = 0; mask < powerSetSize; mask++) {
            boolean[] inSet = new boolean[n];
            for (int i = 0; i < n; i++)
                if ((mask & (1 << i)) != 0)
                    inSet[i] = true;

            boolean ok = true;
            // Preveri vse povezave
            for (int u = 0; u < n && ok; u++) {
                for (int j = 0; j < stSosedov[u] && ok; j++) {
                    int v = sosedi[u][j];
                    if (u < v && !(inSet[u] || inSet[v])) {
                        ok = false;
                    }
                }
            }

            if (ok) {
                int size = Integer.bitCount(mask);
                long sum = 0;
                // Izračunaj vsoto potenčnih števil
                for (int i = 0; i < n; i++)
                    if (inSet[i])
                        sum += 1L << i;

                if (size < minSize || (size == minSize && sum < minPowerSum)) {
                    minSize = size;
                    minPowerSum = sum;
                    bestCover = new int[size];
                    int k = 0;
                    for (int i = 0; i < n; i++)
                        if (inSet[i])
                            bestCover[k++] = i;
                }
            }
        }

        for (int i = 0; i < bestCover.length; i++) {
            System.out.print(bestCover[i]);
            if (i < bestCover.length - 1)
                System.out.print(" ");
        }
        System.out.println();
    }

    public void pozresni() {
        boolean[] pokritePovezave = new boolean[n * n];
        boolean[] inCover = new boolean[n];
        int[] result = new int[n];
        resultLen = 0;
        int remainingEdges = 0;

        // Preštej vse povezave
        for (int u = 0; u < n; u++) {
            remainingEdges += stSosedov[u];
        }
        remainingEdges /= 2; // ker štejemo vsako povezavo dvakrat

        while (remainingEdges > 0) {
            int maxV = -1;
            int maxCount = -1;

            // Poišči vozlišče z največ nepokritimi povezavami
            for (int u = 0; u < n; u++) {
                if (inCover[u])
                    continue;
                int count = 0;
                for (int j = 0; j < stSosedov[u]; j++) {
                    int v = sosedi[u][j];
                    if (!pokritePovezave[u * n + v] && !inCover[v]) {
                        count++;
                    }
                }
                if (count > maxCount || (count == maxCount && u < maxV)) {
                    maxCount = count;
                    maxV = u;
                }
            }

            if (maxCount == 0)
                break; // ni več nepokritih povezav

            inCover[maxV] = true;
            result[resultLen++] = maxV;

            // Označi vse povezave tega vozlišča kot pokrite
            for (int j = 0; j < stSosedov[maxV]; j++) {
                int v = sosedi[maxV][j];
                if (!pokritePovezave[maxV * n + v]) {
                    pokritePovezave[maxV * n + v] = true;
                    pokritePovezave[v * n + maxV] = true;
                    remainingEdges--;
                }
            }
        }

        izpisiArray(result);
    }

    public void izpisiArray(int[] arr) {
        // arr je polje, ki na i-tem mestu ima oznako vozlisca, ki smo izbrali v i-tem
        // koraku
        int idxStart = resultLen > 100 ? resultLen - 100 : 0;
        for (int i = idxStart; i < resultLen; i++) {
            System.out.print(arr[i]);
            if (i < resultLen - 1)
                System.out.print(" ");
        }
        System.out.println();
    }
}