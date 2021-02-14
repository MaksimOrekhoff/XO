package io.baik.xo.view;

import io.baik.xo.controller.CurrentMoveController;
import io.baik.xo.controller.MoveController;
import io.baik.xo.controller.WinnerController;
import io.baik.xo.model.Field;
import io.baik.xo.model.Figure;
import io.baik.xo.model.Game;
import io.baik.xo.model.exeptions.AlreadyOccupiedException;
import io.baik.xo.model.exeptions.InvalidPointException;

import java.awt.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleView {
    final private CurrentMoveController currentMoveController =
            new CurrentMoveController();

    final WinnerController winnerController =
            new WinnerController();

    final MoveController moveController = new MoveController();

    public void show(final Game game) {
        System.out.printf("Game name: %s\n", game.getName());
        final Field field = game.getField();
        for (int x = 0; x < field.getSize(); x++) {
            if (x != 0) printSeparator();
            printLine(field, x);
        }

    }

    private void printLine(final Field field, final int x) {


        for (int y = 0; y < field.getSize(); y++) {
            if (y != 0) System.out.print("|");
            System.out.print(" ");
            final Figure figure;
            try {
                figure = field.getFigure(new Point(y, x));
            } catch (final InvalidPointException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
            System.out.print(figure != null ? figure : " ");
            System.out.print(" ");
        }
        System.out.println();
    }

    private void printSeparator() {
        System.out.println("-----------");
    }

    private Point askPoint() {
        return new Point(askCoordinate("X") - 1,
                askCoordinate("Y") - 1);
    }

    private int askCoordinate(final String coordinateName) {
        System.out.printf("Please input %s ", coordinateName);
        final Scanner scanner = new Scanner(System.in);
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Enter number");
            return askCoordinate(coordinateName);
        }

    }

    public boolean move(final Game game) {
        try {
            final Field field = game.getField();
            final Figure winner = winnerController.getWinner(field);
            if (winner != null) {
                System.out.printf("Winner is %s\n", winner);
                return false;
            }

            final Figure currentFigure =
                    currentMoveController.currentMove(field);
            if (currentFigure == null) {
                System.out.println("No winner and no moves left!");
                return false;
            }
            System.out.printf("Please enter move point for: %s\n", currentFigure);
            final Point point = askPoint();
            try {
                moveController.applyFigure(field, currentFigure, point);
            } catch (AlreadyOccupiedException e) {

                System.out.println("Point is invalid!");
            }
        } catch (InvalidPointException e) {
            e.printStackTrace();
        }
        System.out.println();
        return true;
    }

}
