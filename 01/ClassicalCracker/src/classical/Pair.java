/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package classical;

/**
 *
 * @author nc
 */
public class Pair<A, B> implements Comparable<Pair<A, B>> {

  private A first;
  private B second;

  /**
   * Creates a new empty instance of
   * <code>Pair</code>.
   */
  public Pair(A a, B b) {
    this.first = a;
    this.second = b;
  }

  public A getFirst() {
    return first;
  }

  public void setFirst(A first) {
    this.first = first;
  }

  public B getSecond() {
    return second;
  }

  public void setSecond(B second) {
    this.second = second;
  }

  @Override
  public boolean equals(Object other) {
    if (other instanceof Pair) {
      Pair otherPair = (Pair) other;
      return ((this.first == otherPair.first
        || (this.first != null && otherPair.first != null
        && this.first.equals(otherPair.first)))
        && (this.second == otherPair.second
        || (this.second != null && otherPair.second != null
        && this.second.equals(otherPair.second))));
    }

    return false;
  }

  @Override
  public String toString() {
    return "(" + first + ", " + second + ")";
  }

  @Override
  public int compareTo(Pair<A, B> o) {
    int cmp = compare(this.first, o.first);
    return cmp == 0 ? compare(this.second, o.second) : cmp;
  }

  private static int compare(Object o1, Object o2) {
    return o1 == null ? o2 == null ? 0 : -1 : o2 == null ? +1
      : ((Comparable) o1).compareTo(o2);
  }
}
