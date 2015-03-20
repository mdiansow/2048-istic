package ihm;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.Board;
import model.Tile;
import sample.Controller;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by mds on 17/03/15.
 * Class ${CLASS}
 */
public class BoardUI extends Application implements Observer {

    public static final int CELL_SIZE = 128;
    private static final int BORDER_WIDTH = (14 + 2) / 2;
    private static final int TOP_HEIGHT = 92;
    private static final int GAP_HEIGHT = 50;
    private static final String FONT_NAME = "Arial";

    private GridPane grid;

    private Controller c;
    private Tile[][] data;
    private boolean isStart = false;

    @Override
    public void start(Stage stage) throws Exception {
        Group root = new Group();
        Scene scene = new Scene(root, 500, 600);
        stage.setTitle("2048");
        stage.setResizable(false);
        this.c = new Controller(this);

        grid = new GridPane();
        grid.setAlignment(Pos.CENTER);

        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setGridLinesVisible(true);

        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (isStart && event.getCode().toString().equals("RIGHT")) {
                    System.out.println("Key type  " + event.getCode().getName());
                    c.right();
                }
                if (isStart && event.getCode().toString().equals("LEFT")) {
                    System.out.println("Key type  " + event.getCode().getName());
                    c.left();
                }
                if (isStart && event.getCode().toString().equals("UP")) {
                    System.out.println("Key type  " + event.getCode().getName());
                    c.up();
                }
                if (isStart && event.getCode().toString().equals("DOWN")) {
                    System.out.println("Key type  " + event.getCode().getName());
                    c.down();
                }
            }
        });
        root.getChildren().add(grid);
        grid.setPadding(new Insets(10));

        HBox hbox = new HBox();
        hbox.setPadding(new Insets(10, 10, 10, 10));
        hbox.setSpacing(10);
        hbox.setStyle("-fx-background-color: #336699;");

        Button startButton = new Button("Start");
        startButton.setPrefSize(100, 20);
        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                isStart = !isStart;
                if(isStart){
                    startButton.setText("Stop");
                }else {
                    startButton.setText("Start");
                }
                c.up();
            }
        });
        hbox.setSpacing(8);
        hbox.getChildren().add(startButton);

        root.getChildren().add(hbox);

        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Board) {
            data = (Tile[][]) arg;
            grid.getChildren().clear();
            grid.setAlignment(Pos.CENTER);

            for (int i = 0; i < Controller.SIDE_SIZE_IN_SQUARES; i++) {
                for (int j = 0; j < Controller.SIDE_SIZE_IN_SQUARES; j++) {
                    Tile tile = data[i][j];
                    int value = 0;
                    if (tile != null) {
                        value = tile.getRank();
                    }
                    final int size = value < 100 ? 36 : value < 1000 ? 32 : 24;
                    final Font font = new Font(FONT_NAME, size);
                    Label label = new TileUI(tile);
                    label.setFont(font);
                    grid.add(label, j, i);
                }
            }


            grid.setPadding(new Insets(25, 25, 25, 25));
            grid.setGridLinesVisible(true);
            System.out.println("data \t" + data.toString());
        }
    }
}
