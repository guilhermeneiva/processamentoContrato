package application;

import java.util.Locale;

import entities.ProcessoConsole;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		ProcessoConsole.operacao();
	}
}
