import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

public abstract class ContactsApp {
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";

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
        System.out.printf("%s-------------------------------------------\n", RED);
        System.out.printf("%s%-18s %s| %s%-11s \n", GREEN, "Name", RESET, GREEN, "Phone Number");
        System.out.printf("%s-------------------------------------------%s\n", RED, RESET);
        for (Contact line : contacts) {
            System.out.printf("%s%-18s %s| %s%-11s %s\n", PURPLE, line.getContactName(), RESET, CYAN, phoneNumberFormat(line.getContactNumber()), RESET);
        }
        System.out.printf("%s-------------------------------------------%s\n", RED, RESET);
    }

    public static String phoneNumberFormat(Long n) {
        return n / 10000000 + "-" + n % 10000000 / 10000 + "-" + n % 10000000 % 10000;
    }


    public static void addContact() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the name");
        String newContactName = scanner.nextLine();
        System.out.println("Enter the number");
        try {
            long newContactNumber = scanner.nextLong();
            if (String.valueOf(newContactNumber).length() == 10 || String.valueOf(newContactNumber).length() == 7) {
                Contact newContact = new Contact(newContactName, newContactNumber);
                System.out.println(newContact.toString());
                contacts.add(newContact);
            } else {
                System.out.println("Please enter 7 or 10 digit number:");
                addContact();
            }
        } catch (Exception e) {
            System.out.println("Numbers Only");
            addContact();
        }

    }

    public static void searchContact() {
        Scanner scanner = new Scanner(System.in);
        boolean agree = true;
        do {
            System.out.println("Please enter the Name you are looking for:");
            System.out.println("exit by Entering Number: 2");
            String input = scanner.nextLine();
            boolean found = false;
            if (!input.equalsIgnoreCase("2")) {
                for (Contact line : contacts) {
                    if (line.getContactName().toLowerCase().contains(input.toLowerCase())) {
                        System.out.printf("%s%s%s \n", GREEN, line, RESET);
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    System.out.println("We can't find the person you are looking for");
                }
            } else if (input.equals("2")) {
                agree = false;
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
        try {
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
                    break;
                }
            } while (agree);
        } catch (Exception e) {
            System.out.printf("%s%s%s%s\n", "Please enter a ", GREEN, "Number", RESET);
            deleteContact();
        }

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
