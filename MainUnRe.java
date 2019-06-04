import java.util.Scanner;

public class Main
{
    private static int DIA = 1;
    private static int HORA = 0;
    private static int SEMANA = 0;
    static Scanner input = new Scanner(System.in);

    static int state_cont = 0;
    static int redo_cont = 0;

    static String[] names = new String[100];
    static String[] adress = new String[100];
    static int[] types = new int[100];
    static double[] salary = new double[100];
    static int[] Syndicato = new int[1000];
    static boolean[] synd = new boolean[100];
    static double[][] vendas = new double[100][32];
    static int[] ponto = new int[100];
    static int[] paymentMethod = new int[100];
    static int[] synID = new int[100];
    static int[] payday = new int[100];
    static int[] frequence = new int[100];
    static int[] paymentWeekDay = new int[100];
    static int[] SM = new int[100]; //(salario mensal)
    static int[] comPercentual = new int[100];
    static double[] syndTax = new double[100];
    static double[] fundo = new double[100];
    static int[] daysM = new int[32];
    static int[][] semanal = new int[8][4];

    //-----------------------------------------------------
    static String[][] namesUN = new String[100][1000];
    static String[][] adressUN = new String[100][1000];
    static int[][] typesUN = new int[100][1000];
    static double[][] salaryUN = new double[100][1000];
    static int[][] SyndicatoUN = new int[1000][1000];
    static boolean[][] syndUN = new boolean[100][1000];
    static double[][][] vendasUN = new double[100][32][1000];
    static int[][] pontoUN = new int[100][1000];
    static int[][] paymentMethodUN = new int[100][1000];
    static int[][] synIDUN = new int[100][1000];
    static int[][] paydayUN = new int[100][1000];
    static int[][] frequenceUN = new int[100][1000];
    static int[][] paymentWeekDayUN = new int[100][1000];
    static int[][] SM_UN = new int[100][1000];
    static int[][] comPercentualUN = new int[100][1000];
    static double[][] syndTaxUN = new double[100][1000];
    static double[][] fundoUN = new double[100][1000];

