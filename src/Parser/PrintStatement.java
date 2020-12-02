package Parser;

public class PrintStatement extends Statement
{
	private Expression expression_to_print;
	
	public PrintStatement(Expression expression_to_print)
	{
		super("Print Statement");
		this.expression_to_print = expression_to_print;
	}

	public String toString()
	{
		return super.toString() + "\n\t" + 
				this.expression_to_print.toString();
	}

	public Expression getExpression_to_print() {
		return expression_to_print;
	}
}
