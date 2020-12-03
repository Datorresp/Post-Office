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
	
	//DFS
	public void DFS() {
		for(int i = 0; i < vertex.size(); i++) {
			vertex.get(i).setColor("WHITE");
			vertex.get(i).setPredecessor(null);
		}
		time = 0;
		for(int i = 0; i < vertex.size(); i++) {
			if(vertex.get(i).getColor().equals("WHITE")) {
				DFSVisit(vertex.get(i));
			}
		}
	}

	public void DFSVisit(VertexList<E> u) {
		time++;
		u.setDistance(time);
		u.setColor("GRAY");
		List<Adjacent<E>> adjacents = u.getAdjacents();
		for(int i = 0; i < adjacents.size(); i++) {
			if(adjacents.get(i).getVertex().getColor().equals("WHITE")) {
				adjacents.get(i).getVertex().setPredecessor(u);
				DFSVisit(adjacents.get(i).getVertex());
			}
		}
		u.setColor("BLACK");
		time++;
		u.setF(time);
	}
	
	//BFS
	public void BFS(E origin) {
		Queue<VertexList<E>> q = new LinkedList<VertexList<E>>();
		for(int i = 0; i < vertex.size(); i++) {
			if(vertex.get(i).getObject().equals(origin)) {
				vertex.get(i).setColor("GRAY");
				vertex.get(i).setDistance(0);
				vertex.get(i).setPredecessor(null);
				q.offer(vertex.get(i));
			} else {
				vertex.get(i).setColor("WHITE");
				vertex.get(i).setDistance(Integer.MAX_VALUE);
				vertex.get(i).setPredecessor(null);
			}
		}
		while(!q.isEmpty()) {
			VertexList<E> u = q.poll();
			List<Adjacent<E>> theAdjacents = u.getAdjacents();
			for(int i = 0; i < theAdjacents.size(); i++) {
				if(theAdjacents.get(i).getVertex().getColor().equals("WHITE")) {
					theAdjacents.get(i).getVertex().setColor("GRAY");
					theAdjacents.get(i).getVertex().setDistance(u.getDistance()+1);
					theAdjacents.get(i).getVertex().setPredecessor(u);
					q.offer(theAdjacents.get(i).getVertex());
					
				}
			}
			u.setColor("BLACK");
		}
	}

}
