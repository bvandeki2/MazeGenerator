package com.MazeGenerator.maze;

import java.util.Random;

import com.MazeGenerator.gen.MazeGenRecursiveBacktrackerPerfect;
import com.MazeGenerator.gen.MazeGenerator;

public class Maze {
	private transient MazeGenerator gen = MazeGenRecursiveBacktrackerPerfect.getInstance();
	private long seed;
	private Grid grid;
	
	public Maze(int width, int height) {
		this(width, height, (long) (Math.random() * Long.MAX_VALUE));
	}
	
	public Maze(int width, int height, long seed) {
		this.seed = seed;
		grid = new Grid(width, height);
	}
	
	public void generate() {
		gen.generate(grid, new Random(seed));
	}
	
	public void setGenerator(MazeGenerator gen) {
		this.gen = gen;
	}
	
	public void setSize(int width, int height) {
		this.grid = new Grid(width, height);
	}
	
	public void setSeed(long seed) {
		this.seed = seed;
	}
	
	public void randomSeed() {
		this.seed = (long) (Math.random() * Long.MAX_VALUE);
	}
	
	public Grid getGrid() {
		return (grid);
	}
	
	public long getSeed() {
		return (this.seed);
	}
}
