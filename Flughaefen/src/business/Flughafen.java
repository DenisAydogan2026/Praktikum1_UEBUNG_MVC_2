package business;

public class Flughafen {

    private String name;
    private String ort;
    private int anzahlTerminals;
    private int anzahlStartbahnen;
    private String[] airlines;

    public Flughafen(String name, String ort, int anzahlTerminals,
       	int anzahlStartbahnen, String[] airlines){
    	this.name = name;
      	this.ort = ort;
       	this.anzahlTerminals = anzahlTerminals;
       	this.anzahlStartbahnen = anzahlStartbahnen;
       	this.airlines = airlines;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrt() {
		return ort;
	}

	public void setOrt(String ort) {
		this.ort = ort;
	}

	public int getAnzahlTerminals() {
		return anzahlTerminals;
	}

	public void setAnzahlTerminals(int anzahlTerminals) {
		this.anzahlTerminals = anzahlTerminals;
	}

	public int getAnzahlStartbahnen() {
		return anzahlStartbahnen;
	}

	public void setAnzahlStartbahnen(int anzahlStartbahnen) {
		this.anzahlStartbahnen = anzahlStartbahnen;
	}

	public String[] getAirlines() {
		return airlines;
	}

	public void setAirlines(String[] airlines) {
		this.airlines = airlines;
	}

 	public String getAirlinesAlsString(char trenner) {
		String ergebnis = "";
		int i = 0;
		for(i = 0; i < this.getAirlines().length - 1; i++) {
			ergebnis = ergebnis + this.getAirlines()[i] + trenner;
		}
		return ergebnis	+ this.getAirlines()[i];
	}

	public String gibFlughafenZurueck(char trenner){
  		return this.getName() + trenner
  			+ this.getOrt() + trenner
  			+ this.getAnzahlTerminals() + trenner
  		    + this.getAnzahlStartbahnen() + trenner + "\n"
  		    + this.getAirlinesAlsString(trenner) + "\n";
  	}
}
