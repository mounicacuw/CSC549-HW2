package Interpreter;
import java.util.ArrayList;

public class VariableEnvironment 
{
	private ArrayList<NameValuePair> theVariables;
	
	public VariableEnvironment()
	{
		this.theVariables = new ArrayList<NameValuePair>();
	}
	
	public void display()
	{
		for(NameValuePair nvp : this.theVariables)
		{
			nvp.display();
		}
	}
	
	//take in a name and a value and create a NameValuePair and add
	//it to theVariables
	public void addVariable(String name, int value)
	{
		this.theVariables.add(new NameValuePair(name, value));
	}
	//update the value of the variable if it is found in the environment
	public int updateVariable(String name, int value) throws Exception
	{
		for(NameValuePair nvp: this.theVariables)
		{
			
			if(nvp.getName().equals(name))
			{
				this.theVariables.set(this.theVariables.indexOf(nvp),new NameValuePair(name, value));
				return 1;
				
			}
		}
		throw new Exception("Variable Not found");
	}
	//take in a name and it should retrieve the value associated
	//with that variable name.  For now, you can assume that
	//any name you look for, will be found.
	public int getValue(String name) throws Exception
	{
		for(NameValuePair nvp: this.theVariables)
		{
			if(nvp.getName().equals(name))
			{
				return nvp.getValue();
			}
		}
		//return -1;
		throw new Exception("Variable Not Found");
	}
}