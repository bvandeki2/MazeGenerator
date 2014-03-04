package com.MazeGenerator.gen;

import java.util.Random;

import com.MazeGenerator.maze.Cell;
import com.MazeGenerator.maze.Grid;

public class MazeGenBinaryTreePerfect extends MazeGenerator {
	private static final MazeGenerator instance = new MazeGenBinaryTreePerfect();
	
	public static final String name = "Binary Tree (Perfect)";
	
	public void generate(Grid g, Random r) {
		g.fill(Cell.SOLID);
		
		for(int x = 1; x < g.getWidth() - 1; x += 2) {
			for(int y = 1; y < g.getHeight() - 1; y += 2) {
				g.setCell(x, y, Cell.EMPTY);
				
				if (x == 1 && y == 1)
					continue;
				
				if (x == 1) {
					g.setCell(x, y - 1, Cell.EMPTY);
					continue;
				}
				if (y == 1) {
					g.setCell(x - 1, y, Cell.EMPTY);
					continue;
				}
				
				if (r.nextBoolean())
					g.setCell(x - 1, y, Cell.EMPTY);
				else
					g.setCell(x, y - 1, Cell.EMPTY);
			}
		}
	}
	
	public static MazeGenerator getInstance() {
		return (instance);
	}
}
