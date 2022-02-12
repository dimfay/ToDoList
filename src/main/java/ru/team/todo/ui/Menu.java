package ru.team.todo.ui;

import ru.team.todo.ui.commands.Command;

import java.util.*;

public class Menu {

    private final Map<String, Command> commands = new HashMap<>();

    public Menu addCommand(Command command) {
        this.commands.put(command.getName(), command);
        return this;
    }

    public void startUI() {
        while (true) {
            try {
                System.out.println("Available commands: ");
                List<Command> sortedCommands = new ArrayList<>(this.commands.values());
                //Сортируем все элементы
                Collections.sort(sortedCommands);
                for (Command cmd : sortedCommands) {
                    System.out.println("'" + cmd.getName() + "': " + cmd.getDescription());
                }
                Scanner scanner = new Scanner(System.in);
                String userInput = scanner.nextLine();
                //Удаляем пробелы
                userInput = userInput.trim();
                if (userInput.isEmpty()) {
                    continue;
                }
                Command cmd = this.commands.get(userInput.toLowerCase());
                if (cmd == null) {
                    throw new IllegalArgumentException("Command '" + userInput + "' not found!");
                }
                cmd.execute();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
