import java.util.*;

public class DeliveryService {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<Address, Integer> costPerAddress = new HashMap<>();

        costPerAddress.put(new Address("Россия", "Москва"), 200);
        costPerAddress.put(new Address("Россия", "Казань"), 200);
        costPerAddress.put(new Address("США", "Нью-Йорк"), 500);
        costPerAddress.put(new Address("Германия", "Берлин"), 300);
        costPerAddress.put(new Address("Китай", "Пекин"), 250);

        int totalCost = 0;
        Set<String> uniqueCountries = new HashSet<>();

        System.out.println("Сервис доставки товаров по миру");
        System.out.println("Для выхода введите 'end'");

        while (true) {
            System.out.println("\nЗаполнение нового заказа.");

            System.out.print("Введите страну: ");
            String country = scanner.nextLine();
            if (country.equalsIgnoreCase("end")) break;

            System.out.print("Введите город: ");
            String city = scanner.nextLine();
            if (city.equalsIgnoreCase("end")) break;

            System.out.print("Введите вес (кг): ");
            String weightInput = scanner.nextLine();
            if (weightInput.equalsIgnoreCase("end")) break;

            try {
                int weight = Integer.parseInt(weightInput);
                if (weight <= 0) {
                    System.out.println("Вес должен быть положительным числом!");
                    continue;
                }

                Address deliveryAddress = new Address(country, city);

                if (costPerAddress.containsKey(deliveryAddress)) {
                    int costPerKg = costPerAddress.get(deliveryAddress);
                    int deliveryCost = costPerKg * weight;

                    totalCost += deliveryCost;

                    uniqueCountries.add(country);

                    System.out.println("Стоимость доставки составит: " + deliveryCost + " руб.");
                    System.out.println("Общая стоимость всех доставок: " + totalCost + " руб.");
                    System.out.println("Количество уникальных стран доставки: " + uniqueCountries.size());
                } else {
                    System.out.println("Доставки по этому адресу нет");
                }

            } catch (NumberFormatException e) {
                System.out.println("Ошибка! Введите корректный вес (целое число)");
            }
        }

        System.out.println("Программа завершена.");
        scanner.close();
    }
}