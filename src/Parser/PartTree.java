package Parser;

public abstract class PartTree {
	
	private String rootType;
	
	public PartTree(String rootType)
	{
		this.rootType = rootType;
	}
	
	public String toString()
	{
		return this.rootType;
	}
}