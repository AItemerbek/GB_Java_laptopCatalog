import java.util.*;

/*
Подумать над структурой класса Ноутбук для магазина техники - выделить поля и методы. Реализовать в java.
Создать множество ноутбуков.
Написать метод, который будет запрашивать у пользователя критерий (или критерии) фильтрации и выведет ноутбуки, отвечающие фильтру. Критерии фильтрации можно хранить в Map.
Например:
“Введите цифру, соответствующую необходимому критерию:
1 - ОЗУ
2 - Объем ЖД
3 - Операционная система
4 - Цвет …
Далее нужно запросить минимальные значения для указанных критериев - сохранить параметры фильтрации можно также в Map.
Отфильтровать ноутбуки из первоначального множества и вывести проходящие по условиям.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Добро пожаловать в симулятор магазина ноутбуков!");
        System.out.println("Для начала давайте сгенерируем псевдослучайный каталог.");
        System.out.print("Сколько ноутбуков будет содержать каталог? (рекомендуется от 50 до 100): ");
        int num = scanner.nextInt();
        Set<Laptops> warehouse = creteWarehouse(num);
        System.out.println("Каталог размером " + num + " успешно создан!");
        boolean flag = true;
        while (flag) {
            System.out.println("Меню программы. Введите команду, чтобы начать работу с каталогом.");
            System.out.println("Введите 1 -> Общее сведения о каталоге");
            System.out.println("Введите 2 -> Просмотр всего каталога");
            System.out.println("Введите 3 -> Настроить фильтр");
            System.out.println("Введите 4 -> Выйти из программы");
            int choise = scanner.nextInt();
            switch (choise){
                case 1:
                    printStatistics(warehouse);
                    break;
                case 2:
                    printCatalog(warehouse);
                    break;
                case 3:
                    HashMap<Integer,Object> fl = createFilter();
                    printCatalog(findLaptop(fl,warehouse));
                    break;
                case 4:
                    flag = false;
                    break;
            }
        }

    }
    static Scanner scanner = new Scanner(System.in);

    static Set<Laptops> creteWarehouse(int quantity) {
        Set<Laptops> warehouse = new HashSet<>();
        for (int i = 0; i < quantity; i++) {
            warehouse.add(new Laptops());
        }
        return warehouse;
    }

    static HashMap<Integer, Object> createFilter() {
        HashMap<Integer, Object> filter = new HashMap<>();
        boolean flag = true;
        while (flag) {
            if (filter.isEmpty()) System.out.println("Фильтр не задан. Показать весь каталог -> нажмите 0");
            else {
                System.out.println("Текущий фильтр:");
                printFilter(filter);
            }
            System.out.println("Сделайте Ваш выбор при помощи ввода соответствующей команды:");
            System.out.println("1 -> Выбрать бренд. Если бренд уже выбран, повторное нажатие его заменит");
            System.out.println("2 -> Задать минимальную диагональ монитора. Если фильтр настроен, то повторный вызов команды его заменит");
            System.out.println("3 -> Задать минимальную частоту процессора. Если фильтр настроен, то повторный вызов команды его заменит");
            System.out.println("4 -> Задать минимальный объем памяти видеокарты. Если фильтр настроен, то повторный вызов команды его заменит");
            System.out.println("5 -> Задать минимальный объем оперативной памяти. Если фильтр настроен, то повторный вызов команды его заменит");
            System.out.println("6 -> Задать минимальный объем памяти жесткого диска. Если фильтр настроен, то повторный вызов команды его заменит");
            System.out.println("0 -> Сохранить фильтр и вывести подходящие ноутбуки на экран");
            System.out.print("Ваш выбор: ");
            int f = scanner.nextInt();
            switch (f) {
                case 1:
                    System.out.println("Введите название бренда: ");
                    filter.put(1, scanner.next().toUpperCase());
                    break;
                case 2:
                    System.out.println("Введите размер диагонали: ");
                    filter.put(2, scanner.nextInt());
                    break;
                case 3:
                    System.out.println("Введите производительность процессора: ");
                    filter.put(3, scanner.nextDouble());
                    break;
                case 4:
                    System.out.println("Введите размер памяти видеокарты: ");
                    filter.put(4, scanner.nextInt());
                    break;
                case 5:
                    System.out.println("Введите размер оперативной памяти: ");
                    filter.put(5, scanner.nextInt());
                    break;
                case 6:
                    System.out.println("Введите размер жесткого диска: ");
                    filter.put(6, scanner.nextInt());
                    break;
                default:
                    flag = false;
            }
        }
        return filter;
    }

    static Set<Laptops> findLaptop(HashMap<Integer, Object> filter, Set<Laptops> warehouse) {
        Set<Laptops> findingfLaptops = new HashSet<>();
        for (Integer key : filter.keySet()) {
            if (findingfLaptops.isEmpty()) findingfLaptops = filterByKey(key, warehouse, filter);
            else findingfLaptops = filterByKey(key, findingfLaptops, filter);
        }
        return findingfLaptops;
    }

    static Set<Laptops> filterByKey(Integer key, Set<Laptops> warehouse, HashMap<Integer, Object> filter) {
        Set<Laptops> laptops = new HashSet<>();
        for (Laptops item : warehouse) {
            if (filter.get(key) instanceof String) {
                if (item.getAtribute(key).equals(filter.get(key))) laptops.add(item);
            } else if (filter.get(key) instanceof Double) {
                double artibute = (double) item.getAtribute(key);
                double k = (double) filter.get(key);
                if (artibute >= k) laptops.add(item);
            } else {
                int artibute = (int) item.getAtribute(key);
                int k = (int) filter.get(key);
                if (artibute >= k) laptops.add(item);
            }
        }
        return laptops;
    }

    static void printFilter(HashMap<Integer, Object> filter){
        for (Integer i : filter.keySet()) {
            switch (i){
                case 1:
                    System.out.printf("Название брэнда= %s", filter.get(i));
                    break;
                case 2:
                    System.out.printf("Диагональ экрана >= %s дюймов", filter.get(i));
                    break;
                case 3:
                    System.out.printf("Частота процессора >= %s ГГц", filter.get(i));
                    break;
                case 4:
                    System.out.printf("Объем видеопамяти >= %s Гб" , filter.get(i));
                    break;
                case 5:
                    System.out.printf("Объем оперативной памяти >= %s Гб", filter.get(i));
                    break;
                case 6:
                    System.out.printf("Объем памяти жесткого диска >= %s Гб", filter.get(i));
                    break;
            }
            System.out.print("; ");
        }
        System.out.println();
    }
    static void printCatalog(Set<Laptops> warehouse){
        if (warehouse.isEmpty()) System.out.println("Ничего не найдено");
        else{
            System.out.println("╒════════════════════╤════════════════════╤════════════════════╤════════════════════╤════════════════════╤════════════════════╕");
            System.out.println("│ Брэнд              │ Диагональ, inch    │ Частота, GHz       │ Видеокарта, GB     │ RAM, Gb            │ SSD, Gb            │");
            for (Laptops laptops : warehouse) {
                System.out.println("├────────────────────┼────────────────────┼────────────────────┼────────────────────┼────────────────────┼────────────────────┤");
                System.out.printf("│%20s",laptops.brand);
                System.out.printf("│%20s",laptops.diagonal);
                System.out.printf("│%20.2f",laptops.cpuSpeed);
                System.out.printf("│%20s",laptops.graphics);
                System.out.printf("│%20s",laptops.ram);
                System.out.printf("│%20s",laptops.ssd);
                System.out.printf("│\n");
            }
            System.out.println("╘════════════════════╧════════════════════╧════════════════════╧════════════════════╧════════════════════╧════════════════════╛");
        }
    }
    static void printStatistics(Set<Laptops> warehouse){
        System.out.println("Общее сведения о каталоге:");
        HashSet<String> brands = new HashSet<>();
        HashSet<Integer> diagonals = new HashSet<>();
        HashSet<Double> chipSpeed = new HashSet<>();
        HashSet<Integer> video = new HashSet<>();
        HashSet<Integer> rams = new HashSet<>();
        HashSet<Integer> ssds = new HashSet<>();
        for (Laptops  laptops: warehouse) {
            brands.add(laptops.brand);
            diagonals.add(laptops.diagonal);
            chipSpeed.add(laptops.cpuSpeed);
            video.add(laptops.graphics);
            rams.add(laptops.ram);
            ssds.add(laptops.ssd);
        }
        System.out.printf("Бренды в наличии: %s \n", brands);
        System.out.printf("Диагонали в наличии: от %s до %s дюймов\n", Collections.min(diagonals),Collections.max(diagonals));
        System.out.printf("Процессоры в наличии: от %.2f до %.2f ГГц\n",Collections.min(chipSpeed),Collections.max(chipSpeed));
        System.out.printf("Видеокарты в наличии: от %s до %s Гб\n",Collections.min(video),Collections.max(video));
        System.out.printf("Оперативная память в наличии: от %s до %s Гб\n",Collections.min(rams),Collections.max(rams));
        System.out.printf("Жесткие диски в наличии: от %s до %s Гб\n",Collections.min(ssds),Collections.max(ssds));
        System.out.println("==========================================================================================");
    }
}
