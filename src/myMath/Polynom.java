package myMath;

import java.util.ArrayList;
import java.util.Iterator;
import myMath.Monom;

/**
 * This class represents a Polynom with add, multiply functionality, it also
 * should support the following: 1. Riemann's Integral:
 * https://en.wikipedia.org/wiki/Riemann_integral 2. Finding a numerical value
 * between two values (currently support root only f(x)=0). 3. Derivative
 * 
 * @author Boaz
 *
 */
public class Polynom implements Polynom_able {
	ArrayList<Monom> Poli = new ArrayList();

	/**
	 * Constructors for the polynomial
	 */
	public Polynom() {
		this.Poli = new ArrayList<Monom>();
	}

	public Polynom(String s) {
		String sLower = s.toLowerCase();//Converts the string to lower case
		Polynom empty = new Polynom();
		if (sLower.length() == 0) {
			throw new RuntimeException("Polynom is empty");
		}
		String splusemin = "";//Create a string that receive plus and minus values
		for (int i = 0; i < sLower.length(); i++) {
			if (sLower.charAt(i) == '-' || sLower.charAt(i) == '+') {
				splusemin += sLower.charAt(i);
			}
		}
		String[] str = sLower.split("\\+|\\-");
		if (sLower.charAt(0) != '-') {//If the first element in a string is different from minus, insert the plus value into the "splusemin" string
			splusemin = '+' + splusemin;
			for (int i = 0; i < splusemin.length(); i++) {
				if (splusemin.charAt(i) == '+') {
					Monom m = new Monom(str[i]);
					empty.add(m);
				} else {
					Monom m = new Monom(str[i]);
					Monom m1 = new Monom(-1, 0);
					m.multiply(m1);
					empty.add(m);
				}
			}
		} 
		else {//If the first element in a string is a minus,multiply the monon next to the mark in minus
			for (int i = 1; i < str.length; i++) {
				if (splusemin.charAt(i - 1) == '+') {
					Monom m = new Monom(str[i]);
					empty.add(m);
				} 
				else {
					Monom m = new Monom(str[i]);
					Monom m1 = new Monom(-1, 0);
					m.multiply(m1);
					empty.add(m);
				}
			}
		}

		this.Poli = ((Polynom) empty.copy()).Poli;
	}

	/** 
	 * This method create a deep copy of this Polynom.
	 * @return deep copy of this Polynom.
	 * */
	@Override
	public Polynom_able copy() {
		Iterator<Monom> It = this.iterator();
		Polynom_able Copy = new Polynom();
		while (It.hasNext()) {
			Monom m1 = new Monom(It.next());
			Copy.add(m1);
		}
		return Copy;
	}

	/**
	 *  This method computes this Polynom value in x. 
	 *  @param x.
	 *  @return the answer of the function f(x).
	 *  */
	@Override
	public double f(double x) {
		double ans = 0;
		Iterator<Monom> iter = this.iterator();
		while (iter.hasNext()) {
			Monom m = iter.next();
			ans += m.f(x);
		}
		return ans;
	}

	/**
	 *  This method add Polynom p1 to this Polynom.
	 *  @param p1 type Polynom_able.
	 *  */
	@Override
	public void add(Polynom_able p1) {     
		Iterator<Monom> iter = p1.iterator();
		while (iter.hasNext()) {
			Monom m = iter.next();
			this.add(m);
		}
	}

	/** 
	 * This method add Monom m1 to this Polynom.
	 * @param m1 type Monom.
	 *  */
	@Override
	public void add(Monom m1) {
		boolean found_power = false;
		Iterator<Monom> iter = this.iterator();
		while (iter.hasNext()) {
			Monom m = iter.next();
			if (m.get_power() == m1.get_power()) { // same power
				m.add(m1);
				found_power = true;
				if(m.isZero()) { // if(m.get_coefficient()==0)
					iter.remove();
				}

			}
		}

		if (!found_power) {
			this.Poli.add(m1);
		}
		Monom_Comperator SortPoli = new Monom_Comperator();
		this.Poli.sort(SortPoli);
	}
	/**
	 *  This method subtract Monom m1 from this Monom. 
	 *  @param p1 type Polynom_able.
	 *  */
	public void sub(Monom m1) {
		boolean found_power = false;
		Iterator<Monom> iter = this.iterator();
		while(iter.hasNext()) {
			Monom m = iter.next();
			if(m.get_power()==m1.get_power()) { // same power
				m.sub(m1);
				found_power = true;
				if(m.isZero()) { //if(m.get_coefficient()==0)
					iter.remove();
				}
			}
		}
		if(!found_power){
			this.Poli.add(m1);
		}

		Monom_Comperator SortPoli=new Monom_Comperator();
		this.Poli.sort(SortPoli);
	}

