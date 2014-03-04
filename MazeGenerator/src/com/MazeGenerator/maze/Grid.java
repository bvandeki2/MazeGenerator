package com.MazeGenerator.maze;

import java.util.ArrayList;


public class Grid {
	private int width, height;

	// True if wall is in the space
	private ArrayList<ArrayList<Cell>> cells;
	
	public Grid(int width, int height) {
		this.width = width;
		this.height = height;
		this.cells = new ArrayList<ArrayList<Cell>>();
		
		for(int x = 0; x < this.width; x ++) {
			this.cells.add(new ArrayList<Cell>());
			for(int y = 0; y < this.height; y ++) {
				this.cells.get(x).add(Cell.EMPTY);
			}
		}
	}
	
	public boolean isInBounds(int x, int y) {
		return (x >= 0 && x < width && y >= 0 && y < height);
	}
	
	public void fill(Cell value) {
		for(int x = 0; x < this.width; x ++)
		for(int y = 0; y < this.height; y ++)
			setCell(x, y, value);
	}
	
	public Cell getCell(int x, int y) {
		return (cells.get(x).get(y));
	}
	
	public void setCell(int x, int y, Cell value) {
		cells.get(x).set(y, value);
	}
	
	public int getWidth() {
		return (width);
	}

	public int getHeight() {
		return (height);
	}
	
}
