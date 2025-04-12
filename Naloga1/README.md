# Problem največjega podzaporedja

Problem največjega podzaporedja v zaporedju celih števil išče največje (t.j. z največjo vsoto) neprekinjeno zaporedje. Primer: v zaporedju "-1, 2, -5, 2, -1, 2, -2, 1", je največje podzaporedje "2, -1, 2"; Njegova vsota je 3. Vsa
druga podzaporedje imajo manjšo vsoto.

Implementirajte razdred NajvecjePodzaporedje, ki bo namenjen iskanju največjega podzaporedja števil. Razdred naj vsebuje konstruktor NajvecjePodzaporedje(int[] elementi) in metode: 
- grobaSila()
- deliVladaj()
- kadanovAlgoritem()
- izpisiNajvecjePodzaporedje()

## Opis željenega delovanja metode grobaSila()
 
Implementacija metode naj sledi psevdokodi, ki smo jo predstavili na vajah.
Metoda naj na začetku izvajanja v ukazno vrstico izpiše niz "groba sila", kateremu sledi znak za novo vrstico.
System.out.println("groba sila")

## Opis željenega delovanja metode deliVladaj()
 
Implementacija metode naj sledi psevdokodi, ki smo jo predstavili na vajah.

Poleg implementacije metode deliVladaj, je potrebno implementirati še metodi najvecjeZaporedje() in najvecjeSredinskoZaporedje(). Metodi lahko sprejmeta poljubno število argumentov. Zahtevano je le, da metoda najvecjeZaporedje() na začetku izvajanja v ukazno vrstico izpiše niz "najvecje zaporedje", kateremu sledi znak za novo vrstico.

System.out.println("najvecje zaporedje")
 
Enako naj metoda najvecjeSredinskoZaporedje() na začetku izvajanja v ukazno vrstico izpiše niz "najvecje sredinsko", kateremu sledi znak za novo vrstico.
 
System.out.println("najvecje sredinsko")

## Opis željenega delovanja metode kadanovAlgoritem()
 
Implementacija metode naj sledi psevdokodi, ki smo jo predstavili na vajah. Metoda naj na začetku izvajanja v ukazno vrstico izpiše niz "kadanov algoritem", kateremu sledi znak za novo vrstico.
System.out.println("kadanov algoritem")

## Opis željenega delovanja metode izpisiNajvecjePodzaporedje()

Metoda naj v ukazno vrstico izpiše niz, kjer so z vejicami ločeni naslednji trije elementi; index začetka največjega podzaporedja, index konca največjega podzaporedja in skupna vsota največjega podzaporedja. Nizu mora slediti znak za novo vrstico.
