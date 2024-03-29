import edu.princeton.cs.algs4.Stopwatch;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;

public class Insertion {

  public static <T extends Comparable<? super T>> void sort(T[] a) {
    int size = a.length;
    int ex = 0;
    int comp = 0;
    int min = 0;
    if (size == 0 || size == 1) {
      return;
    }

    for (int j = 1; j < a.length; j++) {
      comp++;
      if (less(a[j], a[min])) min = j;
    }
    exch(a,0,min);

    for (int i = 1; i < size; i++) {
      T cur = a[i];
      int index = i-1;
      while (less(cur, a[index])) {
        comp++;
        a[index+1] = a[index];
        index--;
      }
      ex++;
      a[index+1] = cur;
    }

    System.out.println("ex: " + ex);
    System.out.println("comp: " + comp);
  }

  private static <T extends Comparable<? super T>> boolean less(T v, T w) {
    return v.compareTo(w) < 0;
  }

  private static <T extends Comparable<? super T>>  void exch(T[] a, int i, int j) {
    T t = a[i];
    a[i] = a[j];
    a[j] = t;
  }

  private static <T extends Comparable<? super T>> void show(T[] a) {
    for (int i = 0; i < a.length; i++) {
      StdOut.print(a[i] + " ");
    }
    StdOut.println();
  }

  public static <T extends Comparable<? super T>> boolean isSorted(T[] a) {
    for (int i = 1; i < a.length; i++) {
      if (less(a[i], a[i - 1])) return false;
    }
    return true;
  }

  private static Integer[] useInt(String[] strs) {
    int size = strs.length;
    Integer[] ints = new Integer[size];
    for (int i = 0; i < size; i++) {
      ints[i] = Integer.parseInt(strs[i]);
    }
    return ints;
  }

  public static void main(String[] args) {
    if (args[0].equals("short")) {
      while (StdIn.hasNextLine()) {
        String a = StdIn.readLine();
        String[] as = a.split(" ");
        Stopwatch sw = new Stopwatch();
        sort(as);
        System.out.println(sw.elapsedTime());
        assert isSorted(as);
        //show(as);
      }
    } else if (args[0].equals("long")) {
        String[] as = StdIn.readAllStrings();
        sort(as);
        assert isSorted(as);
        show(as);
    } else {
        String[] as = StdIn.readAllStrings();
        Integer[] ints = useInt(as);
        Stopwatch sw = new Stopwatch();
        sort(ints);
        System.out.println(sw.elapsedTime());
        assert isSorted(ints);
        //show(ints);
    }
  }
}
