package com.student;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Date;
import java.util.ListIterator;

public class StudentAccountRecords {
    private LinkedList<StudentAccount> students = new LinkedList<>();
    private HashMap<Integer, StudentAccount> idHashTable;
    private HashMap<Integer, StudentAccount> compositeHashTable;
    private int size;

    //constructor takes in max size, stays constant throughout
    public StudentAccountRecords(int size) {
        this.size = size;
        idHashTable = new HashMap<>(size);
        compositeHashTable = new HashMap<>(size);
    }

    //adds a student record
    public void addStudentRecord(StudentAccount student) {

        //in case we have to update rather than add
        if(getByIDKey(student.getStudentID()) != null && getByCompositeKey(student.hashCode()) != null) {
            ListIterator<StudentAccount> listIterator = students.listIterator();
            while(listIterator.hasNext()) {
                StudentAccount next = listIterator.next();
                if(next.getStudentID() == student.getStudentID())
                    next.setData("This has been updated woop woop!");
            }
            //in case one key matches, but both don't
        } else if(getByIDKey(student.getStudentID()) != null){
            System.out.println("Your ID matches but the Name and DOB do not...something is wrong...");
        } else if(getByCompositeKey(student.hashCode()) != null) {
            System.out.println("Your Name and DOB match but the ID does not... something is wrong...");
        } else {
            //actually adding people to the list
            students.add(student);
            idHashTable.put(student.getStudentID(), student);
            compositeHashTable.put(student.hashCode(), student);
        }



    }

    //removes a student account from the list and tables
    public StudentAccount removeStudentAccount(StudentAccount student) {
        int index = 0;
        ListIterator<StudentAccount> listIterator = students.listIterator();
        while(listIterator.hasNext()) {
            StudentAccount next = listIterator.next();
            if(next.getStudentID() == student.getStudentID())
                index = listIterator.nextIndex()-1;
        }
        students.remove(index);
        idHashTable.remove(student.getStudentID(), student);
        compositeHashTable.remove(student.hashCode(), student);
        return student;
    }

//    public int hash (int ID) {
//        return ID*13 % size;
//    }


    //gets the element by ID Key
    public StudentAccount getByIDKey(Integer key) {
        return idHashTable.get(key);
    }

//    public int hashComposite(StudentAccount studentAccount) {
//        String str = studentAccount.getFirstName()+studentAccount.getLastName()+studentAccount.getDateOfBirth();
//        return hash(str);
//
//    }

    //returns the composite Hash key: a concatenated string of the first name, last name, and DOB
//    public String hashCode(StudentAccount studentAccount) {
//        return studentAccount.getFirstName()+studentAccount.getLastName()+studentAccount.getDateOfBirth();
//    }

//    private int hash(String key)
//    { return (key.hashCode() & 0x7fffffff) % size; }
    //gets a student based on their Name, First Name, and DOB
    public StudentAccount getByCompositeKey(Integer key) {
        return compositeHashTable.get(key);
    }

    //prints out the students linked list and the hash tables
    public void print() {
        System.out.println("Printing Linked List elements:");
        ListIterator<StudentAccount> listIterator = students.listIterator();
        while(listIterator.hasNext()) {
            StudentAccount next = listIterator.next();
            System.out.println(next);
        }
        System.out.println("Finished Printing Linked List Elements");
        System.out.println("Printing ID Hash Table");
        System.out.println(idHashTable);
        System.out.println("Printing Composite Hash Table");
        System.out.println(compositeHashTable);
    }

    public LinkedList<StudentAccount> getByName(String name) {
        LinkedList<StudentAccount> studentsWithName = new LinkedList<>();

        ListIterator<StudentAccount> listIterator = students.listIterator();
        while(listIterator.hasNext()) {
            StudentAccount next = listIterator.next();
            String fullName = next.getFirstName() + " " + next.getLastName();
            if(fullName.contains(name))
                studentsWithName.add(next);
        }

        return studentsWithName;
    }

    public static void main(String[] args) {
        StudentAccountRecords records = new StudentAccountRecords(10);
        Date date = new Date(1500000000);

        StudentAccount student1 = new StudentAccount("Shreya", "Goshal", date);
        StudentAccount student2 = new StudentAccount("Arijit", "Singh", date);
        StudentAccount student3 = new StudentAccount("Armaan", "Malik", date);
        StudentAccount student4 = new StudentAccount("Neha", "Kakkar", date);
        StudentAccount student5 = new StudentAccount("Lata", "Mangeshkar", date);
        StudentAccount student6 = new StudentAccount("Amaal", "Malik", date);
        StudentAccount student7 = new StudentAccount("Tony", "Kakkar", date);
        StudentAccount student8 = new StudentAccount("Kishore", "Kumar", date);
        StudentAccount student9 = new StudentAccount("Alka", "Yagnik", date);
        StudentAccount student10 = new StudentAccount("Jagjit", "Singh", date);


        records.addStudentRecord(student1);
        records.addStudentRecord(student2);
        records.addStudentRecord(student3);
        records.addStudentRecord(student4);
        records.addStudentRecord(student5);
        records.addStudentRecord(student5);
        records.addStudentRecord(student6);
        records.addStudentRecord(student7);
        records.addStudentRecord(student8);
        records.addStudentRecord(student9);
        records.addStudentRecord(student10);

        records.print();

        records.removeStudentAccount(student1);
        records.print();

        records.removeStudentAccount(student5);
        records.print();

        System.out.println("People with last Name \"Kakkar\":");
        System.out.println(records.getByName("Kakkar"));


    }
}