    public static void main(String[] args)
    {
        while(true)
        {
            System.out.println("\n------------------------------\n");
            System.out.println("\nModo de acesso: (Digite o número)\n" +
                    "0. Encerrar acesso\n" +
                    "1. Admin\n" +
                    "2. Empregado\n");
            int o = input.nextInt();
            int operation = 0;
            if(o == 1)
            {
                while(true)
                {
                    System.out.println("\n------------------------------\n");
                    System.out.println("Digite o número da operação que deseja:\n\n" +
                            "0. Finalizar\n" +
                            "1. Adcionar empregado\n" +
                            "2. Remover empregado\n" +
                            "3. Lançar taxa de serviço\n" +
                            "4. Reajustar informações de um empregado\n" +
                            "5. Rodar a folha de pagamento para hoje\n" +
                            "6. Criar nova agenda de pagamento\n" +
                            "7. Passar as horas/ Checar horas\n" +
                            "8. Passar os dias/ Checar dia\n" +
                            "9. Checar semana\n" +
                            "10. Checar os empregados registrados na empresa\n" +
                            "11. Desfazer operação\n" +
                            "12. Refazer operação\n");
                    operation = input.nextInt();
                    input.nextLine();
                    int id;
                    if(operation == 0) break;
                    switch(operation)
                    {
                        case 1:
                            int i;
                            for(i = 0; i<100; i++)
                            {
                                if(names[i] == null) break;
                            }
                            add(i);
                            saveState_UNDO();
                            break;
                        case 2:
                            System.out.print("Digite o ID do empregado que deseja remover:\n");
                            id = input.nextInt();
                            remove(id);
                            saveState_UNDO();
                            break;
                        case 3:
                            System.out.print("Digite seu ID:\n");
                            id = input.nextInt();
                            if(names[id] == null)
                            {
                                System.out.print("O empregado não existe\n");
                            }
                            else if(synd[id])
                            {
                                System.out.print("Insira a taxa de serviço:\n");
                                fundo[id] -= input.nextDouble();
                                System.out.println("Taxa cobrada com sucesso!\n");
                            }
                            else
                                System.out.print("Você nao pertence ao sindicato\n");
                            saveState_UNDO();
                            break;
                        case 4:
                            System.out.println("Digite o ID do empregado que deseja modificar:\n");
                            update(input.nextInt());
                            saveState_UNDO();
                            break;
                        case 5:
                            System.out.println("------------------------------------");
                            System.out.println("Empregados que devem ser pagos hoje:\n");
                            int[] topay = new int[100];
                            int cont = 0;
                            for(i = 0; i<100; i++)
                            {
                                if(names[i] != null)
                                {
                                    if(SM[i] == 1)
                                    {
                                        if(payday[i] == DIA)
                                        {
                                            topay[cont++] = i;
                                            calcCom(i);
                                            PrintEmployee(i);
                                        }
                                    }
                                    else
                                    {
                                        if(paymentWeekDay[i] == (DIA%7)+1)
                                        {
                                            if(SEMANA%frequence[i] == 0)
                                            {
                                                topay[cont++] = i;
                                                calcCom(i);
                                                PrintEmployee(i);
                                            }
                                        }
                                    }
                                }
                            }
                            System.out.println("Digite ENTER..\n");
                            input.nextLine();
                            for(i = 0; i<100; i++)
                            {
                                id = topay[i];
                                if(types[id] == 1) fundo[id] = 0;
                                else if(types[id] == 2) fundo[id] = salary[id];
                            }
                            System.out.println("Empregados pagos com sucesso!\n");
                            saveState_UNDO();
                            break;
                        case 6:
                            System.out.print("1 - Mensal / 2 - Semanal\n");
                            if(input.nextInt() == 1)
                            {
                                System.out.print("Qual dia deseja adcionar na agenda de pagamento?\n");
                                daysM[input.nextInt()] = 1;
                                System.out.print("Agenda adcionada com sucesso\n");
                            }
                            else
                            {
                                System.out.print("Qual dia da semana será efetuado o pagamento?\n\n" +
                                        "1. Domingo\n" +
                                        "2. Segunda\n" +
                                        "3. Terça\n" +
                                        "4. Quarta\n" +
                                        "5. Quinta\n" +
                                        "6. Sexta\n" +
                                        "7. Sábado\n");
                                int day = input.nextInt();
                                System.out.print("E qual a frequência semanal? (A frequência não ultrapassa o valor de 3 semanas)\n");
                                semanal[day][input.nextInt()] = 1;
                            }
                            break;
                        case 7:
                            System.out.println(" 1 - Avançar horas / 2 - Checar");
                            if(input.nextInt() == 1)
                            {
                                System.out.println("Deseja avançar quantas horas?");
                                HORA += input.nextInt();
                                HORA %= 24;
                                System.out.println("------------------------------------");
                                System.out.printf("Hora atual: %d\n", HORA);
                            }
                            else
                            {
                                System.out.println("------------------------------------");
                                System.out.printf("Hora atual: %d\n", HORA);
                            }

                            break;
                        case 8:
                            System.out.println(" 1 - Avançar dias / 2 - Checar\n");
                            if(input.nextInt() == 2)
                            {
                                System.out.println("------------------------------------");
                                System.out.printf("Dia atual: %d\n", DIA);
                            }
                            else
                            {
                                System.out.println("Quantos dias deseja avançar?");
                                int qt = input.nextInt();
                                DIA += qt;
                                SEMANA = DIA/7;
                                System.out.println(SEMANA);
                                DIA %= 31;
                                System.out.println("------------------------------------");
                                System.out.printf("Dia atual: %d\n", DIA);
                            }
                            if(DIA == 30)
                            {
                                for (i = 0; i<100; i++)
                                {
                                    if(names[i] != null)
                                        fundo[i] -= 10;
                                }
                            }
                            if(DIA == 0) DIA++;
                            break;
                        case 9:
                            System.out.println("------------------------------------");
                            System.out.printf("Semana atual: %d\n", SEMANA);
                            break;
                        case 10:
                            System.out.println("------------------------------------");
                            System.out.println("Sua empresa possui esses empregados:\n");
                            for(i = 0; i<100; i++)
                            {
                                if(names[i] != null)
                                {
                                    PrintEmployee(i);
                                }
                            }
                            break;
                        case 11:
                            System.out.print("Deseja desfazer a última operação?\n" +
                                    "y - yes\n" +
                                    "n - no\n");
                            if(input.nextLine().intern() == "y")
                            {
                                UNDO();
                            }
                            System.out.print("Operação desfeita com sucesso!\n");
                            break;
                        case 12:
                            System.out.print("Deseja refazer a última operação?\n" +
                                    "y - yes\n" +
                                    "n - no\n");
                            if(input.nextLine().intern() == "y")
                            {
                                REDO();
                            }
                            System.out.print("Operação refeita com sucesso!\n");
                        case 999:
                            System.out.println(state_cont);
                            System.out.println(redo_cont);
                            break;
                        default:
                            System.out.print("Digite novamente..\n");
                            break;
                    }

                }
            }
            else if(o == 2)
            {
                while(true)
                {
                    System.out.println("\n------------------------------\n");
                    System.out.println("Digite a operação que deseja:\n\n" +
                            "0. Finalizar\n" +
                            "1. Registro de vendas\n" +
                            "2. Marcar ponto\n" +
                            "3. Alterar agenda de pagamento\n");
                    operation = input.nextInt();
                    int id, i;
                    input.nextLine();
                    if(operation == 0) break;
                    switch (operation)
                    {
                        case 1:
                            System.out.println("\n------------------------------\n");
                            System.out.print("Digite seu ID:\n");
                            id = input.nextInt();
                            System.out.println("\nO que deseja?:\n" +
                                    "1. Registrar Venda\n" +
                                    "2. Acessar vendas efetuadas\n");
                            if(input.nextInt() == 1)
                            {
                                System.out.print("Digite o valor da venda:\n");
                                vendas[id][DIA] += input.nextDouble();
                                System.out.println("Venda registrada com sucesso");
                            }
                            else
                            {
                                for (i = 1; i<=DIA; i++)
                                {
                                    System.out.printf("DIA : %d\n" +
                                            "Valor total: %f\n", i, vendas[id][i]);
                                }
                            }
                            saveState_UNDO();
                            break;
                        case 2:
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
                                System.out.printf("Você trabalhou %d horas..\n", i);

                                if(cont > 8 && types[id] == 1)
                                {
                                    fundo[id] = salary[id] + (cont - 8)*1.5*salary[id];
                                }
                            }
                            System.out.println("Horário registrado!");
                            saveState_UNDO();
                            break;
                        case 3:
                            System.out.println("\n------------------------------\n");
                            System.out.print("Digite seu ID:\n");
                            id = input.nextInt();
                            System.out.println("Deseja uma agenda:\n" +
                                    "1. Mensal\n" +
                                    "2. Semanal\n");
                            if(input.nextInt() == 1)
                            {
                                System.out.println("\n------------------------------\n");
                                System.out.print("Dias disponíveis para as agendas mensais:\n");
                                for (i = 1; i<32; i++)
                                {
                                    if(daysM[i] == 1)
                                    {
                                        System.out.printf("Mensal %d\n", i);
                                    }
                                }
                                System.out.println("\n------------------------------\n");
                                System.out.println("\nQual dia da lista você deseja?\n");
                                input.nextLine();
                                String day = input.nextLine();
                                if(day.intern() == "$")
                                    payday[id] = 30;
                                else
                                    payday[id] = Integer.valueOf(day);
                                frequence[id] = 0;
                                paymentWeekDay[id] = 0;
                                SM[id] = 1;
                                System.out.println("\nDia alterado com sucesso!\n");
                            }
                            else
                            {
                                int j;
                                for (i = 0; i < 8; i ++)
                                {
                                    for (j=0; j<4; j++)
                                    {
                                        if(semanal[i][j] == 1)
                                        {
                                            System.out.printf("Semanal %d ", j);
                                            switch (i){
                                                case 1: System.out.print("Domingo\n"); break;
                                                case 2: System.out.print("Segunda\n"); break;
                                                case 3: System.out.print("Terça\n"); break;
                                                case 4: System.out.print("Quarta\n"); break;
                                                case 5: System.out.print("Quinta\n"); break;
                                                case 6: System.out.print("Sexta\n"); break;
                                                case 7: System.out.print("Sábado\n"); break;
                                                default: break;
                                            }

                                        }
                                    }
                                }
                            }
                            break;
                        default:
                            System.out.print("Digite novamente..\n");
                            break;
                    }
                }
            }
            else
                break;
        }
    }

    private static void saveState_UNDO()
    {
        int i,j,k;
        for (i=0; i < 100; i++)
        {
            namesUN[i][state_cont] = names[i];
            adressUN[i][state_cont] = adress[i];
            typesUN[i][state_cont] = types[i];
            salaryUN[i][state_cont] = salary[i];
            SyndicatoUN[i][state_cont] = Syndicato[i];
            syndUN[i][state_cont] = synd[i];

            for(j = 0; j <32 ; j++) vendasUN[i][j][state_cont] = vendas[i][j];

            pontoUN[i][state_cont] = ponto[i];
            paymentMethodUN[i][state_cont] = paymentMethod[i];
            synIDUN[i][state_cont] = synID[i];
            paydayUN[i][state_cont] = payday[i];
            frequenceUN[i][state_cont] = frequence[i];
            paymentWeekDayUN[i][state_cont] = paymentWeekDay[i];
            SM_UN[i][state_cont] = SM[i];
            comPercentualUN[i][state_cont] = comPercentual[i];
            syndTaxUN[i][state_cont] = syndTax[i];
            fundoUN[i][state_cont] = fundo[i];
        }
        redo_cont = state_cont;
        state_cont = (state_cont+1)%1000;
    }

    private static void UNDO()
    {
        int i,j,k;
        redo_cont--;
        k = (redo_cont);
        if(k < 0) k+=1000;

        for (i=0; i < 100; i++)
        {
            names[i] = namesUN[i][k];
            adress[i] = adressUN[i][k];
            types[i] = typesUN[i][k];
            salary[i] = salaryUN[i][k];
            Syndicato[i] = SyndicatoUN[i][k];
            synd[i] = syndUN[i][k];

            for(j = 0; j <32 ; j++) vendas[i][j] = vendasUN[i][j][k];

            ponto[i] = pontoUN[i][k];
            paymentMethod[i] = paymentMethodUN[i][k];
            synID[i] = synIDUN[i][k];
            payday[i] = paydayUN[i][k];
            frequence[i] = frequenceUN[i][k];
            paymentWeekDay[i] = paymentWeekDayUN[i][k];
            SM[i] = SM_UN[i][k];
            comPercentual[i] = comPercentualUN[i][k];
            syndTax[i] = syndTaxUN[i][k];
            fundo[i] = fundoUN[i][k];
        }
    }

    private static  void REDO()
    {
        int i,j,k;
        if(redo_cont == state_cont) System.out.print("Não há mais operações a serem desfeitas..\n");
        else {
            redo_cont++;
            k = (redo_cont)%1000;

            for (i = 0; i < 100; i++)
            {
                names[i] = namesUN[i][k];
                adress[i] = adressUN[i][k];
                types[i] = typesUN[i][k];
                salary[i] = salaryUN[i][k];
                Syndicato[i] = SyndicatoUN[i][k];
                synd[i] = syndUN[i][k];

                for (j = 0; j < 32; j++) vendas[i][j] = vendasUN[i][j][k];

                ponto[i] = pontoUN[i][k];
                paymentMethod[i] = paymentMethodUN[i][k];
                synID[i] = synIDUN[i][k];
                payday[i] = paydayUN[i][k];
                frequence[i] = frequenceUN[i][k];
                paymentWeekDay[i] = paymentWeekDayUN[i][k];
                SM[i] = SM_UN[i][k];
                comPercentual[i] = comPercentualUN[i][k];
                syndTax[i] = syndTaxUN[i][k];
                fundo[i] = fundoUN[i][k];
            }
        }
    }

    private static void PrintEmployee(int i) {
        System.out.printf("Empregado de ID-[%d]:\n",i);
        System.out.printf("Nome: %s\n", names[i]);
        System.out.printf("Endereço: %s\n", adress[i]);
        System.out.print("Tipo: ");
        if(types[i] == 1)
            System.out.println("Horista");
        else if(types[i] == 2)
            System.out.println("Comissionado");
        else
            System.out.println("Assalariado");
        if(types[i] == 2)
            System.out.printf("Percentual de Comissão : %d\n", comPercentual[i]);
        System.out.printf("Salário: %f\n", salary[i]);
        System.out.printf("Filiação ao sindicato: %s\n", synd[i] ? "true" : "false");
        if(synd[i]) System.out.printf("ID no sindicato: %d\n", synID[i]);
        System.out.print("Método de pagamento: ");
        if(paymentMethod[i] == 1)
            System.out.println("Cheque pelos correios");
        else if(paymentMethod[i] == 2)
            System.out.println("Cheque em mãos");
        else
            System.out.println("Depósito em conta bancária");
        if(SM[i] == 1)
        {
            System.out.printf("Dia de pagamento: %d\n", payday[i]);
        }
        else
        {
            System.out.printf("Frequência: %d vezes por semana\n", frequence[i]);
            System.out.printf("No dia %d da semana\n", paymentWeekDay[i]);
        }
        System.out.printf("Salário total: %f\n", fundo[i]);
        System.out.println("------------------------------------");
    }

    private static void calcCom(int i)
    {
        if(types[i] == 2) {
            double comission = 0;
            int j;
            for (j = 1; j< 32; j++) comission += vendas[i][j];
            fundo[i] += (comission/100)*comPercentual[i];
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

        if(types[id] == 2)
        {
            System.out.println("Digite o percentual de comissão: ");
            comPercentual[id] = input.nextInt();
        }

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
            System.out.println("Pagamento 2 vezes por semana\n" +
                    "Dia de pagamento na sexta-feira.\n\n" +
                    "Pode ser alterado futuramente..\n");
        }
        else
        {
            payday[id] = 31;
            SM[id] = 1; // recebe mensalmente
            System.out.print("Pagamento 1 vez por mês\n" +
                    "Dia de pagamento no último dia do mês\n" +
                    "Dia: 31\n" +
                    "Pode ser alterado futuramente..\n");
        }

        System.out.print("Qual o método de pagamento deseja?\n" +
                "1. Cheque pelos correios\n" +
                "2. Cheque em mãos\n" +
                "3. Depósito em conta bancária\n");
        paymentMethod[id] = input.nextInt();
        input.nextLine();

        System.out.print("Qual o salário do empregado?\n");
        salary[id] = input.nextDouble();
        input.nextLine();

        if(types[id] == 3 || types[id] == 2) fundo[id] = salary[id];

        System.out.println("\n------------------------------\n");
        System.out.printf("\nID do empregado na empresa é: |%d|\n", id);

        System.out.print("\nEmpregado é filiado ao sindicato?\n" +
                "y - sim\n" +
                "n - não\n");

        if(input.nextLine().intern().equals("y"))
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
            System.out.print("A taxa sindical no valor de 10 reais será descontado do seu salário ao fim do mês\n");
            syndTax[id] = 10;
            System.out.printf("Seu ID no sindicato é: |%d|\n", synID[id]);
        }
        else
            synd[id] = false;

        System.out.print("Registrado com sucesso.\n");
        System.out.print("Lembre-se os ID's são muito importantes para acessar as informações guarde-o!!\n");
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
                    "4. Método de pagamento\n" +
                    "5. Participação no sindicato\n" +
                    "6. Taxa sindical\n" +
                    "7. ID sindicato\n");

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
                    System.out.print("Qual o novo método de pagamento que deseja?\n" +
                            "1. Cheque pelos correios\n" +
                            "2. Cheque em mãos\n" +
                            "3. Depósito na conta bancária\n");
                    paymentMethod[id] = input.nextInt();
                    System.out.println("Atualizado.\n");
                    break;
                case 5:
                    System.out.print("\nGostaria de participar do sindicato?\n" +
                            "y - sim\n" +
                            "n - não\n");
                    if(input.nextLine() == "y")
                        synd[id] = true;
                    else
                        synd[id] = false;
                    System.out.println("Atualizado.\n");
                    break;
                case 6:
                    System.out.println("Qual a nova taxa sindical?");
                    syndTax[id] = input.nextDouble();
                    break;
                case 7:
                    System.out.println("Buscaremos um novo ID para você");
                    int i = Syndicato[id];
                    for(i -= 1; i>= 0 ; i--)
                    {
                        if(Syndicato[i] == 0) break;
                    }
                    if(i >= 0) synID[id] = i;
                default:
                    System.out.println("Digite novamente o número.");
                    break;
            }
        }
    }
}
