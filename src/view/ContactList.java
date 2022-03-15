package view;

import controller.ContactManager;
import model.Contact;
import storage.ContactFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Scanner;

public class ContactList {
    public static LinkedList<Contact> contacts = ContactManager.contactList;

    public static void main(String[] args) throws IOException {
        int choice = -1;
        Scanner input = new Scanner(System.in);
        while (choice != 0) {
            System.out.println("---- CHƯƠNG TRÌNH QUẢN LÝ DANH BẠ ----");
            System.out.println("Chọn chức năng theo số để tiếp tục");
            System.out.println("1. Xem danh sách");
            System.out.println("2. Thêm mới");
            System.out.println("3. Cập nhật");
            System.out.println("4. Xóa");
            System.out.println("5. Tìm kiếm");
            System.out.println("6. Đọc từ file");
            System.out.println("7. Ghi vào file");
            System.out.println("0. Thoát");
            System.out.println("Nhập lựa chọn: ");
            choice = input.nextInt();

            switch (choice) {
                case 1:
                    showAllContact();
                    break;
                case 2:
                    Contact newContact = creatNewContact();
                    ContactManager.addNewContact(newContact);
                    break;
                case 3:
                    editContactByPhoneNumber();
                    break;
                case 4:
                    deleteContactByPhoneNumber();
                    break;
                case 5:
                    searchContact();
                    break;
                case 6:
                    ContactFile.readFile();
                    break;
                case 7:
                    ContactFile.writeFile(contacts);
                    break;
                case 0:
                    System.exit(0);
                default:
                    System.out.println("Bạn chưa lựa chọn chức năng");
            }
        }
    }

    public static void showAllContact(){
        for (Contact c: contacts
        ) {
            System.out.println(c);
        }
    }

    public static Contact creatNewContact() {
        Scanner input = new Scanner(System.in);
        System.out.println("Nhập số điện thoại: ");
        String phoneNumber = input.nextLine();
        System.out.println("Nhập nhóm danh bạ: ");
        String contactGroup = input.nextLine();
        System.out.println("Nhập họ và tên: ");
        String fullName = input.nextLine();
        System.out.println("Nhập giới tính: ");
        String gender = input.nextLine();
        System.out.println("Nhập địa chỉ");
        String address = input.nextLine();
        System.out.println("Nhập ngày sinh: ");
        int dayOfBirth = input.nextInt();
        System.out.println("Nhập tháng sinh: ");
        int monthOfBirth = input.nextInt();
        System.out.println("Nhập năm sinh: ");
        int yearOfBirth = input.nextInt();
        LocalDate dateOfBirth = LocalDate.of(yearOfBirth, monthOfBirth,dayOfBirth);
        System.out.println("Nhập email: ");
        String email = input.nextLine();

        Contact newContact = new Contact(phoneNumber,contactGroup,fullName,gender,address,dateOfBirth,email);
        return newContact;
    }

    public static void editContactByPhoneNumber() {
        Scanner input = new Scanner(System.in);
        System.out.println("Nhập số điện thoại: ");
        String searchingNumber = input.nextLine();
        int searchingIndex = ContactManager.findContactIndexByPhoneNumber(searchingNumber);
        if (searchingIndex != -1) {
            Contact newContact = creatNewContact();
            ContactManager.editContactByIndex(searchingIndex,newContact);
            System.out.println("Thêm mới liên hệ thành công!");
        }
        else System.out.println("Không tìm được danh bạ với số điện thoại trên");
    }

    public static void deleteContactByPhoneNumber() {
        Scanner input = new Scanner(System.in);
        System.out.println("Nhập số điện thoại: ");
        String searchingNumber = input.nextLine();
        int searchingIndex = ContactManager.findContactIndexByPhoneNumber(searchingNumber);
        if (searchingIndex != -1) {
            char choice = 0;
            System.out.println("Bạn có muốn xóa thông tin liên hệ: Y/N?");
            switch (choice) {
                case 'Y':
                    ContactManager.removeContactByIndex(searchingIndex);
                    System.out.println("Xóa liên hệ thành công!");
                    break;
                default:
                    break;
            }
        }
        else System.out.println("Không tìm được danh bạ với số điện thoại trên");
    }

    public static void searchContact() {
        Scanner input = new Scanner(System.in);
        int choice = -1;
        System.out.println("1. Tìm liên hệ theo tên");
        System.out.println("2. Tìm liên hệ theo sđt");
        System.out.println("Nhập lựa chọn: ");
        choice = input.nextInt();
        if (choice == 1) {
            System.out.println("Nhập số họ tên: ");
            String searchingName = input.nextLine();
            int searchingIndex = ContactManager.findContactIndexByFullname(searchingName);
            if (searchingIndex != -1) {
                Contact foundContact = contacts.get(searchingIndex);
                System.out.println(foundContact);
            }
            else System.out.println("Không tìm được danh bạ với số điện thoại trên");
        }
        if (choice == 2) {
            System.out.println("Nhập số điện thoại: ");
            String searchingNumber = input.nextLine();
            int searchingIndex = ContactManager.findContactIndexByPhoneNumber(searchingNumber);
            if (searchingIndex != -1) {
                Contact foundContact = contacts.get(searchingIndex);
                System.out.println(foundContact);
            }
            else System.out.println("Không tìm được danh bạ với số điện thoại trên");
        }
    }
}
