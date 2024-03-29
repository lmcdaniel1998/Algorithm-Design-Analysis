import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Queue;

class MyGraph  {	
   ArrayList<Vertex> vertices = new ArrayList<Vertex>();
   boolean directed; 
   int nvertices;
   int nedges; 
   int numComp;

   // connectCheck method finds all connected parts of a graph
   public ArrayList<HashSet<Integer>> connectCheck() {
      // initialize output list
      ArrayList<HashSet<Integer>> output = new ArrayList<HashSet<Integer>>();
      // first hashset will hold a single number representing the number of connected components
      HashSet<Integer> numconnected = new HashSet<Integer>();
      // initialize counter for connected components
      output.add(numconnected);
      int connectcount = 0;
      // initialize queue
      Queue<Vertex> myqueue= new LinkedList<Vertex>();
      // keep looking for connected components until all vertices are discovered
      while (getUnDiscovered() != -1) { 
         Vertex head = vertices.get(getUnDiscovered());
         head.setDiscovered(true);
         myqueue.add(head);
         // hashset to store connected components
         HashSet<Integer> connected = new HashSet<Integer>();
         // run until the queue is empty
         while (myqueue.peek() != null) {
            // process each adjacent vertex to head of queue
            ArrayList<Vertex> adjacent = ((Vertex)myqueue.peek()).getEdges();
            for (int i = 0; i < adjacent.size(); i++ ) {
               // add to queue if vertex hasnt been discovered
               if (!(adjacent.get(i)).getDiscovered()) {
                  ((Vertex)(adjacent.get(i))).setDiscovered(true);
                  myqueue.add((Vertex)adjacent.get(i));
               }
            }
            // add vertex to connected hashset
            connected.add(((Vertex)(myqueue.remove())).getKey());
         }
         output.add(connected);
         connectcount++;
      }
      (output.get(0)).add(connectcount);
      // print out connected components
      System.out.print("[");
      for (int i = 0; i < output.size(); i++) {
         System.out.print("{");
         for (Integer x : output.get(i)) {
            System.out.print(x + " ");
         }
         System.out.print("\b}, ");
      }
      System.out.print("\b\b]\n");
      return output;
   }

   public boolean bipartiteCheck() {
      // initialize queue
      Queue<Vertex> myqueue= new LinkedList<Vertex>();
      // keep looking for uncolored components until all vertices are discovered
      while (getUnColored() != -1) { 
         Vertex head = vertices.get(getUnColored());
         head.setColor(0);
         myqueue.add(head);
         // run until the queue is empty
         while (myqueue.peek() != null) {
            // process each adjacent vertex to head of queue
            ArrayList<Vertex> adjacent = ((Vertex)myqueue.peek()).getEdges();
            for (int i = 0; i < adjacent.size(); i++ ) {
               if ((adjacent.get(i)).getColor() == -1) {
                  // color adjacent vertex opposite of vertex
                  if (((Vertex)myqueue.peek()).getColor() == 0) {
                     ((Vertex)(adjacent.get(i))).setColor(1);
                  }
                  else if (((Vertex)myqueue.peek()).getColor() == 1) {
                     ((Vertex)(adjacent.get(i))).setColor(0);
                  }
                  // mark as discovered and add to queue
                  myqueue.add((Vertex)adjacent.get(i));
               }
               // if adjacent is colored the same as vertex then graph is not bipartite
               else if ((adjacent.get(i)).getColor() == ((Vertex)myqueue.peek()).getColor()) {
                  return false;
               }
            }
            Vertex popped = (Vertex)(myqueue.poll());
         }
      }
      return true;
   }

   public int getUnDiscovered() {
      for (int i = 0; i < nvertices; i++) {
         if (((Vertex)vertices.get(i)).getDiscovered() == false) {
           return i;
         }
      }
      return -1;
   }

   public int getUnColored() {
      for (int i = 0; i < nvertices; i++) {
         if (((Vertex)vertices.get(i)).getColor() == -1) {
           return i;
         }
      }
      return -1;
   }   
	
