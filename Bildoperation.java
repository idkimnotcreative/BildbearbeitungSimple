/**
 * Abstrakte Klasse Bildoperationen,
 * Implementiert einige nützliche Methoden zur Nutzung in Bildoperationen.
 * 
 * @author S. Gebert
 * @version 06.2021
 */
public abstract class Bildoperation
{
    public static final int RGB = 1;
    public static final int HSB = 3;

    public abstract Picture apply(Picture originalbild);
}
