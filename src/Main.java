
import java.util.Scanner;

public class Main
{
    public static SaleResult register(int id, SaleResult sales[])
    {
        Scanner input = new Scanner(System.in);

        sales[id] = new SaleResult();

        System.out.println("Informe o dia atual:");
        int day = input.nextInt();
        System.out.println("E o valor da venda efetuada (lembre-se de separar casas decimais com vírgula):");
        float price = input.nextFloat();
        sales[id].setDay(day, price);
        System.out.println("Venda registrada com sucesso..\n");
        return sales[id];
    }


    public static Employee update(int id, Employee list[])
    {
        Scanner input = new Scanner(System.in);
        while(1 == 1)
        {
            System.out.println("Digite a qual informação que deseja atualizar:\n\n" +
                    "0. Finalizar\n" +
                    "1. Nome\n" +
                    "2. Endereço\n" +
                    "3. Tipo\n" +
                    "4. Atributos:\n");

            int operation = input.nextInt();
            String buffer = input.nextLine();
            if(operation == 0)
            {
                System.out.println("Finalizado.\n");
                break;
            }

            switch(operation)
            {
                case 1:
                    System.out.printf("Nome atual: %s\n", list[id].getName());
                    System.out.println("Digite o novo nome:");
                    list[id].setName(input.nextLine());
                    System.out.println("Atualizado.\n");
                    break;
                case 2:
                    System.out.printf("Endereço atual: %s\n", list[id].getAddress());
                    System.out.println("Digite o novo endereço:");
                    list[id].setAddress(input.nextLine());
                    System.out.println("Atualizado.\n");
                    break;
                case 3:
                    System.out.printf("Tipo atual: %s\n", list[id].getType());
                    System.out.println("Digite o novo tipo:");
                    list[id].setType(input.nextLine());
                    System.out.println("Atualizado.\n");
                    break;
                case 4:
                    System.out.printf("Atributos atuais: %s\n", list[id].getAttribute());
                    System.out.println("Digite o novo Atributo:");
                    list[id].setAttribute(input.nextLine());
                    System.out.println("Atualizado.\n");
                    break;
                default:
                    System.out.println("Digite novamente o número.");
                    break;
            }
        }
        return list[id];
    }

    public static Employee add(int id)
    {
        Scanner input = new Scanner(System.in);
        System.out.print("Digite o nome:\n");
        String name = input.nextLine();
        System.out.print("Endereço:\n");
        String address = input.nextLine();
        System.out.print("Tipo de empregado:\n");
        String type = input.nextLine();
        System.out.print("Atributos do empregado:\n");
        String attribute = input.nextLine();

        Employee newEmp = new Employee(name, address, type, attribute,false, id);
        return newEmp;
    }

    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);

        Employee[] list = new Employee[100];
        SaleResult[] sales = new SaleResult[100];

        while(1 == 1)
        {
            System.out.println("\n--------------------------------------------\n");
            System.out.println("Digite a operação que deseja:\n\n" +
                    "0. Finalizar\n" +
                    "1. Adcionar empregado\n" +
                    "2. Remover empregado\n" +
                    "4. Registrar venda\n" +
                    "6. Reajustar informações de um empregado\n");

            int operation = input.nextInt();
            input.nextLine();
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
                    if(list[i] == null)
                    {
                        id = i;
                        list[id] = add(id);
                        System.out.print("\nGostaria de participar do sindicato?\n" +
                                "y - sim\n" +
                                "n - não\n");
                        if(input.nextLine() == "y")
                            list[id].setSyndicate(true);
                            //ID DO SINDICATO
                        System.out.printf("\nSeu ID na empresa é: |%d|\n", id);
                        System.out.print("\nLembre-se o seu ID é muito importante para acessar suas informações guarde-o!!\n");
                        System.out.println("\nRegistrado com sucesso.");
                    }
                    else
                        System.out.println("Sua empresa atingiu o limite máximo de empregados!\n\n");
                    break;
                case 2:
                    System.out.println("Digite o ID do empregado que deseja remover:\n");
                    id = input.nextInt();
                    list[id] = null;
                    sales[id] = null;
                    System.out.printf("Empregado de ID |%d| foi removido com sucesso.\n", id);
                    break;
                case 4:
                    System.out.println("Informe seu ID:");
                    id = input.nextInt();
                    if(list[id] != null)
                        register(id, sales);
                    else
                        System.out.print("ID não existente no sistema..\n");
                    break;
                case 6:
                    System.out.println("Digite o ID do empregado que deseja modificar:\n");

                    id = input.nextInt();
                    if(list[id] != null)
                        list[id] = update(id, list);
                    else
                        System.out.println("O empregado não existe..\n");
                    break;
                default:
                    System.out.println("Por favor digite novamente o número da operação específica");
                    break;
            }
        }
    }
}
