package com.MazeGenerator.gen;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import com.MazeGenerator.maze.Cell;
import com.MazeGenerator.maze.Grid;

public class MazeGenRecursiveBacktrackerPerfect extends MazeGenerator {
	private static final MazeGenerator instance = new MazeGenRecursiveBacktrackerPerfect();
	
	public static final String name = "Recursive Backtracker (Perfect)";
	
	public void generate(Grid g, Random r) {
		g.fill(Cell.SOLID);
		
		ArrayList<Point> path = new ArrayList<Point>();
		
		int[] dx = {1, 0, -1, 0},
			  dy = {0, -1, 0, 1};
		// Shuffling for directions
		ArrayList<Integer> readOrder = new ArrayList<Integer>();
		readOrder.add(0); readOrder.add(1); readOrder.add(2); readOrder.add(3);
		
		int x = 1, y = 1;
		path.add(new Point(x, y));
		g.setCell(x, y, Cell.EMPTY);
		
		while (!path.isEmpty()) {
			boolean moved = false;
			
			Collections.shuffle(readOrder, r);
			for(int dirIndex : readOrder) {
				
				if (g.isInBounds(x + dx[dirIndex], y + dy[dirIndex])) {
					if (g.getCell(x + dx[dirIndex], y + dy[dirIndex]).equals(Cell.SOLID)) {
						// We have found a wall;  check if we break into any corridors
						if (g.isInBounds(x + 2 * dx[dirIndex], y + 2 * dy[dirIndex])) {
							if (g.getCell(x + 2 * dx[dirIndex], y + 2 * dy[dirIndex]).equals(Cell.SOLID)) {
								// Moving two makes it prettier
								x += dx[dirIndex];
								y += dy[dirIndex];
								g.setCell(x, y, Cell.EMPTY);
								x += dx[dirIndex];
								y += dy[dirIndex];
								g.setCell(x, y, Cell.EMPTY);
								
								path.add(new Point(x, y));
								moved = true;
								
								break;
							}
						}
					}
				}
			}
			
			if (!moved) {
				// Pop the stack
				Point loc = path.remove(path.size() - 1);
				x = loc.x;
				y = loc.y;
			}
		}
	}
	
	public static MazeGenerator getInstance() {
		return (instance);
	}
}
