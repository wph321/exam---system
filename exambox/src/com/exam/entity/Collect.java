package com.exam.entity;



/**
 * Collect entity. @author MyEclipse Persistence Tools
 */

public class Collect  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private Student student;
     private Question question;
     private Quetype quetype;


    // Constructors

    /** default constructor */
    public Collect() {
    }

    
    /** full constructor */
    public Collect(Student student, Question question, Quetype quetype) {
        this.student = student;
        this.question = question;
        this.quetype = quetype;
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

    public Quetype getQuetype() {
        return this.quetype;
    }
    
    public void setQuetype(Quetype quetype) {
        this.quetype = quetype;
    }
   








}