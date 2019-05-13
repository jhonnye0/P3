import java.util.Calendar;
import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class Main
{
    public static Employee register(int id, Employee list[])
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Informe o dia atual:");
        int day = input.nextInt();
        System.out.println("E o valor da venda efetuada (lembre-se de separar casas decimais com vírgula):");
        float price = input.nextFloat();
        list[id].setDay(day, price);
        System.out.println("Venda registrada com sucesso..\n");
        return list[id];
    }

    public static Employee update(int id, Employee list[])
    {
        Scanner input = new Scanner(System.in);
        while(1 == 1)
        {
            System.out.println("Digite a qual informação que deseja atualizar:\n\n" +
                    "0. Retornar\n" +
                    "1. Nome\n" +
                    "2. Endereço\n" +
                    "3. Tipo\n" +
                    "4. Atributos\n" +
                    "5. Método de pagamento\n" +
                    "6. Participar do sindicato\n");

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
                    System.out.print("Tipo atual: ");
                    if(list[id].getType() == 1) System.out.print("Horista\n");
                    else if(list[id].getType() == 2) System.out.print("Comissionado\n");
                    else System.out.print("Assalariado\n");
                    System.out.println("Digite o novo tipo:\n" +
                            "1. Horista\n" +
                            "2. Comissionado\n" +
                            "3. Assalariado\n");
                    list[id].setType(input.nextInt());
                    System.out.println("Atualizado.\n");
                    break;
                case 4:
                    if(list[id].getType() == 1)
                    {
                        System.out.println("Gostaria de mudar o dia de pagamento da semana?\n" +
                                "y - sim\n" +
                                "n - não");
                        if(input.nextLine().intern() == "y")
                        {
                            System.out.println("Qual dia deseja?\n" +
                                    "1. domingo\n" +
                                    "2. segunda\n" +
                                    "3. terça\n" +
                                    "4. quarta\n" +
                                    "5. quinta\n" +
                                    "6. sexta\n" +
                                    "7. sabado\n");
                            int day = input.nextInt();
                            if(day > 0 && day < 8)
                                list[id].setPaymentweekday(day);
                        }
                    }
                    else if(list[id].getType() == 1)
                    {

                    }

                    break;
                case 5:
                    System.out.print("Qual o método de pagamento deseja? (O método pode ser alterado)\n" +
                            "1. Cheque pelos correios\n" +
                            "2. Cheque em mãos\n" +
                            "3. Depósito na conta bancária\n");
                    list[id].setPaymentMethod(input.nextInt());
                    System.out.println("Atualizado.\n");
                    break;
                case 6:
                    System.out.print("\nGostaria de participar do sindicato?\n" +
                            "y - sim\n" +
                            "n - não\n");
                    if(input.nextLine() == "y")
                        list[id].setSyndicate(true);
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
        int type = 0;
        while(1 == 1)
        {
            System.out.print("Tipo de empregado:\n" +
                    "1. Horista\n" +
                    "2. Comissionado\n" +
                    "3. Assalariado\n");
            type = input.nextInt();
            input.nextLine();
            if(type >= 1 && type <=3) break;
            else
                System.out.print("Digite novamente..\n\n");
        }
        int payday = -1;
        int frequence = -1;
        int paymentweekday = -1;
        if(type == 1)
        {
            payday = -1;
            frequence = 1;
            paymentweekday = 6;
            System.out.println("Atributos default:\n" +
                    "Pagamento 1 vez por semana\n" +
                    "Dia de pagamento na sexta-feira\n\n" +
                    "Pode ser alterado futuramente..");
        }
        else if(type == 2)
        {
            payday = -1;
            frequence = 2;
            paymentweekday = 6;
            System.out.println("Atributos default:\n" +
                    "Pagamento 1 vez a cada 2 semanas\n" +
                    "Dia de pagamento na sexta-feira\n\n" +
                    "Pode ser alterado futuramente..");
        }
        else
        {
            Date data = new Date(System.currentTimeMillis());
            SimpleDateFormat format = new SimpleDateFormat("MM");
            if(format.format(data).intern() == "02")
                payday = 28;
            else if(format.format(data).intern() == "04" || format.format(data).intern() == "06" || format.format(data).intern() == "09" || format.format(data).intern() == "11")
                payday = 30;
            else
                payday = 31;
            System.out.printf("\nAtributos default:\n\n" +
                    "Pagamento 1 vez por mês\n" +
                    "Dia de pagamento no último dia do mês\n" +
                    "Dia: %d\n" +
                    "Pode ser alterado futuramente..\n\n", payday);
        }
        int pMethod = 0;
        while(1 == 1)
        {
            System.out.print("Qual o método de pagamento deseja? (O método pode ser alterado)\n" +
                    "1. Cheque pelos correios\n" +
                    "2. Cheque em mãos\n" +
                    "3. Depósito na conta bancária\n");
            pMethod = input.nextInt();
            if(pMethod >=1 && pMethod <= 3) break;
            else
                System.out.print("Digite novamente..\n\n");

        }

        Employee newEmp = new Employee(name, address, type, payday, frequence, paymentweekday, pMethod,false, id);
        return newEmp;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String dateStart = "01/10/2019 10:00:00";
        String dateStop = "01/10/2019 19:40:00";

        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

        Date d1 = null;
        Date d2 = null;

        try
        {
            d1 = format.parse(dateStart);
            d2 = format.parse(dateStop);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        long diff = d2.getTime() - d1.getTime();
        //System.out.println(diff / (60 * 60 * 1000) % 24);

        Employee[] list = new Employee[100];
        int[] Syndicate= new int[100];

        while(1 == 1)
        {
            System.out.println("\n\n--------------------------------------------\n");
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
                        if(input.nextLine().intern() == "y")
                        {
                            int synID = -1;
                            list[id].setSyndicate(true);
                            for(i = 0; i<100; i++)
                            {
                                if (Syndicate[i] == 0) break;
                            }
                            list[id].setSynID(i);
                            System.out.printf("Seu ID no sindicato é: |%d|\n", list[id].getSynID());
                        }

                        System.out.printf("Seu ID na empresa é: |%d|\n", id);
                        System.out.println("\nRegistrado com sucesso.\n");
                        System.out.print("Lembre-se o seu ID é muito importante para acessar suas informações guarde-o!!\n");
                    }
                    else
                        System.out.println("A empresa atingiu o limite máximo de empregados!\n\n");
                    break;
                case 2:
                    System.out.println("Digite o ID do empregado que deseja remover:\n");
                    id = input.nextInt();
                    list[id] = null;
                    System.out.printf("Empregado de ID |%d| foi removido com sucesso.\n", id);
                    break;
                case 3:
                    System.out.println("Digite seu ID:\n");
                    break;
                case 4:
                    System.out.println("Informe seu ID:\n");
                    id = input.nextInt();
                    if(list[id] != null)
                        list[id] = register(id, list);
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
