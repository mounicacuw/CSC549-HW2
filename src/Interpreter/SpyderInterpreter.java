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
	
	public static void interpret(ArrayList<Statement> theStatements)
	{
		for(Statement s : theStatements)
		{
			if(s instanceof RememberStatement)
			{
				SpyderInterpreter.interpretRememberStatement((RememberStatement)s);
			}
		}
	}
	
	private static int getExpressionValue(Expression e)
	{
		if(e instanceof ResolveExpression)
		{
			return SpyderInterpreter.interpretResolveExpression((ResolveExpression)e);
		}
		else if (e instanceof DoMathStatement)
		{
			return SpyderInterpreter.interpretDoMathExpression((DoMathStatement)e);
		}
		throw new RuntimeException("Not a known expression type: " + e.toString());
	}
	
	private static void interpretRememberStatement(RememberStatement rs)
	{
		
		Expression valueExpression = rs.getValueExpression();
		int answer = SpyderInterpreter.getExpressionValue(valueExpression);
		
		SpyderInterpreter.theEnv.addVariable(rs.getName(), answer);
		SpyderInterpreter.theOutput.add("<HIDDEN> Added " + rs.getName() + " = " + answer + " to the variable environment.");
		
	}
	
	private static int interpretDoMathExpression(DoMathStatement dms)
	{
		int d1 = SpyderInterpreter.interpretResolveExpression((ResolveExpression)dms.e1);
		int d2 = SpyderInterpreter.interpretResolveExpression((ResolveExpression)dms.e2);
		return DoMathStatement.doMath(d1, d2, dms.op);
	}
	
	private static int interpretResolveExpression(ResolveExpression rs)
	{
		
		if (isInteger(rs.getName()))
		{
			return Integer.parseInt(rs.getName());
		}
		else {
			try
			{
				return SpyderInterpreter.theEnv.getValue(rs.getName());
			}
			catch(Exception e)
			{
				throw new RuntimeException("Variable " + rs.getName() + " NOT FOUND!");
			}
		}
		
	}

	public static Boolean isInteger(String s)
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
}