package Parser;

public class QuestionStatement extends Statement
{
	private TestExpression testExpression;
	private Statement trueStatement;
	private Statement falseStatement;
	
	public QuestionStatement(TestExpression testExpression, Statement trueStatement, Statement falseStatement)
	{
		super("Question Statement");
		this.testExpression = testExpression;
		this.trueStatement = trueStatement;
		this.falseStatement = falseStatement;
	}

	public String toString()
	{
		return super.toString() + "\n\t" + this.testExpression.toString() +
				"\n\t\t" + this.trueStatement.toString() + 
				"\n\t\t" + this.falseStatement.toString();				
	}

	public TestExpression getTestExpression() {
		return testExpression;
	}

	public Statement getTrueStatement() {
		return trueStatement;
	}

	public Statement getFalseStatement() {
		return falseStatement;
	}

	
}
