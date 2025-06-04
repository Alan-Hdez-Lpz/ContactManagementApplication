import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class ContactDirectory {
    ArrayList<Contact> allContacts = new ArrayList<>();
    HashSet<String> contactTypes = new HashSet<>();
    HashMap<String,Contact> contactsByName = new HashMap<>();

    public ArrayList<Contact> getAllContacts() {
        return allContacts;
    }

    public HashSet<String> getContactTypes() {
        return contactTypes;
    }

    public HashMap<String, Contact> getContactsByName() {
        return contactsByName;
    }

    public void setAllContacts(ArrayList<Contact> allContacts) {
        this.allContacts = allContacts;
    }

    public void setContactTypes(HashSet<String> contactTypes) {
        this.contactTypes = contactTypes;
    }

    public void setContactsByName(HashMap<String, Contact> contactsByName) {
        this.contactsByName = contactsByName;
    }

    public void initializeContactTypes(){
        HashSet<String> contactTypes = getContactTypes();
        contactTypes.add("personal");
        contactTypes.add("professional");
        setContactTypes(contactTypes);
    }

    public void addContactType(String contactType){
        HashSet<String> contactTypes = getContactTypes();
        if (contactTypes.contains(contactType)){
            System.out.println("The contact type already exist");
        } else {
            contactTypes.add(contactType);
        }
        setContactTypes(contactTypes);
    }

    public void displayContactTypes(){
        HashSet<String> contactTypes = getContactTypes();
        System.out.println("Contact Types:");
        for(String contactType : contactTypes) {
            System.out.println(contactType);
        }
    }

    public boolean validateContactType(String contactType){
        HashSet<String> contactTypes = getContactTypes();
        return contactTypes.contains(contactType);
    }

    public void addContact(Contact contact){
        ArrayList<Contact> allContacts = getAllContacts();
        HashMap<String,Contact> contactsByName = getContactsByName();
        boolean contactValidation;
        if(allContacts.isEmpty() && contactsByName.isEmpty()) {
            contactValidation = true;
        } else {
            contactValidation = !contactsByName.containsKey(contact.getName());
        }
        if(contactValidation){
            allContacts.add(contact);
            contactsByName.put(contact.getName(), contact);
            setAllContacts(allContacts);
            setContactsByName(contactsByName);
        } else {
            System.out.println("The contact name already exist");
        }

    }

    public void searchContactByName(String contactName){
        HashMap<String,Contact> contactsByName = getContactsByName();
        if(contactsByName.isEmpty()) {
            System.out.println("No contact recorded");
        } else {
            if (contactsByName.containsKey(contactName)){
                Contact contact = contactsByName.get(contactName);
                System.out.println("Name: " + contact.getName() + "\nPhone number: " + contact.getPhoneNumber() + "\nEmail: " + contact.getEmail() + "\nContact type: " + contact.getContactType() + "\nContact ID: " + contact.getContactId());
            }
            else {
                System.out.println("The contact " + contactName + " no exist");
            }
        }
    }

    public void removeContact(String contactName){
        HashMap<String,Contact> contactsByName = getContactsByName();
        ArrayList<Contact> contacts = getAllContacts();
        if(contactsByName.isEmpty()) {
            System.out.println("No contact recorded");
        } else {
            if (contactsByName.containsKey(contactName)){
                contactsByName.remove(contactName);
                setContactsByName(contactsByName);
                for (int i = 0; i < contacts.size(); i++) {
                    if (contacts.get(i).getName().equalsIgnoreCase(contactName)){
                        contacts.remove(i);
                        i = contacts.size();
                    }
                }
                System.out.println("Contact removed");
            }
            else {
                System.out.println("The contact " + contactName + " no exist");
            }
        }
    }

    public void searchContactsByContactType(String contactType){
        ArrayList<Contact> allContacts = getAllContacts();
        for (Contact contact : allContacts) {
            if(contact.getContactType().equalsIgnoreCase(contactType)){
                System.out.println("Name: " + contact.getName() + "\nPhone number: " + contact.getPhoneNumber() + "\nEmail: " + contact.getEmail() + "\nContact type: " + contact.getContactType() + "\nContact ID: " + contact.getContactId());
                System.out.println();
            }
        }
    }

    public void sortListAlphabetically(){
        ArrayList<Contact> allContacts = getAllContacts();
        allContacts.sort((o1, o2) -> o1.getName().compareTo(o2.getName()));
        for (Contact contact : allContacts) {
            System.out.println("Name: " + contact.getName() + "\nPhone number: " + contact.getPhoneNumber() + "\nEmail: " + contact.getEmail() + "\nContact type: " + contact.getContactType() + "\nContact ID: " + contact.getContactId());
            System.out.println();
        }
    }

    public void updateContact(String contactName, int fieldToUpdate, String newValue){
        ArrayList<Contact> allContacts = getAllContacts();
        HashMap<String,Contact> contactsByName = getContactsByName();
        for (Contact contact : allContacts){
            if(contact.getName().equalsIgnoreCase(contactName)){
                switch (fieldToUpdate){
                    case 1:
                        if(contactsByName.containsKey(newValue)){
                            System.out.println("The contact name already exist, it is not possible update it");
                        } else {
                            contact.setName(newValue);
                            Contact updatedContact = contactsByName.get(contactName);
                            updatedContact.setName(newValue);
                            contactsByName.put(newValue,updatedContact);
                            contactsByName.remove(contactName);
                            System.out.println("Name updated");
                        }
                        break;
                    case 2:
                        contact.setPhoneNumber(newValue);
                        contactsByName.get(contactName).setPhoneNumber(newValue);
                        System.out.println("Phone number updated");
                        break;
                    case 3:
                        contact.setEmail(newValue);
                        contactsByName.get(contactName).setEmail(newValue);
                        System.out.println("Email updated");
                        break;
                    case 4:
                        if(validateContactType(newValue)){
                            contact.setContactType(newValue);
                            contactsByName.get(contactName).setContactType(newValue);
                            System.out.println("Contact type updated");
                        } else {
                            System.out.println("No contact type recorded, it is not possible to update it");
                        }
                        break;
                    default:
                        break;
                }
            }
            setAllContacts(allContacts);
            setContactsByName(contactsByName);
        }
    }
}
