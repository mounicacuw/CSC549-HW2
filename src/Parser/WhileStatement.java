package Parser;

public class WhileStatement extends Statement{
	private TestExpression testExpression;
	private Statement updateStatement;

	public WhileStatement(TestExpression testExpression, Statement updateStatement) {
		
		super("While Statement");
		this.testExpression = testExpression;
		this.updateStatement = updateStatement;
		
	}

	public String toString()
	{
		return super.toString() + "\n\t" + this.testExpression.toString() +
				"\n\t\t" + this.updateStatement.toString();				
	}
	
	public TestExpression getTestExpression() {
		return testExpression;
	}

	
	public Statement getUpdateStatement() {
		return updateStatement;
	}


}