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

    public static Color RED(){
        return new Color((byte)255, (byte)0, (byte)0, 0);
    }

    public static Color GREEN(){
        return new Color((byte)0, (byte)255, (byte)0, 0);
    }

    public static Color BLUE(){
        return new Color((byte)0, (byte)0, (byte)255, 0);
    }
}
