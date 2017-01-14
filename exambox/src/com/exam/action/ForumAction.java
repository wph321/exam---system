package com.exam.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.exam.biz.ForumBiz;
import com.exam.biz.ReplyBiz;
import com.exam.entity.Forum;
import com.exam.entity.Reply;
import com.exam.entity.Student;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ForumAction extends ActionSupport {
	private Forum forum;
	private Reply reply;
	private ForumBiz forumbiz;
	private ReplyBiz replybiz;
	private Integer nowpage;
	private Student student;
	// 上传图片
	private File image;
	private String imageContentType;
	private String imageFileName;
	// 判断用户类型
	private String type;

	public String findByPage() throws Exception {
		try {
			Map<String, Object> session = ActionContext.getContext()
					.getSession();

			int allPage = forumbiz.findPageByTerm(forum);
			if (nowpage == null || nowpage < 1)
				nowpage = 1;
			if (nowpage > allPage)
				nowpage = allPage;
			List<Forum> forumListByPage = forumbiz.findForumByPageTerm(forum,
					nowpage);
			session.put("allPage", allPage);
			session.put("forumListByPage", forumListByPage);
			if ("admin".equals(type))
				return "adminfindByPage_success";
			return "findByPage_success";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "exception";
	}

	public String add() throws Exception {
		try {
			if (image != null && !"".equals(image)) {
				// 添加图片
				String path = ServletActionContext.getRequest().getRealPath(
						"/images/" + imageFileName);

				FileInputStream fis = new FileInputStream(image);
				FileOutputStream fos = new FileOutputStream(path);
				byte[] temp = new byte[1024];
				int size = -1;
				do {
					size = fis.read(temp);
					if (size != -1)
						fos.write(temp, 0, size);
				} while (size != -1);
				fos.flush();
				fos.close();
				fis.close();
				forum.setImage(imageFileName);
			}
			// 当前时间
			Date now = new Date();
			forum.setFdate(now);
			forumbiz.addForum(forum);
			if ("admin".equals(type))
				return "adminadd_success";
			return "add_success";

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "exception";
	}

	public String delete() throws Exception {
		try {
			Map<String, Object> session = ActionContext.getContext()
					.getSession();

			forumbiz.deleteForumByFid(forum);
			if ("admin".equals(type))
				return "admindelete_success";
			student = (Student) session.get("user");
			return "delete_success";

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "exception";
	}

	public String findall() throws Exception {
		try {
			List<Forum> flist = forumbiz.findAllForum();
			Map<String, Object> session = ActionContext.getContext()
					.getSession();
			session.put("flist", flist);
			return "findall_success";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "exception";
	}

	public String findByUserAndPage() throws Exception {
		try {
			Map<String, Object> session = ActionContext.getContext()
					.getSession();

			int allPage = forumbiz.findPageByUser(student);
			if (nowpage == null || nowpage < 1)
				nowpage = 1;
			if (nowpage > allPage)
				nowpage = allPage;
			List<Forum> forumList = forumbiz.findForumByUserAndPage(student,
					nowpage);
			session.put("allPage", allPage);
			session.put("forumList", forumList);

			return "findByUserAndPage_success";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "exception"; 
	}

	public String findone() throws Exception {
		try {
			Forum findoneforum = forumbiz.findForumByFid(forum.getFid());
			Map<String, Object> session = ActionContext.getContext()
					.getSession();
			session.put("findoneforum", findoneforum);
			return "findone_success";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "exception";
	}

	public Forum getForum() {
		return forum;
	}

	public void setForum(Forum forum) {
		this.forum = forum;
	}

	public Reply getReply() {
		return reply;
	}

	public void setReply(Reply reply) {
		this.reply = reply;
	}

	public ForumBiz getForumbiz() {
		return forumbiz;
	}

	public void setForumbiz(ForumBiz forumbiz) {
		this.forumbiz = forumbiz;
	}

	public ReplyBiz getReplybiz() {
		return replybiz;
	}

	public void setReplybiz(ReplyBiz replybiz) {
		this.replybiz = replybiz;
	}

	public Integer getNowpage() {
		return nowpage;
	}

	public void setNowpage(Integer nowpage) {
		this.nowpage = nowpage;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public File getImage() {
		return image;
	}

	public void setImage(File image) {
		this.image = image;
	}

	public String getImageContentType() {
		return imageContentType;
	}

	public void setImageContentType(String imageContentType) {
		this.imageContentType = imageContentType;
	}

	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

}
