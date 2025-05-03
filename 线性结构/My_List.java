package Linear;

import java.util.Iterator;
import java.util.function.Consumer;
import java.util.stream.Stream;

public abstract class My_List<E> implements Iterable<E> {
    public abstract int size();
    public abstract boolean isEmpty();
    public abstract void clear();
    public abstract void add(E element);
    public abstract boolean add(int index, E element);
    public abstract E remove(int index);
    public abstract boolean remove(E element);
    public abstract E get(int index);
    public abstract E set(int index, E element);
    public abstract int indexOf(E element);
    public abstract boolean contains(E element);
    public abstract boolean addAll(My_List<E> other);
    public abstract E[] toArray();
    public abstract String toString();
    public abstract void forEach(Consumer<? super E> consumer);
    public abstract Iterator<E> iterator();
    public abstract Stream<E> Stream();
}
