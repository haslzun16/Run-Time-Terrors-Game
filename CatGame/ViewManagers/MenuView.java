package CatGame.ViewManagers;

/**
 * This is the Menu View Manager Author(s) - Greg Last updated - 3/6/20
 */
import CatGame.ButtonExt;
import CatGame.Controller.MenuController;
import CatGame.Events.EventCodes;
import java.awt.event.ActionListener;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BackgroundImage;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;

public class MenuView extends ViewManager {

    protected static final int BUTTON_START_X = 100;
    protected static final int BUTTON_START_Y = 100;
    protected static final int BUTTON_SPACING = 100;
    protected static final String BACKGROUND = "/resources/imgs/menu_background.png";
    protected MenuController controller;
    protected ButtonExt exitButton;
    private SubSceneExt subSceneOnScreen;
    protected SubSceneExt SETTINGS;
    protected SubSceneExt HOW_TO_PLAY;
    protected SubSceneExt HIGHSCORES;
    private final static int LAYOUT_Y = 150;
    private final static int LAYOUT_X = ViewManager.WIDTH + 10;
    private static int center_x = ViewManager.WIDTH / 2;

    public MenuView(MenuController _cont) {
        this.mainPane = new AnchorPane();
        this.mainScene = new Scene(this.mainPane, MenuView.WIDTH, MenuView.HEIGHT);
        this.mainStage = new Stage();
        this.mainStage.setScene(this.mainScene);
        this.mainStage.setTitle(this.TITLE);
        this.mainStage.setResizable(false);
        this.mainStage.sizeToScene();
        this.controller = _cont;
        this.createMenuButtons();
        this.populateSettings();
        this.populateHowToPlay();
        this.populateHighscores();

        BackgroundImage img = new BackgroundImage(new Image(MenuView.BACKGROUND, MenuView.BCKGRD_WIDTH, MenuView.BCKGRD_HEIGHT, true, true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        this.mainPane.setBackground(new Background(img));
    }

    /**
     * Use this method to show the passed subscene.
     * @param _subscene
     */
    public void showSubScene(SubSceneExt _subscene) {
        if (subSceneOnScreen != null) {
            subSceneOnScreen.moveSubScene(center_x);
        }
        _subscene.moveSubScene(center_x);
        subSceneOnScreen = _subscene;
    }
    /**
     * This method will populate the main menu with the proper buttons and set their actions.
     */
    private void createMenuButtons() {
        this.createMenuButton("Start Game", EventCodes.START_GAME);
        this.createMenuButton("How to Play", EventCodes.HOW_TO_PLAY);
        this.createMenuButton("Highscores", EventCodes.HIGHSCORES);
        this.createMenuButton("Settings", EventCodes.SETTINGS_MENU);
        this.createMenuButton("Exit", EventCodes.EXIT);
    }

    /**
     * This method is a helper to createMenuButtons.
     * This one repeats the placement and code setting for each button.
     * @param _text
     * @param _code
     */
    private void createMenuButton(String _text, int _code) {
        ButtonExt button = new ButtonExt(_text, MenuView.BUTTON_START_X, (MenuView.BUTTON_START_Y + this.mainPane.getChildren().size() * MenuView.BUTTON_SPACING));
        this.placeButton(button, _code, this.mainPane);
    }

    /**
     * This method handles the actual placement of the button on the screen
     * @param _button
     * @param _code
     * @param _pane
     */
    private void placeButton(ButtonExt _button, int _code, AnchorPane _pane) {
        _pane.getChildren().add(_button);
        _button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controller.handle(_code);
            }
        });
    }

    /**
     * This method creates buttons that will have an attached subscene.
     * @param _text
     * @param _code
     * @param _scene
     */
    private void createSubSceneButton(String _text, int _code, SubSceneExt _scene) {
        ButtonExt button = new ButtonExt(_text, _scene.getButtonLayoutX(), (_scene.getButtonLayoutY() + _scene.getSubAnchor().getChildren().size() * _scene.getButtonSpacing()));
        this.placeButton(button, _code, _scene.getSubAnchor());
    }

    /**
     * This method populates the settings subscene.
     */
    private void populateSettings() {
        this.SETTINGS = new SubSceneExt(LAYOUT_X,LAYOUT_Y);
        this.createSubSceneButton("Volume Up", EventCodes.VOL_UP, this.SETTINGS);
        this.createSubSceneButton("Volume Down", EventCodes.VOL_DOWN, this.SETTINGS);
        this.createSubSceneButton("Toggle SFX", EventCodes.TOGGLE_SFX, this.SETTINGS);
        this.createSubSceneButton("Toggle Music", EventCodes.TOGGLE_MUSIC, this.SETTINGS);
        this.mainPane.getChildren().add(this.SETTINGS);
    }

    /**
     * This method populates the how to play subscene.
     */
    private void populateHowToPlay() {
        this.HOW_TO_PLAY = new SubSceneExt(LAYOUT_X,LAYOUT_Y);
        this.mainPane.getChildren().add(this.HOW_TO_PLAY);
    }

    /**
     * This method populates the high scores subscene.
     */
    private void populateHighscores() {
        this.HIGHSCORES = new SubSceneExt(LAYOUT_X,LAYOUT_Y);
        this.mainPane.getChildren().add(this.HIGHSCORES);
    }

    /**
     * This method will show the settings subscene.
     */
    public void showSettings() {
        this.showSubScene(this.SETTINGS);
    }

    /**
     * This method will show the how to play subscene.
     */
    public void showHowToPlay() {
        this.showSubScene(this.HOW_TO_PLAY);
    }

    /**
     * This method will show the high scores subscene.
     */
    public void showHighscores() {
        this.showSubScene(this.HIGHSCORES);
    }

    //=================  GETTERS ===============
    public static int getWIDTH() {
        return MenuView.WIDTH;
    }

    public static int getHEIGHT() {
        return MenuView.HEIGHT;
    }

    public static int getBUTTON_START_X() {
        return MenuView.BUTTON_START_X;
    }

    public static int getBUTTON_START_Y() {
        return MenuView.BUTTON_START_Y;
    }

    public static int getBUTTON_SPACING() {
        return MenuView.BUTTON_SPACING;
    }

    public static String getBACKGROUND() {
        return MenuView.BACKGROUND;
    }

}
