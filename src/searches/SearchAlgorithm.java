package searches;

import application.Maze;

import java.awt.*;
import java.util.*;
import java.util.List;

public abstract class SearchAlgorithm {

    Maze maze;					// The maze being solved
    Point goal;					// The goal Point - will let us know when search is successful
    Collection<Point> data;		// Data structure used to keep "fringe" points
    boolean searchOver = false;	// Is search done?
    boolean searchResult = false;	// Was it successful?
    Point current;				// Current point being explored

    public SearchAlgorithm(Maze mazeInput, Point beginInput, Point goalInput)
    {
        maze=mazeInput;
        goal=goalInput;
        current=beginInput;
        maze.markPath(current);
    }

    public boolean step(){
        // Don't keep computing after goal is reached or determined impossible.
        if(searchOver){
            return searchIsOver();
        }
        // Find possible next steps
        Collection<Point> neighbors = getNeighbors();
        // Choose one to be a part of the path
        Point next = chooseNeighbor(neighbors);
        // mark the next step
        if(next!=null){
            maze.markPath(next);
            recordLink(next);
        }
        // if no next step is found
        else{
            noNextStep();
        }
        resetCurrent();
        checkSearchOver();
        return searchResult;
    }

    /*
     * This method defines which "neighbors" or next points can be reached in the maze from
     * the current one.
     *
     * In this method, the neighbors are defined as the four squares immediately to the north, south,
     * east, and west of the current point, but only if they are in the bounds of the maze.  It does
     * NOT check to see if the point is a wall, or visited.
     *
     * Any other definition of "neighbor" indicates the search subclass should override this method.
     */
    private Collection<Point> getNeighbors(){
        java.util.List<Point> maybeNeighbors = new ArrayList<>();
        maybeNeighbors.add(new Point(current.x-1,current.y));
        maybeNeighbors.add(new Point(current.x+1,current.y));
        maybeNeighbors.add(new Point(current.x,current.y+1));
        maybeNeighbors.add(new Point(current.x,current.y-1));
        List<Point> neighbors = new ArrayList<>();
        for(Point p: maybeNeighbors){
            if(maze.inBounds(p)){
                neighbors.add(p);
            }
        }
        return neighbors;
    }


    abstract Point chooseNeighbor(Collection<Point>neighbors);

    abstract void recordLink(Point next);

    abstract void resetCurrent();

    /*
     * Search is over and unsuccessful if there are no more fringe points to consider.
     * Search is over and successful if the current point is the same as the goal.
     */
    private void checkSearchOver(){
        if(data!= null && data.isEmpty()) {
            searchOver = true;
            searchResult = false;
        }
        if(isGoal(current)){
            searchOver = true;
            searchResult = true;
        }
    }

    public boolean searchIsOver()
    {
        return searchResult;
    }

    /*
     * Tells me when the search is over.
     */
    private boolean isGoal(Point square){
        return square!= null && square.equals(goal);
    }

    abstract void noNextStep();
}
