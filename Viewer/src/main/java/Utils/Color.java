package Utils;

public class Color {
    public int red;
    public int green;
    public int blue;
    public float alpha;

    public Color(byte r, byte g, byte b, float a) {
        this.red = (int)(r / 255f) * Integer.MAX_VALUE;
        this.green = (int)(g / 255f) * Integer.MAX_VALUE;
        this.blue = (int)(b / 255f) * Integer.MAX_VALUE;
        this.alpha = a;
    }

    public static Color RED = new Color((byte)255, (byte)0, (byte)0, 0);
    public static Color GREEN = new Color((byte)0, (byte)255, (byte)0, 0);
    public static Color BLUE = new Color((byte)0, (byte)0, (byte)255, 0);
    public static Color WHITE = new Color((byte)255, (byte)255, (byte)255, 0);
    public static Color BLACK = new Color((byte)0, (byte)0, (byte)0, 0);
}
