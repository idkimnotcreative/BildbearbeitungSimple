import java.util.Random;
import javafx.scene.paint.Color;

/**
 * Algorithmen zur Anwendung von Filtern auf ein Picture
 * z.B. drehen, spiegeln usw.
 *
 * @author S. Gebert
 * @version 06.2021
 */
public class Mehrpixeloperationen  implements Bildoperation
{
    /**
     * Erstellt eine mit der aktuell aktiven Operation veränderte Kopie eines Bildes.
     *
     * @param originalBild Das zu verändernde Bild
     * @return Das geänderte Bild
     */
    @Override
    public Picture apply(Picture originalBild)
    {
        return originalBild.copy();   
    }

    public Picture faltung (Picture originalBild, double[][] filter)
    {   int width = originalBild.getWidth();
        int height  = originalBild.getHeight();
        int length = filter.length;
        int half = length/2;

        Color[][] pixel = originalBild.getPixelsColorTable();
        Color[][] pixelNew = pixel.clone();

        for(int x=half; x<originalBild.getWidth()-half; x++) {
            for(int y=half; x<originalBild.getHeight()-half; y++) {
                double red = 0.0;
                double green = 0.0;
                double blue = 0.0;
                int xx = x - half;
                int yy = y - half;
                for (int i = 0; i < length; i++){
                    for (int j = 0; j < length; j++){
                        red += filter [i][j] * pixel [xx + i][yy + j].getRed();
                        green += filter [i][j] * pixel [xx + i][yy + j].getGreen();
                        blue += filter [i][j] * pixel [xx + i][yy + j].getBlue();
                    }
                }
                if (red < 0.0) red = 0.0;
                if (red > 1.0) red = 1.0;
                if (green < 0.0) green = 0.0;
                if (green > 1.0) green = 1.0;
                if (blue < 0.0) blue = 0.0;
                if (blue > 1.0) blue = 1.0;


                pixelNew[x][y] = new Color (red, green, blue, 1.0);
            }
        }
        Picture neuesBild = originalBild.copy();
        neuesBild.setPixelsArray(pixelNew);
        return neuesBild;

    }
}
