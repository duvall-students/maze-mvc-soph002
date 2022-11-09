package searches;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Stack;

import application.Maze;

public class DFS extends SearchAlgorithm{

	public DFS(Maze mazeBlocks, Point startPoint, Point goalPoint){
		super(mazeBlocks, startPoint, goalPoint);
		searchType="DFS";
		// The data structure for DFS is a stack.
		Stack<Point> stack =new Stack<>();
		stack.push(startPoint);
		data = stack;
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

	// When a new node is chosen, push it on the stack
	void recordLink(Point next){
		Stack<Point> stack = (Stack<Point>)data;
		// FIXME: add try/catch for ClassCastException
		stack.push(next);
	}
	
	/*
	 * Get the next fringe point to consider.
	 * 
	 * This implementation resets the "current" instance variable 
	 * to be the next one on the fringe data structure.
	 */
	void resetCurrent(){
		Stack<Point> stack = (Stack<Point>)data;
		current = stack.peek();
	}

	void noNextStep(){
		maze.markVisited(current);
		Stack<Point> stack = (Stack<Point>)data;
		stack.pop();
	}
}
