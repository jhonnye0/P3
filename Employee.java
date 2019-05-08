public class Employee
{
    private String name;
    private String address;
    private String type;
    private String attribute;
    private int id;

    public Employee(String name, String address, String type, String attribute, int id){
        this.name = name;
        this.address = address;
        this.attribute = attribute;
        this.type = type;
        this.id = id;
    }

    public class PointCard
    {

    }

    public class SaleResult
    {

    }


    public void setName(String name) {
        this.name = name;
    }

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

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
