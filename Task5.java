import java.util.ArrayList;

class Course {
    private String courseCode;
    private String title;
    private String description;
    private int capacity;
    private String schedule;
    private int enrolledStudents;

    public Course(String courseCode, String title, String description, int capacity, String schedule) {
        this.courseCode = courseCode;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
        this.enrolledStudents = 0;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getSchedule() {
        return schedule;
    }

    public int getEnrolledStudents() {
        return enrolledStudents;
    }

    public boolean isFull() {
        return enrolledStudents >= capacity;
    }

    public boolean enrollStudent() {
        if (!isFull()) {
            enrolledStudents++;
            return true;
        }
        return false;
    }

    public boolean dropStudent() {
        if (enrolledStudents > 0) {
            enrolledStudents--;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return courseCode + ": " + title + "\nDescription: " + description + "\nSchedule: " + schedule + "\nCapacity: " + capacity + ", Enrolled: " + enrolledStudents;
    }
}


// student class
import java.util.HashSet;
import java.util.Set;

class Student {
    private String studentID;
    private String name;
    private Set<Course> registeredCourses;

    public Student(String studentID, String name) {
        this.studentID = studentID;
        this.name = name;
        this.registeredCourses = new HashSet<>();
    }

    public String getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }

    public Set<Course> getRegisteredCourses() {
        return registeredCourses;
    }

    public boolean registerCourse(Course course) {
        if (course.enrollStudent()) {
            return registeredCourses.add(course);
        }
        return false;
    }

    public boolean dropCourse(Course course) {
        if (registeredCourses.remove(course)) {
            return course.dropStudent();
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder courses = new StringBuilder();
        for (Course course : registeredCourses) {
            courses.append(course.getCourseCode()).append(" ");
        }
        return "Student ID: " + studentID + "\nName: " + name + "\nRegistered Courses: " + (courses.length() > 0 ? courses.toString() : "None");
    }
}

// course database class
import java.util.ArrayList;
import java.util.List;

class CourseDatabase {
    private List<Course> courses;

    public CourseDatabase() {
        courses = new ArrayList<>();
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public List<Course> getCourses() {
        return courses;
    }

    public Course getCourseByCode(String courseCode) {
        for (Course course : courses) {
            if (course.getCourseCode().equalsIgnoreCase(courseCode)) {
                return course;
            }
        }
        return null;
    }
}

// student database class
import java.util.ArrayList;
import java.util.List;

class StudentDatabase {
    private List<Student> students;

    public StudentDatabase() {
        students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public List<Student> getStudents() {
        return students;
    }

    public Student getStudentByID(String studentID) {
        for (Student student : students) {
            if (student.getStudentID().equalsIgnoreCase(studentID)) {
                return student;
            }
        }
        return null;
    }
}

// main class
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CourseDatabase courseDatabase = new CourseDatabase();
        StudentDatabase studentDatabase = new StudentDatabase();

        courseDatabase.addCourse(new Course("CS101", "Introduction to Computer Science", "Basics of Computer Science", 30, "MWF 9-10AM"));
        courseDatabase.addCourse(new Course("MATH201", "Calculus I", "Introduction to Calculus", 25, "TTh 11-12:30PM"));
        courseDatabase.addCourse(new Course("ENG301", "English Literature", "Study of English Literature", 20, "MWF 10-11AM"));

        studentDatabase.addStudent(new Student("S123", "Rajesh"));
        studentDatabase.addStudent(new Student("S456", "Priya"));

        while (true) {
            System.out.println("\n1. Display Available Courses");
            System.out.println("2. Register for a Course");
            System.out.println("3. Drop a Course");
            System.out.println("4. Display Student Information");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    displayCourses(courseDatabase);
                    break;
                case 2:
                    registerCourse(scanner, courseDatabase, studentDatabase);
                    break;
                case 3:
                    dropCourse(scanner, studentDatabase);
                    break;
                case 4:
                    displayStudentInfo(scanner, studentDatabase);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void displayCourses(CourseDatabase courseDatabase) {
        System.out.println("\nAvailable Courses:");
        for (Course course : courseDatabase.getCourses()) {
            System.out.println(course);
            System.out.println();
        }
    }

    private static void registerCourse(Scanner scanner, CourseDatabase courseDatabase, StudentDatabase studentDatabase) {
        System.out.print("Enter Student ID: ");
        String studentID = scanner.nextLine();
        Student student = studentDatabase.getStudentByID(studentID);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.print("Enter Course Code: ");
        String courseCode = scanner.nextLine();
        Course course = courseDatabase.getCourseByCode(courseCode);
        if (course == null) {
            System.out.println("Course not found.");
            return;
        }

        if (student.registerCourse(course)) {
            System.out.println("Successfully registered for " + course.getTitle());
        } else {
            System.out.println("Failed to register. The course may be full.");
        }
    }

    private static void dropCourse(Scanner scanner, StudentDatabase studentDatabase) {
        System.out.print("Enter Student ID: ");
        String studentID = scanner.nextLine();
        Student student = studentDatabase.getStudentByID(studentID);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.print("Enter Course Code: ");
        String courseCode = scanner.nextLine();
        Course course = null;
        for (Course c : student.getRegisteredCourses()) {
            if (c.getCourseCode().equalsIgnoreCase(courseCode)) {
                course = c;
                break;
            }
        }

        if (course == null) {
            System.out.println("Course not found in your registered courses.");
            return;
        }

        if (student.dropCourse(course)) {
            System.out.println("Successfully dropped " + course.getTitle());
        } else {
            System.out.println("Failed to drop the course.");
        }
    }

    private static void displayStudentInfo(Scanner scanner, StudentDatabase studentDatabase) {
        System.out.print("Enter Student ID: ");
        String studentID = scanner.nextLine();
        Student student = studentDatabase.getStudentByID(studentID);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.println("\nStudent Information:");
        System.out.println(student);
    }
}

// please create different files to run these codes
