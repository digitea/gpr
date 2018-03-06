import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GitUtils {
    private GitUtils () {}

    public static Optional<String> currentBranch () {
        try {
            Path path = Paths.get(System.getProperty("user.dir") + "/.git/HEAD");
            List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
            String string = String.join("", lines);

            Pattern pattern = Pattern.compile("ref: refs/heads/([\\w\\d]+)");
            Matcher matcher = pattern.matcher(string);

            if (!matcher.matches()) {
                return Optional.empty();
            }

            return Optional.of(matcher.group(1));
        } catch (IOException e) {
            return Optional.empty();
        }
    }
}
