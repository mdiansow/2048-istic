package ihm;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import model.Tile;

/**
 * Created by mds on 17/03/15.
 * Class ${CLASS}
 */
public class TileUI extends Label {

    private Location tileLocation;

    private String value;

    private Tile labelTile;

    public TileUI(Tile tile) {
        this.labelTile = tile;

        final int squareSize = BoardUI.CELL_SIZE - 13;
        setMinSize(squareSize, squareSize);
        setMaxSize(squareSize, squareSize);
        setPrefSize(squareSize, squareSize);
        setAlignment(Pos.CENTER);

        if (labelTile != null) {
            this.value = "" + labelTile.getRank();
        }
//        this.merged = false;
        setText(this.value);
        getStyleClass().addAll("game-label", "game-tile-" + this.value);
    }
}