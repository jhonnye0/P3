import java.util.Scanner;

public class Main
{
    private static int DIA = 1;
    private static int HORA = 0;

    static String[] names = new String[100];
    static String[] adress = new String[100];
    static int[] types = new int[100];
    static double[] salary = new double[100];
    static int[] Syndicato = new int[1000];
    static boolean[] synd = new boolean[100];
    static double[][] vendas = new double[100][31];
    static int[] ponto = new int[100];
    static int[] paymentMethod = new int[100];
    static int[] synID = new int[100];
    static int[] payday = new int[100];
    static int[] frequence = new int[100];
    static int[] paymentWeekDay = new int[100];
    static double[] fundo = new double[100];

    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        while(true)
        {
            System.out.println("\n------------------------------\n");
            System.out.println("Digite a operação que deseja:\n\n" +
                    "0. Finalizar\n" +
                    "1. Adcionar empregado\n" +
                    "2. Remover empregado\n" +
                    "3. Registrar venda\n" +
                    "4. Marcar ponto\n" +
                    "5. Lançar taxa de serviço\n" +
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
                        if(names[i] == null) break;
                    }
                    add(i);
                    break;
                case 2:
                    System.out.print("Digite o ID do empregado que deseja remover:\n");
                    id = input.nextInt();
                    remove(id);
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
                        int cont = 0;
                        for (i = ponto[id]; true; i++)
                        {
                            if(i == HORA) break;
                            cont++;
                            i = i%24;
                        }

                        if(cont > 8 && types[id] == 1)
                        {
                            fundo[id] = salary[id] + (cont - 8)*1.5*salary[id];
                        }
                    }
                    System.out.println("Horários registrado!");
                    break;
                case 5:
                    System.out.print("Digite seu ID:\n");
                    id = input.nextInt();
                    if(names[id] == null)
                    {
                        System.out.print("O empregado não existe\n");
                    }
                    else if(synd[id] == true)
                    {
                        System.out.print("Insira a taxa de serviço:\n");
                        fundo[id] -= input.nextDouble();
                        System.out.print("Taxa descontada do salário\n");
                    }
                    else
                        System.out.print("Você nao pertence ao sindicato\n");
                    break;

                default:
                    System.out.print("Digite novamente..\n");
                    break;
            }
        }
    }

    private static void add(int id)
    {
        Scanner input = new Scanner(System.in);

        System.out.print("Digite o nome:\n");
        names[id] = input.nextLine();
        System.out.print("Endereço:\n");
        adress[id] = input.nextLine();
        System.out.print("Tipo de empregado:\n" +
                    "1. Horista\n" +
                    "2. Comissionado\n" +
                    "3. Assalariado\n");
        types[id] = input.nextInt();
        input.nextLine();

        System.out.println("Atributos default:\n");
        if(types[id] == 1)
        {
            frequence[id] = 1;
            paymentWeekDay[id] = 6;
            System.out.println("Pagamento 1 vez por semana\n" +
                        "Dia de pagamento na sexta-feira.\n\n" +
                        "Pode ser alterado futuramente..\n");

        }
        else if(types[id] == 2)
        {
            frequence[id] = 2;
            paymentWeekDay[id] = 6;
            System.out.println("Pagamento 1 vez por semana\n" +
                        "Dia de pagamento na sexta-feira.\n\n" +
                        "Pode ser alterado futuramente..\n");
        }
        else
        {
            payday[id] = 31;
            System.out.print("Pagamento 1 vez por mês\n" +
                    "Dia de pagamento no último dia do mês\n" +
                    "Dia: 30\n" +
                    "Pode ser alterado futuramente..\n");
        }

        System.out.print("Qual o método de pagamento deseja?\n" +
                        "1. Cheque pelos correios\n" +
                        "2. Cheque em mãos\n" +
                        "3. Depósito na conta bancária\n");
        paymentMethod[id] = input.nextInt();
        input.nextLine();

        System.out.print("Qual o salário do empregado?\n");
        salary[id] = input.nextDouble();
        input.nextLine();

        System.out.printf("\nSeu ID na empresa é: |%d|\n", id);

        System.out.print("\nGostaria de participar do sindicato?\n" +
                    "y - sim\n" +
                    "n - não\n");

        if(input.nextLine().intern() == "y")
        {
            synd[id] = true;
            int i;
            for(i = 999; i>=0; --i)
            {
                if (Syndicato[i] == 0)
                {
                    Syndicato[i] = 1;
                    break;
                }
            }
            synID[id] = i;
            System.out.print("A taxa sindical no valor (default) de 10 reais será descontado do seu salário ao fim do mês\n");
            System.out.printf("Seu ID no sindicato é: |%d|\n", synID[id]);
        }
        else
            synd[id] = false;

        System.out.print("Registrado com sucesso.\n");
        System.out.print("Lembre-se os seus ID's são muito importantes para acessar suas informações guarde-o!!\n");
    }

    private static void remove(int id)
    {
        if(names[id] == null) 
        {
            System.out.println("O empregado não está registrado\n");
            return;
        }
        int i,j;
        names[id] = null;
        adress[id] = null;
        types[id] = 0;
        salary[id] = 0;
        Syndicato[id] = 0;
        synd[id] = false;
        for(i = 0; i<100; i++)
        {
            for (j=0; j<31; j++)
            {
                vendas[i][j] = 0;
            }
        }
        ponto[id] = 0;
        paymentMethod[id] = 0;
        synID[id] = 0;
        payday[id] = 0;
        frequence[id] = 0;
        paymentWeekDay[id] = 0;
        fundo[id] = 0;
        System.out.println("Empregado removido com sucesso!");
    }
}
