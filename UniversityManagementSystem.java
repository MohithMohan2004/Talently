import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Class representing a Course
class Course {
    private String courseCode;
    private String courseName;
    private int maxEnrollment;
    private List<Student> enrolledStudents;

    // Constructor to initialize Course
    public Course(String courseCode, String courseName, int maxEnrollment) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.maxEnrollment = maxEnrollment;
        this.enrolledStudents = new ArrayList<>();
    }

    // Getters
    public String getCourseCode() {
        return courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public List<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    // Method to enroll a student
    public boolean enrollStudent(Student student) {
        if (enrolledStudents.size() < maxEnrollment) {
            enrolledStudents.add(student);
            student.addCourse(this);
            return true;
        }
        return false;
    }

    // Method to display course details
    public void displayCourseDetails() {
        System.out.println("Course Code: " + courseCode);
        System.out.println("Course Name: " + courseName);
        System.out.println("Max Enrollment: " + maxEnrollment);
        System.out.println("Enrolled Students: " + enrolledStudents.size());
        System.out.println("------------------------------");
    }
}

// Class representing a Student
class Student {
    private String studentId;
    private String name;
    private List<Course> enrolledCourses;
    private List<Double> grades;

    // Constructor to initialize Student
    public Student(String studentId, String name) {
        this.studentId = studentId;
        this.name = name;
        this.enrolledCourses = new ArrayList<>();
        this.grades = new ArrayList<>();
    }

    // Getters
    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public List<Course> getEnrolledCourses() {
        return enrolledCourses;
    }

    // Method to enroll in a course
    public void addCourse(Course course) {
        enrolledCourses.add(course);
    }

    // Method to assign grade to a course
    public void assignGrade(Course course, double grade) {
        if (enrolledCourses.contains(course)) {
            int courseIndex = enrolledCourses.indexOf(course);
            grades.add(courseIndex, grade);
        } else {
            System.out.println("Student not enrolled in the course: " + course.getCourseName());
        }
    }

    // Method to display student details
    public void displayStudentDetails() {
        System.out.println("Student ID: " + studentId);
        System.out.println("Name: " + name);
        System.out.println("Enrolled Courses:");
        for (int i = 0; i < enrolledCourses.size(); i++) {
            Course course = enrolledCourses.get(i);
            System.out.println(course.getCourseName() + " - Grade: " + (i < grades.size() ? grades.get(i) : "Not Graded"));
        }
        System.out.println("------------------------------");
    }
}

// Class representing the University
class University {
    private String universityName;
    private List<Student> students;
    private List<Course> courses;

    // Constructor to initialize University
    public University(String universityName) {
        this.universityName = universityName;
        this.students = new ArrayList<>();
        this.courses = new ArrayList<>();
    }

    // Method to add a student
    public void addStudent(String studentId, String studentName) {
        Student student = new Student(studentId, studentName);
        students.add(student);
        System.out.println("Student added: " + studentName);
    }

    // Method to add a course
    public void addCourse(String courseCode, String courseName, int maxEnrollment) {
        Course course = new Course(courseCode, courseName, maxEnrollment);
        courses.add(course);
        System.out.println("Course added: " + courseName);
    }

    // Method to find a student by ID
    public Student findStudentById(String studentId) {
        for (Student student : students) {
            if (student.getStudentId().equals(studentId)) {
                return student;
            }
        }
        return null;
    }

    // Method to find a course by course code
    public Course findCourseByCode(String courseCode) {
        for (Course course : courses) {
            if (course.getCourseCode().equals(courseCode)) {
                return course;
            }
        }
        return null;
    }

    // Method to display all courses
    public void displayAllCourses() {
        for (Course course : courses) {
            course.displayCourseDetails();
        }
    }

    // Method to display all students
    public void displayAllStudents() {
        for (Student student : students) {
            student.displayStudentDetails();
        }
    }

    // Getter for University Name
    public String getUniversityName() {
        return universityName;
    }
}

// Main class to manage the system
public class UniversityManagementSystem {  // Renamed from UniversityManagementSystem to Main
    private static Scanner scanner = new Scanner(System.in);  // Import Scanner for user input
    private static University university;

    // Main method to run the system
    public static void main(String[] args) {
        // Initialize University
        university = new University("Global University");

        while (true) {
            // Display Menu
            System.out.println("\nWelcome to " + university.getUniversityName());
            System.out.println("1. Add Student");
            System.out.println("2. Add Course");
            System.out.println("3. Enroll Student in Course");
            System.out.println("4. Assign Grade to Student");
            System.out.println("5. View All Courses");
            System.out.println("6. View All Students");
            System.out.println("7. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    addCourse();
                    break;
                case 3:
                    enrollStudentInCourse();
                    break;
                case 4:
                    assignGradeToStudent();
                    break;
                case 5:
                    viewAllCourses();
                    break;
                case 6:
                    viewAllStudents();
                    break;
                case 7:
                    System.out.println("Exiting the University Management System...");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    // Method to add a student
    private static void addStudent() {
        System.out.print("Enter student ID: ");
        String studentId = scanner.next();
        System.out.print("Enter student name: ");
        String studentName = scanner.next();

        university.addStudent(studentId, studentName);
    }

    // Method to add a course
    private static void addCourse() {
        System.out.print("Enter course code: ");
        String courseCode = scanner.next();
        System.out.print("Enter course name: ");
        String courseName = scanner.next();
        System.out.print("Enter maximum enrollment: ");
        int maxEnrollment = scanner.nextInt();

        university.addCourse(courseCode, courseName, maxEnrollment);
    }

    // Method to enroll a student in a course
    private static void enrollStudentInCourse() {
        System.out.print("Enter student ID: ");
        String studentId = scanner.next();
        Student student = university.findStudentById(studentId);
        if (student != null) {
            System.out.print("Enter course code: ");
            String courseCode = scanner.next();
            Course course = university.findCourseByCode(courseCode);
            if (course != null) {
                if (course.enrollStudent(student)) {
                    System.out.println("Student " + student.getName() + " successfully enrolled in " + course.getCourseName());
                } else {
                    System.out.println("Course is full, enrollment failed.");
                }
            } else {
                System.out.println("Course not found!");
            }
        } else {
            System.out.println("Student not found!");
        }
    }

    // Method to assign grade to a student
    private static void assignGradeToStudent() {
        System.out.print("Enter student ID: ");
        String studentId = scanner.next();
        Student student = university.findStudentById(studentId);
        if (student != null) {
            System.out.print("Enter course code: ");
            String courseCode = scanner.next();
            Course course = university.findCourseByCode(courseCode);
            if (course != null) {
                System.out.print("Enter grade: ");
                double grade = scanner.nextDouble();
                student.assignGrade(course, grade);
                System.out.println("Grade assigned to " + student.getName() + " in course " + course.getCourseName());
            } else {
                System.out.println("Course not found!");
            }
        } else {
            System.out.println("Student not found!");
        }
    }

    // Method to view all courses
    private static void viewAllCourses() {
        university.displayAllCourses();
    }

    // Method to view all students
    private static void viewAllStudents() {
        university.displayAllStudents();
    }
}