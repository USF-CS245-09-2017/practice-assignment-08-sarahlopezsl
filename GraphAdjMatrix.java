import java.util.*;

public class GraphAdjMatrix implements Graph {

	class Vertex {
		int value; 
		Vertex connection; 
		
		public Vertex(){
			value = -1; 
			connection = null; 
			
		}
	}  
	
	int [][] edges; 
	Vertex [] vertices; 
	
	    public GraphAdjMatrix(int v) {
	        
			edges = new int[v][v];
			
			vertices = new Vertex [v];
	       
			for(int i = 0; i<v;i++){
				Vertex x = new Vertex(); 
				vertices[i]= x;
				vertices[i].value = i; 
			}
	    }

	public void addEdge (int src, int tar){
		edges[src][tar] = 1; 
	}
	
	public int[] neighbors (int vertex){
		
		int [] neighbors =  new int [outDegree(vertex)]; 
		int pos = 0; 
		
		for (int i = 0; i <vertices.length; i++){
			
			if(edges[vertex][i]==1){
				neighbors[pos]=i;
				pos++; 
			}
		}
		
		return neighbors;
		
	}
	
	public int outDegree(int vertex){
		int degree = 0; 
		for(int i=0; i<edges.length; i++){
			if(edges[vertex][i]==1)
				degree++;
		}
		return degree;
	}
	
	public int inDegree(int vertex){
		int degree =0;
		for(int i=0;i<edges.length; i++){
			if(edges[i][vertex]==1){
				degree++;
			}
		}
		return degree; 
	}
	
	public int findzero(int[]incident){
		for(int i=0; i<incident.length; i++){
			if(incident[i]==0){
				incident[i]=-1;
				return i;
			} 
		}
		return -1;
	}


	public void topologicalSort() {
		

		int []incident= new int[vertices.length];
		for(int i=0;i<vertices.length;i++){
			incident[i]=inDegree(i);
		}
		
//		for(int i =0; i<incident.length; i++){
//			System.out.println(incident[i]);
//		}
		
		ArrayList<Integer> queue = new ArrayList<Integer>(); 
		

		while (moreIncidents(incident)){ //conditional?
			int vertex= findzero(incident);
			if(vertex !=-1){
				queue.add(vertex); 
				for(int i=0;i<vertices.length; i++){
					if(edges[vertex][i]==1){
						incident[i]--;
					}
				}
			}
		}
		
		for (int i=0; i<queue.size(); i++){
			System.out.print(queue.get(i));
		}
		
		
	}

	private boolean moreIncidents(int []incident) {
		
		for(int i=0; i<incident.length; i++){
			if(incident[i]> -1){
				return true; 
			}

		}
		return false; 
	}
	


}
