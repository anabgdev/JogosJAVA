package jogos;

import java.util.Scanner;

public class JogoDaForca {

	public static void main(String[] args) {

		String palavra[] = { "c", "h", "a", "v", "e", "i", "r", "o"};

		Scanner sc = new Scanner(System.in);

		int forca = 1, vencer = 0, perder = 0, contador = 0;
		
		
		String digito, letrasDigitadas[] = new String[20];

		while (forca == 1) {
			int existePalavra = 0;
			System.out.println("Digite a letra:");
			digito = sc.next();
			letrasDigitadas[contador] = digito;

			for (int i = 0; i < letrasDigitadas.length; i++) {
				if (letrasDigitadas[i] != null) {
					if (letrasDigitadas[i].equals(digito)) {
						existePalavra++;
					}
				}
			}
			
	
			 
			if (!(existePalavra > 1)) {
				
				for (int x = 0; x < palavra.length; x++) {

					if (palavra[x].equals(digito)) {
						System.out.println("Letra correta.");
						vencer++;
						break;
					} else {
						if (x == 4) {
							System.out.println("Letra inexistente.");
							perder++;
						}
					}
				}
				if (perder == 5) {
					System.out.println("Você perdeu.");
					System.exit(0);
				} else if (vencer == 5) {
					System.out.println("Voce venceu.");
					System.exit(0);
				}
			} else {
				System.out.println("Essa letra já foi digitada.");
			}
			contador++;
		}
	}
}
