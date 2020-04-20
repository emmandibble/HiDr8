package com.example.restservice;

public class Goal {

	private final long height;
	private final long weight;
	private final long goal;

	public Goal(long height, long weight, long goal) {
		this.height = height;
		this.weight = weight;
		this.goal = goal;
	}

	public long getHeight() {
		return height;
	}

	public long getWeight() {
		return weight;
	}

	public long getGoal() {return goal;}
}
