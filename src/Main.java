import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
    static Scanner input = new Scanner(System. in);
    static int tableWidth = 60;
    public static void PrintTable(List<Toys> toys)
    {
        String[] header = {"Назва", "Ціна", "Вікові межі", "Характристики"};

        PrintLine();
        PrintRow(header);
        PrintLine();

        for (Toys t : toys){
            String[] str = {t.getName(), t.getPrice(),"Від"+ t.getAge_From()+" до "+t.getAge_To(), t.getCharacteristic()};
            PrintRow(str);

            PrintLine();
        }

    }
    public static void PrintLine()
    {
        System.out.println(new String(new char[tableWidth]).replace("\0", "-"));
    }
    public static String centerString (int width, String s) {
        return String.format("%-" + width  + "s", String.format("%" + (s.length() + (width - s.length()) / 2) + "s", s));
    }
    public static void PrintRow(String[] columns)
    {
        int width = (tableWidth - columns.length) / columns.length;

        StringBuilder row = new StringBuilder("|");
        for (String column : columns)
        {
             row.append(centerString(width, column)).append("|");

        }
        System.out.print(row);
        System.out.println();


    }

    public static void Sort_List(List<Toys> toys,int prise){

        List<Toys> Sort_toys = new ArrayList<>();

        for (var toy : toys)
        {
            if (!toy.getName().equals("М`яч") && Integer.parseInt(toy.getAge_From()) <= 3 && Integer.parseInt(toy.getAge_To()) >= 3 && Integer.parseInt(toy.getPrice()) < prise)
            {
                Toys newToy = new Toys();
                newToy.setName(toy.getName());
                newToy.setPrice(toy.getPrice());
                newToy.setAge_From(toy.getAge_From());
                newToy.setAge_To(toy.getAge_To());
                newToy.setCharacteristic(toy.getCharacteristic());
                Sort_toys.add(newToy);
            }
        }

        Sort_toys.sort((x,y)-> y.Price.compareTo(x.Price));
        PrintTable(Sort_toys);

    }

    public static void main(String[] args) {

        List<Toys> toys = new ArrayList<>();

        try {
            File myObj = new File("src/toys.txt");
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] entries = data.split(",");
                Toys newToy = new Toys();
                newToy.setName(entries[0]);
                newToy.setPrice(entries[1]);
                newToy.setAge_From(entries[2]);
                newToy.setAge_To(entries[3]);
                newToy.setCharacteristic(entries[4]);
                toys.add(newToy);
            }
            myReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        PrintTable(toys);

        boolean exit = false;
        do
        {
            int action;
            System.out.println("  [1] - Знайти");
            System.out.println("  [0] -Завершити роботу");
            System.out.print("  Виберіть дію: "); action = input.nextInt();
            switch (action) {
                case 1 -> {
                    System.out.print("  Введіть ціну: ");
                    int prise = input.nextInt();
                    Sort_List(toys, prise);
                }
                case 0 -> exit = true;
                default -> System.out.println("\nНевідома дія!");
            }

        } while (!exit);

    }
}
