package com.klu.main;

import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.klu.App;
import com.klu.entity.Student;
import com.klu.entity.Certification;

public class App {

    public static void main(String[] args) {

        ApplicationContext context =
                new AnnotationConfigApplicationContext(com.klu.App.class);

        Student student = context.getBean(Student.class);
        Certification cert = student.getCertification();

        Scanner sc = new Scanner(System.in);

        // Student Input
        System.out.print("Enter Student ID: ");
        student.setId(sc.nextInt());
        sc.nextLine();

        System.out.print("Enter Student Name: ");
        student.setName(sc.nextLine());

        System.out.print("Enter Gender: ");
        student.setGender(sc.nextLine());

        // Certification Input
        System.out.print("Enter Certification ID: ");
        cert.setId(sc.nextInt());
        sc.nextLine();

        System.out.print("Enter Certification Name: ");
        cert.setName(sc.nextLine());

        System.out.print("Enter Date Of Completion: ");
        cert.setDateOfCompletion(sc.nextLine());

        student.display();

        sc.close();
    }
}