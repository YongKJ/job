package com.work.action;

import java.util.ArrayList;
import java.util.Map;

import javax.annotation.Resource;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.work.dao.SeekerDao;
import com.work.model.Seeker;

@Controller @Scope("prototype")
public class SeekerAction extends ActionSupport {
	@Resource SeekerDao seekerDao;
	
	private Seeker seeker;
	private String errMessage;
	private String password;
	private Map<String,Object> session = ActionContext.getContext().getSession();

	public Map<String, Object> getSession() {
		return session;
	}
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	
	public Seeker getSeeker(){
		return seeker;
	}
	
	public void setSeeker(Seeker seeker){
		this.seeker=seeker;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getErrMessage(){
		return errMessage;
	}
	
	public void setErrMessage(String errMessage){
		this.errMessage = errMessage;
	}
	//ע��
	public String reg() throws Exception{
		ArrayList<Seeker> listSeeker = seekerDao.QuerySeekerInfo(seeker.getNickname());
		if(listSeeker.size()==0) { 
			seekerDao.AddSeeker(seeker);
			session.put("seeker", seeker);
			return "wanshan";
		}else{
			this.errMessage = " ���ǳ��ѱ�ʹ��! ";
			System.out.print(this.errMessage);
			return "back_reg";
		}
	}
	
	//��¼
	public String login() {
		ArrayList<Seeker> listSeeker = seekerDao.QuerySeekerInfo(seeker.getNickname());
		if(listSeeker.size()==0) { 
			this.errMessage = " �˺Ų����� ";
			System.out.print(this.errMessage);
			return "nouser";
		} 
		else{
		    Seeker db_seeker = listSeeker.get(0);
			if(!db_seeker.getSeekerpassword().equals(seeker.getSeekerpassword())) {
				this.errMessage = " ���벻��ȷ! ";
				System.out.print(this.errMessage);
				return "password_error";
		    }else{
				session.put("seeker", db_seeker);
				return "show_job";
		    }
		}

	}
	//���¸�����Ϣ
	public String editSeeker() throws Exception{
		  seekerDao.UpdateSeeker(seeker);
		  session.put("seeker", seeker);
		  return "seeker_update";
		 }
	
	//�޸�����
	 public String editmima() throws Exception{
	      Seeker s= seekerDao.GetSeekerById(seeker.getSeekerid());
	   if(!s.getSeekerpassword().equals(seeker.getSeekerpassword())) {
	    return "input_error";
	      }else{
	       seeker.setSeekerpassword(password);
	    seekerDao.UpdateSeeker(seeker);
	    return "seeker_update";
	      }
	 }
	//���Ƹ�����Ϣ
	 public String editSeeker1() throws Exception{
	  seekerDao.UpdateSeeker(seeker);
	  session.put("seeker", seeker);
	  return "show_job";
	 }
}
