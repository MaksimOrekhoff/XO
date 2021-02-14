package io.baik.xo.controller;

import io.baik.xo.model.Field;
import io.baik.xo.model.Figure;
import io.baik.xo.model.exeptions.InvalidPointException;

import java.awt.*;

public class WinnerController {
    private Figure figure;

    public Figure getWinner(Field field) throws InvalidPointException {
        try {

            for (int i = 0; i < 3; i++) {
                if (check(field, new Point(i, 0),
                        p -> new Point(p.x, p.y + 1)))
                    return field.getFigure(new Point(i, 0));
            }
            for (int i = 0; i < 3; i++) {
                if (check(field, new Point(0, i),
                        p -> new Point(p.x + 1, p.y)))
                    return field.getFigure(new Point(0, i));
                if (check(field, new Point(0, 0),
                        p -> new Point(p.x + 1, p.y + 1)))
                    return field.getFigure(new Point(0, 0));
                if (check(field, new Point(0, 2),
                        p -> new Point(p.x + 1, p.y - 1)))
                    return field.getFigure(new Point(1, 1));
            }
            if (field.getFigure(new Point(0, 0))
                    == field.getFigure(new Point(0, 1))
                    && field.getFigure(new Point(0, 0))
                    == field.getFigure(new Point(0, 2)))
                return field.getFigure(new Point(0, 0));

            if (field.getFigure(new Point(1, 0))
                    == field.getFigure(new Point(1, 1))
                    && field.getFigure(new Point(1, 0))
                    == field.getFigure(new Point(1, 2)))
                return field.getFigure(new Point(1, 0));

            if (field.getFigure(new Point(2, 0))
                    == field.getFigure(new Point(2, 1))
                    && field.getFigure(new Point(2, 0))
                    == field.getFigure(new Point(2, 2)))
                return field.getFigure(new Point(2, 0));
        } catch (InvalidPointException e) {
            e.printStackTrace();
        }
        return null;

    }

    private boolean check(final Field field,
                          final Point currentPoint,
                          final IPointGenerator pointGenerator) throws InvalidPointException {

        final Figure currentFigure;
        final Figure nextFigure;
        final Point nextPoint = pointGenerator.next(currentPoint);
        try {

            currentFigure = field.getFigure(currentPoint);
            nextFigure = field.getFigure(currentPoint);

        } catch (InvalidPointException e) {
            return true;
        }

        if (currentFigure == null) return false;

        if (currentFigure != nextFigure) return false;

        return check(field, nextPoint, pointGenerator);


    }

    private interface IPointGenerator {
        public Point next(final Point point);
    }


}
