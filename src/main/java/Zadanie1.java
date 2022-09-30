import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


class main {
    public static void main(String[] args) {

    }
}

//1.
//1.A. W jakim celu używa się klas abstrakcyjnych, a w jakim interfejsów?
@Setter
@Getter
//A)
//Klasy abstrakcyjne tworzy się w celu stworzenia wspólnej funkcjonalności,"szkieletu" dla klas, które
//będą je rozszerzać.

// Klasy abstrakcyjne mogą posiadać implementacje wewnątrz.
// Klasy rozszerzające klasę abstrakcyjną muszą zaimplementować wszystkie metody abstrakcyjne z klasy abstrakcyjnej.
// Mogą też, ale nie muszą tworzyć własnej implementacji zwykłych metod.
// Dziedziczą wszystkie pola i zaimplementowane metody z klasy abstrakcyjnej,
// jak w przypadku dziedziczenia zwykłych klas.
//Pola mogą być publiczne, nie-statyczne, statyczne, prywatne, itd. jak w przypadku zwykłych klas.
abstract class Vehicle {
    private String wheels;
    private String type;
    private String identifier;

    private void start() {
        System.out.println("start the vehicle!");
    }

    abstract void printIdentifier();


    private void stop() {
        System.out.println("stop the vehicle!");
    }
}
//  * może posiadać metody abstrakcyjne oraz zwykłe
// * nie można utworzyć nowej instancji klasy abstrakcyjnej -> służy ona jako "szkielet" dla dziedziczących je klas.


@Getter
@Setter
class Sedan extends Vehicle {
    String model;
    String licensePlate;

    @Override
    void printIdentifier() {
        setIdentifier("VIN12829891");
        System.out.println(getIdentifier());
    }
}


//interfejs

//Interfes również służy jako szkielet dla klasy, która ją implementuje, ale bardziej w kontekście
//wymogów jakie dana klasa musi spełnić. Interfejsy nie mają żadnej implementacji wewnątrz.
// Klasy mogą zaimplementować więcej niż jeden interfejs.
interface Vehicle_ {
    String wheels = "4"; //prywatne pola są niedozwolone. Każde pole w interfejsie jest finalne,publiczne i statyczne.

    default void start() { //-> tylko metody domyślne mogą mieć implementacje
        System.out.println("start the vehicle!");
    }

    void printIdentifier(); //-> wszystkie metody w interfejsach są abstrakcyjne, przeznaczone
    // do nadpisania przez klase, która implementuje dany interfejs

    default void stop() {
        System.out.println("stop the vehicle!");
    }
}

interface Movable {
    void moveLeft();

    void moveRight();
}

class Hatchback implements Vehicle_, Movable { //-> klasa może zaimplementować wiele interfejsów
    @Override
    public void start() {
        Vehicle_.super.start();
    }

    @Override
    public void printIdentifier() {
        System.out.println("VIN1234");
    }

    @Override
    public void stop() {
        Vehicle_.super.stop();
    }

    @Override
    public void moveLeft() {
        System.out.println("Hatchback move left");
    }

    @Override
    public void moveRight() {
        System.out.println("Hatchback move right");
    }
}
//* klasa implementująca interfejs musi nadpisać wszystkie abstrakcyjne metody z interfejsu
//*


//1.B. Czym różni się tablica od listy liniowej?

