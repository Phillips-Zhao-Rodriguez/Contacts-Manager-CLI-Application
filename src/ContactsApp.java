import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class ContactsApp {

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
            System.out.println(contact.getContactName() + " " + contact.getContactNumber());
            contacts.add(contact);
        }

        try {
            Files.write(path, contactString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void printContacts () {
        for (Contact line : contacts) {
            System.out.println(line.getContactName() + " : " + line.getContactNumber());
        }
    }

    public static void addContact () {
        Path path = Paths.get("./src/contacts.txt").normalize();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the name");
        String newContactName = scanner.nextLine();
        System.out.println("Enter the number");
        long newContactNumber = scanner.nextLong();
        Contact newContact = new Contact(newContactName, newContactNumber);
        System.out.println(newContact.toString());
        contacts.add(newContact);
    }

    public static void main(String[] args) {

        contactApp();
        addContact();
        printContacts();

    }



}