   void readfile_graph(String filename) throws FileNotFoundException  {
      int x,y;
        //read the input
      FileInputStream in = new FileInputStream(new File(filename));
      Scanner sc = new Scanner(in);
      int temp = sc.nextInt(); // if 1 directed; 0 undirected
      directed = (temp == 1);
      nvertices = sc.nextInt();
      for (int i=0; i<=nvertices-1; i++){
         Vertex tempv = new Vertex(i+1);   // kludge to store proper key starting at 1
         vertices.add(tempv);
      }

      nedges = sc.nextInt();   // m is the number of edges in the file
      int nedgesFile = nedges;
      for (int i=1; i<=nedgesFile ;i++)	{
         //System.out.println(i + " compare " + (i<=nedges) + " nedges " + nedges);
         x=sc.nextInt();
	 y=sc.nextInt();
         //System.out.println("x  " + x + "  y:  " + y  + " i " + i);
	 insert_edge(x,y,directed);
      }  
      // order edges to make it easier to see what is going on
      for (int i=0;i<=nvertices-1;i++)	{
         Collections.sort(vertices.get(i).edges);
      }  
   }
   
   static void process_vertex_early(Vertex v)	{
      timer++;
      v.entry_time = timer;
      //     System.out.printf("entered vertex %d at time %d\n",v.key, v.entry_time);
   }
	
	static void process_vertex_late(Vertex v)	{
		timer++;
		v.exit_time = timer;
		//     System.out.printf("exit vertex %d at time %d\n",v.key , v.exit_time);
	}

	static void process_edge(Vertex x,Vertex y) 	{
		int c = edge_classification(x,y);
		if (c == BACK) System.out.printf("back edge (%d,%d)\n",x.key,y.key);
		else if (c == TREE) System.out.printf("tree edge (%d,%d)\n",x.key,y.key);
		else if (c == FORWARD) System.out.printf("forward edge (%d,%d)\n",x.key,y.key);
		else if (c == CROSS) System.out.printf("cross edge (%d,%d)\n",x.key,y.key);
		else System.out.printf("edge (%d,%d)\n not in valid class=%d",x.key,y.key,c);	
	}
	
	static void initialize_search(MyGraph g)	{
		for(Vertex v : g.vertices)		{
			v.processed = v.discovered = false;
			v.parent = null;
		}
	}
	
	static final int TREE = 1, BACK = 2, FORWARD = 3, CROSS = 4;
	static int timer = 0;
	
	static int edge_classification(Vertex x, Vertex y)	{
		if (y.parent == x) return(TREE);
		if (y.discovered && !y.processed) return(BACK);
		if (y.processed && (y.entry_time > x.entry_time)) return(FORWARD);
		if (y.processed && (y.entry_time < x.entry_time)) return(CROSS);
		System.out.printf("Warning: self loop (%d,%d)\n",x,y);
		return -1;
	}
   
	void insert_edge(int x, int y, boolean directed) 	{
		Vertex one = vertices.get(x-1);
      Vertex two = vertices.get(y-1);
      one.edges.add(two);      
		vertices.get(x-1).degree++;
		if(!directed)
			insert_edge(y,x,true);
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
			for(Vertex w : v.edges)
				System.out.print("  adjacency list: " + w.key);
			System.out.println();
		}
	} 

   class Vertex implements Comparable<Vertex> {
      int key;
      int degree;
      int component;
      int color = -1;    // use mod numColors, -1 means not colored
      boolean discovered = false;
      boolean processed = false;
      int entry_time = 0;
      int exit_time = 0;
      Vertex parent = null;
      ArrayList<Vertex> edges = new ArrayList<Vertex>();
   
      public Vertex(int tkey){ 
         key = tkey;
      }
      public ArrayList<Vertex> getEdges() {
         return edges;
      }
      public int getKey() {
         return key;
      }
      public int getDegree() {
         return degree;
      }
      public boolean getDiscovered() {
         return discovered;
      }
      public void setDiscovered(boolean found) {
         discovered = found;
      }
      public void setColor(int newcolor) {
         color = newcolor;
      }
      public int getColor() {
         return color;
      }

      public int compareTo(Vertex other) {
         if (this.key > other.key){
            return 1;
         }         else if (this.key < other.key) {
            return -1;
         }
         else 
            return 0;
      }
   }

   public Vertex unProcessedV(){	   
      for (Vertex v:  vertices)  {
         if (! v.processed ) return v;
      }
      return null;	
   }
}
