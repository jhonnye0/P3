public class SaleResult
{
    private float[] day = new float [31];
    private int limit;

    public void setDay(int day, float price) {
        this.day[day] = price;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public float getDay(int i) { return day[i]; }
}
