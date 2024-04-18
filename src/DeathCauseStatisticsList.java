import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DeathCauseStatisticsList {
    private ArrayList<DeathCauseStatistic> list;

    public ArrayList<DeathCauseStatistic> repopulate(String path) {
        try {
            List<DeathCauseStatistic> list = Files.lines(Paths.get(path)).skip(2).map(DeathCauseStatistic::fromCsvLine)
            .collect(Collectors.toList());

            List<DeathCauseStatistic> result = list.stream().sorted(
                    (x,y) -> Integer.compare(
                            y.getBracket(age).deathCount,
                            x.getBracket(age).deathCount
                    )
            ).collect(Collectors.toList());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
