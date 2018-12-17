/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokertoolserver;

import engine.Poker_E;
import java.lang.invoke.MethodHandles;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import socketfx.Constants;
import socketfx.FxSocketServer;
import socketfx.SocketListener;

/**
 * FXML Controller class
 *
 * @author jtconnor
 * co-author x17167141
 */
public class ServerTableController implements Initializable, ControlledScreen {

    @FXML
    private ListView<String> serverText; 
    @FXML
    private Button startButton;
    @FXML
    private Button foldButton;
    @FXML
    private Button preflopButton;
    @FXML
    private Button flopButton;
    @FXML
    private Button turnButton;
    @FXML
    private Button riverButton;
    @FXML
    private Button nextHandButton;
    @FXML
    private Button backToLobbyButton;
    @FXML
    private Button connectButton;
    @FXML
    private Label connectedLabel;
    @FXML 
    private ImageView yourCard1;
    @FXML 
    private ImageView yourCard2;
    @FXML 
    private ImageView flopCard1;
    @FXML 
    private ImageView flopCard2;
    @FXML 
    private ImageView flopCard3;
    @FXML 
    private ImageView turnCard;
    @FXML 
    private ImageView riverCard;
    @FXML 
    private ImageView opponentCard1;
    @FXML 
    private ImageView opponentCard2;
    
    private final static Logger LOGGER =
            Logger.getLogger(MethodHandles.lookup().lookupClass().getName());

    private String y1c1, y1c2, y2c1, y2c2, o1c1, o1c2, o2c1, o2c2, fc1, fc2, fc3, tc, rc, resP1, resP2, hand;
    
    
    
    private ObservableList<String> rcvdMsgsData;

    private boolean isConnected;
    
    ScreensController myController;
    
