package pkg;

import java.awt.Color;
import java.awt.Font;
import java.util.Random;

enum GemType {
    GREEN  ("gem_green.png"), 
    BLUE   ("gem_blue.png"), 
    ORANGE ("gem_orange.png"); 
	//define the different types of Gems, comma delimited
	
	public String fileName;
	
	private GemType(String fileName) {
		this.fileName = fileName;
	}
}

public class Gem 
{	
	GemType type;
	public int pointValue;
	int[] points = {0, 5, 10, 15, 20, 25, 30, 35, 40, 45, 50};
	
	public Gem() {
		type = GemType.values()[new Random().nextInt(GemType.values().length)];
		pointValue = points[new Random().nextInt(points.length - 1)];
		
	}
	
	public Gem(GemType type, int points) {
		this.type = type;
		pointValue = points;
	}
	
	public String toString() {
		return "" + getType() + " " + getPoints();
	}
	
	public GemType getType() {
		return type;
	}
	
	public int getPoints() {
		return pointValue;
	}
	
	public void draw(double x, double y) {
		String filename = getType().fileName;
		StdDraw.setFont(new Font("SansSerif", Font.BOLD, 14));
		StdDraw.setPenColor(Color.WHITE);
		StdDraw.picture(x, y, filename);
		StdDraw.text(x, y, getPoints() + "");
	}
	
	/** Tester main method */
	public static void main(String [] args)
	{
		final int maxGems = 16;
		
		// Create a gem of each type
		Gem green  = new Gem(GemType.GREEN, 10);
		Gem blue   = new Gem(GemType.BLUE, 20);
		Gem orange = new Gem(GemType.ORANGE, 30);
		System.out.println(green  + ", " + green.getType()  + ", " + green.getPoints());		
		System.out.println(blue   + ", " + blue.getType()   + ", " + blue.getPoints());
		System.out.println(orange + ", " + orange.getType() + ", " + orange.getPoints());
		green.draw(0.3, 0.7);
		blue.draw(0.5, 0.7);
		orange.draw(0.7, 0.7);
		
		// A row of random gems
		for (int i = 0; i < maxGems; i++)
		{
			Gem g = new Gem();
			g.draw(1.0 / maxGems * (i + 0.5), 0.5);
		}
	}
}
