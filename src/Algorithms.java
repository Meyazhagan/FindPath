import java.nio.file.Path;
import java.util.ArrayList;

public class Algorithms {
  //DIJKSTRA WORKS BY PROPAGATING OUTWARDS UNTIL IT FINDS THE FINISH AND THEN WORKING ITS WAY BACK TO GET THE PATH
		//IT USES A PRIORITY QUE TO KEEP TRACK OF NODES THAT IT NEEDS TO EXPLORE
		//EACH NODE IN THE PRIORITY QUE IS EXPLORED AND ALL OF ITS NEIGHBORS ARE ADDED TO THE QUE
		//ONCE A NODE IS EXLPORED IT IS DELETED FROM THE QUE
		//AN ARRAYLIST IS USED TO REPRESENT THE PRIORITY QUE
		//A SEPERATE ARRAYLIST IS RETURNED FROM A METHOD THAT EXPLORES A NODES NEIGHBORS
		//THIS ARRAYLIST CONTAINS ALL THE NODES THAT WERE EXPLORED, IT IS THEN ADDED TO THE QUE
		//A HOPS VARIABLE IN EACH NODE REPRESENTS THE NUMBER OF NODES TRAVELED FROM THE START
		public void Dijkstra(PathSearching p) {
			final ArrayList<Node> priority = new ArrayList<Node>();	//CREATE A PRIORITY QUE
			priority.add(p.map[p.startx][p.starty]);	//ADD THE START TO THE QUE
			while(p.solving) {
				if(priority.size() <= 0) {	//IF THE QUE IS 0 THEN NO PATH CAN BE FOUND
					p.solving = false;
					break;
				}
				final int hops = priority.get(0).getPathL()+1;	//INCREMENT THE HOPS VARIABLE
				final ArrayList<Node> explored = exploreNeighbors(priority.get(0), hops, p);	//CREATE AN ARRAYLIST OF NODES THAT WERE EXPLORED
				if(explored.size() > 0) {
					priority.remove(0);	//REMOVE THE NODE FROM THE QUE
					priority.addAll(explored);	//ADD ALL THE NEW NODES TO THE QUE
					p.Update();
					p.delay();
				} else {	//IF NO NODES WERE EXPLORED THEN JUST REMOVE THE NODE FROM THE QUE
					priority.remove(0);
				}
			}
		}

  //A STAR WORKS ESSENTIALLY THE SAME AS DIJKSTRA CREATING A PRIORITY QUE AND PROPAGATING OUTWARDS UNTIL IT FINDS THE END
		//HOWEVER ASTAR BUILDS IN A HEURISTIC OF DISTANCE FROM ANY NODE TO THE FINISH
		//THIS MEANS THAT NODES THAT ARE CLOSER TO THE FINISH WILL BE EXPLORED FIRST
		//THIS HEURISTIC IS BUILT IN BY SORTING THE QUE ACCORDING TO HOPS PLUS DISTANCE UNTIL THE FINISH
		public void AStar(PathSearching p) {
			final ArrayList<Node> priority = new ArrayList<Node>();
			priority.add(p.map[p.startx][p.starty]);
			while(p.solving) {
				if(priority.size() <= 0) {
					p.solving = false;
					break;
				}
				final int hops = priority.get(0).getPathL()+1;
				final ArrayList<Node> explored = exploreNeighbors(priority.get(0),hops, p);
				if(explored.size() > 0) {
					priority.remove(0);
					priority.addAll(explored);
					p.Update();
					p.delay();
				} else {
					priority.remove(0);
				}
				sortQue(priority);	//SORT THE PRIORITY QUE
			}
		}
		
		public ArrayList<Node> sortQue(final ArrayList<Node> sort) {	//SORT PRIORITY QUE
			int c = 0;
			while(c < sort.size()) {
				int sm = c;
				for(int i = c+1; i < sort.size(); i++) {
					if(sort.get(i).getEuclidDist()+sort.get(i).getPathL() < sort.get(sm).getEuclidDist()+sort.get(sm).getPathL())
						sm = i;
				}
				if(c != sm) {
					final Node temp = sort.get(c);
					sort.set(c, sort.get(sm));
					sort.set(sm, temp);
				}	
				c++;
			}
			return sort;
		}
		
		public ArrayList<Node> exploreNeighbors(final Node current, final int hops, PathSearching p) {	//EXPLORE NEIGHBORS
			final ArrayList<Node> explored = new ArrayList<Node>();	//LIST OF NODES THAT HAVE BEEN EXPLORED
			for(int a = -1; a <= 1; a++) {
				for(int b = -1; b <= 1; b++) {
					final int xbound = current.getX()+a;
					final int ybound = current.getY()+b;
					if((xbound > -1 && xbound < p.cells) && (ybound > -1 && ybound < p.cells)) {	//MAKES SURE THE NODE IS NOT OUTSIDE THE GRID
						final Node neighbor = p.map[xbound][ybound];
						if((((neighbor.getPathL() == -1) || (neighbor.getPathL()) > hops)) && neighbor.getType()!=2) {	//CHECKS IF THE NODE IS NOT A WALL AND THAT IT HAS NOT BEEN EXPLORED
							explore(neighbor, current.getX(), current.getY(), hops,p);	//EXPLORE THE NODE
							explored.add(neighbor);	//ADD THE NODE TO THE LIST
						}
					}
				}
			}
			return explored;
		}
		
		public void explore(final Node current, final int lastx, final int lasty, final int hops, PathSearching p) {	//EXPLORE A NODE
			if(current.getType()!=0 && current.getType() != 1)	//CHECK THAT THE NODE IS NOT THE START OR FINISH
				current.setType(4);	//SET IT TO EXPLORED
			current.setLastNode(lastx, lasty);	//KEEP TRACK OF THE NODE THAT THIS NODE IS EXPLORED FROM
			current.setPathL(hops);	//SET THE HOPS FROM THE START
			p.checks++;
			if(current.getType() == 1) {	//IF THE NODE IS THE FINISH THEN BACKTRACK TO GET THE PATH
				backtrack(current.getLastx(), current.getLasty(),hops,p);
			}
		}
		
		public void backtrack(int lx, int ly, int hops, PathSearching p) {	//BACKTRACK
			p.length = hops;
			while(hops > 1) {	//BACKTRACK FROM THE END OF THE PATH TO THE START
				final Node current = p.map[lx][ly];
				current.setType(5);
				lx = current.getLastx();
				ly = current.getLasty();
				hops--;
			}
			p.solving = false;
		}
	}