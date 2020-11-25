package Interpreter;

import java.util.ArrayList;

import Parser.*;

public class SpyderInterpreter 
{
	private static VariableEnvironment theEnv = new VariableEnvironment();
	private static ArrayList<String> theOutput = new ArrayList<String>();
	
	public static void displayResults()
	{
		System.out.println("Current Variable Environment");
		SpyderInterpreter.theEnv.display();
		for(String s : SpyderInterpreter.theOutput)
		{
			System.out.println(s);
		}
	}
	
	private static void interpretStatement(Statement s)
	{
		if(s instanceof RememberStatement)
		{
			//interpret a remember statement
			SpyderInterpreter.interpretRememberStatement((RememberStatement)s);
		}
		else if(s instanceof QuestionStatement)
		{
			SpyderInterpreter.interpretQuestionStatement((QuestionStatement)s);
		}
		else if(s instanceof UpdateStatement)
		{
			SpyderInterpreter.interpretUpdateStatement((UpdateStatement)s);
		}
	}
	
	public static void interpret(ArrayList<Statement> theStatements)
	{
		for(Statement s : theStatements)
		{
			SpyderInterpreter.interpretStatement(s);
		}
	}
	
	//determines if a String contains all digits (numbers)
	private static boolean isInteger(String s)
	{
		for(int i = 0; i < s.length(); i++)
		{
			if(!Character.isDigit(s.charAt(i)))
			{
				return false;
			}
		}
		return true;
	}
	
	private static int interpretLiteralExpression(LiteralExpression le)
	{
		if(le instanceof Int_LiteralExpression)
		{
			return ((Int_LiteralExpression) le).getValue();
		}
		throw new RuntimeException("Not a valid literal type...");
	}
	
	private static int interpretDoMathExpression(DoMathExpression dme)
	{
		Expression left = dme.getLeft();
		int leftValue = SpyderInterpreter.getExpressionValue(left);
		Expression right = dme.getRight();
		int rightValue = SpyderInterpreter.getExpressionValue(right);
		String math_op = dme.getOp();
		
		if(math_op.equals("+"))
		{
			return leftValue + rightValue;
		}
		else if(math_op.equals("-"))
		{
			return leftValue - rightValue;
		}
		else if(math_op.equals("*"))
		{
			return leftValue * rightValue;
		}
		else if(math_op.equals("/"))
		{
			return leftValue / rightValue;
		}
		else if(math_op.equals("%"))
		{
			return leftValue % rightValue;
		}
		throw new RuntimeException("Not a valid math operator: " + math_op);
	}
	
	private static int interpretTestExpression(TestExpression te)
	{
		int leftValue = SpyderInterpreter.getExpressionValue(te.getLeft());
		int rightValue = SpyderInterpreter.getExpressionValue(te.getRight());
		String op = te.getOp();
		if(op.equals("<"))
		{
			if(leftValue < rightValue)
			{
				return 1;
			}
			else
			{
				return 0;
			}
		}
		else if(op.equals("<="))
		{
			if(leftValue <= rightValue)
			{
				return 1;
			}
			else
			{
				return 0;
			}
		}
		else if(op.equals(">"))
		{
			if(leftValue > rightValue)
			{
				return 1;
			}
			else
			{
				return 0;
			}
		}
		else if(op.equals(">="))
		{
			if(leftValue >= rightValue)
			{
				return 1;
			}
			else
			{
				return 0;
			}
		}
		else if(op.equals("!="))
		{
			if(leftValue != rightValue)
			{
				return 1;
			}
			else
			{
				return 0;
			}
		}
		else if(op.equals("=="))
		{
			if(leftValue <= rightValue)
			{
				return 1;
			}
			else
			{
				return 0;
			}
		}
		throw new RuntimeException("Not a valid boolean operator: " + op);
	}
	
	private static int interpretResolveExpression(ResolveExpression rs)
	{
		
		//only look up the variable in the env if it is not a LITERAL
		//Literal Types: int
		//this try/catch attempts to convert a string to an int and if it fails it
		//looks the string up as a variable name
		try
		{
			//tries to treat it as a int literal
			return Integer.parseInt(rs.getName());	
		}
		catch(Exception e)
		{
			try
			{
				//if not a literal, look it up in our environment
				return SpyderInterpreter.theEnv.getValue(rs.getName());
			}
			catch(Exception e2)
			{
				throw new RuntimeException("Variable " + rs.getName() + " NOT FOUND!");
			}
		}
	}
	
	private static int getExpressionValue(Expression e)
	{
		if(e instanceof ResolveExpression)
		{
			return SpyderInterpreter.interpretResolveExpression((ResolveExpression)e);
		}
		else if(e instanceof LiteralExpression)
		{
			return SpyderInterpreter.interpretLiteralExpression((LiteralExpression) e);
		}
		else if(e instanceof DoMathExpression)
		{
			return SpyderInterpreter.interpretDoMathExpression((DoMathExpression) e);
		}
		else if(e instanceof TestExpression)
		{
			return SpyderInterpreter.interpretTestExpression((TestExpression) e);
		}
		throw new RuntimeException("Not a known expression type: " + e.getExpressionType());
	}
	
	private static void interpretRememberStatement(RememberStatement rs)
	{
		//we need to resolve this expression before we can actually remember anything
		Expression valueExpression = rs.getValueExpression();
		int answer = SpyderInterpreter.getExpressionValue(valueExpression);
		
		SpyderInterpreter.theEnv.addVariable(rs.getName(), answer);
		SpyderInterpreter.theOutput.add("<HIDDEN> Added " + rs.getName() + " = " + answer + " to the variable environment.");
	}
	private static void interpretUpdateStatement(UpdateStatement us)
	{
		//we need to resolve this expression before we can actually remember anything
		Expression valueExpression = us.getValueExpression();
		int answer = SpyderInterpreter.getExpressionValue(valueExpression);
		
		try {
			int isUpdated = SpyderInterpreter.theEnv.updateVariable(us.getName(), answer);
			//If the Variable returns True, then add the update statement to the output.
			if(isUpdated == 1) {
				SpyderInterpreter.theOutput.add("<HIDDEN> Updated " + us.getName() + " = " + answer + " to the variable environment.");
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}
	
	private static void interpretQuestionStatement(QuestionStatement qs)
	{
		//we need to resolve this expression before we can actually remember anything
		TestExpression testExpression = qs.getTestExpression();
		int answer = SpyderInterpreter.getExpressionValue(testExpression);
		
		if(answer == 1)
		{
			//testExpression was true, so execute the trueStatement
			SpyderInterpreter.interpretStatement(qs.getTrueStatement());
		}
		else
		{
			//testExpression was false, so execute the falseStatement
			SpyderInterpreter.interpretStatement(qs.getFalseStatement());
		}
	}
	
}