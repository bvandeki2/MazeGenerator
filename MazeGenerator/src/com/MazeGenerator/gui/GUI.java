package com.MazeGenerator.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.MazeGenerator.gen.MazeGenerator;
import com.MazeGenerator.maze.Maze;
import com.MazeGenerator.maze.MazePrinter;

public class GUI extends JPanel {
	private static final long serialVersionUID = -7769569171003069731L;
	
	private JFrame frame;
	public MazePanel mp;
	private MazeMenuBar menu;
	private Maze maze;
	private MazePropDialog mpd;
	private MazePrinter printer;
		
	public GUI() {
		frame = new JFrame("MazeGenerator");
		
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		MazeGenerator.loadGenerators();
		
		maze = new Maze(51, 51);
		maze.generate();
		
		mp = new MazePanel(maze);
		frame.add(mp);
		
		mpd = new MazePropDialog(this);
		
		menu = new MazeMenuBar(this);
		frame.setJMenuBar(menu);
		
		printer = new MazePrinter();
		
		frame.pack();
		frame.setVisible(true);
	}
	
	public void setMaze(Maze maze) {
		this.maze = maze;
		mp.setMaze(this.maze);
	}
	
	public JFrame getFrame() {
		return (frame);
	}
	
	public MazePropDialog getMazePropDialog() {
		return (mpd);
	}
	
	public Maze getMaze() {
		return (maze);
	}
	

	public MazePrinter getPrinter() {
		return (printer);
	}

}
