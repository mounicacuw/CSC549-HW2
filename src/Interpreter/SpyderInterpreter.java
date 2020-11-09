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
				//interpret a remember statement
				SpyderInterpreter.interpretRememberStatement((RememberStatement)s);
			}
			//***HW*** add the ability to handle ResolveStatements
			//theOutput should hold:
			// <HIDDEN> Resolved a = 5
			if(s instanceof ResolveStatement)
			{
				//interpret a resolve statement
				SpyderInterpreter.interpretResolveStatement((ResolveStatement)s);
			}
		}
	}		
		
private static void interpretRememberStatement(RememberStatement rs)
	{
		SpyderInterpreter.theEnv.addVariable(rs.getName(), rs.getIntValue());
		SpyderInterpreter.theOutput.add("<HIDDEN> Added " + rs.getName() + " = " + rs.getIntValue() + " to the variable environment.");
	}
     
private static void interpretResolveStatement(ResolveStatement rs)

  {
	
		SpyderInterpreter.theOutput.add("<HIDDEN> Resolved " + rs.getName() + " =  " 
		+ SpyderInterpreter.theEnv.getValue(rs.getName()) +" from the variable environment.");
	

   }
}

