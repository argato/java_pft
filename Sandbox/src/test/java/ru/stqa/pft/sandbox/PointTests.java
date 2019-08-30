package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

  @Test
  public void testDistance(){
    Point p1 = new Point(1.0, 0.0);
    Point p2 = new Point(2.0, 0.0);

    Assert.assertEquals(p1.distance(p2), 1.0);
  }

  @Test
  public void testDistance2(){
    Point p1 = new Point(-1.0, 1.0);
    Point p2 = new Point(5.0, -1.0);

    Assert.assertEquals(p1.distance(p2), Math.sqrt(40));
  }

  @Test
  public void testDistance3(){
    Point p1 = new Point(2.0, 15.0);
    Point p2 = new Point(2.0, 1.0);

    Assert.assertEquals(p1.distance(p2), 14.0);
  }
}
