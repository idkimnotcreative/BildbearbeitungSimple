import processing.core.PApplet;
import processing.core.PImage;
import java.util.Objects;
import java.io.File;
import javafx.scene.paint.Color;

import View.Sketch;

/**
 * Ein Bild.
 * 
 * @author S. Gebert 
 * @version 06.2021
 */
public class Picture
{
    protected Sketch sketch;
    private PImage displayImg;
    private String initialImg;
    private int width = 600;
    private int height = 400;
    private int[] pixels;

    /**
     * Picture Konstruktor
     *
     */
    public Picture()
    {
    }

    /**
     * Picture Konstruktor
     * Erstellt ein Bild 
     * und läd das angegebene Bild aus dem images-Ordner
     * (bisher nur zum Zeitpunkt der Darstellung)
     *
     * @param filename 
     */
    public Picture(String filename)
    {
        this.initialImg = filename;      
    }

    /**
     * Getter für die Pixeldaten des Bildes
     *
     * @return Ein eindimensionales Array
     */
    public int[] getPixelsArray()
    {
        return this.pixels;
    }

    /**
     * Getter für die Pixeldaten des Bildes
     *
     * @return Ein zweidimensionales Array
     */
    public int[][] getPixelsTable()
    {
        return pixelsExplode(this.pixels, this.width, this.height);
    }

    /**
     * Setter für die Pixeldaten des Bildes
     *
     * @param pixelsArray Ein zweidimensionales Array.
     */
    public void setPixelsArray( int[][] pixelsTable )
    {
        setPixelsArray(pixelsFlatten(pixelsTable));
    }

    /**
     * Setter für die Pixel des Bildes
     *
     * @param pixelsArray Ein eindimensionales Array.
     */
    public void setPixelsArray( int[] pixelsArray )
    {
        if (isDisplay()) displayImg.loadPixels();
        this.pixels = pixelsArray; 
        if (isDisplay()) displayImg.updatePixels();
    }

    /**
     * Getter für die Breite des Bildes
     *
     * @return Breite
     */
    public int getWidth()
    {
        return this.width;
    }

    /**
     * Getter für die Höhe des Bildes
     *
     * @return Höhe
     */
    public int getHeight()
    {
        return this.height;
    }

    /**
     * Setter für die Breite und Höhe des Bildes
     *
     * @param width Breite
     * @param height Höhe
     */
    public void setDimensions (int width, int height)
    {
        this.width = width;
        this.height = height;
    }

    /**
     * Erstellt eine Kopie des aktuellen Bildes
     *
     * @return Kopie des Bildes.
     */
    public Picture copy()
    {
        Picture cpy = new Picture();
        cpy.setDimensions(this.width, this.height);
        cpy.setPixelsArray(this.pixels.clone());
        return cpy;
    }

    /**
     * Wendet eine Bildoperation auf das Bild an
     *
     * @param op Die anzuwendende Operation
     */
    public void applyOperation(Bildoperation op)
    {
        Picture pic = op.apply(this);
        width = pic.width;
        height = pic.height;
        pixels = pic.pixels;
        if( isDisplay() ) updateDisplay(); 
    }

    /*** View Methoden (beeinflussen die Anzeige) ***/

    private boolean isDisplay(){
        return Objects.nonNull(sketch);
    }

    /**
     * Zeigt das Bild an.
     *
     */
    public void display()
    {
        if( isDisplay() ) return;

        sketch = new Sketch();
        PApplet.runSketch(new String[]{"sketch"}, sketch);  
        if( Objects.nonNull(this.initialImg) && this.initialImg != "" ){
            load(initialImg);
        } else
        { 
            displayImg = sketch.createImage(width, height, PApplet.RGB);
            updateDisplay();
        }
    }

    /**
     * Läd ein Bild aus dem images Ordner
     * Funktioniert aktuell nur, wenn das Display aktiv ist.
     *
     * @param dateiname Der Dateiname
     */
    public void load(String dateiname) {
        //@FEATURE-REQUEST: enable without sketch
        if( !isDisplay()) return;
        sketch.load("images/"+dateiname); 
        displayImg = sketch.readImage();
        width = displayImg.width;
        height = displayImg.height;
        pixels = displayImg.pixels;
    }

    /**
     * Speichert das aktuell dargestellte Bild im images Ordner
     * Funktioniert aktuell nur, wenn das Display aktiv ist.
     *
     * @param dateiname Der gewünschte Dateiname
     */
    public void save(String dateiname) {
        //@FEATURE-REQUEST: enable without sketch
        if( !isDisplay()) return;
        sketch.save( new File("images/"+dateiname).getAbsolutePath() );
    }

    private void updateDisplay()
    {
        if (isDisplay()) sketch.updateImage(displayImg, pixels);  
    }

    /*** Statische Methoden (auch ohne konkretes Bildobjekt von außen nutzbar) ***/

    /**
     * Wandelt ein Color-Objekt in einen Farbwert um.
     *
     * @param color Ein Color Objekt
     * @return Farbwert
     */
    public static int color(Color color)
    {
        int R = (int)Math.round(255 * color.getRed());
        int G = (int)Math.round(255 * color.getGreen());
        int B = (int)Math.round(255 * color.getBlue());

        R = (R << 16) & 0x00FF0000; 
        G = (G << 8) & 0x0000FF00; 
        B = B & 0x000000FF; 

        return 0xFF000000 | R | G | B; 
    } 

    /**
     * Wandelt Bilddaten (Pixel) aus einem eindimensionalen Array zu Bilddaten in einem zweidimensionalen Array um.
     * @param pixels Eindimensionales Array mit Bilddaten
     * @param width Breite des Bildes
     * @param height Höhe des Bildes
     * @return Ein zweidimensionales Array, der gegebenen Breite und Höhe
     */
    public static int[][] pixelsExplode( int[] pixels, int width, int height )
    {
        int[][] pixels2D = new int[width][height];
        for(int i = 0; i < width; i++){
            for( int k = 0; k < height; k++ ){
                pixels2D[i][k] = pixels[i + k*width];

            }
        }
        return pixels2D;
    }

    /**
     * Wandelt Bilddaten (Pixel) aus einem zweidimensionalen Array zu Bilddaten in einem eindimensionalen Array um.
     * @param pixels Zweidimensionales Array mit Bilddaten
     * @return Ein eindimensionales Array mit den Bilddaten
     */
    public static int[] pixelsFlatten( int[][] pixels2D )
    {
        int[] pixels = new int[ pixels2D.length * pixels2D[0].length ];
        for( int i = 0; i < pixels2D.length; i++ ){
            for( int k = 0; k < pixels2D[0].length; k++ ){
                pixels[i+k*pixels2D.length] = pixels2D[i][k];
            }
        }
        return pixels;
    }

}
