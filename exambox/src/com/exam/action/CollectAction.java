package com.exam.action;


import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.exam.biz.CollectBiz;
import com.exam.biz.QuestionBiz;
import com.exam.biz.QuetypeBiz;
import com.exam.entity.Collect;
import com.exam.entity.Error;
import com.exam.entity.Question;
import com.exam.entity.Quetype;
import com.exam.entity.Student;
import com.exam.entity.Subject;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;


public class CollectAction extends ActionSupport {
	
	private Question question;
	private Quetype quetype;

	private CollectBiz collectBiz;
	private QuestionBiz questionBiz;
	private QuetypeBiz quetypeBiz;
	private Collect collect;
	private Subject subject;
	private Integer nowpage;
	private Integer pages;
	private Integer nextpage;
	private Integer backpage;
	
	public String add(){
		try{
		System.out.println("进入到了添加收藏的方法中");
		
		HttpServletRequest req= ServletActionContext.getRequest();
		HttpServletResponse resp = ServletActionContext.getResponse();
		
		req.setCharacterEncoding("utf-8");
		
		Collect temp_collect = new Collect();
		Map session = ActionContext.getContext().getSession();
		Student stu = (Student) session.get("user");
		//判断获取的question 的id 是否在数据库中已经存在，假设存在，则不需要收藏，返回已经收藏
		int flag=0;
		Collect c=null;
		List<Collect> collectList = collectBiz.findAllCollectByStu(stu);
		for(Collect collect:collectList){
			System.out.println(collect.getId());
			if(collect.getQuestion().getId().equals(question.getId())){
				c=collect;
				flag = 1;
			}
		}
		
		if(flag==0){
		//根据question的id查询question的subject
			Question temp_question = questionBiz.findQuestionById(question.getId());
			temp_collect.setQuestion(temp_question);
			
			Quetype temp_quetype = quetypeBiz.findByid(quetype.getId());
			temp_collect.setQuetype(temp_quetype);
			
			temp_collect.setStudent(stu);
			
			collectBiz.addCollect(temp_collect);
			//添加成功后更新这个集合
			collectList = collectBiz.findAllCollectByStu(stu);
			 //将对象转化为字符串
				String info="收藏成功！";
				System.out.println("服务器返回给客户端的信息："+info);
				
				resp.setCharacterEncoding("utf-8");
				PrintWriter out=resp.getWriter();
				out.print(info);
				
		    	out.flush();
		    	out.close();
			}else{
				collectBiz.deleteCollectById(c);
				String info="已取消收藏！";
				System.out.println("服务器返回给客户端的信息："+info);
				resp.setCharacterEncoding("utf-8");
				PrintWriter out=resp.getWriter();
				out.print(info);
				
		    	out.flush();
		    	out.close();
			}
			System.out.println("长度为："+collectList.size());
			if(session.get("collectList")!=null){
				session.remove("collectList");
				session.put("collectList",collectList);
			}else{
				session.put("collectList",collectList);
			}
		}catch(Exception ex){
			ex.printStackTrace();
			return "exception";
		}
		return "add_success";
	}
		
	public String delete(){
		try{
		System.out.println("进入到了删除的方法中");
		Map session = ActionContext.getContext().getSession();
		collectBiz.deleteCollectById(collect);
		Student stu = (Student) session.get("user");
		List<Collect> collectList = collectBiz.findAllCollectByStu(stu);
		if(session.get("collectList")!=null){
			session.remove("collectList");
			session.put("collectList",collectList);
		}else{
			session.put("collectList",collectList);
		}
		}catch(Exception ex){
			ex.printStackTrace();
			return "exception";
		}
		return  "delete_success";
	}
	public String find(){
		try{
		System.out.println("进入到第一次查找");
		Map session = ActionContext.getContext().getSession();
		nowpage =1;
		pages = collectBiz.findPage();
		
		List<Collect> collectList = collectBiz.findCollectByPage(nowpage);
		nextpage = nowpage+1;
		if(nextpage>pages){
			nextpage= pages;
		}
		backpage = nowpage-1;
		if(backpage==0){
			backpage=1;
		}
	
		
		if(session.get("collectList")!=null){
			session.remove("collectList");
			session.put("collectList",collectList);
		}else{
			session.put("collectList",collectList);
		}
		}catch(Exception ex){
			ex.printStackTrace();
			return "exception";
		}
		return "find_success";
	}
	public String findByPage(){
		try{
			System.out.println("进入到了按页查找的方法");
			if(nowpage==null||nowpage==0){
				nowpage=1;
			}
				Collect temp_collect  = new Collect();
				Question temp_question = new Question();
				Subject temp_subject = new Subject();
				
				temp_collect.setQuestion(temp_question);
				Map session = ActionContext.getContext().getSession();
				Student  stu = (Student) session.get("user");
				temp_subject.setId(subject.getId());
		
			
				session.put("collectsubid",subject.getId());
				List<Collect> collectList ;
				if(temp_subject.getId()!=-1){
					temp_collect.getQuestion().setSubject(temp_subject);
				
					 collectList  = collectBiz.findCollectByPageAndStu(nowpage, stu,temp_collect);
					 pages = collectBiz.findPageByStu(stu,temp_collect);
				}
				else{
					Collect temp_collect1  = new Collect();
					Question question1 = new Question();
					
					temp_collect1.setQuestion(question1);
					temp_collect1.getQuestion().setSubject(temp_subject);
					System.out.println("进入到查询所有的方法中"+nowpage);
					 collectList = collectBiz.findCollectByPageAndStu(nowpage,stu,temp_collect1);
					 pages = collectBiz.findPageByStu(stu,temp_collect1);
				}
			
			//上一页下一页的问题
				nextpage = nowpage+1;
				if(nextpage>pages){
					nextpage= pages;
				}
				backpage = nowpage-1;
				if(backpage==0){
					backpage=1;
				}
			
				if(session.get("collectList")!=null){
					session.remove("collectList");
					session.put("collectList",collectList);
				}else{
					session.put("collectList",collectList);
				}
		}catch(Exception ex){
			ex.printStackTrace();
			return "excetption";
		}
		return "findByPage_success";
	}
	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}
	public Quetype getQuetype() {
		return quetype;
	}
	public void setQuetype(Quetype quetype) {
		this.quetype = quetype;
	}
	public CollectBiz getCollectBiz() {
		return collectBiz;
	}
	public void setCollectBiz(CollectBiz collectBiz) {
		this.collectBiz = collectBiz;
	}
	public QuestionBiz getQuestionBiz() {
		return questionBiz;
	}
	public void setQuestionBiz(QuestionBiz questionBiz) {
		this.questionBiz = questionBiz;
	}
	public QuetypeBiz getQuetypeBiz() {
		return quetypeBiz;
	}
	public void setQuetypeBiz(QuetypeBiz quetypeBiz) {
		this.quetypeBiz = quetypeBiz;
	}

	public Collect getCollect() {
		return collect;
	}

	public void setCollect(Collect collect) {
		this.collect = collect;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public Integer getNowpage() {
		return nowpage;
	}

	public void setNowpage(Integer nowpage) {
		this.nowpage = nowpage;
	}

	public Integer getPages() {
		return pages;
	}

	public void setPages(Integer pages) {
		this.pages = pages;
	}

	public Integer getNextpage() {
		return nextpage;
	}

	public void setNextpage(Integer nextpage) {
		this.nextpage = nextpage;
	}

	public Integer getBackpage() {
		return backpage;
	}

	public void setBackpage(Integer backpage) {
		this.backpage = backpage;
	}

}