import java.util.Scanner; // Import necessario per Input
import java.util.Random; // Import necessario per Random
import java.util.InputMismatchException; // Import necessario per InputMismatchException

public class banca {

	public static void menuPrinc() {

		System.out.println("\nBANCA");
		System.out.println("1. Preleva dalla banca");
		System.out.println("2. Deposita in banca");
		System.out.println("3. Investi soldi del tuo conto bancario");
		System.out.println("4. Mese successivo");
		System.out.println("5. Vai a fine investimento");
		System.out.println();

		System.out.print("Fai la tua scelta: ");

	}

	public static boolean controlNumberDouble(String s) {
		double scelta;

		try {
			scelta = Double.parseDouble(s);
		} catch (NumberFormatException e) {
			System.out.println("In valore inserito non e' numerico decimale!");
			return false;
		}

		return true;
	}

	public static boolean controlNumberInt(String s) {
		int scelta;

		try {
			scelta = Integer.parseInt(s);
		} catch (NumberFormatException e) {
			System.out.println("In valore inserito non e' numerico intero!");
			return false;
		}

		return true;
	}

	public static void main(String[] args) {
		Scanner tastiera = new Scanner(System.in);
		Random random = new Random();

		// Scanner per break
		Scanner sc = new Scanner(System.in);

		boolean continua = true; // variabile per fare un ciclo infinito

		int mese = 1, anno = 2000, scelta = -1;
		double banca = 0.0, portafoglio = 100.0;
		boolean noCash = false;

		// variabili x investimento
		int mesiInvestimento = 0;
		double saldoFinale = 0.0, variazione = 0.0; // Questa variabile conterrà la variazione (guadagno o perdita)
		boolean durataInvestimento = false, haGuadagnato = false, rosso = false;

		while (continua) {

			if (mese == 13) {
				mese = 1;
				anno++;
			} else {
				if (mese > 13) {
					do {
						mese -= 12;
						anno++;
					} while (mese > 12);
				}
			}

			do {
				boolean controllo;

				do {

					if (mesiInvestimento == 0 && durataInvestimento) {

						// saldo finale
						if (haGuadagnato) {
							System.out.println("Hai guadagnato: " + variazione + " euro ");

						} else {
							System.out.println("Hai perso: " + variazione + " euro ");

						}

						if (!rosso) {
							banca += saldoFinale;
						} else {
							banca -= saldoFinale;
						}

						// Se si va in debito con la banca
						if (banca < 0) {
							System.out.println(
									"Attenzione: il tuo conto è in rosso! \n Per poter fare altre transazioni o investimenti devi coprire il debito");
						}

						durataInvestimento = false;

						System.out.print("Premi invio per continuare. ");
						sc.nextLine();
					}

					banca = Math.round(banca * 100.0) / 100.0; // tronco sempre a due cifre dopo la virgola

					System.out.println("\nData: " + mese + "/" + anno);
					System.out.println("Soldi in banca: " + banca + "€");
					System.out.println("Soldi nel portafoglio: " + portafoglio + "€");
					menuPrinc();
					String sceltaS = tastiera.nextLine();

					controllo = controlNumberInt(sceltaS);

					if (controllo) {
						scelta = Integer.parseInt(sceltaS);
						if (scelta < 1 || scelta > 5) {
							System.out.println("La scelta inserita non e' presente nell'elenco!");
							controllo = false;
						}
					}

				} while (!controllo);

				if (scelta == 4) {
					noCash = true;
				} else {
					if (scelta == 5 && banca >= 0) {
						noCash = true;
					} else {
						if (scelta != 2 && banca <= 0) {
							noCash = false;
							System.out.println(
									"Per fare questa operazione devi prima inserire soldi in banca o saldare i debiti!");
							System.out.print("Premi invio per continuare. ");
							sc.nextLine();
							System.out.println();
						} else {
							noCash = true;
						}
					}

				}

			} while (!noCash);

			System.out.println();

			switch (scelta) {

			case 1: {
				boolean controllo;
				do {

					System.out.print("Inserisci l'importo da prelevare: ");
					String sPrelevare = tastiera.nextLine();

					controllo = controlNumberDouble(sPrelevare);

					if (controllo) {
						double preleva = Double.parseDouble(sPrelevare);

						if (preleva > banca || preleva < 0) {
							System.out.println(
									"Non puoi prelevare piu' soldi di quelli che hai nel conto o in negativo!");
							controllo = false;
						} else {
							portafoglio += preleva;
							banca -= preleva;
						}
					}

				} while (!controllo);

				System.out.print("Premi invio per continuare. ");
				sc.nextLine();
				break;
			}

			case 2: {
				boolean controllo;
				do {

					System.out.print("Inserisci l'importo da depositare: ");
					String sDepositare = tastiera.nextLine();

					controllo = controlNumberDouble(sDepositare);

					if (controllo) {
						double deposita = Double.parseDouble(sDepositare);

						if (deposita > portafoglio || deposita < 0) {
							System.out.println(
									"Non puoi depositare piu' soldi di quelli che hai nel portafoglio o in negativo!");
							controllo = false;
						} else {
							banca += deposita;
							portafoglio -= deposita;
						}
					}

				} while (!controllo);

				System.out.print("Premi invio per continuare. ");
				sc.nextLine();
				break;
			}

			case 3: {

				if (durataInvestimento) {

					System.out.println("Stai gia' facendo un investimento");

				} else {

					System.out.println("1. Breve durata (fino a 12 mesi)");
					System.out.println("2. Media durata (13 mesi - 5 anni)");
					System.out.println("3. Lunga durata (oltre 5 anni)");

					boolean valido = false;
					double importoInvestito = 0;

					do {

						try {
							System.out.print("Inserisci l'importo che vuoi investire: ");
							importoInvestito = tastiera.nextDouble();

							if (importoInvestito > banca) {
								System.out.println("Non hai abbastanza fondi per questo investimento.");
								return;
							}

							if (importoInvestito <= 0 || importoInvestito > banca) {
								System.out.println(
										"Errore: Inserisci un importo valido (maggiore di 0 e minore del saldo in banca).");
							} else {
								valido = true; // Se tutto è valido, esci dal ciclo
							}

						} catch (InputMismatchException e) {
							System.out.println(
									"Errore: hai inserito un valore non valido. Inserisci un numero intero per il periodo di investimento e un numero decimale per l'importo.");
							tastiera.nextLine(); // Pulisce il buffer , questo mi permette di evitare loop infiniti cosi
													// mi elimina l'errore che mi blocca di andare avanti
						}

					} while (!valido);

					valido = false; // Reimposta la variabile

					do {
						try {
							System.out.print("Inserisci il periodo di investimento (in mesi): ");
							mesiInvestimento = tastiera.nextInt();

							if (mesiInvestimento <= 0) {
								System.out.println("Errore: Inserisci un valore valido (maggiore di 0).");
							} else {
								valido = true; // Se il valore è valido, esci dal ciclo
							}

						} catch (InputMismatchException e) {
							System.out.println(
									"Errore: hai inserito un valore non valido. Inserisci un numero intero per i mesi.");
							tastiera.nextLine(); // Pulisce il buffer
						}
					} while (!valido);

					durataInvestimento = true;

					// Sottraiamo l'importo investito dal saldo iniziale
					banca -= importoInvestito;

					// Determina il tipo di investimento in base al periodo
					String tipoInvestimento;
					if (mesiInvestimento <= 12) {
						tipoInvestimento = "Breve";
					} else if (mesiInvestimento <= 60) {
						tipoInvestimento = "Medio";
					} else {
						tipoInvestimento = "Lungo";
					}

					// Simulazione del rischio
					double percentualeGuadagno = 0;
					double percentualePerdita = 0;
					haGuadagnato = false;

					int esito = random.nextInt(100) + 1; // Numero casuale da 1 a 100

					switch (tipoInvestimento) {
					case "Breve": {
						if (esito <= 80) {
							haGuadagnato = true;
							percentualeGuadagno = random.nextDouble() * 20; // Guadagno fino al 20%
						} else {
							percentualePerdita = random.nextDouble() * 15; // Perdita fino al 15%
						}
						break;
					}

					case "Medio": {
						if (esito <= 60) {
							haGuadagnato = true;
							percentualeGuadagno = random.nextDouble() * 50; // Guadagno fino al 50%
						} else {
							percentualePerdita = random.nextDouble() * 80; // Perdita fino al 80%
						}
						break;
					}

					case "Lungo": {
						if (esito <= 35) {
							haGuadagnato = true;
							percentualeGuadagno = random.nextDouble() * 80; // Guadagno fino al 80%
						} else {
							percentualePerdita = random.nextDouble() * 120; // Perdita fino al 120%
						}
						break;
					}
					}

					// Calcola il risultato dell'investimento

					if (haGuadagnato) {
						variazione = importoInvestito * (percentualeGuadagno / 100);
					} else {
						variazione = importoInvestito * (percentualePerdita / 100);
					}

					variazione = Math.round(variazione * 100.0) / 100.0;

					if (haGuadagnato) {
						saldoFinale = importoInvestito + variazione;
					} else {
						if (variazione <= importoInvestito) {
							rosso = false;
							saldoFinale = importoInvestito - variazione;
						} else {
							rosso = true;
							saldoFinale = variazione - importoInvestito;
						}
					}

					System.out.print("Premi invio per continuare. ");
					sc.nextLine();
					break;

				}
			}

			case 4: {
				mese++;
				portafoglio += 100;
				if (durataInvestimento) {
					mesiInvestimento--;
				}

				System.out.print("Premi invio per continuare. ");
				sc.nextLine();
				break;
			}

			case 5: {
				if (durataInvestimento) {
					mese += mesiInvestimento;
					portafoglio += (100 * mesiInvestimento);
					mesiInvestimento = 0;
				} else {
					System.out.println("Per fare questa operazione devi prima fare un investimento!");
				}

				System.out.print("Premi invio per continuare. ");
				sc.nextLine();
				break;
			}

			}

		}

	}

}
