package pokertoolclient1;

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
import socketfx.FxSocketClient;
import socketfx.SocketListener;

/**
 * FXML Controller class
 *
 * @author jtconnor
 * co-author x17167141
 */

public class ClientTableController implements Initializable, ControlledScreen {

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

    private final static Logger LOGGER
            = Logger.getLogger(MethodHandles.lookup().lookupClass().getName());

    private ObservableList<String> rcvdMsgsData;
   
    private boolean connected;
    ScreensController myController;
   
    public enum GameDisplayState { 
        
        DISCONNECTED, START, FOLDPUSH, NEXTHAND, WAIT, FOLDPUSHF, FOLDPUSHT, FOLDPUSHR
    }

    private FxSocketClient socket;
    
    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }

    /*
     * Synchronized method responsible for notifying waitForDisconnect()
     * method that it's OK to stop waiting.
     */
    private synchronized void notifyDisconnected() {
        connected = false;
        notifyAll();
    }

    /*
     * Synchronized method to set isConnected boolean
     */
    private synchronized void setIsConnected(boolean connected) {
        this.connected = connected;
    }

    /*
     * Synchronized method to check for value of connected boolean
     */
    private synchronized boolean isConnected() {
        return (connected);
    }

    private void connect() {
        socket = new FxSocketClient(new FxSocketListener(),
                "localhost", 22222,
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
                preflopButton.setDisable(false);
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
        setIsConnected(false);
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
                if (line.equals("start")){displayState(GameDisplayState.START);}
                else if (line.startsWith("1")){
                        yourCard1.setImage(new Image("images/" + file(line) + ".jpg"));
                        displayState(GameDisplayState.WAIT);}
                else if (line.startsWith("2")){
                        yourCard2.setImage(new Image("images/" + file(line) + ".jpg"));
                        displayState(GameDisplayState.WAIT);}
                else if (line.equals("fold")){rcvdMsgsData.add("Opponent fold his hand. You win!");
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
                        displayState(GameDisplayState.FOLDPUSH);}
                else if (line.equals("pushf")){rcvdMsgsData.clear(); rcvdMsgsData.add("Opponent do not give up, what is your choice?");
                        displayState(GameDisplayState.FOLDPUSHF);}
                else if (line.equals("pusht")){rcvdMsgsData.clear(); rcvdMsgsData.add("Opponent is still playing, what is your choice?");
                        displayState(GameDisplayState.FOLDPUSHT);}
                else if (line.equals("pushr")){rcvdMsgsData.clear(); rcvdMsgsData.add("Opponent want to see your hand, what is your choice?");
                        displayState(GameDisplayState.FOLDPUSHR);}
                else if (line.startsWith("3")){
                        flopCard1.setImage(new Image("images/" + file(line) + ".jpg"));
                        displayState(GameDisplayState.WAIT);}
                else if (line.startsWith("4")){
                        flopCard2.setImage(new Image("images/" + file(line) + ".jpg"));
                        displayState(GameDisplayState.WAIT);}
                else if (line.startsWith("5")){
                        flopCard3.setImage(new Image("images/" + file(line) + ".jpg"));
                        displayState(GameDisplayState.WAIT);}
                else if (line.startsWith("6")){
                        turnCard.setImage(new Image("images/" + file(line) + ".jpg"));
                        displayState(GameDisplayState.WAIT);}
                else if (line.startsWith("7")){
                        riverCard.setImage(new Image("images/" + file(line) + ".jpg"));
                        displayState(GameDisplayState.WAIT);}
                else if (line.startsWith("8")){
                        opponentCard1.setImage(new Image("images/" + file(line) + ".jpg"));
                        displayState(GameDisplayState.WAIT);}
                else if (line.startsWith("9")){
                        opponentCard2.setImage(new Image("images/" + file(line) + ".jpg"));
                        displayState(GameDisplayState.WAIT);}
                else if (line.equals("You win!")){rcvdMsgsData.add("You win!");
                    displayState(GameDisplayState.NEXTHAND);}
                else if (line.equals("You lose")){rcvdMsgsData.add("You lose");
                    displayState(GameDisplayState.NEXTHAND);}
                else if (line.equals("nexthand")){rcvdMsgsData.clear();
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
                else if (line.startsWith("Player")){rcvdMsgsData.add(line);
                    displayState(GameDisplayState.WAIT);}
                else {}
                
            }
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
                notifyDisconnected();
                displayState(GameDisplayState.DISCONNECTED);
            } else {
                setIsConnected(true);
                displayState(GameDisplayState.WAIT);
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
            socket.sendMessage("fold");
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
            rcvdMsgsData.clear();
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
        myController.setScreen(PokerToolClient1.screen1ID);
    }
}

