import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.Callable;
import java.util.function.Function;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.w3c.dom.ls.LSOutput;
import javafx.scene.control.TextArea;

public class ThreeCardPokerGame extends Application {

	//Declaring required items
	Player playerOne = new Player(), playerTwo = new Player();
	Dealer theDealer = new Dealer();
	Button P1_b1, P1_b2, P2_b1, P2_b2, D_b1, D_b2, D_b3, start_btn, revealButton, bPlayAgain;
	TextField P1_t1, P1_t2, P2_t1, P2_t2, center_Info;
	MenuBar bar;
	PauseTransition pause = new PauseTransition(Duration.seconds(2));

	//Dimensions used for images
	static final int picHeight = 100;
	static final int picWidth = 75;

	//Player 1
	VBox P1_VB_Info1, P1_VB_Info2, P1_VB_Info3;
	HBox P1_HB_InfoBox;

	//Player 2
	VBox P2_VB_Info1, P2_VB_Info2, P2_VB_Info3;
	HBox P2_HB_InfoBox;

	//Dealer
	VBox D_VB_Info1, D_VB_Info2, D_VB_Info3;
	HBox D_HB_InfoBox;

	//Player 1
	VBox P1_PlayBox;
	Button P1_bPlay;
	Button P1_bFold;

	//Player 2
	VBox P2_PlayBox;
	Button P2_bPlay;
	Button P2_bFold;

	//Other Elements
	VBox MenuAndDealer, center_VB;
	HBox center_HB;

	//Image Elements
	Image pic, pic2, pic3;
	ImageView P1_v1, P1_v2, P1_v3;
	ImageView P2_v1, P2_v2, P2_v3;
	ImageView D_v1, D_v2, D_v3;

	//Labels to display Player Info and Instructions
	Label L1;
	Label L2;
	Label L3;

	//returns string with player 1 stats.
	public String statsPlayerOne(){
		String S = " Player 1 \n Total Winnings: " + playerOne.getTotalWinnings()
				+ "\n Ante Bet: " + playerOne.getAnteBet()
				+ "\n Pair Plus Bet: " + playerOne.getPairPlusBet()
				+ "\n Play Wager Bet: " + playerOne.getPlayBet();
		return S;
	}

	//returns string with player 2 stats.
	public String statsPlayerTwo(){
		String S = " Player 2 \n Total Winnings: " + playerTwo.getTotalWinnings()
				+ "\n Ante Bet: " + playerTwo.getAnteBet()
				+ "\n Pair Plus Bet: " + playerTwo.getPairPlusBet()
				+ "\n Play Wager Bet: " + playerTwo.getPlayBet();
		return S;
	}

	//String containing instructions.
	String instructions = " Instructions: \n"
				+ "Ante range: $5 - $25 \n"
				+ "Pair Plus range: $5 - $25 \n"
				+ "Total Winnings shows Net winnings since the game began. \n\n"
				+ "Note: \nIf the dealer's cards are not Queen \nHigh or better, then Ante is taken to next round \n"
				+ "and the game begins in the next round directly. \nThe dealer's card are not shown in this case.\n";


