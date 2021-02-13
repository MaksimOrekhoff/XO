package io.baik.xo.model;

import java.awt.*;

public class Field {

    private final int INT_SIZE = 3;

    private final Figure[][] field = new Figure[INT_SIZE][INT_SIZE];
    
    public int getSize() {
        return INT_SIZE;
    }

    public Figure getFigure(Point point) {
        return field[point.x][point.y];
    }

    public void setFigure(final Point point, Figure figure) {
        field[point.x][point.y] = figure;
    }
}
