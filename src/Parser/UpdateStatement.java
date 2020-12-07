package Parser;

public class UpdateStatement extends Statement
{
	private String name;
	private String type;
	private Expression value;
	
	public UpdateStatement(String name, Expression value)
	{
		super("Update Statement");
		this.name = name;
		this.value = value;
	}

	public String toString()
	{
		return super.toString() + "\n\t" + 
	this.name + " = " + this.value;
	}
	
	public String getName() 
	{
		return name;
	}

	public Expression getValueExpression() 
	{
		return value;
	}
	
	
}

