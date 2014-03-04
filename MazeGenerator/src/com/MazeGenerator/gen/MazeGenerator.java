package com.MazeGenerator.gen;

import java.util.ArrayList;
import java.util.Random;

import com.MazeGenerator.maze.Cell;
import com.MazeGenerator.maze.Grid;
import com.MazeGenerator.util.ClassFinder;

public class MazeGenerator {
	
	private static ArrayList<MazeGenerator> generators = new ArrayList<>();
	private static ArrayList<String> generatorNames = new ArrayList<>();
	
	public final String name = "invalid";
	
	public static void loadGenerators() {
		try {
			Class<? extends Object>[] gens = ClassFinder.getClasses("com.MazeGenerator.gen");
			for(Class<? extends Object> c : gens)
				register(c);
		} catch (Exception e) {
			System.err.println("Could not find/load generator classes");
			e.printStackTrace();
			System.exit(-1);
		}
	}
	
	private static void register(Class<? extends Object> c) throws Exception {
		MazeGenerator mg = (MazeGenerator) c.newInstance();
		if (!generators.contains(mg) && !c.equals(MazeGenerator.class)) {
			generators.add(mg);
			generatorNames.add((String) c.getField("name").get(new String()));
		}
	}
	
	public static ArrayList<MazeGenerator> getGenerators() {
		return (generators);
	}
	
	public static ArrayList<String> getGeneratorNames() {
		return (generatorNames);
	}
	
	public void generate(Grid grid, Random random) {
		grid.fill(Cell.EMPTY);
	}
}