	//Initialize On-Screen Elements
	public void initializeElements(){

		//player 1 buttons
		P1_b1 = new Button("Ante");
		if(playerOne.getAnteBet()!=0){
			P1_b1.setDisable(true);
		}
		P1_b2 = new Button("Pair Plus");

		//player 2 buttons
		P2_b1 = new Button("Ante");
		if(playerTwo.getAnteBet()!=0){
			P2_b1.setDisable(true);
		}
		P2_b2 = new Button("Pair Plus");

		//Dealer buttons
		D_b1 = new Button("Ante");
		D_b2 = new Button("Pair Plus");
		D_b3 = new Button("Play Wager");

		//Images For Cards
		pic = new Image("ek_aur.jpg");
		pic2 = new Image("jokerCard.jpg");
		pic3 = new Image("cardBack.jpg");

		//Player 1 ImageViews
		P1_v1 = new ImageView(pic);
		P1_v1.setFitHeight(picHeight); P1_v1.setFitWidth(picWidth); P1_v1.setPreserveRatio(true);

		P1_v2 = new ImageView(pic);
		P1_v2.setFitHeight(picHeight); P1_v2.setFitWidth(picWidth); P1_v2.setPreserveRatio(true);

		P1_v3 = new ImageView(pic);
		P1_v3.setFitHeight(picHeight); P1_v3.setFitWidth(picWidth); P1_v3.setPreserveRatio(true);

		//Player 2 ImageViews
		P2_v1 = new ImageView(pic2);
		P2_v1.setFitHeight(picHeight); P2_v1.setFitWidth(picWidth); P2_v1.setPreserveRatio(true);

		P2_v2 = new ImageView(pic2);
		P2_v2.setFitHeight(picHeight); P2_v2.setFitWidth(picWidth); P2_v2.setPreserveRatio(true);

		P2_v3 = new ImageView(pic2);
		P2_v3.setFitHeight(picHeight); P2_v3.setFitWidth(picWidth); P2_v3.setPreserveRatio(true);

		//Dealer ImageViews
		D_v1 = new ImageView(pic3);
		D_v1.setFitHeight(picHeight); D_v1.setFitWidth(picWidth); D_v1.setPreserveRatio(true);

		D_v2 = new ImageView(pic3);
		D_v2.setFitHeight(picHeight); D_v2.setFitWidth(picWidth); D_v2.setPreserveRatio(true);

		D_v3 = new ImageView(pic3);
		D_v3.setFitHeight(picHeight); D_v3.setFitWidth(picWidth); D_v3.setPreserveRatio(true);

		//Player 1 TextFields
		P1_t1 = new TextField();
		P1_t2 = new TextField();

		//Player 2 TextFields
		P2_t1 = new TextField();
		P2_t2 = new TextField();

		// Player 1 Play and Fold Box
		P1_bPlay = new Button("Play");
		P1_bFold = new Button("Fold");
		P1_PlayBox = new VBox(P1_bPlay, P1_bFold);

		// Player 2 Play and Fold Box
		P2_bPlay = new Button("Play");
		P2_bFold = new Button("Fold");
		P2_PlayBox = new VBox(P2_bPlay, P2_bFold);

		//player 1 - HBox
		P1_VB_Info1 = new VBox(P1_v1, P1_t1, P1_b1);
		P1_VB_Info2 = new VBox(P1_v2, P1_t2, P1_b2);
		P1_VB_Info3 = new VBox(P1_v3);
		P1_HB_InfoBox = new HBox(P1_VB_Info1, P1_VB_Info2, P1_VB_Info3, P1_PlayBox);
		P1_HB_InfoBox.setPadding(new Insets(100, 10,0,10));
		P1_bPlay.setDisable(true);
		P1_bFold.setDisable(true);

		//properties
		P1_VB_Info1.setMaxWidth(1.1*picWidth);
		P1_VB_Info2.setMaxWidth(1.1*picWidth);
		P1_VB_Info3.setMaxWidth(1.1*picWidth);

		//player 2 - HBox
		P2_VB_Info1 = new VBox(P2_v1, P2_t1, P2_b1);
		P2_VB_Info2 = new VBox(P2_v2, P2_t2, P2_b2);
		P2_VB_Info3 = new VBox(P2_v3);
		P2_HB_InfoBox = new HBox(P2_PlayBox, P2_VB_Info1, P2_VB_Info2, P2_VB_Info3);
		P2_HB_InfoBox.setPadding(new Insets(100, 10,0,10));
		P2_bPlay.setDisable(true);
		P2_bFold.setDisable(true);

		//properties
		P2_VB_Info1.setMaxWidth(1.1*picWidth);
		P2_VB_Info2.setMaxWidth(1.1*picWidth);
		P2_VB_Info3.setMaxWidth(1.1*picWidth);

		//Print Stats and Instructions
		L1 = new Label(statsPlayerOne());
		L2 = new Label(statsPlayerTwo());
		L3 = new Label(instructions);

		//Dealer - HBox
		D_VB_Info1  = new VBox(D_v1);
		D_VB_Info2  = new VBox(D_v2);
		D_VB_Info3  = new VBox(D_v3);
		D_HB_InfoBox = new HBox(L1, D_VB_Info1, D_VB_Info2, D_VB_Info3, L2);

		//properties
		D_HB_InfoBox.setPadding(new Insets(20, 0, 20, 225)); //Dealer - center screen
		L1.setPadding(new Insets(5) );
		L2.setPadding(new Insets(5) );

		//Center - Text box
		center_Info = new TextField();
		center_Info.setText("This is the General Information box");

		//center buttons for gameplay
		start_btn = new Button("Start Game");
		revealButton = new Button("Reveal Dealer's Cards");
		revealButton.setDisable(true);
		bPlayAgain = new Button("Play Again");
		bPlayAgain.setDisable(true);
		center_HB = new HBox(start_btn, revealButton, bPlayAgain);
		center_VB = new VBox(center_HB, center_Info, L3);

		// properties
		L3.setStyle("-fx-text-fill: white;" + "-fx-font-size: 15;");
		L2.setStyle("-fx-text-fill: white;" + "-fx-font-size: 15;");
		L1.setStyle("-fx-text-fill: white;" + "-fx-font-size: 15;");
		center_VB.setPadding(new Insets(30, 0, 10, 0));

	}

