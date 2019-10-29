import java.io.*;
import java.util.*;

class Dijkstra {
	ArrayList<Vertex> vertices = new ArrayList<Vertex>();
   	boolean directed; 
	int nvertices;
	int nedges; 
	int numComp;

	ArrayList<Vertex> heap;
   	int heapCap = 200;

	public static int[][] findShortPaths(String filename) {
		Dijkstra DIJ = new Dijkstra();
		try {
			DIJ.readfile_graph(filename);
		}
		catch (FileNotFoundException exception) {
			System.out.println("enter a valid filename");
		}
		int[][] returnMatrix = new int[DIJ.nvertices][3];
		int returnLoc = 0;
		DIJ.MyHeap();							// create heap
		if (!(DIJ.buildHeap(DIJ.vertices))) {				// populate the heap
			System.out.println("heap not populated list too large");
		}
		((Vertex)((DIJ.vertices).get(0))).distance = 0;			// set start vertex distance to 0
		while(((DIJ.heap).size()) > 1) {				// visit all vertices in graph
			ArrayList<Edge> adjEdges = ((Vertex)(DIJ.heap.get(1))).getEdges();		// get edges adjacent to vertex with smallest distance
			for (int i = 0; i < adjEdges.size(); i++) {
				int newWeight;
				if (((Vertex)(DIJ.heap.get(1))).distance == Integer.MAX_VALUE) {	// remove infinity from distance 
					((Vertex)(DIJ.heap.get(1))).distance = 0;
				}
				newWeight = ((Vertex)(DIJ.heap.get(1))).distance + ((Edge)(adjEdges.get(i))).weight;	// calculate distance from start to plus edge
				// check if newWeight is less than weight at vertex acrross edge
				if (((Vertex)(((Edge)adjEdges.get(i)).destination)).distance > newWeight) {
					((Vertex)(((Edge)adjEdges.get(i)).destination)).distance = newWeight;
					((Vertex)(((Edge)adjEdges.get(i)).destination)).prev = ((Vertex)(DIJ.heap.get(1)));	// update prev vertex
				}
			}
			Vertex next = DIJ.deleteMin();				// remove best vertex from queue
			next.discovered = true;					// mark as discovered
			// add vertex to SPT
			returnMatrix[returnLoc][0] = next.key;
			returnMatrix[returnLoc][1] = next.distance;
			returnMatrix[returnLoc][2] = ((Vertex)(next.prev)).key;
			returnLoc++;
		}
		// sort returnMatrix by vertex number
		java.util.Arrays.sort(returnMatrix, new java.util.Comparator<int[]>() {
			public int compare(int[] a, int[] b) {
				return Integer.compare(a[0], b[0]);
			}
		});
		return returnMatrix;	
	}

	void readfile_graph(String filename) throws FileNotFoundException  {
		int x, y, z;
		//read the input
		FileInputStream in = new FileInputStream(new File(filename));
		Scanner sc = new Scanner(in);
		int temp = sc.nextInt(); // if 1 directed; 0 undirected
		directed = (temp == 1);
		nvertices = sc.nextInt();
		for (int i = 0; i <= nvertices - 1; i++) {
			Vertex tempv = new Vertex(i + 1);   // kludge to store proper key starting at 1
			tempv.prev = tempv;			// set prev to itself for initialization
			vertices.add(tempv);
		}

		nedges = sc.nextInt();   // m is the number of edges in the file
		int nedgesFile = nedges;
		for (int i = 1; i <= nedgesFile ;i++) {
			x = sc.nextInt();
			y = sc.nextInt();
			z = sc.nextInt();
			insert_edge(x, y, z, directed);
		}  
		// order edges to make it easier to see what is going on
		for (int i = 0; i <= nvertices - 1;i++) {
			Collections.sort(vertices.get(i).edges);
      		}  
	}

	void insert_edge(int x, int y, int z, boolean directed) 	{
		Vertex one = vertices.get(x-1);
		Vertex two = vertices.get(y-1);
		Edge tempEdge1 = new Edge(one, two, z);
		one.edges.add(tempEdge1);      
		vertices.get(x-1).degree++;
		if (!directed)
			insert_edge(y, x, z, true);
		else
			nedges++;
	}

	void remove_edge(Vertex x, Vertex y)  {
		if(x.degree<0)
			System.out.println("Warning: no edge --" + x + ", " + y);
		x.edges.remove(y);
		x.degree--;
	}

	void print_graph()	{
		for(Vertex v : vertices)	{
			System.out.println("vertex: "  + v.key);
			for(Edge w : v.edges)
				System.out.print("  adjacency list: source key: " + w.source.key + "  dest key: " + w.destination.key + " weight: " + w.weight);
			System.out.println();
		}
	} 

	class Vertex implements Comparable<Vertex> {
		int key;
		int degree;
		boolean discovered = false;
		Vertex prev = null;
		int distance = Integer.MAX_VALUE;
		ArrayList<Edge> edges = new ArrayList<Edge>();
   
		public Vertex(int tkey) { key = tkey; }

		public ArrayList<Edge> getEdges() { return edges; }

		public int getKey() { return key; }

		public int getDegree() { return degree; }

		public boolean getDiscovered() { return discovered; }

