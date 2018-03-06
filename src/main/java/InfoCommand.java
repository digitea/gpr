import java.util.Optional;

public class InfoCommand implements Command {

    public String run () throws Exception {
        Optional<String> branch = GitUtils.currentBranch();
        return String.format("On branch '%s'", branch.orElse("null"));
    }
}
