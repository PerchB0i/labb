import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class DeathCauseStatistic {
    private String ICDCode;
    private int[] deathsPerAge;

    public static DeathCauseStatistic fromCsvLine(String line) {
        String[] fields = line.split(",");
        DeathCauseStatistic newCause = new DeathCauseStatistic();
        newCause.ICDCode = fields[0].trim();
        for (int i = 0; i < 20; i++)
        {
            if (fields[i+2].equals("-"))
            {
                newCause.deathsPerAge[i] = 0;
            } else {
                newCause.deathsPerAge[i] = Integer.parseInt(fields[i+2]);
            }
        }

        return newCause;
    }

    public String getCode() {
        return ICDCode;
    }

    public class AgeBracketsDeaths {
        public final int young, old, deathCount;

        public AgeBracketsDeaths(int newYoung, int newOld, int deathCount) {
            this.young = newYoung;
            this.old = newOld;
            this.deathCount = deathCount;
        }
    }
    public AgeBracketsDeaths getBracket(int age) {
        AgeBracketsDeaths newBracket;
        int deathCount;
        if (age >= 95) {
            newBracket = new AgeBracketsDeaths(95, Integer.MAX_VALUE, deathCount);
        } else {
            int temp = (age/5)*5;
            newBracket = new AgeBracketsDeaths(temp, temp+4, deathCount);
        }

        return newBracket;
    }

}
