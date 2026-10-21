package gui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import business.Flughafen;
import javafx.event.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import ownUtil.*;

public class FlughaefenAnwendungssystem {

    //---Anfang Attribute der grafischen Oberflaeche---
    private Pane pane     					= new  Pane();
    private Label lblEingabe    	 		= new Label("Eingabe");
    private Label lblAnzeige   	 	    	= new Label("Anzeige");
    private Label lblName 					= new Label("Name:");
    private Label lblOrt   		 			= new Label("Ort:");
    private Label lblAnzahlTerminals  	 	= new Label("Anzahl Terminals:");
    private Label lblAnzahlStartbahnen   	= new Label("Anzahl Startbahnen:");
    private Label lblAirlines  				= new Label("Airlines:");
    private TextField txtName 	 			= new TextField();
    private TextField txtOrt				= new TextField();
    private TextField txtAnzahlTerminals	= new TextField();
    private TextField txtAnzahlStartbahnen	= new TextField();
    private TextField txtAirlines	 		= new TextField();
    private TextArea txtAnzeige  			= new TextArea();
    private Button btnEingabe 		 		= new Button("Eingabe");
    private Button btnAnzeige 		 		= new Button("Anzeige");
    private MenuBar mnbrMenuLeiste  		= new MenuBar();
    private Menu mnDatei             		= new Menu("Datei");
    private MenuItem mnItmCsvImport 		= new MenuItem("csv-Import");
    private MenuItem mnItmTxtImport 		= new MenuItem("txt-Import");
    private MenuItem mnItmCsvExport 		= new MenuItem("csv-Export");
    //-------Ende Attribute der grafischen Oberflaeche-------

    // speichert temporaer ein Objekt vom Typ Flughafen
    private Flughafen flughafen;

    public FlughaefenAnwendungssystem(Stage primaryStage){
    	Scene scene = new Scene(this.pane, 700, 340);
    	primaryStage.setScene(scene);
    	primaryStage.setTitle("Verwaltung von Flughaefen");
    	primaryStage.show();
    	this.initKomponenten();
		this.initListener();
    }

    private void initKomponenten(){
       	// Labels
    	lblEingabe.setLayoutX(20);
    	lblEingabe.setLayoutY(40);
    	Font font = new Font("Arial", 24);
    	lblEingabe.setFont(font);
    	lblEingabe.setStyle("-fx-font-weight: bold;");
    	lblAnzeige.setLayoutX(400);
    	lblAnzeige.setLayoutY(40);
      	lblAnzeige.setFont(font);
       	lblAnzeige.setStyle("-fx-font-weight: bold;");
       	lblName.setLayoutX(20);
    	lblName.setLayoutY(90);
    	lblOrt.setLayoutX(20);
    	lblOrt.setLayoutY(130);
    	lblAnzahlTerminals.setLayoutX(20);
    	lblAnzahlTerminals.setLayoutY(170);
    	lblAnzahlStartbahnen.setLayoutX(20);
    	lblAnzahlStartbahnen.setLayoutY(210);
    	lblAirlines.setLayoutX(20);
    	lblAirlines.setLayoutY(250);
       	pane.getChildren().addAll(lblEingabe, lblAnzeige,
       		lblName, lblOrt, lblAnzahlTerminals,
       		lblAnzahlStartbahnen, lblAirlines);

    	// Textfelder
     	txtName.setLayoutX(170);
    	txtName.setLayoutY(90);
    	txtName.setPrefWidth(200);
    	txtOrt.setLayoutX(170);
    	txtOrt.setLayoutY(130);
    	txtOrt.setPrefWidth(200);
    	txtAnzahlTerminals.setLayoutX(170);
    	txtAnzahlTerminals.setLayoutY(170);
    	txtAnzahlTerminals.setPrefWidth(200);
      	txtAnzahlStartbahnen.setLayoutX(170);
    	txtAnzahlStartbahnen.setLayoutY(210);
    	txtAnzahlStartbahnen.setPrefWidth(200);
    	txtAirlines.setLayoutX(170);
    	txtAirlines.setLayoutY(250);
    	txtAirlines.setPrefWidth(200);
      	pane.getChildren().addAll(
     		txtName, txtOrt, txtAnzahlTerminals,
     		txtAnzahlStartbahnen, txtAirlines);

        // Textbereich
        txtAnzeige.setEditable(false);
     	txtAnzeige.setLayoutX(400);
    	txtAnzeige.setLayoutY(90);
     	txtAnzeige.setPrefWidth(270);
    	txtAnzeige.setPrefHeight(185);
       	pane.getChildren().add(txtAnzeige);

        // Buttons
        btnEingabe.setLayoutX(20);
        btnEingabe.setLayoutY(290);
        btnAnzeige.setLayoutX(400);
        btnAnzeige.setLayoutY(290);
        pane.getChildren().addAll(btnEingabe, btnAnzeige);

 		// Menue
  	    this.mnbrMenuLeiste.getMenus().add(mnDatei);
  	    this.mnDatei.getItems().add(mnItmCsvImport);
  	    this.mnDatei.getItems().add(mnItmTxtImport);
  	    this.mnDatei.getItems().add(new SeparatorMenuItem());
  	    this.mnDatei.getItems().add(mnItmCsvExport);
 	    pane.getChildren().add(mnbrMenuLeiste);
   }

