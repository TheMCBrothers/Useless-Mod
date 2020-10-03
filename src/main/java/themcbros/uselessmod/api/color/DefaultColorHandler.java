package themcbros.uselessmod.api.color;

public class DefaultColorHandler implements IColorHandler {

    public static final IColorHandler INSTANCE = new DefaultColorHandler(-1);

    private int color;

    private DefaultColorHandler(int color) {
        this.color = color;
    }

    @Override
    public int getColor() {
        return this.color;
    }

    @Override
    public void setColor(int color) {
        this.color = color;
    }
}
