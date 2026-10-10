import java.util.ArrayList;
import java.util.Scanner;

class Student {
    private String name;
    private double grade;

    public Student(String name, double grade) {
        this.name = name;
        this.grade = grade;
    }

    public String getName() { return name; }
    public double getGrade() { return grade; }
    public void setGrade(double grade) { this.grade = grade; }
}

public class StudentGradeTracker {
    private static final ArrayList<Student> students = new ArrayList<>();
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n===== Student Grade Tracker =====");
            System.out.println("1. Add student");
            System.out.println("2. Update grade");
            System.out.println("3. Remove student");
            System.out.println("4. Show summary report");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            choice = readInt();

            switch (choice) {
                case 1: addStudent(); break;
                case 2: updateGrade(); break;
                case 3: removeStudent(); break;
                case 4: showReport(); break;
                case 5: System.out.println("Goodbye!"); break;
                default: System.out.println("Invalid choice.");
            }
        } while (choice != 5);
    }

    private static void addStudent() {
        System.out.print("Student name: ");
        String name = sc.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("Name cannot be empty.");
            return;
        }
        double grade = readGrade();
        students.add(new Student(name, grade));
        System.out.println("Student added.");
    }

    private static void updateGrade() {
        System.out.print("Student name to update: ");
        Student s = find(sc.nextLine().trim());
        if (s == null) {
            System.out.println("Student not found.");
            return;
        }
        s.setGrade(readGrade());
        System.out.println("Grade updated.");
    }

    private static void removeStudent() {
        System.out.print("Student name to remove: ");
        Student s = find(sc.nextLine().trim());
        if (s == null) {
            System.out.println("Student not found.");
        } else {
            students.remove(s);
            System.out.println("Student removed.");
        }
    }

    private static void showReport() {
        if (students.isEmpty()) {
            System.out.println("No students to report.");
            return;
        }
        double sum = 0;
        Student highest = students.get(0);
        Student lowest = students.get(0);

        System.out.println("\n--- Summary Report ---");
        System.out.printf("%-20s %s%n", "Name", "Grade");
        for (Student s : students) {
            System.out.printf("%-20s %.2f%n", s.getName(), s.getGrade());
            sum += s.getGrade();
            if (s.getGrade() > highest.getGrade()) highest = s;
            if (s.getGrade() < lowest.getGrade()) lowest = s;
        }
        System.out.println("----------------------");
        System.out.printf("Total students : %d%n", students.size());
        System.out.printf("Average score  : %.2f%n", sum / students.size());
        System.out.printf("Highest score  : %.2f (%s)%n", highest.getGrade(), highest.getName());
        System.out.printf("Lowest score   : %.2f (%s)%n", lowest.getGrade(), lowest.getName());
    }

    private static Student find(String name) {
        for (Student s : students) {
            if (s.getName().equalsIgnoreCase(name)) return s;
        }
        return null;
    }

    private static int readInt() {
        try {
            return Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static double readGrade() {
        while (true) {
            System.out.print("Grade (0-100): ");
            try {
                double g = Double.parseDouble(sc.nextLine().trim());
                if (g >= 0 && g <= 100) return g;
            } catch (NumberFormatException ignored) { }
            System.out.println("Please enter a valid number between 0 and 100.");
        }
    }
}
