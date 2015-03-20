package sample;

import ihm.BoardUI;
import model.Board;
import model.BoardImpl;

import java.util.Random;
import java.util.logging.Logger;

public class Controller {
    public static final int SIDE_SIZE_IN_SQUARES = 4;
    private BoardUI game;
    private Board board;

    public Controller( BoardUI game) {
        this.game = game;
        board = new BoardImpl(SIDE_SIZE_IN_SQUARES);
        board.loadBoard(initMatrix());
        board.addObserver(this.game);
    }

    private int[][] initMatrix() {
        int[][] res = new int[SIDE_SIZE_IN_SQUARES][SIDE_SIZE_IN_SQUARES];
        int firstX = generateXYValue();
        int firstY = generateXYValue();

        int lastX = generateXYValue();
        int lastY = generateXYValue();

        res[firstX][firstY] = new Random().nextDouble() < 0.9 ? 2 : 4;
        res[lastX][lastY] = new Random().nextDouble() < 0.9 ? 2 : 4;

        return res;
    }

    int generateXYValue() {
        return new Random().nextInt(SIDE_SIZE_IN_SQUARES);
    }

    public void right() {
        board.packIntoDirection(Board.Direction.RIGHT);
        board.commit();
        board.printBoard(Logger.getGlobal(), "Right");
    }

    public void left() {
        board.packIntoDirection(Board.Direction.LEFT);
        board.commit();
        board.printBoard(Logger.getGlobal(), "left");
    }

    public void up() {
        board.packIntoDirection(Board.Direction.TOP);
        board.commit();
        board.printBoard(Logger.getGlobal(), "TOP");
    }

    public void down() {
        board.packIntoDirection(Board.Direction.BOTTOM);
        board.commit();
        board.printBoard(Logger.getGlobal(), "down");
    }
}