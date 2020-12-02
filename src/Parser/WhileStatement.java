
package Parser;

import java.util.ArrayList;

public class WhileStatement extends Statement
{
	private Expression test_expression;
	private ArrayList<Statement> statements_to_execute = new ArrayList<Statement>();

	
	public WhileStatement(Expression test_expression, ArrayList<Statement> statements_to_execute)
	{
		super("While Statement");
		this.test_expression = test_expression;
		this.statements_to_execute = statements_to_execute;
	}

	public String toString()
	{
		return super.toString() + "\n\t" + 
				this.test_expression.toString() + " " + this.statements_to_execute.toString();
	}

	public Expression getTest_expression() {
		return test_expression;
	}

	public ArrayList<Statement> getStatement_to_execute() {
		return statements_to_execute;
	}
}