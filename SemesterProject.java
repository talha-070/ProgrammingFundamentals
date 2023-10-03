package pf;

import java.util.*;
import java.io.*;
import java.lang.*;


public class SemesterProject {

    public static void main(String[] args) {
        try {
            boolean keeprunning = true;
            while (keeprunning) {//The program will keep running until the user wants to stop it
                File f2 = new File("Students.txt");
                Scanner input1 = new Scanner(f2);//Taking input from a file for roll number and names of students to print them on console
                System.out.println("The Name of the students with their roll numbers are: ");
                int count = 0;//Total number of students
                while (input1.hasNext()) {
                    int s1 = input1.nextInt();
                    String s2 = input1.next();
                    System.out.println("Name:" + s2 + " Roll Number:" + s1);//printing
                    count++;
                }
                input1.close();
//                AllRolls(count);//This gives us an Array of roll numbers of all students
                int[] RN = AllRolls(count);//This is the roll number Array to be used multiple times 
                System.out.println("There are two tests to be performed.\n"
                        + "If you want to perform both of the tests by the students as shown above then enter '1'\n"
                        + "If you want to remove a student then enter '2'\n"
                        + "If you want to add a student then enter '3'\n"
                        + "If you want to change a student's name then enter '4'\n"
                        + "If you want to search a student then enter '5'\n"
                        + "If you want to Exit then enter '0'\n");
                Scanner input2 = new Scanner(System.in);
                int mode = input2.nextInt();
                switch (mode) {
                    case 1:
                        Test1();//Method for test 1
                        Test2();//Method for test 2
                        System.out.println("The tests were performed successfully!");
                        System.out.println("\n-------------\n");
                        break;
                    case 2:
                        int roll1 = CheckRoll();//The method CheckRoll() is generic, because I wanted to use the roll number entered by the user in different methods
                        boolean b1 = RollExists(RN, roll1);//This method checks weather the roll number exists or not
                        RemoveStudent(roll1, b1);//This method removes the specific student whose roll number is entered by the user
                        System.out.println("\n-------------\n");
                        break;
                    case 3:
                        AddStudent(RN);//This method adds the student whose name is entered by the user and give a roll number to him automatically
                        System.out.println("\n-------------\n");
                        break;
                    case 4:
                        int roll2 = CheckRoll();//explaind this earlier
                        boolean b2 = RollExists(RN, roll2);//explaind this earlier
                        ChangeName(roll2, b2);//This method changes the name which then user enters correct name of the specific student whose roll number is entered by the user
                        System.out.println("\n-------------\n");
                        break;
                    case 5:
                        int roll3 = CheckRoll();//explaind this earlier
                        boolean b3 = RollExists(RN, roll3);//explaind this earlier
                        Search(roll3, b3);//This method searches for the entered roll number and gives back the student's name
                        System.out.println("\n-------------\n");
                        break;
                    case 0:
                        System.out.println("Exiting...");
                        keeprunning = false;//the loop stops
                        break;
                    default:
                        System.out.println("Invalid input");//if any other value is entered then it will show invalid input on the console
                        System.out.println("\n-------------\n");
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println("The exception occured is: " + e.toString());
        }
    }

    private static void Test1() {
        try {
            System.out.println("Now for the 1st test:\n");
            File f4 = new File("Result1.txt");
            f4.createNewFile();//This creates new file when this method starts
            System.out.println("The Students will perform their tests roll number wise...\n");
            File f3 = new File("Students.txt");
            Scanner input2 = new Scanner(f3);//Student information takan in
            PrintWriter pw = new PrintWriter(f4);//object for printing on file
            pw.println("The Results of the 1st test are:");//this line is printed on file "Results1"
            pw.println("");
            while (input2.hasNext()) {//this loop will continue until all student have given the test
                String s1 = input2.next();
                String s2 = input2.next();
                System.out.println("Now " + s2 + " Roll Number:" + s1 + " will perform the test...");//for specific student

                System.out.println("\n-------------\n");

                File f = new File("MCQs1.txt");//the MCQs are written in this file
                Scanner input3 = new Scanner(f);//MCQs takan in to be printed on console
                while (input3.hasNext()) {
                    String s = input3.nextLine();
                    System.out.println(s);//All the MCQs are printed on console
                }
                input3.close();//closing the file

                System.out.println("\n-------------\n");

                System.out.println("Now Enter the correct Answers of the given above Questions: ");
                Scanner input = new Scanner(System.in);
                int[] Array1 = {4, 3, 2, 2, 2};//correct Answers stored in an Array
                int count1 = 0;//Marks obtained by the student
                for (int j = 0; j <= 4;) {//This loop takes answers of questions
                    System.out.println("For " + (j + 1) + " Question:");//Numbering of questions
                    int a = input.nextInt();
                    if (a > 4 || a < 1) {//A MCQ has only "1,2,3,4" options, so only one of these must be entered
                        System.out.println("This option is not valid. Please again enter one of the options for the same question");
                        j = j;
                    } else if (a == Array1[j]) {//if it is a coorect answer then marks will be incremented by 1
                        count1++;
                        j++;
                    } else {//if it is a wrong answer then the loop will keep on going
                        j++;
                    }
                }
                System.out.println("Roll Number:" + s1 + ", your marks in the 1st test are: " + count1);//marks printed on console for specific student
                System.out.print("The True Answers were: {");//All the true answers are also printed on console so that the student can tally their marks
                for (int j = 0; j < Array1.length; j++) {
                    System.out.print(Array1[j] + ",");
                }
                System.out.println("}\n");

                System.out.println("\n-------------\n");
                pw.println(s2 + ", Roll Number:" + s1 + " got " + count1 + " marks");//this prints the marks obtained by each student on the "Result1" file
            }
            pw.close();//closing the Print Writer object
            input2.close();//closing the file
        } catch (Exception e) {
            System.out.println("The exception occured is: " + e.toString());
        }
    }

    private static void Test2() {
        try {
            System.out.println("Now for the 2nd Test: \n");
            File f4 = new File("Result2.txt");
            f4.createNewFile();//This creates new file when this method starts
            System.out.println("The Students will perform their tests roll number wise...\n");
            File f3 = new File("Students.txt");
            Scanner input2 = new Scanner(f3);//Student information takan in
            PrintWriter pw = new PrintWriter(f4);//object for printing on file
            pw.println("The Results of the 2nd test are:");//this line is printed on file "Results2"
            pw.println("");
            while (input2.hasNext()) {//this loop will continue until all student have given the test
                String s1 = input2.next();
                String s2 = input2.next();
                System.out.println("Now " + s2 + " Roll Number:" + s1 + " will perform the test...");//for specific student

                System.out.println("\n-------------\n");

                File f = new File("MCQs2.txt");//the MCQs are written in this file
                Scanner input3 = new Scanner(f);//MCQs takan in to be printed on console
                while (input3.hasNext()) {
                    String s = input3.nextLine();
                    System.out.println(s);//All the MCQs are printed on console
                }
                input3.close();//closing the file

                System.out.println("\n-------------\n");

                System.out.println("Now Enter the correct Answers of the given Questions: ");
                Scanner input = new Scanner(System.in);
                int[] Array1 = {2, 4, 3, 4, 2};//correct Answers stored in an Array
                int count = 0;//Marks obtained by the student
                for (int j = 0; j <= 4;) {//This loop takes answers of questions
                    System.out.println("For " + (j + 1) + " Questiion:");//Numbering of questions
                    int a = input.nextInt();
                    if (a > 4 || a < 1) {//A MCQ has only "1,2,3,4" options, so only one of these must be entered
                        System.out.println("This option is not valid. Please again enter one of the options for the same question");
                        j = j;
                    } else if (a == Array1[j]) {//if it is a coorect answer then marks will be incremented by 1
                        count++;
                        j++;
                    } else {//if it is a wrong answer then the loop will keep on going
                        j++;
                    }
                }
                System.out.println("Roll Number:" + s1 + ", your marks in the 2nd test are: " + count);//marks printed on console for specific student
                System.out.print("The True Answers were: {");//All the true answers are also printed on console so that the student can tally their marks
                for (int j = 0; j < Array1.length; j++) {
                    System.out.print(Array1[j] + ",");
                }
                System.out.println("}\n");

                System.out.println("\n-------------\n");
                pw.println(s2 + ", Roll Number:" + s1 + " got " + count + " marks");//this prints the marks obtained by each student on the "Result2" file
            }
            pw.close();//closing the file
            input2.close();//closing the file
        } catch (Exception e) {
            System.out.println("The exception occured is: " + e.toString());
        }
    }

    private static void RemoveStudent(int roll1, boolean b1) {
        try {
            if (b1 == true) {//if this is true then it means that the student exists
                File f = new File("Students.txt");//the original file
                File temp = new File("NewStudents.txt");//creating a temporary file
                PrintWriter pw = new PrintWriter(temp);//object for printing on the temporary file
                Scanner input1 = new Scanner(f);//getting input from the original file
                input1.useDelimiter("[ \n]");//This is used to seperate things in a file, like we use splitting function for strings
                while (input1.hasNext()) {//the loop will continue until all the input is taken from the file
                    int proll = input1.nextInt();//this takes roll number of the student from the original file
                    String name = input1.next();//this takes name of the student from the original file
                    if (proll != roll1) {//if the roll entered by the user of student to be removed is equall to the roll number of the student stored in the original file then it will not be printed on the temporary file
                        pw.println(proll + " " + name);//the rest of the data is same to same printed on the temporary file
                    }
                }
                input1.close();//closes the file
                pw.close();//closes the file
                File dump = f;//for renaming of temporary file
                f.delete();//deletes the originalfile
                temp.renameTo(dump);//temporary file renamed            
            } else {
                System.out.println("No such student exist to remove");
            }
        } catch (Exception e) {
            System.out.println("The exception occured is: " + e.toString());
        }

    }

    private static void AddStudent(int[] RN) {
        try {
            Scanner input2 = new Scanner(System.in);
            File f2 = new File("Students.txt");
            FileWriter fw = new FileWriter(f2, true);//FileWriter object is used because we want to append in the file
            BufferedWriter bw = new BufferedWriter(fw);//this optimises the input stream
            PrintWriter pw = new PrintWriter(bw);//this prints the data on the file
            System.out.println("Enter name of the student. Just the first name and a new roll number will be alloted to him by the computer automatically.");
            String s2 = input2.next();//takes in a name from the user
            newRoll(RN);//calls a methods to alloct a new roll number to the new student
            pw.println(newRoll(RN) + " " + s2);//prints the data on the file
            pw.close();//closes the file
        } catch (Exception e) {
            System.out.println("The exception occured is: " + e.toString());
        }

    }

    private static void ChangeName(int roll2, boolean b2) {
        try {
            if (b2 == true) {//if this is true then it means that the student exists
                System.out.println("Now enter the correct name of the student. Just the First name.");
                Scanner input = new Scanner(System.in);
                String newN = input.next();//Takes a new name for the student from the user
                File f = new File("Students.txt");//the original file
                File temp = new File("NewStudents.txt");//creating a temporary file
                FileWriter fw = new FileWriter(temp);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter pw = new PrintWriter(bw);//object for printing on the temporary file
                Scanner input1 = new Scanner(f);//getting input from the original file
                while (input1.hasNext()) {//the loop will continue until all the input is taken from the file
                    int proll = input1.nextInt();//this takes roll number of the student from the original file
                    String name = input1.next();//this takes name of the student from the original file
                    if (proll == roll2) {//if the roll number entered by the user is same on the original file then against that roll number the new name will be printed on the temporary file
                        pw.println(proll + " " + newN);
                    } else {//the rest of the data is printed on the temporary file
                        pw.println(proll + " " + name);
                    }
                }
                input1.close();//closes the file
                pw.close();//closes the file
                File dump = f;//for renaming of temporary file
                f.delete();//deletes the original file
                temp.renameTo(dump);//temporary file renamed to original file
            } else {
                System.out.println("Their is no such student with this roll number");
            }
        } catch (Exception e) {
            System.out.println("The exception occured is: " + e.toString());
        }

    }

    private static int newRoll(int[] RN) {
        for (int k = 1; k < 100;) {//This methods returns a roll number that is not taken in acending order
            for (int l = 0; l < RN.length; l++) {
                if (k != RN[l]) {
                    return k;
                } else {
                    k++;
                }
            }
        }
        return 0;
    }

    private static void Search(int roll3, boolean b3) {
        try {
            if (b3 == true) {//if this is true then it means that the student exists
                File f1 = new File("Students.txt");
                Scanner input = new Scanner(f1);//reading from a file
                boolean found = false;//for loop breaking condition
                while (input.hasNext() && !found) {
                    int Roll = input.nextInt();//for roll number taking in from file
                    String s = input.next();//for name taking in from file
                    if (Roll == roll3) {//if the roll number entered by the user matches by the roll number on the file then it will be printed on console
                        System.out.println("The student exists");
                        System.out.println("Name:" + s + ", Roll Number:" + Roll);
                        found = true;
                    }
                }
                input.close();//closes the file
            } else {
                System.out.println("No such student found");
            }
        } catch (Exception e) {
            System.out.println("The occured is: " + e.toString());
        }

    }

    private static int[] AllRolls(int count) {
        int[] AS = new int[count];//declaring an Array, count is the total number of students
        try {
            File f1 = new File("Students.txt");//this program returns an Array of all the roll number present in file
            Scanner input1 = new Scanner(f1);//reading from a file        
            int i = 0;//for Array indexing
            while (input1.hasNext()) {
                int roll = input1.nextInt();
                String s2 = input1.next();
                AS[i] = roll;//storing roll number according to Array indexing
                i++;
            }
            input1.close();//closes the file        
        } catch (Exception e) {
            System.out.println("The exception occured is: " + e.toString());
        }
        return AS;
    }

    private static boolean RollExists(int[] RN, int roll) {
        boolean b = false;//This program returns true or false wheather the roll number exists or not
        for (int i = 0; i < RN.length;) {
            if (roll == RN[i]) {
                b = true;
                break;
            } else {
                i++;
            }
        }
        return b;
    }

    private static int CheckRoll() {
        System.out.println("Enter the Roll number of the Student");//this program simply takes in a value entered by the user for roll number and this roll number is then used for searching, removing or changing name
        Scanner input = new Scanner(System.in);
        int x = input.nextInt();
        return x;
    }

}
