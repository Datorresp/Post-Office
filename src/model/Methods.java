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

}
