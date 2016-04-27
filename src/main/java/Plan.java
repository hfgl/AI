

public class Plan {

	private String id;
    private String content;

    public Plan(String event)
    {
        this.content = event;
    }

    public String getid() 
    {
        return this.id;
    }

    public void setid(String id) 
    {
        this.id = id;
    }

    public String getevent() 
    {
        return this.content;
    }

    public void setevent(String event) 
    {
        this.content = event;
    }
}
