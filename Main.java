import java.util.*;

class Wezel {
    char znak;
    int czestosc;
    Wezel lewy = null, prawy = null;

    // Konstruktor dla klasy Wezel
    Wezel(char znak, int czestosc) {
        this.znak = znak;
        this.czestosc = czestosc;
    }

    // Konstruktor dla klasy Wezel z lewym i prawym dzieckiem
    Wezel(char znak, int czestosc, Wezel lewy, Wezel prawy) {
        this.znak = znak;
        this.czestosc = czestosc;
        this.lewy = lewy;
        this.prawy = prawy;
    }
}

public class Main {
    // Funkcja do liczenia częstości występowania znaków
    static Map<Character, Integer> policzCzestosc(String tekst) {
        Map<Character, Integer> mapaCzestosci = new HashMap<>();
        for (char c : tekst.toCharArray()) {
            mapaCzestosci.put(c, mapaCzestosci.getOrDefault(c, 0) + 1);
        }
        return mapaCzestosci;
    }

    // Funkcja do budowania drzewa Huffmana
    static Wezel zbudujDrzewoHuffmana(String tekst) {
        Map<Character, Integer> mapaCzestosci = policzCzestosc(tekst);
        PriorityQueue<Wezel> kolejka = new PriorityQueue<>(Comparator.comparingInt(l -> l.czestosc));

        // Dodajemy do kolejki priorytetowej węzły dla każdego znaku
        for (Map.Entry<Character, Integer> wpis : mapaCzestosci.entrySet()) {
            kolejka.add(new Wezel(wpis.getKey(), wpis.getValue()));
        }

        // Tworzymy drzewo Huffmana łącząc zawsze dwa węzły o najmniejszej częstości
        while (kolejka.size() != 1) {
            Wezel lewy = kolejka.poll();
            Wezel prawy = kolejka.poll();

            int suma = lewy.czestosc + prawy.czestosc;
            kolejka.add(new Wezel('\0', suma, lewy, prawy));
        }

        // Zwracamy korzeń drzewa Huffmana
        return kolejka.peek();
    }

    // Funkcja do obliczania kodów Huffmana
    static void koduj(Wezel korzen, String str, Map<Character, String> kodHuffmana) {
        if (korzen == null)
            return;

        if (korzen.lewy == null && korzen.prawy == null) {
            kodHuffmana.put(korzen.znak, str);
        }

        koduj(korzen.lewy, str + '0', kodHuffmana);
        koduj(korzen.prawy, str + '1', kodHuffmana);
    }

    public static void main(String[] args) {
        Scanner skaner = new Scanner(System.in);
        System.out.println("Wprowadź tekst do zakodowania:");
        String tekst = skaner.nextLine();
        tekst = tekst.replaceAll("[^a-zA-Z]", "");

        Wezel korzen = zbudujDrzewoHuffmana(tekst);
        Map<Character, String> kodHuffmana = new HashMap<>();
        koduj(korzen, "", kodHuffmana);

        System.out.println("Znak\tCzęstość\tKod Huffmana");
        Map<Character, Integer> mapaCzestosci = policzCzestosc(tekst);
        List<Map.Entry<Character, Integer>> wpisy = new ArrayList<>(mapaCzestosci.entrySet());
        wpisy.sort(Map.Entry.comparingByValue());

        // Wyświetlamy znaki, ich częstość i odpowiadający im kod Huffmana
        for (Map.Entry<Character, Integer> wpis : wpisy) {
            System.out.println(wpis.getKey() + "\t\t\t" + wpis.getValue() + "\t\t\t" + kodHuffmana.get(wpis.getKey()));
        }
    }
}