	//Initialize Menu Bar and it's elements
	void initializeMenu(BorderPane pane, Stage primaryStage) {

		bar = new MenuBar();
		Menu mOne = new Menu("option");

		//Fresh Start
		MenuItem iOne = new MenuItem("Fresh Start");
		iOne.setOnAction(e->
		{
			try{
				playerOne = new Player();
				playerTwo = new Player();
				theDealer = new Dealer();
				start(primaryStage);
			}
			catch(Exception e1)
			{
				e1.printStackTrace();
			}

		});

		// New Look
		MenuItem iTwo = new MenuItem("New Look");
		iTwo.setOnAction(e-> {
			pane.setStyle("-fx-background-color: #5F9EA0;" + "-fx-border-color: hotpink;" + "-fx-font-family: 'Arial Black'");
			bar.setStyle("-fx-background-color: green;");
		});

		//Exit
		MenuItem iThree = new MenuItem("Exit");
		iThree.setOnAction(e-> {
			Platform.exit();} );

		mOne.getItems().addAll(iOne, iTwo, iThree);
		bar.getMenus().addAll(mOne);
		MenuAndDealer = new VBox(bar, D_HB_InfoBox); //menu bar VBox containing menu bar & dealer box
	}

	//returns string containing name of image for a particular card.
	public String return_CardImageName(Card C)
	{
		String S = "" + C.getValue()  + C.getSuit() + ".jpg";
		return S;
	}

	//Main Function - calls start loop
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		// TODO Auto-generated method stub
		primaryStage.setTitle("Let's Play Three Card Poker!!!");
		BorderPane pane = new BorderPane();
		pane.setStyle("-fx-background-color: green;" + "-fx-font-family: 'Comic Sans MS';");

		initializeElements();
		initializeMenu(pane, primaryStage);

		//properties
		bar.setStyle("-fx-background-color: lightblue;" );

		//event handlers for buttons
		P1_b1.setOnAction(e->{ //Ante Button - P1

			int textVal = Integer.parseInt(P1_t1.getText());
			if(textVal < 5 || textVal > 25){
				center_Info.setText("Ante out of range, should be between $5 - $25");
			}else{
				playerOne.setAnteBet( Integer.parseInt(P1_t1.getText()) );
				//System.out.println(playerOne.getAnteBet());
				L1.setText(statsPlayerOne());
				P1_t1.clear();
				center_Info.clear();
			}
		});

		P2_b1.setOnAction(e->{ //Ante Button - P2

			int textVal = Integer.parseInt(P2_t1.getText());
			if(textVal < 5 || textVal > 25){
				center_Info.setText("Ante out of range, should be between $5 - $25");
			}else{
				playerTwo.setAnteBet( Integer.parseInt(P2_t1.getText()) );
				L2.setText(statsPlayerTwo());
				P2_t1.clear();
				center_Info.clear();
			}
		});

		P1_b2.setOnAction(e->{ //PP Bet Button - P1
			int textVal = Integer.parseInt(P1_t2.getText());
			if(textVal < 5 || textVal > 25){
				center_Info.setText("Pair Plus Bet out of range, should be between $5 - $25");
			}else {
				playerOne.setPairPlusBet(Integer.parseInt(P1_t2.getText()));
				L1.setText(statsPlayerOne());
				P1_t2.clear();
				center_Info.clear();
			}
		});

		P2_b2.setOnAction(e->{ //PP Bet Button - P2
			int textVal = Integer.parseInt(P2_t2.getText());
			if(textVal < 5 || textVal > 25){
				center_Info.setText("Pair Plus Bet out of range, should be between $5 - $25");
			}else {
				playerTwo.setPairPlusBet(Integer.parseInt(P2_t2.getText()));
				L2.setText(statsPlayerTwo());
				P2_t2.clear();
				center_Info.clear();
			}
		});

