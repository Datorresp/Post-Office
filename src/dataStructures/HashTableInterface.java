package dataStructures;

public interface HashTableInterface<K, V> {

	public V search(K key);

	public void insert(K key, V value);

	public void delete(K key);

}