package Utils;

public class Color {
    public int red;
    public int green;
    public int blue;
    public float alpha;

    public Color(short r, short g, short b, float a) {
        this.red = (int)(((float)(r % 256)) / 255f * Integer.MAX_VALUE);
        this.green = (int)(((float)(g % 256)) / 255f * Integer.MAX_VALUE);
        this.blue = (int)(((float)(b % 256)) / 255f * Integer.MAX_VALUE);
        this.alpha = a;
    }

    public static Color RED = new Color((short)255, (short)0, (short)0, 0);
    public static Color GREEN = new Color((short)0, (short)255, (short)0, 0);
    public static Color BLUE = new Color((short)0, (short)0, (short)255, 0);
    public static Color WHITE = new Color((short)255, (short)255, (short)255, 0);
    public static Color BLACK = new Color((short)0, (short)0, (short)0, 0);
    public static Color GREY = new Color((short)100, (short)100, (short)100, 0);
    public static Color YELLOW = new Color((short)255, (short)255, (short)0, 0);
}