		public void setDiscovered(boolean found) { discovered = found; }


		public int compareTo(Vertex other) {
			if (this.key > other.key) {
				return 1;
			}
			else if (this.key < other.key) {
				return -1;
			}
			else {
				return 0;
			}
		}
	}

	class Edge implements Comparable<Edge> {
		Vertex source;
		Vertex destination;
		int weight;

		public Edge(Vertex source, Vertex destination, int weight) {
			this.source = source;
			this.destination = destination;
			this.weight = weight;
		}

		public int compareTo(Edge other) {
			if (this.weight > other.weight) {
				return 1;
			}
			else if (this.weight < other.weight) {
				return -1;
			} 
			else {
				return 0;
			}
		}
	}


  	public int getHeapCap() { return heapCap; }

   	public int getHeapSize() { return heap.size() - 1; }

   	public boolean isFull() {
      		if (getHeapCap() == getHeapSize()) {
      	 		return true;
      		}
      		else {
      	 		return false;
      		}
   	}

   	public boolean isEmpty() {
   	  	if (getHeapSize() == 0) {
   	  	 	return true;
   	  	}
   	  	else {
   	  	 	return false;
   	  	}
   	}

   	// creates heap of default size
   	public void MyHeap() {
   	  	// heap starts at index 1
      		heap = new ArrayList<Vertex>(heapCap + 1);
		Vertex head = new Vertex(-1);
      		heap.add(head);
   	}

   	// creates heap of specified size
   	public void MyHeap(int capacity) {
   	  	heapCap = capacity;
   	  	// heap starts at index 1
      		heap = new ArrayList<Vertex>(heapCap + 1);
		Vertex head = new Vertex(-1);
      		heap.add(head);
   	}

   	// must call myHeap before this method
   	public boolean buildHeap(ArrayList<Vertex> array) {
      		// check if array fits inside of heap
      		if (array.size() > heapCap + 1) {
      			// array doesn't fit inside of heap
      	 		return false;
      		}
      		// initialize array structure with keys in order given
      		for (int x = 0; x < array.size(); x++) {
			if (((Vertex)(array.get(x))).discovered == false) {
				heap.add(array.get(x));
			}
		}
      		// loop from last node with children down to root
      		for (int i = (heap.size() / 2); i > 0; i--) {
      	 		driftDown(i);
      		}
      		return true;
   	}

   	public boolean insert(Vertex item) {
      		if (isFull() == false) {
         		if (isEmpty() == true) {
         			heap.set(1, item);
         			return true;
         		}
         		else {
         			// add new item to the end of the heap
         			heap.add(item);
         			// drift up
         			driftUp(getHeapSize());
         			return true;
         		}
      		}
      		else {
      	 		return false;
      		}
   	}

   	public Vertex deleteMin() {
   	  	Vertex minHeap;
          	if (getHeapSize() == 1) {
            		minHeap = heap.get(1);
			heap.remove(1);
			return minHeap;
          	}
   	  	if (isEmpty() == false) {
   	  	 	// save top of heap
   	  	 	minHeap = heap.get(1);
   	  	 	// move back of list to first position
   	  	 	heap.set(1, heap.get(getHeapSize()));
   	  	 	heap.remove(getHeapSize());
   	  	 	// drift new head down
   	  	 	driftDown(1);
   	  	 	return minHeap;
   	  	}
   	  	else {
   	  	 	return heap.get(0);
		}
   	}

   	public void driftDown(int index) {
   	  	int pos = index;
   	  	Vertex tmp = heap.get(pos);
   	  	int child;
   	  	while (pos * 2 <= getHeapSize()) {		// pos is parent
   	  	 	child = pos * 2;
   	  	 	// get min child
   	  	 	if (child != getHeapSize() && ((Vertex)(heap.get(child + 1))).distance < ((Vertex)(heap.get(child))).distance) {
   	  	 		child++;
   	  	 	}
   	  	 	if (((Vertex)(heap.get(child))).distance < tmp.distance) {			// heap[child] is smaller
   	  	 		heap.set(pos, heap.get(child));		// move it up
   	  	 		pos = child;						// drift down pos
   	  	 	}
   	  	 	else {
   	  	 		break;
   	  	 	}
   	  	}
   	  	heap.set(pos, tmp);
   	}

   	public void driftUp(int index) {
   	  	int pos = index;
   	  	Vertex tmp;
   	  	while ((pos / 2) > 0) {					// drift up until root
   	  	 	// drift up until parent is smaller
   	  	 	if (((Vertex)(heap.get(pos))).distance < ((Vertex)(heap.get(pos / 2))).distance) {
   	  	 		tmp = heap.get(pos / 2);
   	  	 		heap.set(pos / 2, heap.get(pos));
   	  	 		heap.set(pos, tmp);
   	  	 	}
   	  	 	pos = pos / 2;
   	  	}
   	}

	public void printHeap() {
		for (int i = 1; i < getHeapSize() + 1; i++ ) {
			System.out.println("Vertex: " + ((Vertex)(heap.get(i))).key + "	Prev: " + (((Vertex)(heap.get(i))).prev).getKey() + "		Distance: " + ((Vertex)(heap.get(i))).distance);
		}
	}
}
