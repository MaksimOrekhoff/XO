package io.baik.xo.model;

import io.baik.xo.model.exeptions.AlreadyOccupiedException;
import io.baik.xo.model.exeptions.InvalidPointException;

import java.awt.*;

public class Field {

    private final int INT_SIZE = 3;

    private final int MIN_COORDINATE = 0;

    private final int MAX_COORDINATE = INT_SIZE;

    private final Figure[][] field = new Figure[INT_SIZE][INT_SIZE];
    
    public int getSize() {
        return INT_SIZE;
    }

    public Figure getFigure(Point point) throws InvalidPointException {
        if (!checkPoint(point)) {
            throw new InvalidPointException();
        }
        return field[point.x][point.y];
    }

    public void setFigure(final Point point, Figure figure) throws InvalidPointException, AlreadyOccupiedException {
        if (!checkPoint(point)) {
            throw new InvalidPointException();
        }

        if (field[point.x][point.y] != null) {
            throw new AlreadyOccupiedException();

        }
        field[point.x][point.y] = figure;
    }

    private boolean checkPoint(final Point point) {
        return checkCoordinate(point.x) && checkCoordinate(point.y);
    }

    private boolean checkCoordinate(final int coordinate) {
        return coordinate >= MIN_COORDINATE && coordinate < MAX_COORDINATE;
    }
}
