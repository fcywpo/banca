import java.util.Scanner;
import java.util.Random;

public class banca {
	
	public static void menuPrinc () {
		
		System.out.println("\nBANCA");
		System.out.println( "1. Preleva dalla banca" );
		System.out.println( "2. Deposita in banca" );
		System.out.println( "3. Investi soldi del tuo conto bancario" );
		System.out.println( "4. Mese successivo" );
		System.out.println( "5. Vai a fine investimento" );
	    System.out.println();
	    
	    System.out.print( "Fai la tua scelta: " );
		
	}
	
	public static boolean controlNumber( String s ) {
		double scelta;
		
		try {	
			scelta = Double.parseDouble(s);
		} catch (NumberFormatException e) {
			System.out.println("In valore inserito non e' numerico!");
			return false;
		}
		
		return true;
	}
	

	public static void main(String[] args) {
		Scanner tastiera = new Scanner (System.in);
		Random random = new Random();
		
		//Scanner per break
		Scanner sc = new Scanner(System.in);
		
		boolean continua = true; //variabile per fare un ciclo infinito
		
		
		int mese=1, anno = 2000, scelta=-1;
		double banca=0.0, portafoglio=0.0;
		boolean noCash = false;
		
		//variabili x investimento
		int nextmesi=0;
		double saldoFinale;
		boolean durataInvestimento=false;
		
		
		while (continua){
			
			if ( mese == 13 ) {
				mese = 1;
				anno++;
				portafoglio += 100;
			} else {
				if ( mese > 13 ) {
					do {
						mese -= 12;
						anno++;
						portafoglio += 100;
					} while (mese > 12);
				} else {
					portafoglio += 100;
				}
			}
			
			do{
	            boolean controllo;
	            
	            do {
	            	
	    			System.out.println("\nData: " + mese + "/" + anno);
	    			System.out.println("Soldi in banca: " + banca + "€");
	    			System.out.println("Soldi nel portafoglio: " + portafoglio + "€");
	    			menuPrinc();
	    			String sceltaS = tastiera.nextLine();
	    			
	    			controllo = controlNumber(sceltaS);
	    				
	    			if (controllo) {
	    				scelta = Integer.parseInt(sceltaS);
	    				if (scelta < 1 || scelta > 5) {
	    					System.out.println("La scelta inserita non e' presente nell'elenco!");
	    					controllo = false;
	    				}
	    			}
	    			
	    		} while (!controllo);
	             
	            
	            if ( !noCash && (scelta == 2 || scelta == 4)){
	            	noCash = true;
	            }
	            
	            if ( (scelta != 2 || scelta != 4) && banca <= 0 ){
	            	noCash = false;
	            	System.out.println ( "Per fare questa operazione devi prima inserire soldi in banca!" );
	            	System.out.print("Premi invio per continuare. ");
					sc.nextLine();
	            	System.out.println();
	            }
	            
	        } while ( !noCash );
			
			System.out.println();
			
			switch (scelta) {
			
			case 1:{
				boolean controllo;
				do {
					
					System.out.println("Inserisci l'importo da prelevare");
	    			String sPrelevare = tastiera.nextLine();
					
	    			controllo = controlNumber(sPrelevare);
	    			
	    			if (controllo) {
	    				double preleva = Double.parseDouble(sPrelevare);
	    				
	    				if ( preleva > banca || preleva < 0 ) {
	    					System.out.println("Non puoi prelevare piu' soldi di quelli che hai nel conto o in negativo!");
	    				} else {
	    					portafoglio += preleva;
	    					banca -= preleva;
	    				}
	    			}
	    			
				}while (!controllo);
    			
				System.out.print("Premi invio per continuare. ");
				sc.nextLine();
				break;
			}
			
			case 2:{
				boolean controllo;
				do {
					
					System.out.println("Inserisci l'importo da depositare");
	    			String sDepositare = tastiera.nextLine();
					
	    			controllo = controlNumber(sDepositare);
	    			
	    			if (controllo) {
	    				double deposita = Double.parseDouble(sDepositare);
	    				
	    				if ( deposita > portafoglio || deposita < 0 ) {
	    					System.out.println("Non puoi depositare piu' soldi di quelli che hai nel portafoglio o in negativo!");
	    				} else {
	    					banca += deposita;
	    					portafoglio -= deposita;
	    				}
	    			}
	    			
				}while (!controllo);
    			
				System.out.print("Premi invio per continuare. ");
				sc.nextLine();
				break;
			}

			case 3:{
				
				//trycatch fare
				
					if(durataInvestimento) {
						
						 System.out.println("Stai gia' facendo un investimento");
						 
					}else{

			        System.out.println("1. Breve durata (fino a 12 mesi)");
			        System.out.println("2. Media durata (13 mesi - 5 anni)");
			        System.out.println("3. Lunga durata (oltre 5 anni)");
			        
			        System.out.print("Inserisci l'importo che vuoi investire: ");
			        double importoInvestito = tastiera.nextDouble();

			        if (importoInvestito > banca) {
			            System.out.println("Non hai abbastanza fondi per questo investimento.");
			            return;
			        }
			        
			        System.out.print("Inserisci il periodo di investimento (in mesi): ");
			        int mesiInvestimento = tastiera.nextInt();
			          
			        durataInvestimento=true;
			        
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
			        boolean haGuadagnato = false;

			        int esito = random.nextInt(100) + 1; // Numero casuale da 1 a 100
			       
			        switch (tipoInvestimento) {
			            case "Breve":
			                if (esito <= 80) {
			                    haGuadagnato = true;
			                    percentualeGuadagno = random.nextDouble() * 10; // Guadagno fino al 10%
			                } else {
			                    percentualePerdita = random.nextDouble() * 10; // Perdita fino al 10%
			                }
			                break;

			            case "Medio":
			                if (esito <= 60) {
			                    haGuadagnato = true;
			                    percentualeGuadagno = random.nextDouble() * 20; // Guadagno fino al 20%
			                } else {
			                    percentualePerdita = random.nextDouble() * 20; // Perdita fino al 20%
			                }
			                break;

			            case "Lungo":
			                if (esito <= 50) {
			                    haGuadagnato = true;
			                    percentualeGuadagno = random.nextDouble() * 30; // Guadagno fino al 30%
			                } else {
			                    percentualePerdita = random.nextDouble() * 120; // Perdita fino al 120%
			                }
			                break;
			        }
			        
			     // Calcola il risultato dell'investimento
			     
			        double variazione; // Questa variabile conterrà la variazione (guadagno o perdita)

			        if (haGuadagnato) {
			            variazione = importoInvestito * (percentualeGuadagno / 100);
			        } else {
			            variazione = importoInvestito * (percentualePerdita / 100);
			        }

			        
			        //da qui
			        if(nextmesi>=mesiInvestimento) {
			        	
			        	 //saldo finale  
				        if (haGuadagnato) {
				            saldoFinale = importoInvestito + variazione;
				            saldoFinale =+ banca;
				            System.out.println("Hai guadagnato : " + saldoFinale + " euro ");
				        } else {
				            saldoFinale = importoInvestito - variazione;
				            saldoFinale =- banca;
				            System.out.println("Hai perso  : " + saldoFinale + " euro ");
				        }
			        	
			        	
			        	//Se si va in debito con la banca
			        	if (banca < 0) {
		                	System.out.println("Attenzione: il tuo conto è in rosso! /n Per poter fare altre transazioni o investimenti devi coprire il debito");
		            	}
			        	
			        	durataInvestimento=false;
			        	nextmesi=0;
			        	
			        }//a qui copia
			        
					}
			        
				System.out.print("Premi invio per continuare. ");
				sc.nextLine();
				break;
			}
			
			case 4:{
				mese++;
				nextmesi++;
				
				System.out.print("Premi invio per continuare. ");
				sc.nextLine();
				break;
			}
			
			case 5:{
				
				System.out.print("Premi invio per continuare. ");
				sc.nextLine();
				break;
			}
			
			}
			
		}
		
	}

	
	// test pop
}
