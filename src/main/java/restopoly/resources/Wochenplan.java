package restopoly.resources;

public class Wochenplan {

	private String id;
    private String event;

    public Wochenplan(String event)
    {
        this.event = event;
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
        return this.event;
    }

    public void setevent(String event) 
    {
        this.event = event;
    }
}
