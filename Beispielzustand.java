

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Die Test-Klasse BeispielbildTest.
 *
 * @author  (Ihr Name)
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Beispielzustand
{
    private Picture p1;
    private GeometrischeBildoperationen op1;

    /**
     * Konstruktor fuer die Test-Klasse Beispielzustand
     */
    public Beispielzustand()
    {
    }

    /**
     *  Setzt das Testgerüst fuer den Test.
     *
     * Wird vor jeder Testfall-Methode aufgerufen.
     */
    @Before
    public void setUp()
    {
        p1 = new Picture("iris.jpg");
        op1 = new GeometrischeBildoperationen();
        op1.setOp(1);
        p1.display();
    }

    /**
     * Gibt das Testgerüst wieder frei.
     *
     * Wird nach jeder Testfall-Methode aufgerufen.
     */
    @After
    public void tearDown()
    {
    }
}
