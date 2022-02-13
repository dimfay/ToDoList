package ru.team.todo.ui.commands;

public abstract class Command implements Comparable<Command> {

    private final String name;
    private final String description;

    public Command(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Сортирует по алфавиту имена команд
     */
    @Override
    public int compareTo(Command other) {
        int i = this.getName().compareTo(other.getName());
        if (i == 0) {
            i = -1;
        }
        return i;
    }

    public abstract void execute();

}
