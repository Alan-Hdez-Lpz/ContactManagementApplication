import java.util.Scanner;

public class ContactManager {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ContactDirectory contactDirectory = new ContactDirectory();
        Contact contact;
        boolean validation;
        contactDirectory.initializeContactTypes();
        do {
            System.out.println("Welcome to the Contact Management App!\n" +
                    "\n" +
                    "Please select an operation:\n" +
                    "1. Register unique contact types.\n" +
                    "2. Display unique contact types.\n" +
                    "3. Add new contacts to the directory.\n" +
                    "4. Search for a contact by name and display their details.\n" +
                    "5. Update contact information.\n" +
                    "6. Remove contacts.\n" +
                    "7. Search contacts by contact types.\n" +
                    "8. Sort and display the list of contacts alphabetically.");
            int operation = sc.nextInt();
            switch (operation){
                case 1:
                    String contactTypeName;
                    boolean contactTypeValidation;
                    do{
                        System.out.println("Insert contact type's name:");
                        contactTypeName = sc.next();
                        if(contactDirectory.validateContactType(contactTypeName.toLowerCase())){
                            System.out.println("Contact type already exist");
                            contactTypeValidation = true;
                        } else {
                            contactTypeValidation = false;
                        }
                    }while (contactTypeValidation);
                    contactDirectory.addContactType(contactTypeName.toLowerCase());
                    System.out.println("Contact type added");
                    break;
                case 2:
                    contactDirectory.displayContactTypes();
                    break;
                case 3:
                    contact = new Contact();
                    System.out.println("Insert name:");
                    String name = sc.next();
                    System.out.println("Insert phoneNumber:");
                    String phoneNumber = sc.next();
                    System.out.println("Insert email:");
                    String email = sc.next();
                    boolean validationContactType;
                    String contactType;
                    do{
                        System.out.println("Insert contactType:");
                        contactType = sc.next();
                        if(contactDirectory.validateContactType(contactType.toLowerCase())){
                            validationContactType = false;
                        } else {
                            System.out.println("Insert a registered contact type");
                            validationContactType = true;
                        }
                    }while (validationContactType);
                    contact.setContactId("Contact"+"_"+name+"_"+contactType);
                    contact.setName(name);
                    contact.setPhoneNumber(phoneNumber);
                    contact.setEmail(email);
                    contact.setContactType(contactType);
                    contactDirectory.addContact(contact);
                    System.out.println("Contact added");
                    break;
                case 4:
                    System.out.println("Insert contact name:");
                    String contactNameSearch = sc.next();
                    contactDirectory.searchContactByName(contactNameSearch);
                    break;
                case 5:
                    System.out.println("Insert contact name that you want to update:");
                    String contactNameUpdate = sc.next();
                    System.out.println("Select the field that you want to update\n" +
                            "1. Name\n" +
                            "2. Phone number\n" +
                            "3. Email\n" +
                            "4. Contact type");
                    int fieldToUpdate = sc.nextInt();
                    System.out.println("Insert the new value:");
                    String newValue = sc.next();
                    contactDirectory.updateContact(contactNameUpdate,fieldToUpdate,newValue);
                    break;
                case 6:
                    System.out.println("Insert the contact name that wants to remove:");
                    String contactNameRemove = sc.next();
                    contactDirectory.removeContact(contactNameRemove);
                    break;
                case 7:
                    System.out.println("Insert contact type:");
                    String contactTypeSearch = sc.next();
                    contactDirectory.searchContactsByContactType(contactTypeSearch);
                    break;
                case 8:
                    contactDirectory.sortListAlphabetically();
                    break;
                default:
                    break;
            }
            System.out.println("Do you want to make another operation? (y/n)");
            String answer = sc.next();
            if (answer.equalsIgnoreCase("y")) {
                validation = true;
            } else if (answer.equalsIgnoreCase("n")) {
                validation = false;
            } else {
                System.out.println("Invalid input");
                validation = false;
            }
        } while (validation);
    }
}
