package io.baik.xo.controller;

import io.baik.xo.model.Field;
import io.baik.xo.model.Figure;
import io.baik.xo.model.exeptions.AlreadyOccupiedException;
import io.baik.xo.model.exeptions.InvalidPointException;

import java.awt.*;

public class MoveController {

    public void applyFigure(final Field field,
                            final Figure figure,
                            final Point point) throws AlreadyOccupiedException,
                                                      InvalidPointException {
        if (field.getFigure(point) != null) {
            throw new AlreadyOccupiedException();

        }
        field.setFigure(point, figure);
    }

}
