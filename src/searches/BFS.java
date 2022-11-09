package searches;

import java.awt.Point;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

import application.Maze;

public class BFS extends SearchAlgorithm{

	public static final String BFS = "BFS";
	// Keeps up with the child-parent trail so we can recreate the chosen path
	HashMap<Point,Point> childParent;

	public BFS(Maze mazeBlocks, Point startPoint, Point goalPoint){
		super(mazeBlocks, startPoint, goalPoint);
		searchType= BFS;
		data = new LinkedList<>();
		data.add(startPoint);
		childParent = new HashMap<>();
	}


	/*
	 * This method defines the neighbor that gets chosen as the newest "fringe" member
	 * 
	 * It chooses the first point it finds that is empty.
	 */
	Point chooseNeighbor(Collection<Point> neighbors){
		for(Point p: neighbors){
			if(maze.get(p)==Maze.EMPTY){
				return p;
			}
		}
		return null;
	}

	/*
	 * In addition to putting the new node on the data structure, 
	 * we need to remember who the parent is.
	 */
	void recordLink(Point next){
		Queue<Point> queue = (Queue<Point>) data;
		queue.add(next);
		childParent.put(next,current);
	}

	/*
	 * The new node is the one next in the queue
	 */
	void resetCurrent(){
		Queue<Point> queue = (Queue<Point>) data;
		current = queue.peek();
	}
	/*
	 * Use the trail from child to parent to color the actual chosen path
	 */
	private void colorPath(){
		Point step = goal;
		maze.markPath(step);
		while(step!=null){
			maze.markPath(step);
			step = childParent.get(step);
		}
	}

	@Override
	public boolean searchIsOver(){
		colorPath();
		return searchResult;
	}

	void noNextStep(){
		maze.markVisited(current);
		Queue<Point> queue = (Queue<Point>) data;
		queue.remove();
	}



}
