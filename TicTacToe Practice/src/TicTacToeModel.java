import java.util.ArrayList;
import java.util.List;

public class TicTacToeModel {

    public static final int SIZE = 3;
    public static final boolean X = true;
    public static final boolean O = false;

    public enum Status {X_WON, O_WON, TIE, UNDECIDED};

    private char[][] grid;
    private boolean turn;
    private Status status;

    private List<TicTacToeViews> ticTacToeViews;


    public TicTacToeModel() {
        grid = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                grid[i][j] = ' ';
            }
        }
        turn = X;
        status = Status.UNDECIDED;

        ticTacToeViews = new ArrayList<>();

    }

    public void addTicTacToeView(TicTacToeViews tttv){

        ticTacToeViews.add(tttv);
    }

    public void removeTicTacToeView(TicTacToeViews tttv){

        ticTacToeViews.remove(tttv);
    }


    private void changeTurn() {
        turn = !turn;
    }


    private Status updateStatus() {
        for(int i = 0 ; i < SIZE ; i++) {
            if (grid[i][0] == grid[i][1] && grid[i][1] == grid[i][2] && grid[i][0] != ' ') {
                if (grid[i][0] == 'X') {
                    return Status.X_WON;
                } else {
                    return Status.O_WON;
                }
            } else if (grid[0][i] == grid[1][i] && grid[1][i] == grid[2][i] && grid[0][i] != ' ') {
                if (grid[0][i] == 'X') {
                    return Status.X_WON;
                } else {
                    return Status.O_WON;
                }
            }
        }
        if (grid[0][0] == grid[1][1] && grid[1][1] == grid[2][2] && grid[0][0] != ' '){
            if(grid[0][0] == 'X'){
                return Status.X_WON;
            }
            else{
                return Status.O_WON;
            }
        }
        else if (grid[0][2] == grid[1][1] && grid[1][1] == grid[2][0] && grid[0][2] != ' '){
            if(grid[0][2] == 'X'){
                return Status.X_WON;
            }
            else{
                return Status.O_WON;
            }
        }
        for(int i = 0 ; i < SIZE ; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (grid[i][i] == ' ') {
                    return Status.UNDECIDED;
                }
            }
        }
        return Status.TIE;
    }

    public Status getStatus() {return status;}

    public boolean getTurn() {return turn;}

    public void play(int x, int y) {
        if (grid[x][y] != ' ') return;
        grid[x][y] = turn? 'X' : 'O';
        status = updateStatus();
        for(TicTacToeViews tttv : ticTacToeViews){
            tttv.handleTicTacToeUpdate(new TicTacToeEvent(this, status, x, y));
        }
        changeTurn();
    }
}
