import java.util.Scanner;

public class Main
{
    public static Employee add()
    {
        Scanner input = new Scanner(System.in);
        Employee newEmp = new Employee(input.nextLine(), input.nextLine(), input.nextLine(), input.nextLine(), input.nextInt());
        return newEmp;
    }

    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);

        Employee[] List = new Employee[100];

        int quantity = 0;
        while(1 == 1)
        {
            int operation = input.nextInt();
            if(operation == -1) break;

            switch(operation)
            {
                case 1:
                    add();
                case 2:
                    //remove();
                case 6:
                    //update();
            }
        }
    }
}
