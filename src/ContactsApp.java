import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class ContactsApp {
    public static void contactApp() {

        Path path = Paths.get("./src/contacts.txt").normalize();

        List<Contact> contacts;
        List<String> contactString = Collections.singletonList("");
        HashMap<String, String> names;
        List<Long> numbers;

        try {
            contactString = Files.readAllLines(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String line : contactString) {
            String[] contactInfo = line.split(" + ");
            Contact contact = new Contact(contactInfo[0], Long.parseLong(contactInfo[1]));
            System.out.println(contact.getContactName() + " " + contact.getContactNumber());
        }


    }

    public static void main(String[] args) {
        contactApp();
    }



}
