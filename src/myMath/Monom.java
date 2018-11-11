
package myMath;
/**
 * This class represents a simple "Monom" of shape a*x^b, where a is a real number and a is an integer (summed a none negative), 
 * see: https://en.wikipedia.org/wiki/Monomial 
 * The class implements function and support simple operations as: construction, value at x, derivative, add and multiply. 
 * @author Boaz
 *
 */
public class Monom implements function{
	/**
	 * Constructors for the monom
	 */
	public Monom(double a, int b){
		this.set_coefficient(a);
		this.set_power(b);
	}
	public Monom(Monom ot) {
		this(ot.get_coefficient(), ot.get_power());
	}
	/**
	 * Create getters
	 */
	public double get_coefficient(){
		return this._coefficient;
	}
	public int get_power(){
		return  this._power;

	}
	/**
	 * this method prints the Monom.
	 * @return the Monom.
	 */
	public String toString(){
		return " "+ this.get_coefficient() + "x^" + this.get_power();

	}


	/**
	 *   This method computes this Monom value in x.
	 *  @param x.
	 *  @return the answer of the function f(x).
	 *   */
	public double f(double x) {
		double a=get_coefficient();
		int b=get_power();
		double ans=a*Math.pow(x, b);
		return ans;
	}

	/**
	 * This method test if this Monom is none zero and has a none negative power
	 *@param a type Monom.
	 *@return a boolean answer, if there equals, the answer will be true.
	 * */
	public boolean equals(Monom a)
	{
		return a.get_coefficient()==this._coefficient && a.get_power()==this._power;
	}
	
	/** 
	 * This method return "true" if this Monom is a zero.
	 * @return a boolean answer.
	 *  */
	public boolean isZero(){
		boolean ans=false;
		if(this.get_power()<0)
			ans=true;
		if(this.get_coefficient()==0){
			ans=true;
		}
		return ans;
	}
	/**
	 * This method compute a new Monom which is the derivative of this Monom.
	 * @return the new Monom which is the derivative of this Monom
	 */	
	public Monom derivative(){
		double a=this.get_coefficient()*this.get_power();
		int  b=this.get_power()-1;
		return new Monom(a,b);
	}
	/** 
	 * This method add Monom m1 to this Monom.
	 * @param m1 type Monom.
	 *  */
	public void add(Monom m){
		if(m.get_power()!=this._power){
			throw new RuntimeException("can not,the num is't diffrent");
		}
		else
		{
			this.set_coefficient(m.get_coefficient()+this.get_coefficient());
		}
	}
	/**
	 *  This method subtract Monom m from this Monom. 
	 *  @param m type Monom.
	 *  */
	public void sub(Monom m){
		if(m.get_power()!=this._power){
			throw new RuntimeException("can not,the num is't diffrent");

		}
		else
		{
			this._coefficient=(this.get_coefficient()-m.get_coefficient());
		}
	}

	/**
	 *  This method multiply this Monom by m. 
	 *  @param m type Monom.
	 *  */
	public void multiply(Monom m){
		this.set_coefficient(this.get_coefficient()*m.get_coefficient());
		this.set_power(this.get_power() + m.get_power());
	}
	
	/**
	 * Constructor for the monom
	 */
	public Monom(String s)
	{
		if(s.length()==0){
			throw new RuntimeException("Monom is empty");
		}
		double a=0;
		int b=0;
		String sLower=s.toLowerCase();
		int ind_x=s.indexOf("x");
		int ind_po=s.indexOf("^");
		int ind_pi=s.indexOf("*");
		if(ind_x==-1)//without x
		{	
			a=Double.parseDouble(sLower);
		}
		else//with x
		{
			if(ind_po==-1)//without power
			{
				if(!sLower.substring(ind_x+1, sLower.length()).equals(""))
				{
					throw new RuntimeException("eror");
				}
				else
				{
					if(ind_pi==-1)//without multiplication sign
					{
						b=1;
						if(sLower.substring(0, ind_x).equals("")||sLower.substring(0, ind_x).equals("-"))
						{
							if(sLower.substring(0, ind_x).equals("-"))
								a=-1;	
							else
								a=1;
						}
						else
						{
							a=Double.parseDouble(sLower.substring(0, ind_x));
						}
						
					}//with multiplication sign
					else
					{
						b=1;
						a=Double.parseDouble(sLower.substring(0, ind_pi));
					}
				}
			}
			else//with power
			{
				if(ind_pi==-1)//without multiplication sign
				{
					if(sLower.substring(0, ind_x).equals("")||sLower.substring(0, ind_x).equals("-"))//if there isn't a sign before the coefficient or there is a minus sign before the coefficient 
					{
						if(sLower.substring(0, ind_x).equals("-"))//if there is a minus sign before the coefficient
						{
							a=-1;	
							b=Integer.parseInt(sLower.substring(ind_po+1, sLower.length()));
						}
						else// if there isn't a sign before the coefficient
						{
							a=1;
							b=Integer.parseInt(sLower.substring(ind_po+1, sLower.length()));
						}
					}
					else//if there is a number before the coefficient
					{
						b=Integer.parseInt(sLower.substring(ind_po+1, sLower.length()));
						a=Double.parseDouble(sLower.substring(0, ind_x));
					}
				}
				else //with multiplication sign
				{
					b=Integer.parseInt(sLower.substring(ind_po+1, sLower.length()));
					a=Double.parseDouble(sLower.substring(0, ind_pi));
				}
			}
		}
		this._power=b;
		this._coefficient=a;
	}


	//****************** Private Methods and Data *****************

	private void set_coefficient(double a){
		this._coefficient = a;
	}
	private void set_power(int p) {
		this._power = p;
	}

	private double _coefficient; 
	private int _power; 

}

