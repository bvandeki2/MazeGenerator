package com.MazeGenerator.gen;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import com.MazeGenerator.maze.Cell;
import com.MazeGenerator.maze.Grid;

public class MazeGenPrim extends MazeGenerator {
	private static final MazeGenerator instance = new MazeGenPrim();
	
	public static final String name = "Prim's Algorithm";
	
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
			
			int index = (int) (Math.random() * path.size());
			Point p = path.get(index);
			x = p.x;
			y = p.y;
			
			Collections.shuffle(readOrder, r);
			for(int dirIndex : readOrder) {
				
				if (g.isInBounds(x + dx[dirIndex], y + dy[dirIndex])) {
					if (g.getCell(x + dx[dirIndex], y + dy[dirIndex]).equals(Cell.SOLID)) {
						// We have found a wall;  check if we break into any corridors
						int countEmpty = 0;
						for(int i = 0; i < 4; i ++) {
							if (g.isInBounds(x + dx[dirIndex] + dx[i], y + dy[dirIndex] + dy[i]))
								countEmpty += g.getCell(x + dx[dirIndex] + dx[i], y + dy[dirIndex] + dy[i]).equals(Cell.EMPTY) ? 1 : 0;
							else
								countEmpty ++;
						}
						
						// Allow the corridor we are currently in
						if (countEmpty <= 1) {
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
			
			if (!moved) {
				// Pop the stack
				Point loc = path.remove(index);
				x = loc.x;
				y = loc.y;
			}
		}
	}
	
	public static MazeGenerator getInstance() {
		return (instance);
	}
}
