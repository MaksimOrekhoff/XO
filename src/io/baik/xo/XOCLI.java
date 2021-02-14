package io.baik.xo;

import io.baik.xo.model.Field;
import io.baik.xo.model.Figure;
import io.baik.xo.model.Game;
import io.baik.xo.model.Player;
import io.baik.xo.view.ConsoleView;

public class XOCLI {
    public static void main(final String[] args) {
        final String name1 = "Maksim";
        final String name2 = "Katya";
        final Player[] players = new Player[2];
        players[0] = new Player(name1, Figure.X);
        players[1] = new Player(name2, Figure.O);
        final Game gameXO = new Game(players, new Field(3), "XO");

        final ConsoleView consoleView = new ConsoleView();
        while (consoleView.move(gameXO)) {
            consoleView.show(gameXO);
        }

    }
}