		start_btn.setOnAction(e->{ //Start button

			if(playerOne.getAnteBet() == 0 || playerTwo.getAnteBet() == 0){ //Ante not entered
				center_Info.setText("Please Enter Ante for both the Two Players");
			}else{
				theDealer.setDealersHand(theDealer.dealHand());
				if(playerOne.getAnteBet() == 0) {
					//Do Nothing
				}else if(playerOne.getAnteBet() < 5 || playerOne.getAnteBet() > 25){ // Invalid Ante entered
					center_Info.setText("Please Enter Valid AnteBet for Player 1. (Must be between $5 - $25)");
				}else{

					//change enable/disable
					P1_bPlay.setDisable(false);
					P1_bFold.setDisable(false);
					playerOne.setHand(theDealer.dealHand());

					//change enable/disable
					P1_b1.setDisable(true);
					P1_b2.setDisable(true);

					//Add new Images
					Image P1_card1 = new Image(return_CardImageName(playerOne.getHand().get(0)));
					Image P1_card2 = new Image(return_CardImageName(playerOne.getHand().get(1)));
					Image P1_card3 = new Image(return_CardImageName(playerOne.getHand().get(2)));

					//Add Pause
					PauseTransition pause3 = new PauseTransition(Duration.seconds(0.5));
					PauseTransition pause1 = new PauseTransition(Duration.seconds(0.5));
					PauseTransition pause2 = new PauseTransition(Duration.seconds(0.5));

					pause3.setOnFinished(e1->{
						P1_v1.setImage(P1_card1);
						P1_v1.setFitHeight(picHeight); P1_v1.setFitWidth(picWidth); P1_v1.setPreserveRatio(true);
						pause1.play();
					});
					pause3.play();

					pause1.setOnFinished(e1->{
						P1_v2.setImage(P1_card2);
						P1_v2.setFitHeight(picHeight); P1_v2.setFitWidth(picWidth); P1_v2.setPreserveRatio(true);
						pause2.play();
					});

					pause2.setOnFinished(e1->{
						P1_v3.setImage(P1_card3);
						P1_v3.setFitHeight(picHeight); P1_v3.setFitWidth(picWidth); P1_v3.setPreserveRatio(true);
					});

					center_Info.clear();
				}

				if(playerTwo.getAnteBet() == 0){ //Ante not entered
					//Do nothing
				}else if(playerTwo.getAnteBet() < 5 || playerTwo.getAnteBet() > 25){ //Invalid Ante entered
					center_Info.setText("Please Enter Valid AnteBet for Player 2. (Must be between $5 - $25)");
				}else{

					//change enable/disable
					P2_bPlay.setDisable(false);
					P2_bFold.setDisable(false);
					playerTwo.setHand(theDealer.dealHand());
					P2_b1.setDisable(true);
					P2_b2.setDisable(true);

					// Add images
					Image P2_card1 = new Image(return_CardImageName(playerTwo.getHand().get(0)));
					Image P2_card2 = new Image(return_CardImageName(playerTwo.getHand().get(1)));
					Image P2_card3 = new Image(return_CardImageName(playerTwo.getHand().get(2)));

					// Add pauses
					PauseTransition pause3 = new PauseTransition(Duration.seconds(0.5));
					PauseTransition pause1 = new PauseTransition(Duration.seconds(0.5));
					PauseTransition pause2 = new PauseTransition(Duration.seconds(0.5));

					pause3.setOnFinished(e1->{
						P2_v1.setImage(P2_card1);
						P2_v1.setFitHeight(picHeight); P2_v1.setFitWidth(picWidth); P2_v1.setPreserveRatio(true);
						pause1.play();
					});
					pause3.play();

					pause1.setOnFinished(e1->{
						P2_v2.setImage(P2_card2);
						P2_v2.setFitHeight(picHeight); P2_v2.setFitWidth(picWidth); P2_v2.setPreserveRatio(true);
						pause2.play();
					});

					pause2.setOnFinished(e2->{
						P2_v3.setImage(P2_card3);
						P2_v3.setFitHeight(picHeight); P2_v3.setFitWidth(picWidth); P2_v3.setPreserveRatio(true);
					});

					center_Info.clear();
				}
				start_btn.setDisable(true);
			}
			revealButton.setDisable(false);
		});

		// Play and fold event handlers
		P1_bPlay.setOnAction(e->{
			P1_PlayBox.setDisable(true);
			playerOne.playFold = 1;

			playerOne.setPlayBet(playerOne.getAnteBet());
			L1.setText(statsPlayerOne());
		});

