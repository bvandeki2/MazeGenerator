package com.MazeGenerator.maze;

import java.awt.Color;

public enum Cell {
	EMPTY(Color.WHITE), SOLID(Color.BLACK), DOOR(Color.YELLOW), START(Color.GREEN), END(Color.RED);
	
	private Color c;
	
	private Cell(Color c) {
		this.c = c;
	}
	
	public Color getColor() {
		return (c);
	}
}
