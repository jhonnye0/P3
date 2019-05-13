public class Employee
{
    private String name;
    private String address;
    private int type;
    private int synID;
    private float[] day = new float [31];
    private int limit;

    //Assalariado ---------------------
    private int payday;

    //Horista/Comissionado --------------
    private int paymentweekday;
    /*
    1. domingo
    2. segunda
    3. terça
    4. quarta
    5. quinta
    6. sexta
    7. sabado
     */

    private int frequence;
    /*
    a cada x semanas
     */
    private int paymentMethod;
    /*
    1. correios
    2. maos
    3. conta
     */

    private boolean syndicate;
    private float salary;
    private int id;

    public Employee(String name, String address, int type, int payday, int frequence, int paymentweekday, int paymentMethod, boolean syndicate, int id)
    {
        this.name = name;
        this.address = address;
        this.payday = payday;
        this.frequence = frequence;
        this.paymentweekday = paymentweekday;
        this.type = type;
        this.syndicate = syndicate;
        this.paymentMethod = paymentMethod;
        this.id = id;
    }

    public void setName(String name) { this.name = name;}

    public String getName() { return name; }


    public void setAddress(String address) { this.address = address; }

    public String getAddress() { return address; }


    public void setPaymentweekday(int paymentweekday) { this.paymentweekday = paymentweekday; }

    public int getPaymentweekday() { return paymentweekday; }


    public void setFrequence(int frequence) { this.frequence = frequence; }

    public int getFrequence() { return frequence; }


    public void setPayday(int payday) { this.payday = payday; }

    public int getPayday() { return payday; }


    public void setSynID(int synID) { this.synID = synID; }

    public int getSynID() { return synID; }


    public void setType(int type) { this.type = type; }

    public int getType() { return type; }


    public void setSyndicate(boolean syndicate) { this.syndicate = syndicate; }

    public boolean isSyndicate() { return syndicate; }


    public void setSalary(float salary) { this.salary = salary; }

    public float getSalary() { return salary; }


    public void setPaymentMethod(int paymentMethod) { this.paymentMethod = paymentMethod; }

    public int getPaymentMethod() { return paymentMethod; }


    public void setId(int id) { this.id = id; }

    public int getId() { return id; }


    public void setDay(int day, float price) {
        this.day[day] = price;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public float getDay(int i) { return day[i]; }
}
