package com.MazeGenerator.maze;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;

public class MazePrinter implements Printable {
	
	private Maze maze;
	
	public MazePrinter() {
		maze = null;
	}
	
	@Override
	public int print(Graphics g, PageFormat pf, int page)
			throws PrinterException {
		if (page != 0 || maze == null)
			return (NO_SUCH_PAGE);
		
		Graphics2D g2d = (Graphics2D) g;

		g2d.translate(pf.getImageableX(), pf.getImageableY());
		
		Grid grid = maze.getGrid();
		double scale = (int) Math.min(pf.getImageableWidth() / grid.getWidth(), 
				pf.getImageableHeight() / grid.getHeight()) / 2;
		
		int[] dx = {1, 0, -1, 0}, dy = {0, -1, 0, 1};
		
		for(int x = 0; x < grid.getWidth(); x ++) {
			for(int y = 0; y < grid.getHeight(); y ++) {
				Cell c = grid.getCell(x, y);
				g2d.setColor(c.getColor());
				
				if (c != Cell.SOLID && c != Cell.EMPTY)
					g2d.fillRect((int) (x * scale), (int) (y * scale),  (int) scale + 1, (int) scale + 1);
				if (c == Cell.SOLID) {
					for(int direction = 0; direction < 4; direction ++) {
						if (grid.isInBounds(x + dx[direction], y + dy[direction])) {
							if (grid.getCell(x + dx[direction], y + dy[direction]) == Cell.SOLID) {
								g2d.drawLine((int) ((x + 0.5) * scale), (int) ((y + 0.5) * scale), 
										(int) ((x + 0.5 + 0.5 * dx[direction]) * scale), (int) ((y + 0.5 + 0.5 * dy[direction])));
							}
						}
					}
				}
			}
		}
		
		return (PAGE_EXISTS);
	}
	
	public void setMaze(Maze maze) {
		this.maze = maze;
	}
}
