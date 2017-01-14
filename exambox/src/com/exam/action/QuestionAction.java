package com.exam.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import com.exam.biz.PointBiz;
import com.exam.biz.QuestionBiz;
import com.exam.biz.SubjectBiz;
import com.exam.entity.Point;
import com.exam.entity.Question;
import com.exam.entity.Quetype;
import com.exam.entity.Subject;
import com.exam.util.HibernateSessionFactory;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Question entity. @author MyEclipse Persistence Tools
 */

public class QuestionAction extends ActionSupport {

	private Question question;
	private QuestionBiz questionBiz;
	private String[] mutiquestion;
	private Map<String, Object> session;
	private String resultStr;
	private String dxquestion;
	private String pdquestion;
	private SubjectBiz subjectBiz;
	private PointBiz pointBiz;
	private String result;
	private Integer pid;
	private Point point;
	private List<Question> questionList;
	private Integer qid;
	private String mohu;
	private Integer nowpage;
	private Integer page;
	private Subject subject;
	private String points;
	private Integer allPage;
	private String pointrel;
	private Integer qtypeid;
	private Integer subid;
	private Integer pointid;
	//�����ϴ�
	private File xls;
	private String xlsContentType;
	private String xlsFileName;
	// Question��תnote
	public String note() {
		try {
			session = ActionContext.getContext().getSession();
			question = questionBiz.findQuestionById(qid);
			session.put("question", question);
			return "note_success";
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "question_false";
	}

	// ͨ��ҳ������Question
	public String searchfindByPage() throws Exception {
		try {
			session = ActionContext.getContext().getSession();
			allPage = questionBiz.mufindPageBymu(mohu);
			session.put("allPage", allPage);
			session.put("mohu", mohu);
			if (nowpage == null || nowpage < 1)
				nowpage = 1;
			if (nowpage > allPage)
				nowpage = allPage;
			System.out.println("nowpage..........." + nowpage);
			List<Question> questionList = questionBiz.mufindQuestionByPage(
					mohu, nowpage);
			session.put("questionList", questionList);
			System.out.println("allPage....." + allPage);
			return "search_success";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "question_false";
	}

	// ͨ�������ѯQuestion
	public String find() {
		try {
			System.out.println("find.....");
			session = ActionContext.getContext().getSession();
			allPage = questionBiz.findPage(pid,subid);
			if(nowpage==null||nowpage<1)
				nowpage=1;
			if(nowpage>allPage)
				nowpage=allPage;
			List<Question> questionList = questionBiz
					.findQuestion(pid, nowpage,subid);
			session.put("questionList", questionList);
			System.out.println("questionList...." + questionList);
			return "find_success";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "question_false";
	}

	// ģ����ѯ����
	public String search() {
		try {
			session = ActionContext.getContext().getSession();
			String mohu = (String) session.get("mohu");
			Integer mupage = questionBiz.mufindPageBymu(mohu);
			List<Question> questionList = questionBiz.mufindQuestionByPage(
					mohu, page);
			return "search_success";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "question_false";
	}

	// ������ӵĹ���
	public String add() {
		try {
			session = ActionContext.getContext().getSession();
			if (question.getQuetype().getId() == 1) {
				question.setResult(dxquestion);
				Set pset = new HashSet<Point>();
				System.out.println("pointrel..........." + pointrel);
				String[] pointn = pointrel.split("[+]");
				for (int i = 1; i < pointn.length; i++) {
					Point p = pointBiz.findonePoint(Integer.valueOf(pointn[i]));
					pset.add(p);
				}
				question.setPoints(pset);
				System.out.println();
				questionBiz.addQuestion(question);
			} else if (question.getQuetype().getId() == 2) {
				resultStr = "";
				for (int i = 0; i < mutiquestion.length; i++) {
					if (mutiquestion[i] != null) {
						String muti = this.getMutiquestion()[i];
						resultStr = resultStr + muti;
					}
				}
				question.setResult(resultStr);
				Set pset = new HashSet<Point>();
				String[] pointn = pointrel.split("+");
				for (int i = 1; i < pointn.length; i++) {
					Point p = pointBiz.findonePoint(Integer.valueOf(pointn[i]));
					pset.add(p);
				}
				question.setPoints(pset);
				questionBiz.addQuestion(question);
			} else if (question.getQuetype().getId() == 3) {
				question.setResult(pdquestion);
				Set pset = new HashSet<Point>();
				String[] pointn = pointrel.split("+");
				for (int i = 1; i < pointn.length; i++) {
					Point p = pointBiz.findonePoint(Integer.valueOf(pointn[i]));
					pset.add(p);
				}
				question.setPoints(pset);
				questionBiz.addQuestion(question);
			}

			return "question_add_success";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "question_false";
	}

	// ��ת�޸�����
	public String tiaoupdate() {
		try {
			session = ActionContext.getContext().getSession();
			question = questionBiz.findQuestionById(qid);
			session.put("question", question);
			return "update_tiao_success";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "question_false";
	}

	// �޸�����
	public String update() {
		try {
			session = ActionContext.getContext().getSession();
			System.out.println("updatequestion...." + question.getDifficulty());
			if (question.getQuetype().getId() == 1) {
				question.setResult(dxquestion);
				System.out.println();
				questionBiz.updateQuestion(question);
			} else if (question.getQuetype().getId() == 2) {
				resultStr = "";
				for (int i = 0; i < mutiquestion.length; i++) {
					if (mutiquestion[i] != null) {
						String muti = this.getMutiquestion()[i];
						resultStr = resultStr + muti;
					}
				}
				question.setResult(resultStr);
				questionBiz.updateQuestion(question);
			} else if (question.getQuetype().getId() == 3) {
				question.setResult(pdquestion);
				questionBiz.updateQuestion(question);
			}
			return "update_success";
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "question_false";
	}

	// ȷ���޸�
	public String updateSuccess() {
		try {
			session = ActionContext.getContext().getSession();
			System.out.println("updatesuccess....." + question.getQtitle());
			questionBiz.addQuestion(question);
			return "updateSuccess_success";
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "question_false";
	}

	// ɾ������(��ʱ��ʵ��)
	public String delete() {
		try {
			session = ActionContext.getContext().getSession();
			return "delete_success";
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "question_false";
	}

	// ͨ�����ַ����ѯQuestion(��ʱû��)
	public String findBy() {
		try {
			session = ActionContext.getContext().getSession();
			System.out.println("findBypid......." + pid);
			List<Question> questionList = questionBiz.findQuestionByPoint(pid);
			System.out.println("questionList" + questionList);
			// ���ù��˵�����
			JsonConfig jc = new JsonConfig();
			// "id", "subject" ,"qtitle" , "difficulty","knowpint"
			jc.setExcludes(new String[] { "points", "notes", "cards", "errors",
					"collects", "quetype", "questions", "grades", "points",
					"examdates" });
			result = JSONArray.fromObject(questionList, jc).toString();
			System.out.println("���صĽ����Question.........��" + result);
			return SUCCESS;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "question_false";
	}
	//������������
	public String batch() {
		try {
			if(xls!=null&&!"".equals(xls)){
				//���ͼƬ
				Session session = HibernateSessionFactory.getSession();
				//String path =ServletActionContext.getRequest().getRealPath("/XLS/"+xlsFileName);
				Transaction ts = session.beginTransaction();
				   // ����excel�ļ�
				FileInputStream fs = new FileInputStream(xls);

				// �õ� workbook
				Workbook workBook = Workbook.getWorkbook(fs);
				// ȡ��sheet��������workbook���ж��sheet �������� wb.getSheets()�������õ����еġ�
				// getSheets() �������� Sheet[] ���� Ȼ���������������������Ƕ��ѭ�����¡�
				Sheet sheet = workBook.getSheet(0);// ����ֻȡ�õ�һ��sheet��ֵ��Ĭ�ϴ�0��ʼ
				System.out.println(sheet.getColumns());// �鿴sheet����
				System.out.println(sheet.getRows());// �鿴sheet����
				Cell cell = null;// ���ǵ�����Ԫ��
				// ��ʼѭ����ȡ�� cell �������
				for (int j = 1; j < sheet.getRows(); j++) {
					Question q=new Question();
					q.setSubject(subject);
					Quetype qt=new Quetype();
					qt.setId(Integer.valueOf(sheet.getCell(0, j).getContents()));
					q.setQuetype(qt);
					q.setQtitle(sheet.getCell(1, j).getContents());
					q.setOptiona(sheet.getCell(2, j).getContents());
					q.setOptionb(sheet.getCell(3, j).getContents());
					q.setOptionc(sheet.getCell(4, j).getContents());
					q.setOptiond(sheet.getCell(5, j).getContents());
					q.setResult(sheet.getCell(6, j).getContents());
					q.setDifficulty(Integer.valueOf(sheet.getCell(7, j).getContents()));;
					q.setImage(sheet.getCell(8, j).getContents());
					q.setAnalysis(sheet.getCell(9, j).getContents());
					
					session.save(q);
				}
				ts.commit();
				HibernateSessionFactory.closeSession();
				workBook.close();// �ǵùر�
				fs.close();
				 
			}
			return "question_add_success";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "question_false";
	}
	public String getResultStr() {
		return resultStr;
	}

	public void setResultStr(String resultStr) {
		this.resultStr = resultStr;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public String[] getMutiquestion() {
		return mutiquestion;
	}

	public void setMutiquestion(String[] mutiquestion) {
		this.mutiquestion = mutiquestion;
	}

	public QuestionBiz getQuestionBiz() {
		return questionBiz;
	}

	public void setQuestionBiz(QuestionBiz questionBiz) {
		this.questionBiz = questionBiz;
	}

	public String getDxquestion() {
		return dxquestion;
	}

	public void setDxquestion(String dxquestion) {
		this.dxquestion = dxquestion;
	}

	public String getPdquestion() {
		return pdquestion;
	}

	public void setPdquestion(String pdquestion) {
		this.pdquestion = pdquestion;
	}

	public SubjectBiz getSubjectBiz() {
		return subjectBiz;
	}

	public void setSubjectBiz(SubjectBiz subjectBiz) {
		this.subjectBiz = subjectBiz;
	}

	public PointBiz getPointBiz() {
		return pointBiz;
	}

	public void setPointBiz(PointBiz pointBiz) {
		this.pointBiz = pointBiz;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
	}

	public List<Question> getQuestionList() {
		return questionList;
	}

	public void setQuestionList(List<Question> questionList) {
		this.questionList = questionList;
	}

	public Integer getQid() {
		return qid;
	}

	public void setQid(Integer qid) {
		this.qid = qid;
	}

	public String getmohu() {
		return mohu;
	}

	public void setmohu(String mohu) {
		this.mohu = mohu;
	}

	public String getMohu() {
		return mohu;
	}

	public void setMohu(String mohu) {
		this.mohu = mohu;
	}

	public Integer getNowpage() {
		return nowpage;
	}

	public void setNowpage(Integer nowpage) {
		this.nowpage = nowpage;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getPointid() {
		return pointid;
	}

	public void setPointid(Integer pointid) {
		this.pointid = pointid;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public String getPoints() {
		return points;
	}

	public void setPoints(String points) {
		this.points = points;
	}

	public String getPointrel() {
		return pointrel;
	}

	public void setPointrel(String pointrel) {
		this.pointrel = pointrel;
	}

	public Integer getAllPage() {
		return allPage;
	}

	public void setAllPage(Integer allPage) {
		this.allPage = allPage;
	}

	public Integer getQtypeid() {
		return qtypeid;
	}

	public void setQtypeid(Integer qtypeid) {
		this.qtypeid = qtypeid;
	}

	public Integer getSubid() {
		return subid;
	}

	public void setSubid(Integer subid) {
		this.subid = subid;
	}

	public Integer getpointid() {
		return pointid;
	}

	public void setpointid(Integer pointid) {
		this.pointid = pointid;
	}

	public File getXls() {
		return xls;
	}

	public void setXls(File xls) {
		this.xls = xls;
	}

	public String getXlsContentType() {
		return xlsContentType;
	}

	public void setXlsContentType(String xlsContentType) {
		this.xlsContentType = xlsContentType;
	}

	public String getXlsFileName() {
		return xlsFileName;
	}

	public void setXlsFileName(String xlsFileName) {
		this.xlsFileName = xlsFileName;
	}

}