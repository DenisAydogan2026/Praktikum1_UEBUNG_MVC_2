package business;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FlughaefenModel {

	
	
	private Flughafen flughafen;

	public Flughafen getFlughafen() {
		return flughafen;
	}

	public void setFlughafen(Flughafen flughafen) {
		this.flughafen = flughafen;
	}
	
	
	
	public void leseAusDatei(String typ)  throws IOException{
    	 
      		if("csv".equals(typ)){
      			BufferedReader ein = new BufferedReader(new FileReader("Flughafen.csv"));
      			String[] zeile = ein.readLine().split(";");
      			this.flughafen = new Flughafen(zeile[0],
      				zeile[1],
      				Integer.parseInt(zeile[2]),
      				Integer.parseInt(zeile[3]),
      				zeile[4].split("_"));
      				ein.close();
      	  			
    
	}
}
	
	 public void schreibeFlughaefenInCsvDatei() throws IOException {
			 
				BufferedWriter aus
					= new BufferedWriter(new FileWriter("FlughaefenAusgabe.csv", true));
				aus.write(flughafen.gibFlughafenZurueck(';'));
				aus.close();
    	 
	}
	 
}
