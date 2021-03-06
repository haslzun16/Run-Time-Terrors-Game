package CatGame.Controller;

/**
 * This method handles calls from the MenuView.
 * Author(s): Greg Dwyer
 * Last Updated: 4/27/20
 */

import CatGame.Events.EventCodes;
import CatGame.Models.SFX;
import CatGame.ViewManagers.MenuView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MenuController {

    private final MenuView VIEW;

    public MenuController() {
        this.VIEW = new MenuView(this);
        SFX.startMusic();
    }

    /**
     * This method takes an event code from the view and calls the correct classes.
     */
    public void handle(int _code) {
        switch (_code) {
            case EventCodes.START_GAME:
                GameController game = new GameController(this.getViewStage());
                break;
            case EventCodes.HOW_TO_PLAY:
                this.VIEW.showHowToPlay();
                break;
            case EventCodes.HIGHSCORES:
                this.VIEW.updateHighscores();
                this.VIEW.showHighscores();
                break;
            case EventCodes.SETTINGS_MENU:
                this.VIEW.showSettings();
                break;
            case EventCodes.EXIT:
                this.VIEW.getMainStage().close();
                break;
            case EventCodes.VOL_UP:
                SFX.volUp();
                break;
            case EventCodes.VOL_DOWN:
                SFX.volDown();
                break;
            case EventCodes.TOGGLE_SFX:
                SFX.toggleSFXMute();
                break;
            case EventCodes.TOGGLE_MUSIC:
                SFX.toggleMusicMute();
                break;
        }
    }

//==================GETTERS======================
    public AnchorPane getViewPane() {
        return this.VIEW.getMainPane();
    }

    public Stage getViewStage() {
        return this.VIEW.getMainStage();
    }
}
