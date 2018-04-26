import java.util.ArrayList;
/*
 * Name: <your name>
 * EID: <your EID>
 */
;

public class Graph implements Program2{
	// n is the number of ports
	private int n;
	ArrayList<ArrayList<Edge>> g = new ArrayList<ArrayList<Edge>>();

	// Edge is the class to represent an edge between two nodes
	// node is the destination node this edge connected to
	// time is the travel time of this edge
	// capacity is the capacity of this edge
	// Use of this class is optional. You may make your own, and comment
	// this one out.
	private class Edge{
		public int node;
		public int time;
		public int capacity;
		public Edge(int n, int t, int c){
			node = n;
			time = t;
			capacity = c;
		}

		// prints out an Edge.
		public String toString() {
			return "" + node;
		}
	}

	// Here you have to define your own data structure that you want to use
	// to represent the graph
	// Hint: This include an ArrayList or many ArrayLists?
	// ....

	// This function is the constructor of the Graph. Do not change the parameters
	// of this function.
	//Hint: Do you need other functions here?
	public Graph(int x) {
		n = x;
	}
    
	// This function is called by Driver. The input is an edge in the graph.
	// Your job is to fix this function to generate your graph.
	// Do not change its parameters or return type.
	// Hint: Here is the place to build the graph with the data structure you defined.
	public void inputEdge(int port1, int port2, int time, int capacity) {
		g.get(port1).add(new Edge(port2, time, capacity));
		g.get(port2).add(new Edge(port1, time, capacity));
	}

	// This function is the solution for the Shortest Path problem.
	// The output of this function is an int which is the shortest travel time from source port to destination port
	// Do not change its parameters or return type.
	public int findTimeOptimalPath(int sourcePort, int destPort) {
		ArrayList<Integer> heads = new ArrayList<Integer>();
		boolean[] vis = new boolean[n];
		int[] dis = new int[n];
		for(int i = 0; i < n; i++){
			vis[i] = false;
			dis[i] = -1;
		}
		heads.add(sourcePort);
		while(!desFound(heads, dis, destPort)){
			
		}
		


		return -1;
	}
	public boolean desFound(ArrayList<Integer> heads, int[] dis, int destPort){
		boolean ret = true;
		for(Integer x: heads){
			if(dis[x.intValue()] < dis[destPort] || dis[destPort] == -1){
				ret = false;
			}
		}
		return ret;
	}
	/**
	 * update all surrounding nodes,
	 * sort
	 * add the shortest
	 * change head
	 */
	public void update(ArrayList<Integer> heads, int[]dis, int head){
		
	}
	public void compareDis(int head, int des, int[]dis){
		int weight = getEdge(head, des).time;;
		if(dis[des] == -1 || dis[des] > (weight + dis[head])){
			//dis[des] = getEdge(head, des).time + ;
			return;
		}
		if(dis[des] > weight){
			//dis[des] = 
		}
		
	}
	public Edge getEdge(int head, int des){
		for(Edge x: g.get(head)){
			if(x.node == des){
				return x;
			}
		}
		return null;
	}

	// This function is the solution for the Widest Path problem.
	// The output of this function is an int which is the maximum capacity from source port to destination port 
	// Do not change its parameters or return type.
	public int findCapOptimalPath(int sourcePort, int destPort) {
		return -1;
	}

	// This function returns the neighboring ports of node.
	// This function is used to test if you have contructed the graph correct.
	public ArrayList<Integer> getNeighbors(int node) {
		ArrayList<Integer> edges = new ArrayList<Integer>();
		//edges.addAll(g.get(node));
		for(Edge x: g.get(node)){
			edges.add(x.node);
		}
		return edges;
	}

	public int getNumPorts() {
		return n;
	}
}
