import java.util.Random;

/**
 * Algorithmen zur Änderung der Pixelpositionen eines Pictures
 * z.B. drehen, spiegeln usw.
 *
 * @author Simon Gebert
 * @version 06.2021
 */
public class GeometrischeBildoperationen  extends Bildoperation
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
     * @param originalbild Das zu verändernde Bild
     * @return Das geänderte Bild
     */
    @Override
    public Picture apply(Picture originalbild)
    {
        Picture neuesBild;
        // Pro geometrische Operation wird hier eine Zeile benötigt, die die entprechende (private) Methode aufruft
        switch( this.op ){
            case OP_SpiegelHorizontal: neuesBild = spiegelHorizontal(originalbild);break;
            case OP_SpiegelVertikal: neuesBild =  spiegelVertikal(originalbild);break;
            case OP_DreheLinks: neuesBild = dreheLinks(originalbild);break;
            case OP_DreheRechts:
            case OP_Drehe180:
            case OP_Nil:
            default: neuesBild = originalbild.copy();
        }
        return neuesBild;     
    }

    public void setOp(int op)
    {
        if(op > this.opCount ) return;
        this.op = op;
    }

    // Anleitung zur Erstellung e<iner weiteren geometrischen Operation.
    // 1. Erstelle eine public Methode geometrischeOperation( Picture originalBild ),
    //    die die aktuell gewählte Operation festlegt und anschließend ausführt. (siehe Beispiele unten)
    // 2. Erstelle eine private Methode geometrischeOperation( Picture originalBild ),
    //    diese führt die tatsächliche Operation durch. (siehe Beispiele unten)
    //    Mit pixelsExplode( ... ) und pixelsFlatten(...) können Bilddaten 
    //    zwischen eindimensionaler und zweidimensionaler Darstellung umgewandelt werden.
    //

    /** 
     * spiegeleHorizontal spiegelt das Bild, so dass rechts und links getauscht werden
     * @param originalbild Ein Bild (Picture), das gespiegelt werden soll
     */
    public Picture spiegelHorizontal(Picture originalbild) {
        this.op = OP_SpiegelHorizontal; 

        int breite = originalbild.getWidth();
        int hoehe  = originalbild.getHeight();

        int[][] pixel = originalbild.getPixelsTable();
        int[][] pixelNeu = new int[breite][hoehe];

        for(int x=0; x < breite; x++) {
            for(int y=0;y < hoehe; y++) {
                pixelNeu[x][y] = pixel[(breite-1)-x][y];
            }
        }
        Picture neuesBild = originalbild.copy();
        neuesBild.setPixelsArray(pixelNeu);
        return neuesBild;
    }

    /** 
     * spiegeleVertikal spiegelt das Bild, so dass oben und unten getauscht werden
     * @param originalbild Ein Bild (Picture), das gespiegelt werden soll
     * @return Eine gespiegelte Kopie des Bildes
     */
    public Picture spiegelVertikal(Picture originalbild) {
        this.op = OP_SpiegelVertikal; 
        // originalbild.runOp(  );

        int breite = originalbild.getWidth();
        int hoehe  = originalbild.getHeight();

        int[][] pixel = originalbild.getPixelsTable();
        int[][] pixelNeu = new int[breite][hoehe];

        for(int x=0; x < breite; x++) {
            for(int y=0;y < hoehe; y++) {
                pixelNeu[x][y] = pixel[x][(hoehe-1)-y];
            }
        }
        Picture neuesBild = originalbild.copy();
        neuesBild.setPixelsArray(pixelNeu); 
        return neuesBild;
    }

    public Picture dreheLinks( Picture originalbild) {
        this.op = OP_DreheLinks;
        //originalbild.runOp(  );

        return originalbild.copy();
    }

}
