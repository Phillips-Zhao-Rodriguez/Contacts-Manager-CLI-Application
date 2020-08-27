import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Contact {
    private String contactName;
    private long contactNumber;

    public Contact(String contactName, long contactNumber) {
        this.contactName = contactName;
        this.contactNumber = contactNumber;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public long getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(long contactNumber) {
        this.contactNumber = contactNumber;
    }

//    public static List<String> contactsToNameStrings (List<Contact> contacts) {
//        List<String> contactString = new ArrayList<>();
//        for (Contact contact : contacts) {
//            contactString.add(contact.getContactName());
//        }
//        return contactString;
//    }



}
