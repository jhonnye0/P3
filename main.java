import java.util.Scanner;

/*
0. id
1. name
2. adress
3. type
4. syndicate
5. synID
6. paymentMethod
7. salary
8. payday
9. frequence
10. paymentWeekDay
 */

public class Main
{
    private static int DIA = 1;
    private static int HORA = 0;

    public static void main(String[] args)
    {
        String[][] lista = new String[100][11];
        int[] Syndicate = new int[100];
        double[][] vendas = new double[100][30];
        int[] ponto = new int[100];

        Scanner input = new Scanner(System.in);
        while(true)
        {
            System.out.println("\n------------------------------\n");
            System.out.println("Digite a operação que deseja:\n\n" +
                    "0. Finalizar\n" +
                    "1. Adcionar empregado\n" +
                    "2. Remover empregado\n" +
                    "3. Marcar o Ponto\n" +
                    "4. Registrar venda\n" +
                    "6. Reajustar informações de um empregado\n");
            int operation = input.nextInt();
            input.nextLine();
            int id;
            if(operation == 0) break;
            switch(operation)
            {
                case 1: // Adcionar novo empregado
                    int i;
                    for(i = 0; i<100; i++)
                    {
                        if(lista[i][0] == null) break;
                    }
                    lista[i][0] = String.valueOf((int)i);
                    add(i, lista, Syndicate);
                    break;
                case 2:
                    System.out.print("Digite o ID do empregado que deseja remover:\n");
                    id = input.nextInt();
                    lista[id][0] = null;
                    Syndicate[id] = 0;
                    break;
                case 3:
                    System.out.print("Digite seu ID:\n");
                    id = input.nextInt();
                    System.out.print("Digite o valor da venda:\n");
                    vendas[id][DIA] = input.nextDouble();
                    break;
                case 4:
                    System.out.print("Digite seu ID:\n");
                    id = input.nextInt();
                    System.out.print("1 - Chegando / 2 - Saindo\n");
                    if(input.nextInt() == 1)
                    {
                        ponto[id] = HORA;
                    }
                    else
                    {
                        int cal = HORA - ponto[id];
                        if(cal > 8 && Integer.parseInt(lista[id][3]) == 1)
                        {
                            lista[id][7] = String.valueOf(Double.parseDouble(lista[id][7]) + (cal - 8)*1.5);

                        }
                    }
                    break;
                default:
                    System.out.print("Digite novamente..\n");
                    break;
            }
        }
    }

    private static void add(int id, String[][] lista, int[] syndicate)
    {
        Scanner input = new Scanner(System.in);

        System.out.print("Digite o nome:\n");
        lista[id][1] = input.nextLine();
        System.out.print("Endereço:\n");
        lista[id][2] = input.nextLine();
        System.out.print("Tipo de empregado:\n" +
                    "1. Horista\n" +
                    "2. Comissionado\n" +
                    "3. Assalariado\n");
        lista[id][3] = String.valueOf(input.nextInt());
        input.nextLine();

        System.out.println("Atributos default:\n");
        if(Integer.parseInt(lista[id][3]) == 1)
        {
            lista[id][9] = String.valueOf((int) 1);
            lista[id][10] = String.valueOf((int) 6);
            System.out.println("Pagamento 1 vez por semana\n" +
                        "Dia de pagamento na sexta-feira.\n\n" +
                        "Pode ser alterado futuramente..\n");

        }
        else if(Integer.parseInt(lista[id][3]) == 2)
        {
            lista[id][9] = String.valueOf((int) 2);
            lista[id][10] = String.valueOf((int) 6);
            System.out.println("Pagamento 1 vez por semana\n" +
                        "Dia de pagamento na sexta-feira.\n\n" +
                        "Pode ser alterado futuramente..\n");
        }
        else
        {
            lista[id][8] = String.valueOf((int) 31);
            System.out.print("Pagamento 1 vez por mês\n" +
                    "Dia de pagamento no último dia do mês\n" +
                    "Dia: 30\n" +
                    "Pode ser alterado futuramente..\n");
        }

        System.out.print("Qual o método de pagamento deseja?\n" +
                        "1. Cheque pelos correios\n" +
                        "2. Cheque em mãos\n" +
                        "3. Depósito na conta bancária\n");
        lista[id][6] = String.valueOf((int)input.nextInt());
        input.nextLine();

        System.out.print("Qual o salário do empregado?\n");
        lista[id][7] = String.valueOf((double)input.nextDouble());
        input.nextLine();

        System.out.print("\nGostaria de participar do sindicato?\n" +
                    "y - sim\n" +
                    "n - não\n");
        if(input.nextLine().intern() == "y")
        {
            lista[id][4] = String.valueOf((int) 1);
            int i;
            for(i = 0; i<100; i++)
            {
                if (syndicate[i] == 0) break;
            }
            lista[id][5]= String.valueOf((int)i);
            System.out.printf("\nSeu ID na empresa é: |%d|\n", Integer.parseInt(lista[id][0]));
            System.out.printf("Seu ID no sindicato é: |%d|\n", Integer.parseInt(lista[id][5]));
            System.out.print("Registrado com sucesso.\n");
            System.out.print("Lembre-se o seu ID é muito importante para acessar suas informações guarde-o!!\n");

        }
        else
            lista[id][4] = String.valueOf((int) 0);
    }
}
