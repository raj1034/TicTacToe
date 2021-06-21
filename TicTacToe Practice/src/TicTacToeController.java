import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeController implements ActionListener {

    private TicTacToeModel tttm;
    public TicTacToeController(TicTacToeModel tttm){
        this.tttm = tttm;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String[] coordinates = e.getActionCommand().split(" ");
        tttm.play(Integer.parseInt(coordinates[0]), Integer.parseInt(coordinates[1]));

    }
}
