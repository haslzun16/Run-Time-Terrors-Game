package CatGame.Models;

/**
 * This class lays out the keyboard functionality for gameplay. Author(s): Greg Dwyer, Erika Sudderth Last Updated: 3/25/20
 */
import CatGame.Controller.GameController;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class KeyboardInput extends Input {

    private GameController controller;
    private boolean up = false;
    private boolean down = false;
    private boolean left = false;
    private boolean right = false;
    private boolean space = false;
    private boolean esc = false;

    public KeyboardInput(GameController _cont) {
        this.controller = _cont;
        this.setKeyboardPressedAction();
        this.setKeyboardReleasedAction();
    }

    /**
     * This method sets the behavior of the keys for the game
     */
    private void setKeyboardPressedAction() {
        this.controller.getViewScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:
                        up = true;
                        if (down) {
                            down = false;
                        }
                        break;
                    case DOWN:
                        down = true;
                        if (up) {
                            up = false;
                        }
                        break;
                    case LEFT:
                        left = true;
                        if (right) {
                            right = false;
                        }
                        break;
                    case RIGHT:
                        right = true;
                        if (left) {
                            left = false;
                        }
                        break;
                    case SPACE:
                        space = true;
                        break;
                    case ESCAPE:
                        esc = true;
                        break;
                }
            }
        }
        );
    }

    private void setKeyboardReleasedAction() {
        this.controller.getViewScene().setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:
                        up = false;
                        break;
                    case DOWN:
                        down = false;
                        break;
                    case LEFT:
                        left = false;
                        break;
                    case RIGHT:
                        right = false;
                        break;
                    case SPACE:
                        space = false;
                        break;
                    case ESCAPE:
                        esc = false;
                        break;
                }
            }
        }
        );
    }

//=================  GETTERS ===============
    public boolean isUp() {
        return up;
    }

    public boolean isDown() {
        return down;
    }

    public boolean isLeft() {
        return left;
    }

    public boolean isRight() {
        return right;
    }

    public boolean isSpace() {
        return space;
    }

    public boolean isEsc() {
        return esc;
    }

}