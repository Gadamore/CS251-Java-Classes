package lab7;

import java.util.ArrayList; 
import java.util.List;
import java.util.Scanner;

public class Lab7 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PointList lst = new PointList();
		
		while(true) {
			System.out.print("Enter x, y values (type 0 0 to exit): ");
			int x = in.nextInt();
			int y = in.nextInt();
			
			if (x == 0 && y == 0) {
				break;
			}
			in.nextLine();
			
			if(lst.insert(x, y)) {
				System.out.println(lst); 
			}
			 
		}
		in.close();
	}
}

class PointList {
	private List<Point> points = new ArrayList<>();
	
	boolean insert(int x, int y) {
		Point point = new Point(x,y);
		boolean ret = false;
		int i = 0;
		while (i < points.size() && points.get(i).distanceToOrigin() < point.distanceToOrigin()){
			i++;
		}	
		if (i == points.size() || !points.get(i).equals(point)){
			points.add(i, point);
			ret = true;
		}
		return ret;
	}
	
	@Override
	public String toString() {
		String ret = "";
		for (Point p : points){
			ret = ret + String.format("%s, distance to origin %2.2f\n", p, p.distanceToOrigin());
		}
		return ret;
	}
}

class Point {
	private int x, y;
	private static Point zero = new Point(0, 0);
	
	Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	double distance(Point that) {
		return Math.sqrt(Math.pow(this.x - that.x, 2) + Math.pow(this.y - that.y, 2));
	}
	
	double distanceToOrigin() {
		return distance(zero);
	}
	
	boolean equals(Point that) {
		return this.x == that.x && this.y == that.y;
	}
	
	public String toString() { return String.format("(%d, %d)", x, y); }
}
