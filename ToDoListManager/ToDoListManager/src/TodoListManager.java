import java.util.ArrayList;
import java.util.Scanner;

public class TodoListManager {
    private static ArrayList<String> todos = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== Управление списком дел ===");

        while (true) {
            printMenu();
            int choice = getIntInput("Ваш выбор: ");

            switch (choice) {
                case 0 -> {
                    System.out.println("Выход из программы. До свидания!");
                    return;
                }
                case 1 -> addTodo();
                case 2 -> printTodos();
                case 3 -> deleteTodoByNumber();
                case 4 -> deleteTodoByText();
                default -> System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\nВыберите операцию:");
        System.out.println("0. Выход из программы");
        System.out.println("1. Добавить дело");
        System.out.println("2. Показать дела");
        System.out.println("3. Удалить дело по номеру");
        System.out.println("4. Удалить дело по названию");
    }

    private static void addTodo() {
        System.out.print("Введите название задачи: ");
        String todo = scanner.nextLine().trim();

        if (todo.isEmpty()) {
            System.out.println("Дело не может быть пустым!");
            return;
        }

        // Проверяем на дубликаты
        if (todos.contains(todo)) {
            System.out.println("Такое дело уже существует в списке!");
            return;
        }

        todos.add(todo);
        System.out.println("Добавлено!");
        printTodos();
    }

    private static void printTodos() {
        if (todos.isEmpty()) {
            System.out.println("Список дел пуст.");
            return;
        }

        System.out.println("Ваш список дел:");
        for (int i = 0; i < todos.size(); i++) {
            System.out.println((i + 1) + ". " + todos.get(i));
        }
    }

    private static void deleteTodoByNumber() {
        if (todos.isEmpty()) {
            System.out.println("Список дел пуст. Нечего удалять.");
            return;
        }

        int number = getIntInput("Введите номер для удаления: ");

        if (number < 1 || number > todos.size()) {
            System.out.println("Дела с номером " + number + " не существует!");
            return;
        }

        String removedTodo = todos.remove(number - 1);
        System.out.println("Удалено!");
        printTodos();
    }

    private static void deleteTodoByText() {
        if (todos.isEmpty()) {
            System.out.println("Список дел пуст. Нечего удалять.");
            return;
        }

        System.out.print("Введите задачу для удаления: ");
        String text = scanner.nextLine().trim();

        if (todos.contains(text)) {
            todos.remove(text);
            System.out.println("Удалено!");
            printTodos();
        } else {
            System.out.println("Дела с названием '" + text + "' не найдено!");
        }
    }

    private static int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Ошибка! Введите целое число.");
            }
        }
    }
}