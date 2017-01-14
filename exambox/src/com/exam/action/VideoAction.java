package com.exam.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.exam.biz.SubjectBiz;
import com.exam.biz.VedioBiz;
import com.exam.entity.Admin;
import com.exam.entity.Subject;
import com.exam.entity.Vedio;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class VideoAction extends ActionSupport {
	private VedioBiz vedioBiz;
	private SubjectBiz subjectBiz;
	private Integer sid;
	private Integer videoNowPage;
	private String title;
	private String content;
	private File pic;
	private String type;
	private Integer vid;
	//图片文件名称
	private String picFileName;
	//图片文件类型
	private String picContentType;
	/*
	 * 注：文件名与文件类型前缀必须相同
	 */
	private Integer cid;
	//科目类型
	private File shiping;
	private String shipingFileName;
	//视频名称
	private String shipingContentType;
	//视频类型
	
	//图片视频上传
	public String addVideo(){
		this.uploadImg();
		this.uploadVideo();
		System.out.println("pic_name:"+picFileName);
		System.out.println("pic_type:"+picContentType);
		System.out.println("vi_name:"+shipingFileName);
		System.out.println("vi_type:"+shipingContentType);	
		//保存到数据库
		//获取sesion
		ActionContext ac = ActionContext.getContext();
		Map session =  ac.getSession();
		Vedio vd = new Vedio();
		//保存关联表subject的id
		Subject subject = new Subject();
		subject.setId(cid);
		vd.setSubject(subject);
		//获取并保存session中的用户
		vd.setAdmin((Admin) session.get("user"));
		vd.setVname(title);
		vd.setTime(new Date());
		vd.setVfile(shipingFileName);
		vd.setImage(picFileName);
		vd.setVex(content);
		try {
			vedioBiz.addVideo(vd);
			return SUCCESS;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	//图片上传方法
	public String uploadImg(){
		//获取上传文件的保存路径
		String filepath =ServletActionContext.getServletContext()
						.getRealPath("/video/images/"+picFileName);
		//判断路径是否存在，不存在则创建目录
//		if(!uploadFile.exists()){
//			uploadFile.mkdir();//创建该目录
//		}
			
		try {
			//声明文件输入流，为输入流指定文件路径
			FileInputStream input = new FileInputStream(pic);
			//声明输出流，获取文件的文件地址及名称
			FileOutputStream out = new 
					FileOutputStream(filepath);
			byte[] b = new byte[1024];//每次写入的大小
			int i = 0;
			while((i=input.read(b))>0){
				out.write(b, 0, i);
			}
			//关闭输入输出流
			input.close();
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}

	//视频上传方法
	public String uploadVideo(){
			
		//获取上传文件的保存路径
		String filepath =ServletActionContext.getServletContext()
						.getRealPath("/video/"+shipingFileName);
		//判断路径是否存在，不存在则创建目录
//		if(!uploadFile.exists()){
//			uploadFile.mkdir();//创建该目录
//		}
			
		try {
			//声明文件输入流，为输入流指定文件路径
			FileInputStream input = new FileInputStream(shiping);
			//声明输出流，获取文件的文件地址及名称
			FileOutputStream out = new
					FileOutputStream(filepath);
			byte[] b = new byte[1024];//每次写入的大小
			int i = 0;
			while((i=input.read(b))>0){
				out.write(b, 0, i);
			}
			//关闭输入输出流
			input.close();
			out.close();
			return SUCCESS;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "exception";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "exception";
		}
	}
	
	public String showAll(){
		ActionContext ac = ActionContext.getContext();
		Map session =  ac.getSession();
		try {
			
			int videoAllPage = vedioBiz.findAllPage();
			
			if(videoNowPage==null ||videoNowPage==0)
				videoNowPage = 1;
			List<Vedio> vedioList = vedioBiz.findVedioByPage(videoNowPage);
			session.put("VideoAllPage",videoAllPage);
			session.put("videoList", vedioList);
			
			return "showVideo_success";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "exception";
		}
	}
	

	
	//前台vedio列表
	public String showAllVideo(){
		ActionContext ac = ActionContext.getContext();
		Map session =  ac.getSession();
		try {
			
			int videoAllPage = vedioBiz.findAllPage();
			List<Subject> subject = subjectBiz.findSubject();
			if(videoNowPage==null ||videoNowPage==0)
				videoNowPage = 1;
			List<Vedio> vedioList = vedioBiz.findVedioByPage(videoNowPage);
			session.put("VideoAllPage",videoAllPage);
			session.put("videoList", vedioList);
			session.put("subjectList", subject);
			return "showVideo_success";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "exception";
		}
	}
	
	//video播放页面查找方法
	public String findVideoById(){
		
		ActionContext ac = ActionContext.getContext();
		Map session =  ac.getSession();
		
		try {
			Vedio video = vedioBiz.findVideoByVideoId(vid);
			List<Vedio> vedioList = vedioBiz.findVedioByPage(1);
			session.put("shiping", video);
			session.put("videolist", vedioList);
			return SUCCESS;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "exception";
		}
	}
	
	
	//根据科目查找video
	public String findBySub(){
		ActionContext ac = ActionContext.getContext();
		Map session = ac.getSession();
		
		try {
			int allPage = vedioBiz.findAllpageByType(sid);
			if(videoNowPage==null ||videoNowPage < 1)
				videoNowPage = 1;
			
			if(videoNowPage>allPage)
				videoNowPage=allPage;
			List<Vedio> vedioList = vedioBiz.findVedioByPageAndType(videoNowPage, sid);
			
			session.put("allPageBySubject", allPage);
			session.put("videoListBySubject", vedioList);
			
			//判断是否管理员查询
			if("admin".equals(type)){
				return "adminfindByPage_success";
			}else{
			return "findBySub_success";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "exception";
		}
	}
	
	//查询单个视频信息
	public String findone(){
		
		ActionContext ac = ActionContext.getContext();
		Map session = ac.getSession();
		
		try {
			Vedio vedio = vedioBiz.findVideoByVideoId(vid);
			session.put("shiping", vedio);
			return "find_success";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "exception";
		}
	}

	public String update(){
		//保存到数据库
		
		//获取sesion
		ActionContext ac = ActionContext.getContext();
		Map session =  ac.getSession();
		Vedio vd = new Vedio();
		Vedio vd1 = (Vedio) session.get("shiping");
		vd.setId(vd1.getId());
		//保存关联表subject的id
		Subject subject = new Subject();
		subject.setId(cid);
		vd.setSubject(subject);
		//获取并保存session中的用户
		vd.setAdmin((Admin) session.get("user"));
		vd.setVname(title);
		vd.setTime(new Date());
		
		if(picFileName==null||"".equals(picFileName)){
			Vedio vedio = (Vedio) session.get("shiping");
			vd.setImage(vedio.getImage());
			}else{
				this.uploadVideo();
				//封装要修改的视频类
//			System.out.println("pic_name:"+picFileName);
//			System.out.println("pic_type:"+picContentType);
//			System.out.println("vi_name:"+shipingFileName);
//			System.out.println("vi_type:"+shipingContentType);	
				vd.setImage(picFileName);
			}
		if(shipingFileName==null||"".equals(shipingFileName)){
			Vedio vedio = (Vedio) session.get("shiping");
			vd.setVfile(vedio.getVfile());
		}else{
			this.uploadImg();
			vd.setVfile(shipingFileName);
		}
		vd.setVex(content);
		
		try {
			vedioBiz.updatevedio(vd);
			return "update_success";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "exception";
		}
		
	}
	
	public String delete(){
		
		ActionContext ac = ActionContext.getContext();
		Map session =  ac.getSession();
		
		try {
			vedioBiz.deleteVedioById(vid);
			return "delete_success";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "exception";
		}
	}
	
	//get AND SET method
	
	
	public String getTitle() {
		return title;
	}

	public Integer getVideoNowPage() {
		return videoNowPage;
	}

	public void setVideoNowPage(Integer videoNowPage) {
		this.videoNowPage = videoNowPage;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public File getPic() {
		return pic;
	}

	public void setPic(File pic) {
		this.pic = pic;
	}

	public String getPicFileName() {
		return picFileName;
	}

	public void setPicFileName(String picFileName) {
		this.picFileName = picFileName;
	}

	public String getPicContentType() {
		return picContentType;
	}

	public void setPicContentType(String picContentType) {
		this.picContentType = picContentType;
	}

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public File getShiping() {
		return shiping;
	}

	public void setShiping(File shiping) {
		this.shiping = shiping;
	}

	public String getShipingFileName() {
		return shipingFileName;
	}

	public void setShipingFileName(String shipingFileName) {
		this.shipingFileName = shipingFileName;
	}

	public String getShipingContentType() {
		return shipingContentType;
	}

	public void setShipingContentType(String shipingContentType) {
		this.shipingContentType = shipingContentType;
	}

	public VedioBiz getVedioBiz() {
		return vedioBiz;
	}

	public void setVedioBiz(VedioBiz vedioBiz) {
		this.vedioBiz = vedioBiz;
	}

	public Integer getVid() {
		return vid;
	}

	public void setVid(Integer vid) {
		this.vid = vid;
	}

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public SubjectBiz getSubjectBiz() {
		return subjectBiz;
	}

	public void setSubjectBiz(SubjectBiz subjectBiz) {
		this.subjectBiz = subjectBiz;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}