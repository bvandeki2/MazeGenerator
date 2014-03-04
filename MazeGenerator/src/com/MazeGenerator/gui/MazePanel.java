package com.MazeGenerator.gui;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import com.MazeGenerator.maze.Grid;
import com.MazeGenerator.maze.Maze;

public class MazePanel extends JPanel {
	private static final long serialVersionUID = -565962316062515981L;
	
	private Maze maze;
	
	public MazePanel() {
		this(null);
	}
	
	public MazePanel(Maze maze) {
		super();
		this.maze = maze;
		
		setPreferredSize(new Dimension(480, 480));
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Grid grid = maze.getGrid();
		
		float xScale = (float) getWidth() / grid.getWidth(), yScale = (float) getHeight() / grid.getHeight();
		
		for(int x = 0; x < grid.getWidth(); x ++) {
			for(int y = 0; y < grid.getHeight(); y ++) {
				g.setColor(grid.getCell(x, y).getColor());
				
				g.fillRect((int) (x * xScale), (int) (y * yScale), (int) xScale + 1, (int) yScale + 1);
			}
		}
	}
	
	public void setMaze(Maze maze) {
		this.maze = maze;
		repaint();
	}
}
