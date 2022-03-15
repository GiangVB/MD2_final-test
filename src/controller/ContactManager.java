package controller;

import model.Contact;

import java.util.LinkedList;

public class ContactManager {
    public static LinkedList<Contact> contactList = new LinkedList<>();

    public static void addNewContact(Contact newContact) {
        contactList.add(newContact);
    }

    public static void removeContactByIndex(int index) {
        contactList.remove(index);
    }

    public static void editContactByIndex(int index, Contact newContact) {
        contactList.set(index, newContact);
    }

    public static int findContactIndexByPhoneNumber(String searchingNumber) {
        int index = -1;
        for (int i = 0; i < contactList.size(); i++) {
            Contact currentContact = contactList.get(i);
            String currentContactPhoneNumber = currentContact.getPhoneNumber();
            if (currentContactPhoneNumber.equals(searchingNumber)) {
                index = i;
                return index;
            }
        }
        return index;
    }

    public static int findContactIndexByFullname(String searchingName) {
        int index = -1;
        for (int i = 0; i < contactList.size(); i++) {
            Contact currentContact = contactList.get(i);
            String currentContactFullname = currentContact.getFullname();
            if (currentContactFullname.equals(searchingName)) {
                index = i;
                return index;
            }
        }
        return index;
    }
}
