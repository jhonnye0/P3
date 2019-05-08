import java.util.Scanner;

public class Main
{
    public static Employee add(String name, String address, String type, String attribute, int id)
    {
        Scanner input = new Scanner(System.in);
        Employee newEmp = new Employee(name, address, type, attribute, id);
        return newEmp;
    }

    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);

        Employee[] list = new Employee[100];

        while(1 == 1)
        {
            System.out.println("Digite a operação que deseja:\n" +
                    "0. Finalizar\n" +
                    "1. Adcionar empregado\n" +
                    "2. Remover empregado\n" +
                    "6. Reajustar informações de um empregado\n");

            int operation = input.nextInt();
            int id;
            if(operation == 0) break;

            switch(operation)
            {
                case 1:
                    int i;
                    for(i = 0; i<100; i++)
                    {
                        if(list[i] == null) break;
                    }
                    id = i;

                    System.out.print("Digite o nome:\n");
                    String name = input.nextLine();
                    System.out.print("Endereço:\n");
                    String address = input.nextLine();
                    System.out.println("Tipo de empregado:");
                    String type = input.nextLine();
                    System.out.println("Atributos do empregado:");
                    String attribute = input.nextLine();

                    list[id] = add(name, address, type, attribute, id);
                    break;
                case 2:
                    id = input.nextInt();
                    //remove(id);
                    break;
                case 6:
                    id = input.nextInt();
                    //update(id);
                    break;
                default:
                    System.out.println("Por favor digite novamente o número da operação específica");
                    break;
            }
        }
    }
}
