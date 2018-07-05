package assignment4Graph;

public class Graph {
	
	boolean[][] adjacency;
	int nbNodes;
	
	public Graph (int nb){
		this.nbNodes = nb;
		this.adjacency = new boolean [nb][nb];
		for (int i = 0; i < nb; i++){
			for (int j = 0; j < nb; j++){
				this.adjacency[i][j] = false;
			}
		}
	}
	
	public void addEdge (int i, int j){
		// ADD YOUR CODE HERE
		this.adjacency[i][j] = true; // both need to be true to be related 
		this.adjacency[j][i] = true;
	}
	
	public void removeEdge (int i, int j){
		// ADD YOUR CODE HERE
		this.adjacency[i][j] = false;
		this.adjacency[j][i] = false;  
	}
	
	public int nbEdges(){
		// ADD YOUR CODE HERE
		int counter = 0; 
		for(int i = 0 ; i < this.nbNodes ; i++){
			for(int j = 0 ; j < this.nbNodes ; j++ ){
				if(i <= j ){
					if (this.adjacency[i][j] == true){
						counter ++ ;
					}
				}
			}
		}
		return counter; // DON'T FORGET TO CHANGE THE RETURN
	}
	
	private void resetVisited( boolean[]Vnode ){
		for(int i = 0 ; i< nbNodes ; i++){
			Vnode[i] = false;
		}
	} 
	//use DFS to search 
	//https://java2blog.com/depth-first-search-in-java/

	private void DFS( int root,boolean[] FirstVisit) {
		FirstVisit[root] = true; // root first 
		for(int i = 0;i < nbNodes;i ++) {
			this.adjacency[i][i]=false;
		}
		for(int deph = 0;deph < this.nbNodes ; deph ++ ) {
			if( this.adjacency[root][deph] == true && root != deph) {
				if(FirstVisit[deph] == false) {
					FirstVisit[deph] = true;
					DFS(deph,FirstVisit);
				}
			}
		}
	}


	public boolean cycle(int start){
		// ADD YOUR CODE HERE
			boolean[] visted = new boolean[nbNodes]; //array to record
			for(int counter = 0 ; counter < nbNodes ; counter ++){
				visted[counter] = false; 
			}
			boolean reverse = this.adjacency[start][start];
			this.adjacency[start][start] = false; 
			//start false 
			for(int deph = 0 ; deph < nbNodes ; deph ++){
				this.resetVisited(visted ); //reset back to false every round 
				if(this.adjacency[start][deph] == true && start != deph) {
					boolean reverse2 = this.adjacency[deph][deph];
					this.adjacency[deph][deph] = false;
					this.removeEdge(start,deph);
					DFS(start,visted);
					if(visted[deph] == true) {
						this.adjacency[start][start] = reverse;
						this.adjacency[deph][deph] = reverse2;
						this.addEdge(start, deph);
						return true;
					}
					this.addEdge(start, deph);
				}
			}
			this.adjacency[start][start]=reverse;
		return false; // DON'T FORGET TO CHANGE THE RETURN
	}
    //helper function for bfs 
	private void adding(Integer[] set , Integer a ) {
		int index = 0;
		while(set[index] != null) {
			index ++ ;
		}
		set[ index ] = a;
	}

	private void shift( Integer[] set) {
		set[0] = null;
		for(int i = 0 ; i < nbNodes - 1 ; i ++) {
			set[ i ] = set[i+1];
		}
		set[ nbNodes - 1] = null;

	}
	public int shortestPath(int start, int end){
		//initialize
		boolean[] checked = new boolean[ this.nbNodes ];
		int dFS = 0;
		int buf = 0;
		Integer[] set = new Integer[ this.nbNodes ];
		Integer[] pred = new Integer[ this.nbNodes ];

		set[0] = start;

		checked[start] = true;
	    
		int min = nbNodes + 1;
		//only 1, no other nodes 
	    if ( start == end && adjacency[start][end]) return 1;
	    // cycle but more than 1 nodes 
		if ( start == end && cycle(start)){
	        for (int i = 0 ; i < adjacency.length ; i ++){ //go through each step 
	            if ( adjacency[ start ][ i ] ){
	                removeEdge( start , i ); 
	                if ( !cycle( start ) ) {
                        if (min > shortestPath( i , end) ) {
                        	min = shortestPath(i, end);
                        }
	                    addEdge(start,i); 
					}
					else {
                        addEdge(start,i);
                    }
                }
            }
            return min+1;
        }
		//normal condition 
        while(set[ 0 ] != null) {
            buf = set[0];
            set[0] = null; //starting point 
            this.shift(set);
            for (int i = 0; i < adjacency.length; i++) {
				// go through each one 
                if (this.adjacency[buf][i] && !checked[i]) {
					// only go through the ibe unchecked 
                    pred[i] = buf;
                    if (i == end) { //hit the end 
                        set[0] = null;
                        break;
					}
					//did not hit the end go agian 
					else {
                        int j = 0;
                        while (set[j] != null) {
                        	j++;
                        }
                        set[j] = i;
                        checked[i] = true;
                    }
                }
            }
		}
		
        if (pred[end] == null){     
            return this.nbNodes + 1;
        }
        for (int i = end; i != start; i = pred[i]){
            dFS ++;
        }
        return dFS;
	}

}
