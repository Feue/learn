package Advanced.multithreading;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Feue
 * @create 2021-12-14 10:47
 */
public class CopyOnWriteMap<K, V> implements Map<K, V>, Cloneable {
    private volatile Map<K, V> internalMap;

    public CopyOnWriteMap() {
        this.internalMap = new HashMap<K, V>();
    }

    @Nullable
    @Override
    public V put(K key, V value) {
        synchronized (this) {
            Map<K, V> newMap = new HashMap<>(this.internalMap);
            V val = newMap.put(key, value);
            this.internalMap = newMap;
            return val;
        }
    }

    @Override
    public boolean replace(K key, V oldValue, V newValue) {
        synchronized (this) {
            Map<K, V> newMap = new HashMap<>(this.internalMap);
            V val = newMap.get(key);
            if (val != oldValue) {
                return false;
            }
            newMap.put(key, newValue);
            this.internalMap = newMap;
            return true;
        }
    }

    @Override
    public V get(Object key) {
        return this.internalMap.get(key);
    }

    @Override
    public int size() {
        return this.internalMap.size();
    }

    @Override
    public boolean isEmpty() {
        return this.internalMap.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return this.internalMap.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return this.internalMap.containsValue(value);
    }

    @Override
    public V remove(Object key) {
        synchronized (this) {
            Map<K, V> newMap = new HashMap<>(this.internalMap);
            V val = newMap.remove(key);
            this.internalMap = newMap;
            return val;
        }
    }

    @Override
    public void clear() {
        synchronized (this) {
            this.internalMap = new HashMap<>();
        }
    }

    @NotNull
    @Override
    public Set<K> keySet() {
        return this.internalMap.keySet();
    }

    @NotNull
    @Override
    public Collection<V> values() {
        return this.internalMap.values();
    }

    @NotNull
    @Override
    public Set<Entry<K, V>> entrySet() {
        return this.internalMap.entrySet();
    }

    @Override
    public void putAll(@NotNull Map<? extends K, ? extends V> m) {
        synchronized (this) {
            Map<K, V> newMap = new HashMap<>(this.internalMap);
            newMap.putAll(m);
            this.internalMap = newMap;
        }
    }
}
