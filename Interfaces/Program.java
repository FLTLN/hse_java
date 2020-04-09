/**
 * 
 * @author FLTLN
 * 
 * Main class for program with interface.
 * 
 */
class Program
{
    /**
     * 
     * Call "System.out.print" from main.
     * 
     * @param args arguments string
     * 
     */
    public static void main(final String args[])
    {
        Pokemon pokemon = new Pikachu();
        pokemon.choose();

        pokemon = new Majicarp();
        pokemon.choose();
    }
}