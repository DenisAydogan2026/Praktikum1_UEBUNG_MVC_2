package main;


import gui.FlughaefenControl;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		new FlughaefenControl(primaryStage);
	}

	public static void main(String[] args){
		launch(args);
	}
}
