import Model.Pet;
import Model.Race;
import dao.MysqlDBConnection;
import dao.PetDao;

import java.util.List;
import java.util.Scanner;



public class Main {
    public static void main(String[] args) {


        PetDao dao = new PetDao(new MysqlDBConnection());

        Scanner scanner = new Scanner(System.in);

        String command;

        do {
            System.out.println("Podaj komendę [insert,list,delete]");
            command = scanner.nextLine();
            // todo: dodać metodę update

            if (command.startsWith("insert")) {
                String[] words = command.split(" ");
                Pet pet = Pet.builder()
                        .name(words[1])
                        .ownerName(words[2])
                        .age(Integer.parseInt(words[3]))
                        .weight(Double.parseDouble(words[4]))
                        .pureRace(Boolean.parseBoolean(words[5]))
                        .race(Race.valueOf(words[6].toUpperCase()))
                        .build();

                dao.insertPet(pet);
            } else if (command.startsWith("list")) {
                // list
                List<Pet> list = dao.getAllPets();

                System.out.println("Rekordy: ");
                list.forEach(System.out::println);
                System.out.println();
            }else if(command.startsWith("delete")){
                String[] words = command.split(" ");

                dao.deletePet(Long.parseLong(words[1]));
            }

        } while (!command.equalsIgnoreCase("quit"));
    }
}




