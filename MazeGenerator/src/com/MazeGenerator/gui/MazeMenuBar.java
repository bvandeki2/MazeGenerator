package com.MazeGenerator.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.MazeGenerator.maze.MazePrinter;

public class MazeMenuBar extends JMenuBar implements ActionListener {
	private static final long serialVersionUID = 2817794218004706509L;
	
	private GUI gui;
	
	public MazeMenuBar(GUI gui) {
		super();
		
		this.gui = gui;
		
		JMenu fileMenu = new JMenu("File");
		this.add(fileMenu);
		
		JMenu propsMenu = new JMenu("Properties");
		this.add(propsMenu);
		
		JMenuItem newMaze = new JMenuItem("New Maze"),
				  mazeProp = new JMenuItem("Maze Properties"),
				  printMaze = new JMenuItem("Print Maze"),
				  exit = new JMenuItem("Exit");
		newMaze.setActionCommand("newMaze");
		mazeProp.setActionCommand("props");
		printMaze.setActionCommand("print");
		exit.setActionCommand("exit");
		
		fileMenu.add(newMaze);
		fileMenu.add(printMaze);
		fileMenu.addSeparator();
		fileMenu.add(exit);
		
		propsMenu.add(mazeProp);
		
		newMaze.addActionListener(this);
		mazeProp.addActionListener(this);
		printMaze.addActionListener(this);
		exit.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		switch (ae.getActionCommand()) {
		case "newMaze":
			gui.getMaze().randomSeed();
			gui.getMaze().generate();
			gui.getFrame().repaint();
			break;
		case "props":
			gui.getMazePropDialog().showDialog();
			break;
		case "print":
			PrinterJob pj = PrinterJob.getPrinterJob();
			MazePrinter mp = gui.getPrinter();
			mp.setMaze(gui.getMaze());
			pj.setPrintable(mp);
			if (pj.printDialog()) {
				try {
					pj.print();
				} catch (PrinterException e) {
					e.printStackTrace();
				}
			}
			break;
		case "exit":
			System.exit(0);
			break;
		}
	}
}