   private void initListener() {
	    btnEingabe.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
        	    nehmeFlughafenAuf();
            }
	    });
	    btnAnzeige.setOnAction(new EventHandler<ActionEvent>() {
	    	@Override
	        public void handle(ActionEvent e) {
	            zeigeFlughaefenAn();
	        }
   	    });
	    mnItmCsvImport.setOnAction(new EventHandler<ActionEvent>() {
	    	@Override
	        public void handle(ActionEvent e) {
	       	 	leseAusDatei("csv");
	    	}
	    });
	    mnItmTxtImport.setOnAction(new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent e) {
		     	leseAusDatei("txt");
		    }
    	});
	    mnItmCsvExport.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				schreibeFlughaefenInCsvDatei();
			}
	    });
    }

    private void nehmeFlughafenAuf(){
    	try{
    		this.flughafen = new Flughafen(
    			txtName.getText(),
   	            txtOrt.getText(),
   	            Integer.parseInt(txtAnzahlTerminals.getText()),
   	        	Integer.parseInt(txtAnzahlStartbahnen.getText()),
    		    txtAirlines.getText().split(";"));
    		zeigeInformationsfensterAn("Der Flughafen wurde aufgenommen!");
       	}
       	catch(Exception exc){
       		zeigeFehlermeldungsfensterAn(exc.getMessage());
     	}
    }

    private void zeigeFlughaefenAn(){
    	if(this.flughafen != null){
    		txtAnzeige.setText(
    			this.flughafen.gibFlughafenZurueck(' '));
    	}
    	else{
    		zeigeInformationsfensterAn("Bisher wurde kein Flughafen aufgenommen!");
    	}
    }

    private void leseAusDatei(String typ){
    	try {
      		if("csv".equals(typ)){
      			BufferedReader ein = new BufferedReader(new FileReader("Flughafen.csv"));
      			String[] zeile = ein.readLine().split(";");
      			this.flughafen = new Flughafen(zeile[0],
      				zeile[1],
      				Integer.parseInt(zeile[2]),
      				Integer.parseInt(zeile[3]),
      				zeile[4].split("_"));
      				ein.close();
      	  			zeigeInformationsfensterAn(
      	  	   			"Der Flughafen wurde gelesen!");
      		}
       		else{
	   			zeigeInformationsfensterAn(
	   				"Noch nicht implementiert!");
	   		}
		}
		catch(IOException exc){
			zeigeFehlermeldungsfensterAn(
				"IOException beim Lesen!");
		}
		catch(Exception exc){
			zeigeFehlermeldungsfensterAn(
				"Unbekannter Fehler beim Lesen!");
		}
	}

	private void schreibeFlughaefenInCsvDatei() {
		try {
			BufferedWriter aus
				= new BufferedWriter(new FileWriter("FlughaefenAusgabe.csv", true));
			aus.write(flughafen.gibFlughafenZurueck(';'));
			aus.close();
   			zeigeInformationsfensterAn(
	   			"Die Flughaefen wurden gespeichert!");
		}
		catch(IOException exc){
			zeigeFehlermeldungsfensterAn(
				"IOException beim Speichern!");
		}
		catch(Exception exc){
			zeigeFehlermeldungsfensterAn(
				"Unbekannter Fehler beim Speichern!");
		}
	}

    private void zeigeInformationsfensterAn(String meldung){
    	new MeldungsfensterAnzeiger(AlertType.INFORMATION,
    		"Information", meldung).zeigeMeldungsfensterAn();
    }

    void zeigeFehlermeldungsfensterAn(String meldung){
       	new MeldungsfensterAnzeiger(AlertType.ERROR,
        	"Fehler", meldung).zeigeMeldungsfensterAn();
    }

}
