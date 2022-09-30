import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class Zadanie2 {
    //2.
    //Mając tablicę int[] numbers, wypełnioną liczbami całkowitymi i posortowaną malejąco
    // ( numbers[i] > numbers[i+1] ),
    // sprawdź czy występuje w niej liczba int x.
    // Metoda powinna zwracać wartość TRUE, jeśli dana liczba występuje oraz FALSE w przeciwnym wypadku.
    // W rozwiązaniu zależy nam na jak najmniejszej złożoności obliczeniowej (priorytet) oraz pamięciowej.
    // Podaj szacowaną złożoność obliczeniową oraz pamięciową. Poniżej sygnatura metody do napisania.
    //

    //  Czas: O(log n)
    // Pamięć: O(1)

    public static void main(String[] args) {


    }
}

class binarySearch {
    public static boolean search(int[] numbers, int x) {
        return helper(numbers, 0, numbers.length, x);
    }

    private static boolean helper(int[] numbers, int low, int high, int x) {

        while (low < high) {
            int mid = (low + high) / 2;
            if (numbers[mid] == x) {
                return true;
            }
            if (numbers[mid] > x) {
                low = mid + 1;
            }
            if (numbers[mid] < x) {
                high = mid;
            }
        }
        return false;
    }
}

class Tests {
        //testy
    @Test
    void test() {
        int[] arr = {5, 4, 3, 2, 1};
        List<Integer> list = IntStream.rangeClosed(0, 230).mapToObj(x -> Integer.valueOf(x)).collect(Collectors.toList());
        List<Integer> list2 = IntStream.rangeClosed(0, 899).mapToObj(x -> Integer.valueOf(x)).collect(Collectors.toList());
        List<Integer> list3 = IntStream.rangeClosed(0, 7890).mapToObj(x -> Integer.valueOf(x)).collect(Collectors.toList());
        List<Integer> list4 = IntStream.rangeClosed(0, 9999).mapToObj(x -> Integer.valueOf(x)).collect(Collectors.toList());
        List<Integer> list5 = IntStream.rangeClosed(0, 19999).mapToObj(x -> Integer.valueOf(x)).collect(Collectors.toList());
        List<Integer> list6 = IntStream.rangeClosed(0, 19238).mapToObj(x -> Integer.valueOf(x)).collect(Collectors.toList());
        List<Integer> list7 = IntStream.rangeClosed(0, 39022).mapToObj(x -> Integer.valueOf(x)).collect(Collectors.toList());
        List<Integer> list8 = IntStream.rangeClosed(0, 59039).mapToObj(x -> Integer.valueOf(x)).collect(Collectors.toList());

        Collections.reverse(list);
        Collections.reverse(list2);
        Collections.reverse(list3);
        Collections.reverse(list4);
        Collections.reverse(list5);
        Collections.reverse(list6);
        Collections.reverse(list7);
        Collections.reverse(list8);
        assertFalse(binarySearch.search(list.stream().mapToInt(x -> x).toArray(), 900));
        assertTrue(binarySearch.search(list2.stream().mapToInt(x -> x).toArray(), 786));
        assertTrue(binarySearch.search(list3.stream().mapToInt(x -> x).toArray(), 1234));
        assertFalse(binarySearch.search(list4.stream().mapToInt(x -> x).toArray(), 10000));
        assertFalse(binarySearch.search(list5.stream().mapToInt(x -> x).toArray(), 25738));
        assertTrue(binarySearch.search(list6.stream().mapToInt(x -> x).toArray(), 18279));
        assertTrue(binarySearch.search(list7.stream().mapToInt(x -> x).toArray(), 11729));
        assertTrue(binarySearch.search(list8.stream().mapToInt(x -> x).toArray(), 38927));

    }

}