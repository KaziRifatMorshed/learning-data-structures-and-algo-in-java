package DataStructure.Tree;

import java.util.Iterator;

interface Position<E> {
    E getElement();
}

interface tree<E> extends Iterable<E> {
    Position<E> root();

    Position<E> parent(Position<E> p);

    Iterable<Position<E>> children(Position<E> p);

    int numChildren(Position<E> p);

    boolean isInternal(Position<E> p);

    boolean isExternal(Position<E> p);

    boolean isRoot(Position<E> p);

    int Size();

    boolean isEmpty();

    Iterator<E> iterator();

    Iterable<Position<E>> positions();

//    ===================


}


