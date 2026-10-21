package gui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import business.FlughaefenModel;
import business.Flughafen;
import javafx.stage.Stage;

public class FlughaefenControl {
	
	private FlughaefenModel flughaefenModel;
	private FlughaefenView	flughaefenView;
	
	public FlughaefenControl(Stage primaryStage) {
		
		
		this.flughaefenModel = new FlughaefenModel();
		this.flughaefenView = new FlughaefenView(this, primaryStage, 
				flughaefenModel);
	}
	
	
	 
	 	
	 public void nehmeFlughafenAuf() {
		 try {
			 
			 this.flughaefenModel.setFlughafen(new Flughafen(flughaefenView.getTxtName().getText(),flughaefenView.getTxtOrt().getText(),
					 Integer.parseInt(flughaefenView.getTxtAnzahlTerminals().getText()),
					 Integer.parseInt(flughaefenView.getTxtAnzahlStartbahnen().getText()),
					 flughaefenView.getTxtAirlines().getText().split(";")));
			 flughaefenView.zeigeInformationsfensterAn("Der Flughafen wurde aufgenommen!");
		 }
		 	catch(Exception exc){
			 		flughaefenView.zeigeFehlermeldungsfensterAn(exc.getMessage());
	     	}
		 
	 }
	 
	 
	 public void leseAusDatei(String typ){
	    	try {
	    		if("csv".equals(typ)) {
	    		flughaefenModel.leseAusDatei(typ);
	    		flughaefenView.zeigeInformationsfensterAn(
      	  	   			"Der Flughafen wurde gelesen!");
	    		}
	    		else{
		   			flughaefenView.zeigeInformationsfensterAn(
		   				"Noch nicht implementiert!");
		   		}
			}
			catch(IOException exc){
				flughaefenView.zeigeFehlermeldungsfensterAn(
					"IOException beim Lesen!");
			}
			catch(Exception exc){
				flughaefenView.zeigeFehlermeldungsfensterAn(
					"Unbekannter Fehler beim Lesen!");
			}
		}
	 
	 
	 public void schreibeFlughaefenInCsvDatei() {
			try {
				flughaefenModel.schreibeFlughaefenInCsvDatei();
				flughaefenView.zeigeInformationsfensterAn(
		   			"Die Flughaefen wurden gespeichert!");
			}
			catch(IOException exc){
				flughaefenView.zeigeFehlermeldungsfensterAn(
					"IOException beim Speichern!");
			}
			catch(Exception exc){
				flughaefenView.zeigeFehlermeldungsfensterAn(
					"Unbekannter Fehler beim Speichern!");
			}
		}
	

}
