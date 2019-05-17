import java.util.Scanner;

public class Main
{
    private static int DIA = 1;
    private static int HORA = 0;
    static Scanner input = new Scanner(System.in);

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
                    }
                    else
                        System.out.print("Você nao pertence ao sindicato\n");
                    break;
                case 6:
                    System.out.println("Digite o ID do empregado que deseja modificar:\n");
                    update(input.nextInt());

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

    private static void update(int id)
    {
        while(true)
        {
            System.out.println("Digite o número da informação a qual deseja atualizar:\n\n" +
                    "0. Retornar\n" +
                    "1. Nome\n" +
                    "2. Endereço\n" +
                    "3. Tipo\n" +
                    "4. Atributos\n" +
                    "5. Método de pagamento\n" +
                    "6. Participar do sindicato\n" +
                    "7. Salário\n");

            int operation = input.nextInt();
            input.nextLine();
            if(operation == 0)
            {
                System.out.println("Finalizado.\n");
                break;
            }

            switch(operation)
            {
                case 1:
                    System.out.printf("Nome atual: %s\n", names[id]);
                    System.out.println("Digite o novo nome:");
                    names[id] = input.nextLine();
                    System.out.println("Atualizado.\n");
                    break;
                case 2:
                    System.out.printf("Endereço atual: %s\n", adress[id]);
                    System.out.println("Digite o novo endereço:");
                    adress[id] = input.nextLine();
                    System.out.println("Atualizado.\n");
                    break;
                case 3:
                    System.out.print("Tipo atual: ");
                    if(types[id] == 1) System.out.print("Horista\n");
                    else if(types[id] == 2) System.out.print("Comissionado\n");
                    else System.out.print("Assalariado\n");
                    System.out.println("Digite o número do tipo que deseja:\n" +
                            "1. Horista\n" +
                            "2. Comissionado\n" +
                            "3. Assalariado\n");
                    types[id] = input.nextInt();
                    System.out.println("Atualizado.\n");
                    break;
                case 4:
                    if(types[id] == 1)
                    {
                        System.out.println("Gostaria de mudar o dia de pagamento da semana?\n" +
                                "y - sim\n" +
                                "n - não");
                        if(input.nextLine().intern() == "y")
                        {
                            System.out.println("Qual dia deseja?\n\n" +
                                    "1. domingo\n" +
                                    "2. segunda\n" +
                                    "3. terça\n" +
                                    "4. quarta\n" +
                                    "5. quinta\n" +
                                    "6. sexta\n" +
                                    "7. sabado\n");
                            int day = input.nextInt();
                            input.nextLine();
                            if(day > 0 && day < 8)
                                paymentWeekDay[id] = day;

                            System.out.println("Seu pagamento é efetuado uma vez por semana.\n");
                        }
                    }
                    else if(types[id] == 2)
                    {
                        System.out.println("Qual dia deseja?\n\n" +
                                "1. domingo\n" +
                                "2. segunda\n" +
                                "3. terça\n" +
                                "4. quarta\n" +
                                "5. quinta\n" +
                                "6. sexta\n" +
                                "7. sabado\n");
                        int day = input.nextInt();
                        input.nextLine();
                        if(day > 0 && day < 8)
                            paymentWeekDay[id] = day;

                        System.out.println("Seu pagamento é efetuado uma vez a cada x semanas.\n");
                        System.out.println("Digite o x que deseja:\n");
                        frequence[id] = input.nextInt();
                        input.nextLine();
                    }
                    else if(types[id] == 3)
                    {
                        System.out.println("Seu pagamento é efetuado uma vez por mês.\n");
                        System.out.println("Qual dia deseja?\n");
                        frequence[id] = input.nextInt();
                        input.nextLine();
                    }
                    System.out.println("Atualizado.\n");
                    break;
                case 5:
                    System.out.print("Qual o método de pagamento deseja? (O método pode ser alterado)\n" +
                            "1. Cheque pelos correios\n" +
                            "2. Cheque em mãos\n" +
                            "3. Depósito na conta bancária\n");
                    paymentMethod[id] = input.nextInt();
                    System.out.println("Atualizado.\n");
                    break;
                case 6:
                    System.out.print("\nGostaria de participar do sindicato?\n" +
                            "y - sim\n" +
                            "n - não\n");
                    if(input.nextLine() == "y")
                        synd[id] = true;
                    else
                        synd[id] = false;
                    System.out.println("Atualizado.\n");
                    break;
                case 7:
                    System.out.print("Salário atual: ");
                    System.out.println(salary[id]);
                    System.out.print("Qual o novo salário?\n");
                    salary[id] = input.nextDouble();
                    System.out.println("Atualizado.\n");
                    break;
                default:
                    System.out.println("Digite novamente o número.");
                    break;
            }
        }
    }
}
