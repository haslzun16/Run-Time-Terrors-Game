package CatGame.Sprite;

/**
 * Cat class extends abstract Sprite class. Initializes the cat and calls on it to move and shoot claws.
 *
 * @author Erika Sudderth, Greg Dwyer Last updated: 4/1/20
 */
import CatGame.ViewManagers.ViewManager;
import javafx.animation.Animation;
import static javafx.animation.Animation.INDEFINITE;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;
import javafx.util.Duration;

public class Cat extends Sprite {

    private final String IMAGE = "/resources/imgs/cat_sprite_map.png";
    private final int DIMENSIONS = 128;
    private final int SPRITE_COLUMNS = 2;
    private final int FRAME_COUNT = 2;
    private final Duration FRAME_DURATION = Duration.millis(700);
    private final int OFFSET = 0;
    private final double STARTING_X = (ViewManager.getWidth() / 2) - (this.DIMENSIONS / 2);
    private final double STARTING_Y = 20;
    private final int CAT_SPEED = 7;
    private final int CLAW_SPEED = 2;
    private int shootingSpeed = 666;
    private Group animationGroup;
    AnchorPane pane;

    /**
     * This constructor sets the image and starting position, then begins the animation.
     */
    public Cat(AnchorPane _pane) {
        this.pane = _pane;
        this.setAnimationFields();
        this.animationGroup = new Group(this.spriteImage);
        _pane.getChildren().add(this.animationGroup);
        this.moveCat();
        this.shootClaws();
    }

<<<<<<< HEAD
    /**
     * Method that shoots a claw every x seconds.
     */
    public void shootClaws() {
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(666), event -> this.createClawPath()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

=======
>>>>>>> 0529dab6a1c7d41f9051289f605321df169f514c
    private void setAnimationFields() {
        this.spriteImage = new ImageView(this.IMAGE);
        this.animation = new SpriteAnimation(this.spriteImage, this.FRAME_DURATION, this.FRAME_COUNT, this.SPRITE_COLUMNS, this.OFFSET, this.OFFSET, this.DIMENSIONS, this.DIMENSIONS);
        this.spriteImage.setViewport(new Rectangle2D(this.OFFSET, this.OFFSET, this.DIMENSIONS, this.DIMENSIONS));
        this.animation.setCycleCount(Animation.INDEFINITE);
        this.animation.play();
    }

    private void moveCat() {
        //Create the lines the cat will follow in sequence.
        Polyline lines = new Polyline(new double[]{
            this.STARTING_X, this.STARTING_Y + this.getCenter(),
            (double) ViewManager.getWidth(), this.STARTING_Y + this.getCenter(),
            0, this.STARTING_Y + this.getCenter(),
            this.STARTING_X, this.STARTING_Y + this.getCenter()
        });

        //Set up the actual movement.
        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.seconds(this.CAT_SPEED));
        pathTransition.setNode(this.animationGroup);
        pathTransition.setPath(lines);
        pathTransition.setCycleCount(INDEFINITE);
        pathTransition.setInterpolator(Interpolator.LINEAR);
        pathTransition.play();
    }

    /**
     * Method that creates a path for shooting the claws. The path begins at the cat's location at the time and continues along a straight line down to the bottom of the screen.
     */
    private void createClawPath() {
        Projectiles claw = new Projectiles(this.pane);
        //Set initial image to beginning of path transition to prevent flickering image.
        claw.getClawGroup().setTranslateX(this.animationGroup.getTranslateX() + this.getCenter());
        claw.getClawGroup().setTranslateY(this.animationGroup.getTranslateY() + this.getCenter());
        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.seconds(this.CLAW_SPEED));
        pathTransition.setNode(claw.getClawGroup());
        //To create a Line: Line(initial x, initital y, final x, final y).
        pathTransition.setPath(new Line(this.animationGroup.getTranslateX() + this.getCenter(), this.STARTING_Y + this.getCenter(), this.animationGroup.getTranslateX() + this.getCenter(), ViewManager.getHeight() + this.getCenter()));
        pathTransition.play();

        //Remove object once it has left the screen.
        pathTransition.onFinishedProperty().set((EventHandler<ActionEvent>) (ActionEvent event) -> {
            claw.removeClaw();
        });

    }

     /**
     * Method that shoots a claw every x seconds.
     */
    private void shootClaws() {
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(this.shootingSpeed), event -> this.createClawPath()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

//=================  GETTERS ===============
    public Group getAnimationGroup() {
        return this.animationGroup;
    }

    public int getDimensions() {
        return this.DIMENSIONS;
    }

    public int getCenter() {
        return this.DIMENSIONS / 2;
    }

    public int getXPos() {
        return (int) this.animationGroup.getTranslateX() + this.getCenter();
    }

    public int getYPos() {
        return (int) this.animationGroup.getTranslateY() + this.getCenter();
    }
}
