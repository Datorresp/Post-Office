package model;

public class Methods {
	
	//floyd warshall
	
	public long[][] floydWarshall(){
		long[][] min = new long [vertex.size()][vertex.size()];
		for(int i=0; i < vertex.size();i++) {
			for(int j=0; j < vertex.size();j++) {
				if (i==j) {
					min[i][j]=0;
				}else {
					min[i][j]= vertex.get(i).weight(vertex.get(j));
				}
			}
		}
		for (int k = 0; k < vertex.size(); k++) {
			for (int i = 0; i < vertex.size(); i++) {
				for (int j = 0; j < vertex.size(); j++) {
					if(min[i][j] > min[i][k] + min[k][j]) {
						min[i][j] = min[i][k] + min[k][j];
					}
				}
				
			}
			
		}
		return min;
		
	}
	
	//Dijkstra
	@Override
	public void Dijkstra(E origin) {
		PriorityQueue<VertexList<E>> pq = new PriorityQueue<VertexList<E>>();
		for(int i = 0; i < vertex.size(); i++) {
			if(vertex.get(i).getObject().equals(origin)) {
				vertex.get(i).setDistance(0);
			}else {
				vertex.get(i).setDistance(Integer.MAX_VALUE);
			}
			vertex.get(i).setPredecessor(null);
			pq.add(vertex.get(i));
		}
		while(!pq.isEmpty()) {
			VertexList<E> u = pq.poll();
			List<Adjacent<E>> adjacents = u.getAdjacents();
			for(int i = 0; i < adjacents.size(); i++) {
				int alt = u.getDistance() + adjacents.get(i).getWeight();
				if(alt < adjacents.get(i).getVertex().getDistance()) {
					adjacents.get(i).getVertex().setDistance(alt);
					adjacents.get(i).getVertex().setPredecessor(u);
					pq.remove(adjacents.get(i).getVertex());
					pq.add(adjacents.get(i).getVertex());
				}
			}
		}
	}
	//prim 
	public int prim(E node) {
		int cost = 0;
		VertexList<E> firstNode = null;
		boolean finded = false;
		for(int i = 0; i < vertex.size() && !finded; i++) {
			if(vertex.get(i).getObject().equals(node)) {
				firstNode = vertex.get(i);
				finded = true;
			}
		}
		List<VertexList<E>> addVertexVisited = new ArrayList<>();
		List<Adjacent<E>> noVisited = new ArrayList<>();
		addVertexVisited.add(firstNode);
		System.out.println("aqui");
		for(int i = 0; i < firstNode.getAdjacents().size();i++) {
			noVisited.add(firstNode.getAdjacents().get(i));
		}
		int m = 1;
		boolean t = false;
		while(!noVisited.isEmpty() && !t) {			
				Adjacent<E>  temp = compareWeight(noVisited);
				if(temp != null) {
				if(!existInList(temp,addVertexVisited)) {
					addVertexVisited.add(temp.getVertex());
					cost += temp.getWeight();
					m++;
					Adjacent<E> temp1 = temp;
					noVisited.remove(temp1);
					for(int i = 0; i < temp.getVertex().getAdjacents().size();i++) {
						noVisited.add(temp.getVertex().getAdjacents().get(i));
					}

					
				}else {
					noVisited.remove(temp);
				}
		}else {
			t = true;
		}
	}
		return cost;
	}

}
