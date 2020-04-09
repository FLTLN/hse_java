public class SymbolCounter 
{
    SymbolCounter() {symbolCount = new int[52]; };

    public String toString()
    {
        String res = new String();

        for(int i = 0; i < 51; i++)
        {
            // Не нашёл способа сделать это без C-style форматирования. Плохо искал скорее всего.
            if(symbolCount[i] != 0)
            {
                res = res + "letter " + getCharFromIndex(i) 
                          + " number of occurrences " + String.format("%-6d", symbolCount[i]) 
                          + " occurrence frequency " + (float)symbolCount[i]/(float)symbolsCount 
                          + "\n";

            }
        }

        if(res == "")
        {
            return "No letters found";
        }
        return res;
    }

    public void count(final String input) {
        for (int i = 0; i < input.length(); i++) {
            symbolsCount++;
            symbolCount[getIndexFromChar(input.charAt(i))] += 1;
        }
    }

    public void reset() {
        symbolsCount = 0;
        for (int i = 0; i < 51; i++) {
            symbolCount[i] = 0;
        }
    }

    private int getIndexFromChar(final char symbol) {
        int index = (int) symbol;
        index -= 65;
        if (index > 25) {
            index -= 7;
        }
        if ((index < 0) || (index > 50)) {
            return 51;
        }

        return index;
    }

    private char getCharFromIndex(int index) {
        if (index <= 25) {
            index += 65;
        } else {
            index += 72;
        }

        return (char) index;
    }

    private final int[] symbolCount;
    private long symbolsCount;
}