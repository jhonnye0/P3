public class Employee
{
    private String name;
    private String address;
    private String type;
    private String attribute;
    private boolean syndicate;
    private float salary, extra;
    private int id;

    public Employee(String name, String address, String type, String attribute, boolean syndicate, int id)
    {
        this.name = name;
        this.address = address;
        this.attribute = attribute;
        this.type = type;
        this.syndicate = syndicate;
        this.id = id;
    }

    public void setName(String name) { this.name = name;}

    public String getName() {
        return name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setSyndicate(boolean syndicate) { this.syndicate = syndicate; }

    public boolean isSyndicate() { return syndicate; }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