		P1_bFold.setOnAction(e->{
			P1_PlayBox.setDisable(true);
			playerOne.playFold = 2;
			P1_v1.setImage(pic);
			pause.pause();
			P1_v2.setImage(pic);
			pause.pause();
			P1_v3.setImage(pic);
		});

		P2_bPlay.setOnAction(e->{
			playerTwo.playFold = 1;
			P2_PlayBox.setDisable(true);
			playerTwo.setPlayBet(playerTwo.getAnteBet());
			L2.setText(statsPlayerTwo());
		});

		P2_bFold.setOnAction(e->{
			P2_PlayBox.setDisable(true);
			playerTwo.playFold = 2;
			P2_v1.setImage(pic2);
			pause.pause();
			P2_v2.setImage(pic2);
			pause.pause();
			P2_v3.setImage(pic2);
		});

		//Reveal Button - responsible for comparison and winner check
		revealButton.setOnAction(e->{

			if(playerOne.playFold != 0 && playerTwo.playFold != 0 ) {

				//change images
				Image D1 = new Image(return_CardImageName(theDealer.getDealersHand().get(0)));
				Image D2 = new Image(return_CardImageName(theDealer.getDealersHand().get(1)));
				Image D3 = new Image(return_CardImageName(theDealer.getDealersHand().get(2)));

				D_v1.setImage(D1);
				D_v2.setImage(D2);
				D_v3.setImage(D3);

				D_v1.setFitHeight(picHeight); D_v1.setFitWidth(picWidth); D_v1.setPreserveRatio(true);
				D_v2.setFitHeight(picHeight); D_v2.setFitWidth(picWidth); D_v2.setPreserveRatio(true);
				D_v3.setFitHeight(picHeight); D_v3.setFitWidth(picWidth); D_v3.setPreserveRatio(true);

				String winMessage = ""; // Message output with winner info.
				//PlayFold is 1 when Play was clicked.
				//PlayFold is 2 when Fold was clicked.
				if(playerOne.playFold == 1) {

					// check dealer Q - High card or better
					if(ThreeCardLogic.dHandStatus(theDealer.getDealersHand() )){ // Dealer Qualifies

						// compare hands of dealer and player
						int winNum = ThreeCardLogic.compareHands(theDealer.getDealersHand(), playerOne.getHand());

						if (winNum == 0) {
							winMessage = "Player One Drew, ";
						}else if (winNum == 1) {

							winMessage = "Player One Lost, ";
							playerOne.setTotalWinnings(playerOne.getTotalWinnings()-playerOne.getAnteBet()-playerOne.getPlayBet());
							int res = ThreeCardLogic.evalHand(playerOne.getHand());
							if(res != 0){
								playerOne.setTotalWinnings(playerOne.getTotalWinnings() + ThreeCardLogic.evalPPWinnings( playerOne.getHand(), playerOne.getPairPlusBet()));
							}else{
								playerOne.setTotalWinnings(playerOne.getTotalWinnings() - playerOne.getPairPlusBet() );
							}
						}else {

							winMessage = "Player One Won! , ";
							playerOne.setTotalWinnings(playerOne.getTotalWinnings() + 2* (playerOne.getAnteBet() + playerOne.getPlayBet()));
							int res = ThreeCardLogic.evalHand(playerOne.getHand());
							if(res != 0){
								playerOne.setTotalWinnings(playerOne.getTotalWinnings() + ThreeCardLogic.evalPPWinnings( playerOne.getHand(), playerOne.getPairPlusBet()));
							}else{
								playerOne.setTotalWinnings(playerOne.getTotalWinnings() - playerOne.getPairPlusBet() );
							}
						}

					//Dealer Doesn't Qualify
					}else{
						playerOne.setPlayBet(0);
						center_Info.setText("Dealer's Hand didn't Qualify, Shifting to next Round.");
						try{
							int P1_saveData = playerOne.getTotalWinnings();
							int P2_saveData = playerTwo.getTotalWinnings();
							int P1_saveAnte = playerOne.getAnteBet();
							int P2_saveAnte = playerTwo.getAnteBet();
							playerOne = new Player();
							playerTwo = new Player();
							playerOne.setTotalWinnings(P1_saveData);
							playerTwo.setTotalWinnings(P2_saveData);
							playerOne.setAnteBet(P1_saveAnte);
							playerTwo.setAnteBet(P2_saveAnte);

							start(primaryStage);
						}
						catch(Exception e1)
						{
							e1.printStackTrace();
						}
					}
				}else{
					winMessage = "Player One Folded, ";
					playerOne.setTotalWinnings(playerOne.getTotalWinnings()-playerOne.getAnteBet()-playerOne.getPairPlusBet());
				}

				//PlayFold is 1 when Play was clicked.
				//PlayFold is 2 when Fold was clicked.
				if(playerTwo.playFold == 1){

					// check dealer Q - High card or better
					if(ThreeCardLogic.dHandStatus(theDealer.getDealersHand() )){ // Dealer Qualifies

						// compare hands of dealer and player
						int winNum2 = ThreeCardLogic.compareHands(theDealer.getDealersHand(), playerTwo.getHand());
						if (winNum2 == 0) {
							winMessage += "Player Two Drew.";
						} else if (winNum2 == 1) {
							winMessage += "Player Two Lost.";
							playerTwo.setTotalWinnings(playerTwo.getTotalWinnings()-playerTwo.getAnteBet()-playerTwo.getPlayBet());
							int res = ThreeCardLogic.evalHand(playerTwo.getHand());
							if(res != 0){
								playerTwo.setTotalWinnings(playerTwo.getTotalWinnings() + ThreeCardLogic.evalPPWinnings( playerTwo.getHand(), playerTwo.getPairPlusBet()));
							}else{
								playerTwo.setTotalWinnings(playerTwo.getTotalWinnings() - playerTwo.getPairPlusBet() );
							}
						} else {
							winMessage += "Player Two Won!";
							playerTwo.setTotalWinnings(playerTwo.getTotalWinnings() + 2* (playerTwo.getAnteBet() + playerTwo.getPlayBet()));
							int res = ThreeCardLogic.evalHand(playerTwo.getHand());
							if(res != 0){
								playerTwo.setTotalWinnings(playerTwo.getTotalWinnings() + ThreeCardLogic.evalPPWinnings( playerTwo.getHand(), playerTwo.getPairPlusBet()));
							}else{
								playerTwo.setTotalWinnings(playerTwo.getTotalWinnings() - playerTwo.getPairPlusBet() );
							}
						}

					}else{
						playerTwo.setPlayBet(0);
						center_Info.setText("Dealer's Hand didn't Qualify, Shifting to next Round.");

						try{
							int P1_saveData = playerOne.getTotalWinnings();
							int P2_saveData = playerTwo.getTotalWinnings();
							int P1_saveAnte = playerOne.getAnteBet();
							int P2_saveAnte = playerTwo.getAnteBet();
							playerOne = new Player();
							playerTwo = new Player();
							playerOne.setTotalWinnings(P1_saveData);
							playerTwo.setTotalWinnings(P2_saveData);
							playerOne.setAnteBet(P1_saveAnte);
							playerTwo.setAnteBet(P2_saveAnte);

							start(primaryStage);
						}
						catch(Exception e1)
						{
							e1.printStackTrace();
						}
					}
				}else{
					winMessage += "Player Two Folded.";
					playerTwo.setTotalWinnings(playerTwo.getTotalWinnings() - playerTwo.getAnteBet() - playerTwo.getPairPlusBet());
				}

				L1.setText(statsPlayerOne());
				L2.setText(statsPlayerTwo());
				center_Info.setText(winMessage);
				bPlayAgain.setDisable(false);
				revealButton.setDisable(true);

			}else{
				center_Info.setText("You can only reveal dealer's cards when both players select to Play or to Fold.");
			}

		});

		//Play Again button Event Handler
		bPlayAgain.setOnAction(e->{
			try{
				int P1_saveData = playerOne.getTotalWinnings();
				int P2_saveData = playerTwo.getTotalWinnings();
				playerOne = new Player();
				playerTwo = new Player();
				playerOne.setTotalWinnings(P1_saveData);
				playerTwo.setTotalWinnings(P2_saveData);
				start(primaryStage);
			}
			catch(Exception e1)
			{
				e1.printStackTrace();
			}

		});

		//Set pane elements
		pane.setTop(MenuAndDealer);
		pane.setLeft(P1_HB_InfoBox);
		pane.setRight(P2_HB_InfoBox);
		pane.setCenter(center_VB);

		//Set Scene and Stage
		Scene scene = new Scene(pane,1000,600);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
