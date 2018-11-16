package Test;

import static org.junit.Assert.*;
import org.junit.Test;
import myMath.Monom;
public class MonomTest {

	@Test
	public void testToString(){
		Monom t=new Monom(9.5,9);
		String s="9.5*X^9";
		assertEquals(s,t.toString());
	}
	@Test
	public void testF() {

		double coefficient = Math.random()*10 ;
		int pow = (int)Math.random()*10 ;
		Monom m = new Monom(coefficient , pow);
		int x = (int)Math.random()*10 ;
		if((m.f(x)) != (coefficient * Math.pow(x, pow)) )
			fail("Not yet implemented");
	}

	@Test
	public void testEqualsMonom() {
		Monom M1 = new Monom(5, 0);			
		Monom M2 = new Monom(0, 0);
		assertFalse(M1.equals(M2));
		assertTrue(M1.equals(M1));
	}

	@Test
	public void testIsZero() {

		Monom m =new Monom(0,0);
		if (!m.isZero());
	}

	@Test
	public void testDerivative() {
		Monom m=new Monom (2,2);
		Monom x=m.derivative();
		Monom p= new Monom (4,1);
		if (!p.equals(x));
	}
	@Test
	public void testAdd() {
		double coefficient = Math.random()*10;
		double coefficient_1 = Math.random()*10 ;
		int power = (int)Math.random()*10 ;
		Monom m1 = new Monom(coefficient , power);
		Monom m2 = new Monom(coefficient_1 , power);
		m1.add(m2);
		if(m1.get_coefficient() != (coefficient + coefficient_1))
			fail("Eroor");
	}
	@Test
	public void testSub() {
		Monom a1 = new Monom(3, 3);
		Monom a2 = new Monom(2, 3);
		Monom a3 = new Monom(-5, 3);
		a1.sub(a2);
		assertTrue(a3.equals(a1));
	}
	@Test
	public void testMultiply() {
		double c1=2;
		double c2=4;
		int pow1=2;
		int pow2=1;
		Monom m=new Monom(c1,pow1);
		Monom m1=new Monom(c2,pow2);
		m1.multiply(m);
		if(m1.get_coefficient() != (c1 *c2) || m1.get_power() != (pow1 +pow2))
			fail("Erro");
	}

	@Test
	public void testMonomString() {
		Monom a1 = new Monom(3, 3);
		Monom a2 = new Monom(2, 1);
		assertEquals(a1.toString(),"2*X");
		assertNotEquals(a2.toString()," 3*X^3");

	}
}
