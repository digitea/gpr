public class UnknownCommand implements Command {
    private final String name;

    public UnknownCommand (String name) {
        this.name = name;
    }

    public String run () throws Exception {
        return String.format("Unknown command: '%s'", name)
            + System.lineSeparator()
            + System.lineSeparator()
            + new HelpCommand().run();
    }
}
