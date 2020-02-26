package chapter5;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SimpleEquation extends Chromosome<SimpleEquation> {
	private int x, y;

	public SimpleEquation(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public static SimpleEquation randomInstance() {
		Random random = new Random();
		return new SimpleEquation(random.nextInt(100), random.nextInt(100));
	}

	// 6x - x^2 + 4y - y^2
	@Override
	public double fitness() {
		return 6 * x - x * x + 4 * y - y * y;
	}

	@Override
	public List<SimpleEquation> crossover(SimpleEquation other) {
		SimpleEquation child1 = new SimpleEquation(x, other.y);
		SimpleEquation child2 = new SimpleEquation(other.x, y);
		return List.of(child1, child2);
	}

	@Override
	public void mutate() {
		Random random = new Random();
		if (random.nextDouble() > 0.5) { // mutate x
			if (random.nextDouble() > 0.5) {
				x += 1;
			} else {
				x -= 1;
			}
		} else { // otherwise mutate y
			if (random.nextDouble() > 0.5) {
				y += 1;
			} else {
				y -= 1;
			}
		}

	}

	@Override
	public SimpleEquation copy() {
		return new SimpleEquation(x, y);
	}

	@Override
	public String toString() {
		return "X: " + x + " Y: " + y + " Fitness: " + fitness();
	}

	public static void main(String[] args) {
		ArrayList<SimpleEquation> initialPopulation = new ArrayList<>();
		for (int i = 0; i < 20; i++) {
			initialPopulation.add(SimpleEquation.randomInstance());
		}
		GeneticAlgorithm<SimpleEquation> ga = new GeneticAlgorithm<>(
				initialPopulation,
				0.1, 0.7, GeneticAlgorithm.SelectionType.TOURNAMENT);
		SimpleEquation result = ga.run(100, 13.0);
		System.out.println(result);
	}

}
