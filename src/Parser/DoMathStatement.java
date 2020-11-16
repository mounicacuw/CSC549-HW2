package Parser;

public class DoMathStatement extends Expression 
{
    public String name;
	
	public Expression e1;
	
	public Expression e2;
	
	public String op;
	
	public static String identifier = "do-math";	
	public DoMathStatement(String name)
	{
		super("Do-Math Statement");
		this.name = name;
		String[] parts = name.split("\\s+");
		e1 = new ResolveExpression(parts[1]);
		op = parts[2];
		e2 = new ResolveExpression(parts[3]);
	}
	
	public String toString()
	{
		return super.toString() + "\n\t" + this.name;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public static int doMath(int a, int b, String op)
	{
		if(op.equals("+"))
		{
			return a + b;
		}
		else if(op.equals("-"))
		{
			return a - b;
		}
		else if(op.equals("*"))
		{
			return a * b;
		}
		
		else if(op.equals("%"))
		{
			return a%b;
	    }
		else
		{
			return-1;
		}
	}
}	
