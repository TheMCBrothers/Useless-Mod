package themcbros.uselessmod.color;

import themcbros.uselessmod.api.color.IColorHandler;

public class DummyColorHandler implements IColorHandler {

    public static IColorHandler INSTANCE = new DummyColorHandler();

    private DummyColorHandler() {
    }

    @Override
    public int getColor() {
        return -1;
    }

    @Override
    public void setColor(int color) {
    }
}
