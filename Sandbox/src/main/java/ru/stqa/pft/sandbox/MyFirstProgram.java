package ru.stqa.pft.sandbox;

public class MyFirstProgram {
 public static void main(String[] args) {

   Point p1 = new Point(0.0, 3.5);
   Point p2 = new Point(0.0, 0.0);

   System.out.println("Расстояние = " + distance(p1, p2));
   System.out.println("Расстояние = " + p1.distance(p2));
   System.out.println("Расстояние = " + p2.distance(p1));
 }

   public static double distance(Point p1, Point p2){
     return Math.sqrt(Math.pow(p2.x-p1.x,2) + Math.pow(p2.y-p1.y, 2));
   }


}