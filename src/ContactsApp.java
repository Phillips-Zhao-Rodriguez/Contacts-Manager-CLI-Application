import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

public abstract class ContactsApp {
    public static List<Contact> contacts = new ArrayList<>();
    public static List<String> contactString = Collections.singletonList("");
    public static Path path = Paths.get("./src/contacts.txt").normalize();



    public static void contactApp() {

        try {
            contactString = Files.readAllLines(path);
        } catch (IOException e) {
            e.printStackTrace();
        }


        for (String line : contactString) {
            String[] contactInfo = line.split(" \\+ ");
            Contact contact = new Contact(contactInfo[0], Long.parseLong(contactInfo[1]));
            contacts.add(contact);
        }

//        try {
//            Files.write(path, contactString);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public static void printContacts() {
        System.out.println("-------------------------------------------");
        System.out.printf("%-18s | %-11s \n","Name","Phone Number");
        System.out.println("-------------------------------------------");
        for (Contact line : contacts) {
            System.out.printf("%-18s | %-11s \n", line.getContactName() ,  phoneNumberFormat(line.getContactNumber()));
        }
        System.out.println("-------------------------------------------");
    }
    public static String phoneNumberFormat(Long n){
        return n/10000000 + "-" + n%10000000/10000 + "-" + n%10000000%10000;
    }
    public static void addContact() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the name");
        String newContactName = scanner.nextLine();
        System.out.println("Enter the number");
        long newContactNumber = scanner.nextLong();
        if(String.valueOf(newContactNumber).length()!= 10){
            System.out.println("Please enter 10 digit number:");
            addContact();
        } else {
            Contact newContact = new Contact(newContactName, newContactNumber);
            System.out.println(newContact.toString());
            contacts.add(newContact);
        }
    }

    public static void searchContact() {
        Scanner scanner = new Scanner(System.in);
        boolean agree = true;
        do {
            System.out.println("Search by name enter:1");
            System.out.println("Search by number enter:2");
            System.out.println("exit by number :3");
            String input = scanner.nextLine();
            if (input.equals("1")) {
                System.out.println("please enter the name: ");
                String inputName = scanner.nextLine();
                System.out.println(inputName);
                boolean found = false;
                for (Contact line : contacts) {
//                    System.out.println(line);
                    if (line.getContactName().equals(inputName)) {
                        System.out.println(line);
                        found = true;
                        break;
                    }

                }
                if(!found){
                    System.out.println("We can't find the person you are looking for");
                }
            } else if (input.equals("2")) {
                System.out.println("enter the number:");
                long inputNumber = scanner.nextLong();
                for (Contact line : contacts) {
                    if (line.getContactNumber() == inputNumber) {
                        System.out.println(line);
                    } else {
                        System.out.println("We can't find the number you are looking for");
                    }
                }
            } else if (input.equals("3")) {
                agree = false;

            } else {
                System.out.println("Please enter the valid number");
            }

        } while (agree);


    }

    public static void deleteContact() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Search by name enter:1");
        System.out.println("Search by number enter:2");
        System.out.println("exit by number :3");
//        int input = scanner.nextInt();
        String inputInt = scanner.nextLine();
        int input = Integer.parseInt(inputInt);
        boolean agree = true;
        do {
            if (input == 1) {
                System.out.println("enter the name");
                String inputName = scanner.nextLine();
                for (Contact line : contacts) {
                    if (line.getContactName().equalsIgnoreCase(inputName)) {
                        System.out.println(line);
                        System.out.println("Are you sure you want to delete it? Y or N");
                        String deleteName = scanner.nextLine();
                        if (deleteName.equalsIgnoreCase("Y")) {
                            contacts.remove(line);
                        } else if (deleteName.equalsIgnoreCase("N")) {
                            break;
                        }
                    } else {
                        System.out.println("We can't find the person you are looking for");
                    }
                    break;
                }
            } else if (input == 2) {
                System.out.println("enter the number");
                int inputNumber = scanner.nextInt();
                for (Contact line : contacts) {
                    if (line.getContactNumber() == inputNumber) {
                        System.out.println(line);
                        System.out.println("Are you sure you want to delete it? Y or N");
                        String deleteName = scanner.nextLine();
                        if (deleteName.equalsIgnoreCase("Y")) {
                            contacts.remove(line);

                            break;

                        } else if (deleteName.equalsIgnoreCase("N")) {
                            break;
                        }

                    } else {
                        System.out.println("We can't find the number you are looking for");
                    }
                    break;
                }
            } else if (input == 3) {
                agree = false;

            } else {
                System.out.println("Please enter the valid number");
            }
        } while (agree);


    }

    public static void main(String[] args) {
        boolean agree = true;
        contactApp();

        do {
            System.out.println("1. View Contacts.");
            System.out.println("2. Add a New Contact.");
            System.out.println("3. Search a Contact by Name");
            System.out.println("4. Delete an existing contact.");
            System.out.println("5. Exit");
            System.out.println("Enter an option (1, 2, 3, 4 or 5):");
            Scanner scanner = new Scanner(System.in);
            int input = scanner.nextInt();
            switch (input) {
                case 1:
                    printContacts();
                    break;
                case 2:
                    addContact();
                    break;
                case 3:
                    searchContact();
                    break;
                case 4:
                    deleteContact();
                    break;
                case 5:
                    agree = false;
                    System.out.println("Exit");
                    break;
                default:
                    System.out.println("Invalid option");
            }
        } while (agree);

    }


}
