package com.exam.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import com.exam.biz.CardBiz;
import com.exam.biz.GradeBiz;
import com.exam.biz.QuestionBiz;
import com.exam.entity.Card;
import com.exam.entity.Grade;
import com.exam.entity.Question;
import com.exam.entity.Student;
import com.exam.entity.Subject;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;


public class ExamAction extends ActionSupport {
	private GradeBiz gradebiz;
	private CardBiz cardbiz;
	private QuestionBiz quebiz;
	private Subject subject;
	private Card card;
	private Integer nextqno;
	//按科目练习
	public String add() throws Exception {
		try{
			Map<String,Object> session = ActionContext.getContext().getSession();
			Student stu =(Student)session.get("user");
			//随机抽10题
			Random r=new Random();
			List<Question> qlist =quebiz.findQuestionBySubject(subject.getId());
			Set<Integer> set=new HashSet<Integer>();
			//测试3道题
			while(set.size()<3){
				set.add(r.nextInt(qlist.size()));
			}
			//新建grade;
			Grade g=new Grade();
			g.setTime(new Date());
			g.setStudent(stu);
			g.setSubject(subject);
			gradebiz.addGrade(g);
			Grade grade =gradebiz.findAllGradeByStu(stu).get(0);
			//加题
			Iterator<Integer> num= set.iterator();
			int qno=1;
			while(num.hasNext()){
				Card c=new Card();
				c.setGrade(grade);
				c.setQuestion(qlist.get(num.next()));
				c.setResult("F");
				c.setScore(0);
				c.setQno(qno);
				cardbiz.addCard(c);
				qno++;
			}
			//显示第一题
			card = cardbiz.findCardByGidAndQno(grade.getId(), 1);
			List<Card> cardList=  cardbiz.findCardByGid(grade.getId());
			session.put("cardList",cardList);
			return "add_success";
		}catch(Exception e){
			e.printStackTrace();
		}
		return "exception";
	}
	public String update() throws Exception {
		try{
			Map<String,Object> session = ActionContext.getContext().getSession();
			Card c=cardbiz.findCardById(card.getId());
			c.setUanswer(card.getUanswer());
			//判断成绩
			if(c.getQuestion().getResult().equals(card.getUanswer())){
				c.setResult("T");
				c.setScore(10);
			}
			cardbiz.updateCard(c);
			//更新
			card = cardbiz.findCardByGidAndQno(card.getGrade().getId(), nextqno);
			List<Card> cardList=  cardbiz.findCardByGid(card.getGrade().getId());
			session.put("cardList",cardList);
			return "update_success";
		}catch(Exception e){
			e.printStackTrace();
		}
		return "exception";
	}
	public String findone() throws Exception {
		try{
			card = cardbiz.findCardById(card.getId());
			
			return "findone_success";
		}catch(Exception e){
			e.printStackTrace();
		}
		return "exception";
	}
	public String submit() throws Exception {
		try{
			card = cardbiz.findCardById(card.getId());
			
			return "findone_success";
		}catch(Exception e){
			e.printStackTrace();
		}
		return "exception";
	}
	public GradeBiz getGradebiz() {
		return gradebiz;
	}

	public void setGradebiz(GradeBiz gradebiz) {
		this.gradebiz = gradebiz;
	}

	public CardBiz getCardbiz() {
		return cardbiz;
	}

	public void setCardbiz(CardBiz cardbiz) {
		this.cardbiz = cardbiz;
	}

	public QuestionBiz getQuebiz() {
		return quebiz;
	}

	public void setQuebiz(QuestionBiz quebiz) {
		this.quebiz = quebiz;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	public Card getCard() {
		return card;
	}
	public void setCard(Card card) {
		this.card = card;
	}
	public Integer getNextqno() {
		return nextqno;
	}
	public void setNextqno(Integer nextqno) {
		this.nextqno = nextqno;
	}

	
	
}