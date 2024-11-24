package controller;
import model.Board;

public abstract class GameController extends Controller<Board> {
    public GameController(Board board) {
        super(board);
    }
}
