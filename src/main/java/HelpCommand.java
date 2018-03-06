public class HelpCommand implements Command {
    public String run () throws Exception {
        String[] text = new String[] {
            "usage: gpr <command> [<args>]",
            "",
            "examine the current state",
            "   info        Show current state",
            "start or resume work on an issue",
            "   work        Change staging to an issue",
            "complete work on an issue and create a pull request",
            "   done        File a pull request"
        };

        return String.join(System.lineSeparator(), text);
    }
}
