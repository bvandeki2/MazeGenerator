package com.MazeGenerator.gui;

import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.MazeGenerator.gen.MazeGenerator;
import com.MazeGenerator.maze.Maze;

public class MazePropDialog extends JInternalFrame {
	private static final long serialVersionUID = -5406233738806869316L;
	
	private final JTextField width, height, seed;
	private final JComboBox<String> generatorNames;
	private final ArrayList<MazeGenerator> generators;
	private final JComponent[] inputs;
	private GUI gui;
	
	public MazePropDialog(GUI gui) {
		this.gui = gui;
		
		ArrayList<String> generatorNamestrings = MazeGenerator.getGeneratorNames();
		generators = MazeGenerator.getGenerators();
		
		generatorNames = new JComboBox<String>(
				generatorNamestrings.toArray(new String[generatorNamestrings.size()]));
		
		width = new JTextField("51", 5);
		height = new JTextField("51", 5);
		seed = new JTextField(10);
		
		inputs = new JComponent[] {
				new JLabel("Width (5 - 480): "), width,
				new JLabel("Height (5 - 480): "), height,
				new JLabel("Generator: "), generatorNames,
				new JLabel("Seed (blank for random): "), seed
		};
	}
	
	public void showDialog() {
		Maze maze = gui.getMaze();
		
		
		seed.setText("" + maze.getSeed());
		width.setText("" + maze.getGrid().getWidth());
		height.setText("" + maze.getGrid().getHeight());
		
		JOptionPane.showMessageDialog(null, inputs, "Maze Properties", JOptionPane.PLAIN_MESSAGE);
		
		int width = Integer.parseInt(this.width.getText()),
			height = Integer.parseInt(this.height.getText());
		
		if (width < 5)
			width = 5;
		if (width > 480)
			width = 480;
		if (height < 5)
			height = 5;
		if (height > 480)
			height = 480;
		
		maze.setGenerator(generators.get(generatorNames.getSelectedIndex()));
		
		if (seed.getText().equals(""))
			maze.randomSeed();
		else {
			try { 
				maze.setSeed(Long.parseLong(seed.getText())); 
		    } catch(NumberFormatException e) { 
		    	maze.setSeed(seed.getText().hashCode());
		    }
			
		}
		
		maze.setSize(width, height);
		
		// Refresh
		maze.generate();
		gui.getFrame().repaint();
	}
}
