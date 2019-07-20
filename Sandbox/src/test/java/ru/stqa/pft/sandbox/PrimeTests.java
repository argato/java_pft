package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

public class PrimeTests {

  @Test
  public void testPrime(){
    Assert.assertTrue(Primes.isPrime(Integer.MAX_VALUE));
  }

  @Test
  public void testNonPrime(){
    Assert.assertFalse(Primes.isPrime(Integer.MAX_VALUE-2));
  }

  @Test
  public void testPrimeFast(){
    Assert.assertTrue(Primes.isPrimeFast(Integer.MAX_VALUE));
  }

  @Test
  public void testPrimeFast2(){
    Assert.assertTrue(Primes.isPrimeFast2(Integer.MAX_VALUE));
  }

  @Test(enabled = false)
  public void testPrimeLong(){
    long n = Integer.MAX_VALUE;
    Assert.assertTrue(Primes.isPrime(n));
  }

  @Test (enabled = false)
  public void testNonPrimeLong(){
    long n = Integer.MAX_VALUE - 2;
    Assert.assertFalse(Primes.isPrime(n));
  }

  @Test(enabled = false)
  public void isPrimeWhile(){
    Assert.assertTrue(Primes.isPrimeWhile(Integer.MAX_VALUE));
  }

  @Test(enabled = false)
  public void isNonPrimeWhile(){
    Assert.assertFalse(Primes.isPrimeWhile(Integer.MAX_VALUE-2));
  }
}
