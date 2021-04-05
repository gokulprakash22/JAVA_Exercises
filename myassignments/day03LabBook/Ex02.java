package day03LabBook;

public class Ex02 {
	public static void main(String[] args) {
		Circle circle = new Circle();
		System.out.println("Circle area = " + circle.calculateArea(2));
		Square square = new Square();
		System.out.println("Square area = " + square.calculateArea(7));
		Sphere sphere = new Sphere();
		System.out.println("Sphere area = " + sphere.calculateArea(4));
		System.out.println("Sphere volume = " + sphere.calculateVolume(4));
		Cuboid cuboid = new Cuboid();
		System.out.println("Cuboid area = " + cuboid.calculateArea(5));
		System.out.println("Cuboid volume = " + cuboid.calculateVolume(5));
	}
}

interface Constants {
	double pi = 3.14;
}

interface CalcArea extends Constants {
	double calculateArea(double x);
}

interface CalcVolume extends Constants {
	double calculateVolume(double x);
}

class Circle implements CalcArea {
	@Override
	public double calculateArea(double x) {
		return pi * x * x;
	}
}

class Square implements CalcArea {
	@Override
	public double calculateArea(double x) {
		return x * x;
	}
}

class Sphere implements CalcArea, CalcVolume {
	@Override
	public double calculateArea(double x) {
		return pi * x * x;
	}
	@Override
	public double calculateVolume(double x) {
		return (4 / 3) * pi * x * x * x;
	}
}

class Cuboid implements CalcArea, CalcVolume {
	@Override
	public double calculateArea(double x) {
		return x * x;
	}
	@Override
	public double calculateVolume(double x) {
		return x * x * x;
	}
}