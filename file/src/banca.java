import java.util.Scanner;

public class banca {
	
	public static boolean calendario( int giorno, int mese, int anno ){
		if (giorno >= 32) {
			return false;
		} else {
			if (giorno >= 31 && ( mese == 4 || mese == 6  || mese == 9 || mese == 11)) {
				return false;
			} else {
				if ( anno%4==0) {
					if (giorno >= 30 && mese == 2) {
						return false;
					}
				} else {
					if (giorno >= 29 && mese == 2) {
						return false;
					}
				}
			}
		}
		return true;
	}
	
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
		
		int giorno=0, mese=1, anno = 2000, scelta=-1;
		double banca=0.0, portafoglio=100.0;
		boolean noCash = false;
		
		//for ( int i=0; i<370 ; i++) {
			giorno++;
			
			if (giorno == 32 && mese == 12 ) {
				giorno = 1;
				mese = 1;
				anno++;
				portafoglio += 100;
			} else {
				if ( !calendario(giorno, mese, anno) ) {
					giorno = 1;
					mese++;
					portafoglio += 100;
				}
			}
			
			do{
	            boolean controllo;
	            
	            do {
	            	
	    			System.out.println("\nData: " + giorno + "/" + mese + "/" + anno);
	    			System.out.println("Soldi in banca: " + banca + "€");
	    			System.out.println("Soldi nel portafoglio: " + portafoglio + "€");
	    			menuPrinc();
	    			String sceltaS = tastiera.nextLine();
	    			
	    			controllo = controlNumber(sceltaS);
	    				
	    			if (controllo) {
	    				scelta = Integer.parseInt(sceltaS);
	    				if (scelta < 1 || scelta > 3) {
	    					System.out.println("La scelta inserita non e' presente nell'elenco!");
	    					controllo = false;
	    				}
	    			}
	    				
	    		} while (!controllo);
	             
	            
	            if ( !noCash && scelta == 2 ){
	            	noCash = true;
	            }
	            
	            if ( scelta != 2 && banca == 0 ){
	            	noCash = false;
	            	System.out.println ( "Per fare questa operazione devi prima inserire soldi in banca!" );
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
	    				
	    				if ( deposita > banca || deposita < 0 ) {
	    					System.out.println("Non puoi depositare piu' soldi di quelli che hai nel portafoglio o in negativo!");
	    				} else {
	    					banca += deposita;
	    					portafoglio -= deposita;
	    				}
	    			}
	    			
				}while (!controllo);
    			
				break;
			}

			case 3:{
	
				break;
			}
			
			}
			
		}
		
	//}

}
