package entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import services.ContractService;
import services.NubankService;
import services.OnlinePaymentService;
import services.PaypalService;


public class ProcessoConsole {

	private static Scanner scanner = new Scanner(System.in);

	public static void operacao() {
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		System.out.println("Qual será o serviço escolhido?");


		if (escolhaServico() == false) {
			return;
		}

		System.out.println("Entre com os dados do contrato:");
		System.out.print("Número: ");
		int number = scanner.nextInt();
		System.out.print("Data (dd/MM/yyyy): ");
		LocalDate date = LocalDate.parse(scanner.next(), fmt);
		System.out.print("Valor do contrato: ");
		Double valueTotal = scanner.nextDouble();

		Contract obj = new Contract(number, date, valueTotal);

		System.out.print("Entre com o número de parcelas: ");
		int n = scanner.nextInt();


		ContractService contractService = new ContractService(new NubankService());

		contractService.processContract(obj, n);

		System.out.println("Parcelas: ");
		for(Installment installment : obj.getInstallments()) {
			System.out.println(installment);	
		}

	}

	public static boolean escolhaServico() {
		OnlinePaymentService onlinePaymentService = null;

		System.out.println("\nQual serviço de pagamento será utilizado?");
		System.out.println("1 - Nubank");
		System.out.println("2 - PayPal");

		int numeroServico = scanner.nextInt();

		switch (numeroServico) {
		case 1:
			onlinePaymentService = new NubankService();
			System.out.println("Serviço Nubank selecionado.");
			break;
		case 2:
			onlinePaymentService = new PaypalService();
			System.out.println("Serviço PayPal selecionado.");
			break;
		default:
			System.err.println("Opção de serviço inválida!");
			return false;
		}
		return true;
	}

}
