import java.util.Iterator;

public class Main {
 
    public static void main(String[] args) {
       LinkedDeque<Integer> ld = new LinkedDeque<>();
       ld.pushFront(1);
       ld.pushFront(2);
       ld.pushBack(3);
        System.out.println(ld.popBack());
        System.out.println(ld.popBack());
        System.out.println(ld.popBack());
        ld.pushFront(1);
       ld.pushFront(2);
       ld.pushBack(3);
        System.out.println(ld.popBack());
        System.out.println(ld.popBack());
        System.out.println(ld.popBack());
        ld.pushFront(1);
       ld.pushFront(2);
       ld.pushBack(3);
        System.out.println(ld.popBack());
        System.out.println(ld.popBack());
        System.out.println(ld.popBack());
    }
}