	/**
	 *  This method subtract Polynom p1 from this Polynom. 
	 *  @param p1 type Polynom_able.
	 *  */

	public void substract(Polynom_able p1) {
		Iterator<Monom> It=p1.iterator();
		while(It.hasNext())
		{
			this.sub(It.next());
		}
	}

	/**
	 *  This method multiply this Polynom by p1. 
	 *  @param p1 type Polynom_able.
	 *  */
	public void multiply(Polynom_able p1) {
		Iterator<Monom> iter = this.iterator();
		Polynom temp = new Polynom();
		while (iter.hasNext()) {
			Monom a = iter.next();

			Iterator<Monom> iter1 = p1.iterator();
			while (iter1.hasNext()) {
				Monom c = new Monom(a);
				Monom b = new Monom(iter1.next());
				c.multiply(b);
				temp.add(c);
			}
		}
		this.Poli = ((Polynom) temp.copy()).Poli;
	}

	/**
	 *  This method return the size of this Polynom.
	 *   */
	public int size() {
		return this.Poli.size();
	}

	/**
	 * This method checks whether this Polynom is equal to Polynom p1, if they
	 * are equal, we get true.
	 * @param p1 type Polynom_able.
	 * @return a boolean answer.
	 */
	@Override
	public boolean equals(Polynom_able p1) {
		boolean ans = false;
		if (this.size() == ((Polynom) p1).size()) {
			ans = true;
			Iterator<Monom> iter0 = this.iterator();
			Iterator<Monom> iter1 = p1.iterator();
			while (ans && iter0.hasNext()) {
				Monom m1 = iter0.next();
				Monom m2 = iter1.next();
				if (!m1.equals(m2))
					return false;
			}
		}
		return ans;
	}

	/** 
	 * This method return "true" if this Polynom is a zero.
	 * @return a boolean answer.
	 *  */
	@Override
	public boolean isZero() {
		{
			boolean flag = true;
			Iterator<Monom> iter0 = this.iterator();
			while (iter0.hasNext()) {
				Monom m = iter0.next();
				if (m.get_coefficient() != 0) {
					flag = false;
				}
			}

			return flag;
		}
	}

	/**
	 *  This method return the root of this Polynom. 
	 * @param x0 starting point
	 * @param x1 end point
	 * @param eps step (positive) value
	 * @return  the root of this Polynom.
	 *  */
	@Override
	public double root(double x0, double x1, double eps) {
		double y0 = this.f(x0);
		double y1 = this.f(x1);

		if (y0 * y1 > 0) {
			throw new RuntimeException("Error: f(x0) and f(x1) should be on oposite sides of the X asix");
		}

		double delta_x = Math.abs(x0 - x1);
		double delta_y = Math.abs(y0 - y1);
		if (delta_x > eps || delta_y > eps) {
			double x_mid = (x0 + x1) / 2;
			double y_mid = this.f(x_mid);
			double dir = y0 * y_mid;
			if (dir < 0) {
				return root(x0, x_mid, eps);
			} else {
				return root(x_mid, x1, eps);
			}

		}
		return x0;
	}

	/**
	 * This method compute a new Polynom which is the derivative of this Polynom.
	 * @return the new Polynom which is the derivative of this Polynom.
	 */
	@Override
	public Polynom_able derivative() {
		Polynom Poli_derivative=new Polynom();
		Iterator<Monom>iter=this.Poli.iterator();
		while(iter.hasNext()){
			Monom m=iter.next();
			Poli_derivative.add(m.derivative());
		}
		return Poli_derivative;
	}


	/**
	 * Compute Riemann's Integral over this Polynom starting from x0, till x1
	 * using eps size steps, return the answer-> the area.
	 * @param x0 starting point
	 * @param x1 end point
	 * @param eps size steps
	 * @return the solution to the Riemann's Integral.
	 */
	@Override
	public double area(double x0, double x1, double eps) {

		double width = (x1 - x0) / eps;
		double sum = 0.0;

		for (int i = 0; i < eps; i++) {
			double first_mid_p = x0 + (width / 2.0) + i * width;
			sum = sum + (first_mid_p * first_mid_p - first_mid_p + 3);
		}

		return sum * width;
	}

	/**
	 * this method produces a monom type pointer for this Polynom.
	 * @return an Iterator (of Monoms) over this Polynom
	 */
	@Override
	public Iterator<Monom> iterator() {
		return this.Poli.iterator();

	}

	/**
	 * this method prints the Polynom.
	 * @return the Polynom.
	 */
	public String toString() {
		String s = "";
		Iterator<Monom> iter = this.Poli.iterator();
		while (iter.hasNext()) {
			s += iter.next().toString() + "+";
		}
		return s;
	}

}
