package storage;

import model.Contact;

import java.io.*;
import java.util.LinkedList;

public class ContactFile {
    public static void writeFile (LinkedList<Contact> contacts) throws IOException {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream("ContactList.dat");
            oos = new ObjectOutputStream(fos);
            oos.writeObject(contacts);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            oos.close();
            fos.close();
        }
    }

    public static LinkedList<Contact> readFile() {
        File file = new File("ContactList.dat");
        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Object output = ois.readObject();
            LinkedList<Contact> contacts = (LinkedList<Contact>) output;
            ois.close();
            fis.close();
            return contacts;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new LinkedList<>();
    }
}
