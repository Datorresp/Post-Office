package dataStructures;

public class HashTable <K,V> implements HashTableInterface<K, V> {
	
	private int arraySize;
	
	private NodeH<K, V>[] node;
	
	public HashTable(int size) {
		arraySize = size;
		node = (NodeH<K,V>[])new NodeH[arraySize];
	}
	
	
	public int hashFunction(K key) {
		int hashFunction = key.hashCode() % arraySize;
		return hashFunction;
	}
	
	public NodeH<K, V>[] getNode() {
		return node;
	}

	public void setNode(NodeH<K, V>[] node) {
		this.node = node;
	}

	@Override
	public V search (K key) {
		
		NodeH<K, V> newKey = node[hashFunction(key)];
		V value = null;
		if(newKey != null) {
			
			while(newKey != null) {
				
				if(newKey.getKey().equals(key)) {
					value = newKey.getValue();
				}
				newKey = newKey.getNextNodeH();
			}
		}
		return value;
	}

	
	@Override
	public void insert(K key, V value) {
		
		NodeH<K,V> checkNode = node[hashFunction(key)];
		
		if(checkNode == null) {
			
			checkNode = new NodeH<>(key,value);
			
			node[hashFunction(key)] = checkNode;
			
		}else {
			
			boolean found = false;
			
			while(checkNode != null && !found) {
				
				if(checkNode.getKey().equals(key)) {
					checkNode.setValue(value);
					found = true;
				}
				checkNode = checkNode.getNextNodeH();
			}
			
			checkNode = node[hashFunction(key)];
			NodeH<K, V> nodeH2 = new NodeH<K,V>(key, value);
			nodeH2.setNextNodeH(checkNode);
			checkNode.setPrevNodeH(nodeH2);
			node[hashFunction(key)] = nodeH2;
		}
	}
	

	@Override
	public void delete(K key) {
		
		int position = hashFunction(key);
		NodeH<K, V> aux = node[position];
		boolean found = false; 
		
		while (aux != null && !found) {
			
			if (aux.getKey().equals(key)) {
				found = true;
				
				if (aux.getNextNodeH() != null) {
					aux.getNextNodeH().setPrevNodeH(aux.getPrevNodeH());
				}
				
				if (aux.getPrevNodeH() != null) {
					aux.getPrevNodeH().setNextNodeH(aux.getNextNodeH());
				}
				
				node[position] = aux.getNextNodeH(); 
			}
			aux = aux.getNextNodeH();
		}
	}
}