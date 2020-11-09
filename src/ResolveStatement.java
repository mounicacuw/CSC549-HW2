public class ResolveStatement extends Statement {

    private String name;

    public ResolveStatement(String name) {

        super("Resolve ");
        this.name = name;

    }

    public String getName()
    {
        return name;
    }

    public String toString()
    {
        return super.toString() + "\n" + this.name ;
    }

}


