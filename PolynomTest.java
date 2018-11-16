package Test;
import org.junit.Test;
import static org.junit.Assert.*;
import myMath.Monom;
import myMath.Polynom;



public class PolynomTest {

	@Test
	public void testPolynom() {
		Polynom poly=new Polynom();
		if(!poly.isZero());
	}

	@Test
	public void testPolynomString() {
		Polynom poly=new Polynom();
		Polynom poly1=new Polynom();
		poly.add(new Monom(2,3));
		poly.add(new Monom(4,2));
		poly1.add(new Monom(2,3));
		poly1.add(new Monom(4,2));
		if(!poly.equals(poly1));
	}
	
	@Test
	public void testF() {
		double M = Math.random()*10 ;
		int power = (int)Math.random()*10 ;
		Monom m1 = new Monom(M , power);
		int y = (int)Math.random()*10 ;
		Polynom p = new Polynom();
		p.add(m1);
		if(p.f(y) != (M * Math.pow(y,power)))
			fail("Eror");
	}

	@Test
	public void testAddPolynom_able() {
		Polynom poly1 = new Polynom("x^2+6x");
		Polynom poly2 = new Polynom();
		poly2 = (Polynom)poly1.copy();
		assertTrue(poly1.equals(poly2));
	}

	@Test
	public void testAddMonom(){
		Polynom poly1 = new Polynom("4x^3+4x");
		Polynom poly2 = new Polynom("3x^3+4x+3x^2");
		Monom z1 = new Monom(1, 3);
		poly1.add(z1);
		assertEquals(poly1 , poly2);
	}

	@Test
	public void testSub() {
		Polynom poly1 = new Polynom("2x^3+4x");
		Monom M1 = new Monom(4, 1);
		poly1.sub(M1);
		Polynom poly3 = new Polynom("2x^3");
		assertEquals(poly3 , poly1);
	}

	@Test
	public void testSubstract() {
		Polynom p1 = new Polynom("2x^3+4x");
		Polynom p2 = new Polynom("2x^3");
		Polynom p3 = new Polynom("4x");
		p1.substract(p2);
		assertEquals(p3 , p1);
	}

	@Test
	public void testMultiply() {
		Polynom p1=new Polynom();
		p1.add(new Monom(2, 1));
		p1.add(new Monom(3, 5));
		Polynom p2=new Polynom();
		p2.add(new Monom(3, 0));
		p2.add(new Monom(7, 9));
		p1.multiply(p2);
	}
	@Test
	public void testSize() {
		Polynom poly1 = new Polynom("x^2");
		int x = poly1.size();
		assertEquals(1 , x);
	}
	@Test
	public void testEqualsPolynom_able() {
		Polynom poly1 = new Polynom("x^3");
		Polynom poly2 = new Polynom("x^3");
		assertTrue(poly1.equals(poly2));
	}

	@Test
	public void testIsZero() {
		Polynom P = new Polynom() ;
		if(!P.isZero())
			fail("Erorr");
	}

	@Test
	public void testRoot() {
		Polynom p1 = new Polynom("x^3+4x-1");
		equals(p1.root(-2, 2, 0.000001));
	}

	@Test
	public void testDerivative() {
		Polynom p=new Polynom("4*X^2");
		Polynom der=new Polynom("2*X");
		if (!p.equals(der));
	
	}

	@Test
	public void testArea() {
		Polynom p1= new Polynom("4x^3+2x^2+11x");
		double actual= p1.area(-2, 0, 0.01);
		double expected= 19.41157058000002;
		double y=0.01;	
		assertEquals(expected, actual,y);
	}
	@Test
	public void testToString() {
		Polynom p=new Polynom("3*X^3+4*X^2+6*X+9*X-4*X^3+6*X^7+0.0");
		String s="3*X^3+4*X^2+6*X+9*X-4*X^3+6*X^7+0.0";
		assertEquals(s,p.toString());
	}
}
