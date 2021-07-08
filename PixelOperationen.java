import java.util.Random;
import javafx.scene.paint.Color;

/**
 * Algorithmen zur Änderung der Pixelpositionen eines Pictures
 * z.B. drehen, spiegeln usw.
 *
 * @author S. Gebert
 * @version 06.2021
 */
public class PixelOperationen  implements Bildoperation
{
    private int opCount=5; //number of operations available
    // IDs der geometrischen Operationen
    // Jede geometrische Operation erhält eine eindeutige Zahl,
    // um sich diese besser merken zu können, wird die Zahl einer Konstanten 'static final'
    // mit leserlichem Namen 'OP_<NameDerOperation>' zugeordnet.
    public static final int OP_Nil = 0;
    public static final int OP_SpiegelHorizontal = 1;
    public static final int OP_SpiegelVertikal = 2;
    public static final int OP_DreheLinks = 3;
    public static final int OP_DreheRechts = 4;
    public static final int OP_Drehe180 = 5;

    // ID der aktuell aktiven geometrischen Operation.
    private int op = OP_Nil;

    /**
     * Erstellt eine mit der aktuell aktiven geometrische Operation veränderte Kopie eines Bildes.
     *
     * @param originalBild Das zu verändernde Bild
     * @return Das geänderte Bild
     */
    @Override
    public Picture apply(Picture originalBild)
    {
        // Pro geometrische Operation wird hier eine Zeile benötigt, die die entprechende Operation ausführt.
        switch( this.op ){
            // case OP_SpiegelHorizontal: return spiegelHorizontal(originalBild);
            case OP_DreheRechts:
            case OP_Drehe180:
            case OP_Nil:
            default: return originalBild.copy();
        }    
    }

    /**
     * Wählt eine Operation zum Ausführen via apply aus.
     *
     * @param op Nummer der auszuführenden Operation.
     */
    public void setOperation(int op)
    {
        if(op > this.opCount ) return;
        this.op = op;
    }

    // Anleitung zur Erstellung einer weiteren geometrischen Operation.
    // 1. Erstelle eine Methode mit der Signatur "public Picture meineGeometrischeOperation( Picture originalBild )",
    //    (siehe Beispiele unten)
    // 2. Passe wenn nötig die Methode apply an (siehe oben)
    //    und erstelle falls nötig eine neue Konstante "int OP_meineGeometrischeOperation".
    //

    /** 
     * Spiegelt das Bild, so dass rechts und links getauscht werden
     * @param originalBild Ein Bild (Picture), das gespiegelt werden soll
     */
    public Picture graustufendurchschnitt (Picture originalBild) {
        int breite = originalBild.getWidth();
        int hoehe  = originalBild.getHeight();

        Color[][] pixel = originalBild.getPixelsColorTable();
        Color[][] pixelNeu = new Color[breite][hoehe];

        for(int x=0; x < breite; x++) {
            for(int y=0;y < hoehe; y++) {
                double red=pixel[x][y].getRed();
                double green=pixel[x][y].getGreen();
                double blue=pixel[x][y].getBlue();
                double average= (red+green+blue)/3;
                pixelNeu[x][y] = new Color (average,average, average,1.0);
            }
        }
        Picture neuesBild = originalBild.copy();
        neuesBild.setPixelsArray(pixelNeu);
        return neuesBild;
    }
    public Picture graumin (Picture originalBild) {
        int breite = originalBild.getWidth();
        int hoehe  = originalBild.getHeight();

        Color[][] pixel = originalBild.getPixelsColorTable();
        Color[][] pixelNeu = new Color[breite][hoehe];

        for(int x=0; x < breite; x++) {
            for(int y=0;y < hoehe; y++) {
                double red=pixel[x][y].getRed();
                double green=pixel[x][y].getGreen();
                double blue=pixel[x][y].getBlue();
                double min= Math.min(red, Math.min(green,blue));
                pixelNeu[x][y] = new Color (min,min,min,1.0);
            }
        }
        Picture neuesBild = originalBild.copy();
        neuesBild.setPixelsArray(pixelNeu);
        return neuesBild;
    }
    public Picture graumax (Picture originalBild) {
        int breite = originalBild.getWidth();
        int hoehe  = originalBild.getHeight();

        Color[][] pixel = originalBild.getPixelsColorTable();
        Color[][] pixelNeu = new Color[breite][hoehe];

        for(int x=0; x < breite; x++) {
            for(int y=0;y < hoehe; y++) {
                double red=pixel[x][y].getRed();
                double green=pixel[x][y].getGreen();
                double blue=pixel[x][y].getBlue();
                double max= Math.max(red, Math.max(green,blue));
                pixelNeu[x][y] = new Color (max,max,max,1.0);
            }
        }
        Picture neuesBild = originalBild.copy();
        neuesBild.setPixelsArray(pixelNeu);
        return neuesBild;
    }
    


}