class Listy {
    public static void main(String[] args) {
        //1.tablice w javie
        int[] arr = new int[2]; //-> tablica jednowymiarowa
        int[][] arr2 = new int[2][];//-> tablica dwuwymiarowa

        Integer[] arr3 = new Integer[2];
        //Tablica ma określony rozmiar, którego nie można zmienić.
        //Każdy element ma przypisany indeks. Wszystkie elementy w tablicy muszą być tego samego typu.
        //Tworząc tablice każdy indeks w tablicy ma nieprzypisaną wartość.
        //null jeśli tablica jest typu referencyjnego jak Integer, String, a 0, false jeśli typu int, boolean itd.


        //Dostęp do danych z tablicy:
        int number = arr[2];
        //pobiera element z tablicy z indeksu 2.


         //Za pomocą tablicy dwuwymiarowej możemy np. reprezentować graf skierowany/nieskierowany, wszystkie połączenia wierzchołków.
        //np:
        int[][] matrix = {
                {0, 1, 0, 0, 1}, //wierzchołek 0
                {0, 0, 0, 0, 0}, //wierzchołek 1
                {0, 0, 1, 1, 0}, //wierzchołek 2
                {0, 1, 0, 0, 0}, //wierzchołek 3
                {0, 0, 0, 0, 0}, //wierzchołek 4
                {0, 0, 0, 0, 0}, //wierzchołek 5
        };
        //poszczególny indeks określa połączenia danego wierzchołka z innymi wierzchołkami.
        // 4
        // ^
        // |
        ///2 -> 3 ->1 <- 0 -> 5



        //2. Lista liniowa
        // (Jednokierunkowa) Lista liniowa jest to liniowa struktura danych, w której elementy
        // połączone są za pomocą referencji do następnego obiektu.
        // Nie tak jak w liście, gdzie każdy element ma dany indeks w tablicy i lista ma sciśle określony rozmiar.
        //Rozmiar listy liniowej jest zwiększany kiedy dodajemy elementy lub zmniejszany kiedy usuwamy element z listy.
        //Rozmiar listy jest dynamiczny, a nie stały jak w liście.
        //Tak samo jak w tablicy, wszystkie elementy muszą być tego samego typu.
        // Dwukierunkowa lista liniowa posiada również referencje do poprzedniego obiektu.
        // W przykładach używam dwukierunkowej listy liniowej z referencją next i prev.
        class linkedListNode<T> {
            T value;
            linkedListNode next;
            linkedListNode prev;

            private linkedListNode(T value, linkedListNode next) {
                this.value = value;
                this.next = next;
            }

            private linkedListNode(T value) {
                this.value = value;
                next = null;
            }

            private linkedListNode() {

            }

            linkedListNode linkedList;

        }
        //główne funkcjonalności listy liniowej w javie:
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1); //-> dodawanie elementu na koniec listy
        list.addLast(1);
        list.removeLast();
        list.pop(); // usuwanie  i zwracanie ostatniego elementu  (tail) z listy.
        list.peek();  //zwracanie pierwszego elementu (head) z listy
        list.push(2);
        list.poll(); //zwracanie i usuwanie pierwszego elementu z listy
        list.indexOf(1);
        //Lista liniowa oferuje wygodne wstawianie lub usuwanie konkretnych elementów z listy.
        //Wystarczy wyszukać element, który posiada referencje do interesującego nas elementu i zmienić tą referencje
        //w zależności od naszych potrzeb:

        class addExample<T> extends linkedListNode {
            void add(T value) {
                if (linkedList == null) {
                    linkedList = new linkedListNode(value);
                    linkedList.prev = null;
                } else if (linkedList != null) {
                    addHelper(value, linkedList, linkedList);
                }
            }

            private void addHelper(T value, linkedListNode node, linkedListNode prev) {
                if (node.next != null) {
                    addHelper(value, node.next, node);
                } else {
                    node.next = new linkedListNode(value);
                    node.next.prev = node;
                }

            }

        }
        class removeExample<T> extends linkedListNode {
            public void delete(T value) {
                deleteHelper(value, linkedList);
            }

            private void deleteHelper(T value, linkedListNode linkedList) {
                if (linkedList == null) {
                    return;
                }
                if (linkedList != null && linkedList.value == value && linkedList.next != null) {
                    linkedList.next.prev = linkedList.prev;
                } else if (linkedList != null && linkedList.value == value && linkedList.next == null) {
                    linkedList.prev.next = null;
                } else
                    deleteHelper(value, linkedList.next);
            }
        }
        //Operacje usuwania, wyszukiwania w najgorszym wypadku zajmują O(n)
        //Dodawanie zajmuje 0(1) gdyż dodajemy element na koniec listy.
    }
}