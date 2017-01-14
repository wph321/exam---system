package com.exam.entity;



/**
 * Done entity. @author MyEclipse Persistence Tools
 */

public class Done  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private Student student;
     private Question question;


    // Constructors

    /** default constructor */
    public Done() {
    }

    
    /** full constructor */
    public Done(Student student, Question question) {
        this.student = student;
        this.question = question;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public Student getStudent() {
        return this.student;
    }
    
    public void setStudent(Student student) {
        this.student = student;
    }

    public Question getQuestion() {
        return this.question;
    }
    
    public void setQuestion(Question question) {
        this.question = question;
    }
   








}