package application;

import javafx.scene.shape.Rectangle;
import searches.*;

import java.awt.*;

public class MazeController {
    /*
     * Logic of the program
     */
    // The search algorithms
//    private Greedy greedy;
//    private BFS bfs;
//    private DFS dfs;
//    private RandomWalk rand;
//    private Magic magic;
    private SearchAlgorithm search;		// This string tells which algorithm is currently chosen.  Anything other than
    // the implemented search class names will result in no search happening.
    private SearchFactory selectSearch=new SearchFactory();
    // Where to start and stop the search
    private Point start;
    private Point goal;

    // The maze to search
    private Maze maze;

    private Rectangle[][] mirrorMaze;	// the Rectangle objects that will get updated and drawn.  It is
    // called "mirror" maze because there is one entry per square in
    // the maze.

    private MazeDisplay display;

    public MazeController(int numRows, int numCols, MazeDisplay thisDisplay) {
        start = new Point(1, 1);
        goal = new Point(numRows - 2, numCols - 2);
        maze = new Maze(numRows, numCols);
        display=thisDisplay;
    }

    /*
     * Re-create the maze from scratch.
     * When this happens, we should also stop the search.
     */
    public void newMaze() {
        maze.createMaze(maze.getNumRows(),maze.getNumCols());
        display.redraw();
    }

    public int getCellState(Point position) {
        return maze.get(position);
    }

    /*
     * Does a step in the search regardless of pause status
     */
    public void doOneStep(double elapsedTime){
        if (search != null) search.step();
        display.redraw();
    }

    public void startSearch(String searchType) {
        maze.reColorMaze();
        search= selectSearch.makeSearch(searchType,maze,start,goal);
    }

}
