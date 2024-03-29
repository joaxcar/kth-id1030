import edu.princeton.cs.algs4.Stopwatch;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import java.lang.reflect.Array;

public class MergeIt {
  public static <T extends Comparable<? super T>> void sort(T[] a) {
    @SuppressWarnings("unchecked")
    T[] aux = (T[]) new Comparable[a.length];
    sort(a, aux);
  }

  private static <T extends Comparable<? super T>> void sort(T[] a, T[] aux) {
    int n = a.length; 
    int last = n - 1;
    for (int i = 1; i < n; i = 2 * i) {
      for (int j = 0; j < n; j = j + 2 * i) {
        int mid = j + i - 1 > last ? last : j + i -1;
        int hi = j + 2 * i - 1 > last ? last : j + 2 * i - 1;
        merge(a, aux, j, mid, hi);
      }
    } 
  }


  private static <T extends Comparable<? super T>> void merge(T[] a, T[] aux, int lo,  int mid, int hi) {
    assert isSorted(a, lo, mid);
    assert isSorted(a, mid + 1, hi);

    for (int k = lo; k <= hi; k++) {
      aux[k] = a[k];
    }

    int i = lo;
    int j = mid + 1;
    for (int k = lo; k <= hi; k++) {
      if (i > mid) {
        a[k] = aux[j++];
      }
      else if (j > hi) {
        a[k] = aux[i++];
      }
      else if (less(aux[i], aux[j])) {
        a[k] = aux[i++];
      }
      else {
        a[k] = aux[j++];
      }
    }
    assert isSorted(a, lo, hi);
  }

  private static <T extends Comparable<? super T>> boolean less(T v, T w) {
    return v.compareTo(w) < 0;
  }

  private static <T extends Comparable<? super T>>  void exch(T[] a, int i, int j) {
    T t = a[i];
    a[i] = a[j];
    a[j] = t;
  }

  public static <T extends Comparable<? super T>> void show(T[] a) {
    for (int i = 0; i < a.length; i++) {
      StdOut.print(a[i] + " ");
    }
    StdOut.println();
  }

  public static <T extends Comparable<? super T>> boolean isSorted(T[] a, int start, int end) {
    for (int i = start + 1; i < end; i++) {
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
        assert isSorted(as, 0, as.length - 1);
        show(as);
      }
    } else if (args[0].equals("long")) {
        String[] as = StdIn.readAllStrings();
        sort(as);
        assert isSorted(as, 0, as.length - 1);
        show(as);
    } else {
        String[] as = StdIn.readAllStrings();
        Integer[] ints = useInt(as);
        Stopwatch sw = new Stopwatch();
        sort(ints);
        System.out.println(sw.elapsedTime());
        assert isSorted(ints, 0, ints.length - 1);
        //show(ints);
    }
  }
}
