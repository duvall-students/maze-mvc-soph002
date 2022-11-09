package searches;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

import application.Maze;

public class Magic extends Greedy{
		public Magic(Maze mazeBlocks, Point startPoint, Point goalPoint){
			super(mazeBlocks, startPoint, goalPoint);
			searchType="Magic";
		}

		/*
		 * Rather than choosing the (first) closest NON-wall, choose 
		 * any of the closest next squares.
		 */
		protected Point chooseNeighbor(Collection<Point> neighbors){
			Point closest = closestToGoal(neighbors);
			List<Point> possibles = new ArrayList<>();
			for(Point p: neighbors){
				if(distanceToGoal(p) == distanceToGoal(closest)){
					possibles.add(p);
				}
			}
			if(!possibles.isEmpty()){
				int randIndex = (int)(Math.random()*possibles.size());
				return possibles.get(randIndex);
			}
			return null;
		}

}
