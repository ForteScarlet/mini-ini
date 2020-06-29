/*
 * Copyright (c) 2020. ForteScarlet All rights reserved.
 * Project  mini-ini
 * File     ProxyList.java
 *
 * You can contact the author through the following channels:
 * github https://github.com/ForteScarlet
 * gitee  https://gitee.com/ForteScarlet
 * email  ForteScarlet@163.com
 *
 */

package love.forte.minini.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * proxy list
 * @author <a href="https://github.com/ForteScarlet"> ForteScarlet </a>
 */
public interface ProxyList<E> extends List<E> {
    /**
     * get the real list.
     * @return real list.
     */
    List<E> getProxyList();

    @Override
    default int size() {
        return getProxyList().size();
    }

    @Override
    default boolean isEmpty() {
        return getProxyList().isEmpty();
    }

    @Override
    default boolean contains(Object o) {
        return getProxyList().contains(o);
    }

    @Override
    default Iterator<E> iterator() {
        return getProxyList().iterator();
    }

    @Override
    default Object[] toArray() {
        return getProxyList().toArray();
    }

    @Override
    default <T> T[] toArray(T[] a) {
        return getProxyList().toArray(a);
    }

    @Override
    default boolean add(E property) {
        return getProxyList().add(property);
    }

    @Override
    default boolean remove(Object o) {
        return getProxyList().remove(o);
    }

    @Override
    default boolean containsAll(Collection<?> c) {
        return getProxyList().containsAll(c);
    }

    @Override
    default boolean addAll(Collection<? extends E> c) {
        return getProxyList().addAll(c);
    }

    @Override
    default boolean addAll(int index, Collection<? extends E> c) {
        return getProxyList().addAll(index, c);
    }

    @Override
    default boolean removeAll(Collection<?> c) {
        return getProxyList().removeAll(c);
    }

    @Override
    default boolean retainAll(Collection<?> c) {
        return getProxyList().retainAll(c);
    }

    @Override
    default void clear() {
        getProxyList().clear();
    }

    @Override
    default E get(int index) {
        return getProxyList().get(index);
    }

    @Override
    default E set(int index, E element) {
        return getProxyList().set(index, element);
    }

    @Override
    default void add(int index, E element) {
        getProxyList().add(index, element);
    }

    @Override
    default E remove(int index) {
        return getProxyList().remove(index);
    }

    @Override
    default int indexOf(Object o) {
        return getProxyList().indexOf(o);
    }

    @Override
    default int lastIndexOf(Object o) {
        return getProxyList().lastIndexOf(o);
    }

    @Override
    default ListIterator<E> listIterator() {
        return getProxyList().listIterator();
    }

    @Override
    default ListIterator<E> listIterator(int index) {
        return getProxyList().listIterator(index);
    }

    @Override
    default List<E> subList(int fromIndex, int toIndex) {
        return getProxyList().subList(fromIndex, toIndex);
    }
    
}