    @Override
    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }

    public enum GameDisplayState { 
        
        DISCONNECTED, START, FOLDPUSH, NEXTHAND, WAIT, FOLDPUSHF, FOLDPUSHT, FOLDPUSHR
    }

    private FxSocketServer socket;

    private void connect() {
        socket = new FxSocketServer(new FxSocketListener(),
                2222,
                Constants.instance().DEBUG_NONE);
        socket.connect();
    }

    private void displayState(GameDisplayState state) {
        switch (state) {
            case DISCONNECTED:
                connectButton.setDisable(false);
                startButton.setDisable(true);
                foldButton.setDisable(true);
                preflopButton.setDisable(true);
                flopButton.setDisable(true);
                turnButton.setDisable(true);
                riverButton.setDisable(true);
                nextHandButton.setDisable(true);
                backToLobbyButton.setDisable(true);
                connectedLabel.setText("Not connected");
                break;
            case START:
                connectButton.setDisable(true);
                startButton.setDisable(false);
                foldButton.setDisable(true);
                preflopButton.setDisable(true);
                flopButton.setDisable(true);
                turnButton.setDisable(true);
                riverButton.setDisable(true);
                nextHandButton.setDisable(true);
                backToLobbyButton.setDisable(true);
                connectedLabel.setText("Connected");
                break;
            case FOLDPUSH:
                connectButton.setDisable(true);
                startButton.setDisable(true);
                foldButton.setDisable(false);
                preflopButton.setDisable(false);
                flopButton.setDisable(true);
                turnButton.setDisable(true);
                riverButton.setDisable(true);
                nextHandButton.setDisable(true);
                backToLobbyButton.setDisable(true);
                connectedLabel.setText("Connected");
                break;
            case NEXTHAND:
                connectButton.setDisable(true);
                startButton.setDisable(true);
                foldButton.setDisable(true);
                preflopButton.setDisable(true);
                flopButton.setDisable(true);
                turnButton.setDisable(true);
                riverButton.setDisable(true);
                nextHandButton.setDisable(false);
                backToLobbyButton.setDisable(false);
                connectedLabel.setText("Connected");
                break;
            case WAIT:
                connectButton.setDisable(true);
                startButton.setDisable(true);
                foldButton.setDisable(true);
                preflopButton.setDisable(true);
                flopButton.setDisable(true);
                turnButton.setDisable(true);
                riverButton.setDisable(true);
                nextHandButton.setDisable(true);
                backToLobbyButton.setDisable(true);
                connectedLabel.setText("Connected");
                break;
            case FOLDPUSHF:
                connectButton.setDisable(true);
                startButton.setDisable(true);
                foldButton.setDisable(false);
                preflopButton.setDisable(true);
                flopButton.setDisable(false);
                turnButton.setDisable(true);
                riverButton.setDisable(true);
                nextHandButton.setDisable(true);
                backToLobbyButton.setDisable(true);
                connectedLabel.setText("Connected");
                break;
            case FOLDPUSHT:
                connectButton.setDisable(true);
                startButton.setDisable(true);
                foldButton.setDisable(false);
                preflopButton.setDisable(true);
                flopButton.setDisable(true);
                turnButton.setDisable(false);
                riverButton.setDisable(true);
                nextHandButton.setDisable(true);
                backToLobbyButton.setDisable(true);
                connectedLabel.setText("Connected");
                break;
            case FOLDPUSHR:
                connectButton.setDisable(true);
                startButton.setDisable(true);
                foldButton.setDisable(false);
                preflopButton.setDisable(true);
                flopButton.setDisable(true);
                turnButton.setDisable(true);
                riverButton.setDisable(false);
                nextHandButton.setDisable(true);
                backToLobbyButton.setDisable(true);
                connectedLabel.setText("Connected");
                break;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        isConnected = false;
        displayState(GameDisplayState.DISCONNECTED);

        rcvdMsgsData = FXCollections.observableArrayList();
        serverText.setItems(rcvdMsgsData);

        Runtime.getRuntime().addShutdownHook(new ShutDownThread());

    }

    class ShutDownThread extends Thread {

        @Override
        public void run() {
            if (socket != null) {
                if (socket.debugFlagIsSet(Constants.instance().DEBUG_STATUS)) {
                    LOGGER.info("ShutdownHook: Shutting down Server Socket");    
                }
                socket.shutdown();
            }
        }
    }

    class FxSocketListener implements SocketListener {

        @Override
        public void onMessage(String line) {
            if (line != null && !line.equals("")) {
                if (line.equals("start")){PlayHand();
                    yourCard1.setImage(new Image("images/" + file(y1c1) + ".jpg"));
                    yourCard2.setImage(new Image("images/" + file(y1c2) + ".jpg"));
                    socket.sendMessage(y2c1);
                    socket.sendMessage(y2c2);
                    displayState(GameDisplayState.FOLDPUSH);}
                if (line.equals("fold")){rcvdMsgsData.add("Opponent fold his hand. You win!");
                    yourCard1.setImage(new Image("images/blank.jpg"));
                    yourCard2.setImage(new Image("images/blank.jpg"));
                    flopCard1.setImage(new Image("images/blank.jpg"));
                    flopCard2.setImage(new Image("images/blank.jpg"));
                    flopCard3.setImage(new Image("images/blank.jpg"));
                    turnCard.setImage(new Image("images/blank.jpg"));
                    riverCard.setImage(new Image("images/blank.jpg"));
                    opponentCard1.setImage(new Image("images/blank.jpg"));
                    opponentCard2.setImage(new Image("images/blank.jpg"));
                    displayState(GameDisplayState.NEXTHAND);}
                else if (line.equals("pushpf")){rcvdMsgsData.clear(); rcvdMsgsData.add("Opponent play, what is your choice?");
                    flopCard1.setImage(new Image("images/" + file(fc1) + ".jpg"));
                    flopCard2.setImage(new Image("images/" + file(fc2) + ".jpg"));
                    flopCard3.setImage(new Image("images/" + file(fc3) + ".jpg"));
                    socket.sendMessage(fc1);
                    socket.sendMessage(fc2);
                    socket.sendMessage(fc3);
                    displayState(GameDisplayState.FOLDPUSHF);
                }
                else if (line.equals("pushf")){rcvdMsgsData.clear(); rcvdMsgsData.add("Opponent play again, what is your choice?");
                    turnCard.setImage(new Image("images/" + file(tc) + ".jpg"));
                    socket.sendMessage(tc);
                    displayState(GameDisplayState.FOLDPUSHT);
                }
                else if (line.equals("pusht")){rcvdMsgsData.clear(); rcvdMsgsData.add("Opponent is still playing, what is your choice?");
                    riverCard.setImage(new Image("images/" + file(rc) + ".jpg"));
                    socket.sendMessage(rc);
                    displayState(GameDisplayState.FOLDPUSHR);
                }
                else if (line.equals("pushr")){rcvdMsgsData.clear(); rcvdMsgsData.add("Opponent must have good hand, what is your choice?");
                    opponentCard1.setImage(new Image("images/" + file(o1c1) + ".jpg"));
                    opponentCard2.setImage(new Image("images/" + file(o1c2) + ".jpg"));
                    socket.sendMessage(o2c1);
                    socket.sendMessage(o2c2);
                    rcvdMsgsData.add(resP1);
                    socket.sendMessage(resP2);
                    displayState(GameDisplayState.NEXTHAND);
                }
                else if (line.equals("nexthand")){
                    yourCard1.setImage(new Image("images/blank.jpg"));
                    yourCard2.setImage(new Image("images/blank.jpg"));
                    flopCard1.setImage(new Image("images/blank.jpg"));
                    flopCard2.setImage(new Image("images/blank.jpg"));
                    flopCard3.setImage(new Image("images/blank.jpg"));
                    turnCard.setImage(new Image("images/blank.jpg"));
                    riverCard.setImage(new Image("images/blank.jpg"));
                    opponentCard1.setImage(new Image("images/blank.jpg"));
                    opponentCard2.setImage(new Image("images/blank.jpg"));
                    displayState(GameDisplayState.START);}
                
            }
        }

        private void PlayHand() {
            InitVar();
            Poker_E pe = new Poker_E();
            pe.play();
            y1c1 = ("1" + pe.getP1c1());
            y1c2 = ("2" + pe.getP1c2());
            o1c1 = ("8" + pe.getP2c1());
            o1c2 = ("9" + pe.getP2c2());
            y2c1 = ("1" + pe.getP2c1());
            y2c2 = ("2" + pe.getP2c2());
            o2c1 = ("8" + pe.getP1c1());
            o2c2 = ("9" + pe.getP1c2());
            fc1 = ("3" + pe.getF1c());
            fc2 = ("4" + pe.getF2c());
            fc3 = ("5" + pe.getF3c());
            tc = ("6" + pe.getTc());
            rc = ("7" + pe.getRc());
            int res = pe.getWinner();
            if (res == 1) {resP1 = "You win!"; resP2 = "You lose";}
            else {resP1 = "You lose"; resP2 = "You win!";}
            
        }
        void InitVar(){
            y1c1="";
            y1c2="";
            y2c1="";
            y2c2="";
            o1c1="";
            o1c2="";
            o2c1="";
            o2c2="";
            fc1="";
            fc2="";
            fc3="";
            tc="";
            rc="";
            resP1="";
            resP2="";
            hand="";
        }

        private String file(String line){
            StringBuffer sb = new StringBuffer();
            sb = sb.append(line);
            sb.deleteCharAt(0);
            return sb.toString();
        }
            
        

        @Override
        public void onClosedStatus(boolean isClosed) {
            if (isClosed) {
                isConnected = false;
                    displayState(GameDisplayState.DISCONNECTED);
            } else {
                isConnected = true;
                    displayState(GameDisplayState.START);
                }
            }
        
    }

    @FXML
    private void handleStartButton(ActionEvent event) { 
            socket.sendMessage("start");
            displayState(GameDisplayState.WAIT);
    }
    
    @FXML
    private void handleFoldButton(ActionEvent event) { 
            yourCard1.setImage(new Image("images/blank.jpg"));
            yourCard2.setImage(new Image("images/blank.jpg"));
            flopCard1.setImage(new Image("images/blank.jpg"));
            flopCard2.setImage(new Image("images/blank.jpg"));
            flopCard3.setImage(new Image("images/blank.jpg"));
            turnCard.setImage(new Image("images/blank.jpg"));
            riverCard.setImage(new Image("images/blank.jpg"));
            opponentCard1.setImage(new Image("images/blank.jpg"));
            opponentCard2.setImage(new Image("images/blank.jpg"));
            socket.sendMessage("fold");
            displayState(GameDisplayState.WAIT);
    }
    
    @FXML
    private void handlePreflopButton(ActionEvent event) { 
            socket.sendMessage("pushpf");
            displayState(GameDisplayState.WAIT);
    }
    
    @FXML
    private void handleFlopButton(ActionEvent event) { 
            socket.sendMessage("pushf");
            displayState(GameDisplayState.WAIT);
    }
    
    @FXML
    private void handleTurnButton(ActionEvent event) { 
            socket.sendMessage("pusht");
            displayState(GameDisplayState.WAIT);
    }
    
    @FXML
    private void handleRiverButton(ActionEvent event) { 
            socket.sendMessage("pushr");
            displayState(GameDisplayState.WAIT);
    }
    
    @FXML
    private void handleNextHandButton(ActionEvent event) { 
            socket.sendMessage("nexthand");
            yourCard1.setImage(new Image("images/blank.jpg"));
            yourCard2.setImage(new Image("images/blank.jpg"));
            flopCard1.setImage(new Image("images/blank.jpg"));
            flopCard2.setImage(new Image("images/blank.jpg"));
            flopCard3.setImage(new Image("images/blank.jpg"));
            turnCard.setImage(new Image("images/blank.jpg"));
            riverCard.setImage(new Image("images/blank.jpg"));
            opponentCard1.setImage(new Image("images/blank.jpg"));
            opponentCard2.setImage(new Image("images/blank.jpg"));
            displayState(GameDisplayState.WAIT);
    }

    @FXML
    private void handleConnectButton(ActionEvent event) {
        
        displayState(GameDisplayState.WAIT);
        connect();
        rcvdMsgsData.add("Wait for opponent to start hand");
    }
    @FXML
    private void goToLobbyButton(ActionEvent event) { 
        myController.setScreen(PokerToolServer.screen1ID);
    }

}
