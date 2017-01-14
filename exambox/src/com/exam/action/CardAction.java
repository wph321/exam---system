package com.exam.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import com.exam.biz.CardBiz;
import com.exam.biz.DoneBiz;
import com.exam.biz.ErrorBiz;
import com.exam.biz.GradeBiz;
import com.exam.biz.QuestionBiz;
import com.exam.entity.Card;
import com.exam.entity.Done;
import com.exam.entity.Error;
import com.exam.entity.Grade;
import com.exam.entity.Question;
import com.exam.entity.Student;
import com.exam.entity.Subject;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Mulquestion entity. @author MyEclipse Persistence Tools
 */

public class CardAction extends ActionSupport {
	private GradeBiz gradebiz;
	private CardBiz cardbiz;
	private QuestionBiz quebiz;
	private ErrorBiz errorbiz;
	private DoneBiz donebiz;
	
	private Subject subject;
	private Card card;
	private Integer nextqno;
	private Integer time;
	private Grade grade;
	private Integer pid;
	//���
	private Integer total;
	private Integer right;
	private Integer wrong;
	//����BySUb
	public String down() throws Exception {
		try{
			Map<String,Object> session = ActionContext.getContext().getSession();
			Student stu =(Student)session.get("user");
				
				
			return "down_success";
		}catch(Exception e){
			e.printStackTrace();
		}
		return "exception";
	}
	//����һ�γɼ�����
	public String find() throws Exception {
		try{
			Map<String,Object> session = ActionContext.getContext().getSession();
			List<Card> cardList =cardbiz.findCardByGid(grade.getId());
			total=cardList.size();
			right=0;
			wrong=0;
			for(Card c:cardList){
				if("T".equals(c.getResult())){
					right++;
				}
				if("F".equals(c.getResult())){
					wrong++;
				}
				if(c.getResult()==null)
					wrong++;
			}
			session.put("cardList",cardList);
			return "find_success";
		}catch(Exception e){
			e.printStackTrace();
		}
		return "exception";
	}
	//submit
	public String submit() throws Exception {
		try{
			Map<String,Object> session = ActionContext.getContext().getSession();
			Student stu =(Student)session.get("user");
			
			grade =gradebiz.findGradeById(card.getGrade().getId());
			List<Card> cardList =cardbiz.findCardByGid(grade.getId());
			total=cardList.size();
			right=0;
			wrong=0;
			for(Card c:cardList){
				if("T".equals(c.getResult())){
					right++;
					grade.setScore(grade.getScore()+c.getScore());
				}
				if("F".equals(c.getResult())){
					wrong++;
					//��Ӵ����
					
					//�ж��Ƿ��Ѿ���
					if(errorbiz.findErrorByQidAndStu(c.getQuestion().getId(), stu)==null){
						Error e=new Error();
						e.setGrade(grade);
						e.setQuestion(c.getQuestion());
						e.setQuetype(c.getQuetype());
						e.setStudent(stu);
						errorbiz.addError(e);
					}
				}
				if(c.getResult()==null)
					wrong++;
			}
			gradebiz.updateGrade(grade);
			//����
			session.put("cardList",cardList);
			return "submit_success";
		}catch(Exception e){
			e.printStackTrace();
		}
		return "exception";
	}
	//�������
	public String add() throws Exception {
		try{
			Map<String,Object> session = ActionContext.getContext().getSession();
			Student stu =(Student)session.get("user");
			List<Question> qlist =quebiz.findQuestionBySubject(subject.getId());
			if(qlist.size()<1){
				session.put("ts","<script>alert('��Ǹ����ѡ���Ϊ�գ������ڴ���');</script>");
				return "error";
			}
			//�������С��10��ȫ����ʾ/���������10��
			if(qlist.size()<=10){
				addBySub();
				
			}else{
				//�����10��
				Random r=new Random();
				Set<Integer> set=new HashSet<Integer>();
				//����10����
				while(set.size()<10){
					set.add(r.nextInt(qlist.size()));
				}
				//�½�grade;
				Grade g=new Grade();
				g.setTime(new Date());
				g.setStudent(stu);
				g.setSubject(subject);
				g.setScore(0);
				g.setType("���");
				gradebiz.addGrade(g);
				Grade grade =gradebiz.findAllGradeByStu(stu).get(0);
				//����
				Iterator<Integer> num= set.iterator();
				int qno=1;
				while(num.hasNext()){
					Card c=new Card();
					c.setGrade(grade);
					c.setQuestion(qlist.get(num.next()));
					c.setScore(0);
					c.setQno(qno);
					cardbiz.addCard(c);
					qno++;
				}
				//��ʾ��һ��
				card = cardbiz.findCardByGidAndQno(grade.getId(), 1);
				List<Card> cardList=  cardbiz.findCardByGid(grade.getId());
				session.put("cardList",cardList);
			}
			//ʱ��
			time=1800;
			return "add_success";
		}catch(Exception e){
			e.printStackTrace();
		}
		return "exception";
	}
	
