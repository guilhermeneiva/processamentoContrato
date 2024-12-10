package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import entities.Contract;
import entities.Installment;
import services.ContractService;
import services.NubankService;
import services.PaypalService;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner scanner = new Scanner(System.in);

		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

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
}
