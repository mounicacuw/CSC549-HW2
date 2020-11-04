
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Parser 
{
	static ArrayList<Statement> theListOfStatements = new ArrayList<Statement>();
	

	
	static void display()
	{
		for(Statement s : theListOfStatements)
		{
			System.out.println(s);
		}
	}
	
	static RememberStatement parseRemember(String type, String name, String value)
	{
		//parse this string into language objects
		//turn remember syntax into a RememberStatement
		RememberStatement rs = new RememberStatement(type, name, value);
		return rs;
	}

	static ResolveStatement parseResolve(String name)
	{
		//parse this string into language objects
		//turn remember syntax into a RememberStatement
		// RememberStatement rs = new RememberStatement(name);
		ResolveStatement rs = new ResolveStatement(name);

		return rs;
	}
	
	static void parse(String filename)
	{
		try
		{
			Scanner input = new Scanner(new File(System.getProperty("user.dir") + 
					"/src/" + filename));
			String fileContents = "";
			while(input.hasNext())
			{
				fileContents += input.nextLine();
			}
			
			//System.out.println(fileContents);
			String[] theProgramLines = fileContents.split(";");
			for(int i = 0; i < theProgramLines.length; i++)
			{
				parseStatement(theProgramLines[i]);
				
			}
		}
		catch(Exception e)
		{
			System.err.println("File Not Found!");
		}
	}
	
	// "remember int a = 5"
	static void parseStatement(String s) throws Exception {
		String[] theParts = s.split("\\s+");
		if(theParts[0].equals("remember"))
		{
			theListOfStatements.add(Parser.parseRemember(theParts[1], 
					theParts[2], theParts[4]));

			
		}
		else if(theParts[0].equals("resolve"))
		{
			//write the necessary code to parse the resolve statement
			//into a ResolveStatement object

			theListOfStatements.add(Parser.parseResolve(theParts[1]));
			
		}
		
	}

	
}