	//�״���
	public String addyicuo() throws Exception {
		try {
			Map<String, Object> session = ActionContext.getContext()
					.getSession();
			Student stu = (Student) session.get("user");
			List<Integer> elist = errorbiz.findErrorBySub(subject.getId());
			session.put("elist", elist);
			// �״����
			List<Question> qlist = new ArrayList<Question>();
			for (Integer qid : elist) {
				qlist.add(quebiz.findQuestionById(qid));
			}
			if (qlist.size() < 1) {
				session.put("ts", "<script>alert('��Ǹ����ѡ���Ϊ�գ������ڴ���');</script>");
				return "error";
			}

			// �½�grade;
			Grade g = new Grade();
			g.setTime(new Date());
			g.setStudent(stu);
			g.setSubject(subject);
			g.setScore(0);
			g.setType("�״�");
			gradebiz.addGrade(g);
			Grade grade = gradebiz.findAllGradeByStu(stu).get(0);
			// ����
			int qno = 1;
			for (Question q : qlist) {
				Card c = new Card();
				c.setGrade(grade);
				c.setQuestion(q);
				c.setScore(0);
				c.setQno(qno);
				cardbiz.addCard(c);
				qno++;
			}
			// ��ʾ��һ��
			card = cardbiz.findCardByGidAndQno(grade.getId(), 1);
			List<Card> cardList = cardbiz.findCardByGid(grade.getId());
			session.put("cardList", cardList);
			// ʱ��
			time = 1800;
			return "add_success";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "exception";
	}
	//����ȫ��
		public String addBySub() throws Exception {
			try{
				Map<String,Object> session = ActionContext.getContext().getSession();
				Student stu =(Student)session.get("user");
				List<Question> qlist =quebiz.findQuestionBySubject(subject.getId());
				if(qlist.size()<1){
					session.put("ts","<script>alert('��Ǹ����ѡ���Ϊ�գ������ڴ���');</script>");
					return "error";
				}
				//�½�grade;
				Grade g=new Grade();
				g.setTime(new Date());
				g.setStudent(stu);
				g.setSubject(subject);
				g.setScore(0);
				g.setType("ȫ��");
				gradebiz.addGrade(g);
				Grade grade =gradebiz.findAllGradeByStu(stu).get(0);
				//����
				int qno=1;
				for(Question q:qlist){
					Card c=new Card();
					c.setGrade(grade);
					c.setQuestion(q);
					c.setScore(0);
					c.setQno(qno);
					cardbiz.addCard(c);
					qno++;
				}
				//��ʾ��һ��
				card = cardbiz.findCardByGidAndQno(grade.getId(), 1);
				List<Card> cardList=  cardbiz.findCardByGid(grade.getId());
				session.put("cardList",cardList);
				//ʱ��
				time=36000;
				return "addBySub_success";
			}catch(Exception e){
				e.printStackTrace();
			}
			return "exception";
		}
		//��ϰBY֪ʶ��
		public String addByPoint() throws Exception {
					try{
						Map<String,Object> session = ActionContext.getContext().getSession();
						Student stu =(Student)session.get("user");
						List<Question> qlist =quebiz.findQuestionByPoint(pid);
						if(qlist.size()<1){
							session.put("ts","<script>alert('��Ǹ����ѡ���Ϊ�գ������ڴ���');</script>");
							return "error";
						}
						//�½�grade;
						Grade g=new Grade();
						g.setTime(new Date());
						g.setStudent(stu);
						g.setSubject(subject);
						g.setScore(0);
						g.setType("֪ʶ��");
						gradebiz.addGrade(g);
						Grade grade =gradebiz.findAllGradeByStu(stu).get(0);
						//����
						int qno=1;
						for(Question q:qlist){
							Card c=new Card();
							c.setGrade(grade);
							c.setQuestion(q);
							c.setScore(0);
							c.setQno(qno);
							cardbiz.addCard(c);
							qno++;
						}
						//��ʾ��һ��
						card = cardbiz.findCardByGidAndQno(grade.getId(), 1);
						List<Card> cardList=  cardbiz.findCardByGid(grade.getId());
						session.put("cardList",cardList);
						//ʱ��
						time=36000;
						return "addByPoint_success";
					}catch(Exception e){
						e.printStackTrace();
					}
					return "exception";
		}
		//������bySubject
		public String addByErrorAndSub() throws Exception {
			try{
				Map<String,Object> session = ActionContext.getContext().getSession();
				Student stu =(Student)session.get("user");
				List<Error> elist =errorbiz.findErrorBySubidAndUser(subject.getId(), stu);
				if(elist.size()<1){
					session.put("ts","<script>alert('���ÿ�Ŀ�����ڴ��⣡�Ͻ�ȥ����Ŷ');</script>");
					return "error";
				}
				//�½�grade;
				Grade g=new Grade();
				g.setTime(new Date());
				g.setStudent(stu);
				g.setSubject(subject);
				g.setScore(0);
				g.setType("����");
				gradebiz.addGrade(g);
				Grade grade =gradebiz.findAllGradeByStu(stu).get(0);
				//����
				int qno=1;
				for(Error e:elist){
					Card c=new Card();
					c.setGrade(grade);
					c.setQuestion(e.getQuestion());
					c.setScore(0);
					c.setQno(qno);
					cardbiz.addCard(c);
					qno++;
				}
				//��ʾ��һ��
				card = cardbiz.findCardByGidAndQno(grade.getId(), 1);
				List<Card> cardList=  cardbiz.findCardByGid(grade.getId());
				session.put("cardList",cardList);
				//ʱ��
				time=36000;
				return "addByErrorAndSub_success";
			}catch(Exception e){
				e.printStackTrace();
			}
			return "exception";
		}
		//�������undoδ������BYsubject
				public String addByUndoAndSub() throws Exception {
					try{
						Map<String,Object> session = ActionContext.getContext().getSession();
						Student stu =(Student)session.get("user");
						//������е�BySub
						List<Question> qlist =quebiz.findQuestionBySubject(subject.getId());
						//������������е�BySub
						List<Done> donelist=donebiz.findDoneByStuAndSub(stu, subject);
						List<Question> dqlist=new ArrayList<Question>();
						for(Done d:donelist){
							dqlist.add(d.getQuestion());
						}
						//ȥ��
						qlist.removeAll(dqlist);
						if(qlist.size()<1){
							session.put("ts","<script>alert('��ϲ�����ÿ�Ŀ�����ⶼ������');</script>");
							return "error";
						}
						//�½�grade;
						Grade g=new Grade();
						g.setTime(new Date());
						g.setStudent(stu);
						g.setSubject(subject);
						g.setScore(0);
						g.setType("δ��");
						gradebiz.addGrade(g);
						Grade grade =gradebiz.findAllGradeByStu(stu).get(0);
						//����
						int qno=1;
						for(Question q:qlist){
							Card c=new Card();
							c.setGrade(grade);
							c.setQuestion(q);
							c.setScore(0);
							c.setQno(qno);
							cardbiz.addCard(c);
							qno++;
						}
						//��ʾ��һ��
						card = cardbiz.findCardByGidAndQno(grade.getId(), 1);
						List<Card> cardList=  cardbiz.findCardByGid(grade.getId());
						session.put("cardList",cardList);
						//ʱ��
						time=36000;
						return "addByUndoAndSub_success";
					}catch(Exception e){
						e.printStackTrace();
					}
					return "exception";
				}
	public String update() throws Exception {
		try{
			Map<String,Object> session = ActionContext.getContext().getSession();
			Student stu =(Student)session.get("user");
			Card c=cardbiz.findCardById(card.getId());
			c.setUanswer(card.getUanswer());
			//�жϳɼ�
			if(c.getQuestion().getResult().equals(card.getUanswer())){
				c.setResult("T");//�û����Ƿ���ȷ
				c.setScore(10);
				//����û���ԣ��Ѵ������Ĵ��ɾ��
				Error e=errorbiz.findErrorByQidAndStu(c.getQuestion().getId(), stu);
				if(e!=null){
					errorbiz.deleteErrorById(e);
				}
			}else{
				c.setResult("F");
			}
			//�жϼ����Ƿ�����
			Done d =donebiz.findDoneByQid(c.getQuestion().getId());
			if(d==null){
				Done done=new Done();
				done.setQuestion(c.getQuestion());
				done.setStudent(stu);
				donebiz.addDone(done);
			}
			
			//����+��
			cardbiz.updateCard(c);
			//�ύ����
			if(nextqno==-1){
				submit();
				return "submit_success";
			}
			//����
			card = cardbiz.findCardByGidAndQno(card.getGrade().getId(), nextqno);
			List<Card> cardList=  cardbiz.findCardByGid(card.getGrade().getId());
			session.put("cardList",cardList);
			System.out.println("�����ˣ�������������������������������");
			//ʱ��
			
			return "update_success";
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
	public Integer getTime() {
		return time;
	}
	public void setTime(Integer time) {
		this.time = time;
	}
	public ErrorBiz getErrorbiz() {
		return errorbiz;
	}
	public void setErrorbiz(ErrorBiz errorbiz) {
		this.errorbiz = errorbiz;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Integer getRight() {
		return right;
	}
	public void setRight(Integer right) {
		this.right = right;
	}
	public Integer getWrong() {
		return wrong;
	}
	public void setWrong(Integer wrong) {
		this.wrong = wrong;
	}
	public Grade getGrade() {
		return grade;
	}
	public void setGrade(Grade grade) {
		this.grade = grade;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public DoneBiz getDonebiz() {
		return donebiz;
	}
	public void setDonebiz(DoneBiz donebiz) {
		this.donebiz = donebiz;
	}
	
	
}