package com.github.zjiajun.java.core.java8;

import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Created by zhujiajun
 * 16/7/1 21:10
 */
public class PredicateConsumerExample {

    static class Student {
        private String name;
        private Double grade;
        private Double feeDiscount = 0.0;
        private Double baseFee = 20000.0;

        public Student(String name, Double grade) {
            this.name = name;
            this.grade = grade;
        }

        public void printFee() {
            double newFee = baseFee - (baseFee * feeDiscount / 100);
            System.out.println("Discount " + newFee);
        }
    }

    private static Student updateStudentFee(Student student, Predicate<Student> fiPredicate, Consumer<Student> fiConsumer) {
        if (fiPredicate.test(student))
            fiConsumer.accept(student);
        return student;
    }

    public static void main(String[] args) {
        Student student1 = new Student("leon",9.5);
        updateStudentFee(student1,s1 -> s1.grade > 9, s1 -> s1.feeDiscount = 30.0);
        student1.printFee();

        Student student2 = new Student("mary",8.5);
        updateStudentFee(student2,s2 -> s2.grade > 8, s2 -> s2.feeDiscount = 20.0);
        student2.printFee();
    }
}
