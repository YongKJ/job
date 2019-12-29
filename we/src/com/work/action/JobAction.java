package com.work.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.work.dao.ApplicationDao;
import com.work.dao.JobDao;
import com.work.dao.SeekerDao;
import com.work.model.Application;
import com.work.model.Job;
import com.work.model.Seeker;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Controller @Scope("prototype")
public class JobAction extends ActionSupport{
	@Resource JobDao jobDao;
	@Resource SeekerDao seekerDao;
	@Resource ApplicationDao applicationDao;
	
	private Job job;
	private Seeker seeker;
	
	private List<Job> jobList;
	private String keyAddress;
	private String keyCompany;
	private String keySort;
	private String keyJob;
	private Integer keySalarys;
	private Integer keySalaryb;
	private List<Application> applicationList;
	private Map<String,Object> session = ActionContext.getContext().getSession();

	public Map<String, Object> getSession() {
		return session;
	}
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	
	public Job getJob(){
		return job;
	}
	
	public void setJob(Job job){
		this.job = job;
	}
	
	public List<Job> getJobList(){
		return jobList;
	}
	
	public void setJobList(List<Job> jobList){
		this.jobList = jobList;
	}
	
	public Seeker getSeeker(){
		return seeker;
	}
	
	public void setSeeker(Seeker seeker){
		this.seeker=seeker;
	}
	
	public String getKeyCompany(){
		return keyCompany;
	}
	
	public void setKeyCompany(String keyCompany){
		this.keyCompany = keyCompany;
	}
	
	public String getKeyAddress(){
		return keyAddress;
	}
	
	public void setKeyAddress(String keyAddress){
		this.keyAddress = keyAddress;
	}
	
	public String getKeySort(){
		return keySort;
	}
	
	public void setKeySort(String keySort){
		this.keySort = keySort;
	}
	
	public String getKeyJob(){
		return keyJob;
	}
	
	public void setKeyJob(String keyJob){
		this.keyJob = keyJob;
	}
	
	public Integer getKeySalarys(){
		return keySalarys;
	}
	
	public void setKeySalarys(Integer keySalarys){
		this.keySalarys = keySalarys;
	}
	
	public Integer getKeySalaryb(){
		return keySalaryb;
	}
	
	public void setKeySalaryb(Integer keySalaryb){
		this.keySalaryb = keySalaryb;
	}
	
	public List<Application> getApplicationList() {
		return applicationList;
	}
	public void setApplicationList(List<Application> applicationList) {
		this.applicationList = applicationList;
	}
	//���job
	public String addJob() throws Exception{
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//�������ڸ�ʽ
        job.setPublishtime(df.format(new Date()));// new Date()Ϊ��ȡ��ǰϵͳʱ��
        seeker=(Seeker) session.get("seeker");
        job.setSeeker(seeker);
        jobDao.AddJob(job);
        return "job_message";
	}
	
	//��ʾ����job
	public String showJob(){
		jobList = jobDao.QueryAllJob();
		return "show_job_view";
	}
	
	//��ʾjob����ϸ��Ϣ
	public String showJobDetail() throws Exception{
		job = jobDao.GetJobById(job.getJobid());
		return "detail_job_view";
	}
	
	//����������ѯ��˾������ת��job_main.jsp��
	public String queryJobs()throws Exception{
		jobList = jobDao.QueryJobInfo(keyCompany,keyAddress,keySort,keyJob,keySalarys,keySalaryb);
		return "show_job_view";
	}
	
	//��ʾ��������������
	  public String showDetail(){
	   job = jobDao.GetJobById(job.getJobid());
	   return "detail_myjob";
	  }
	  
	//ɾ�������Ĺ���
	  public String deleteJob()throws Exception{
	   jobDao.DeleteJob(job.getJobid());
	   return "delete_myjob";
	  }
	  
	//��ʾ�༭
	  public String showEdit() throws Exception{
	   job=jobDao.GetJobById(job.getJobid());
	   return "edit_view";
	  }
	  
	  //�༭ҳ��
	  public String editJob()throws Exception{
	   seeker=(Seeker) session.get("seeker");
	   SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
	   job.setPublishtime(df.format(new Date()));
	   System.out.println(seeker.getSeekerid());
	   job.setSeeker(seeker);
	   jobDao.UpdateJob(job);
	   return "edit_myjob";
	  }
	  
	//�鿴����
	  public String showapplist()throws Exception{
	    job=jobDao.GetJobById(job.getJobid());
	    System.out.println(job.getJobid());
	    applicationList=applicationDao.QueryApplicationByJobId(job.getJobid());
	    System.out.println(applicationList.size());
	    return "show_applicationlist"; 
	   }
	  
	//����seeker�����Ĺ���
	  public String fjob(){
	   seeker=(Seeker) session.get("seeker");
	   jobList = jobDao.QueryJobInfoSid(seeker);
	   return "fjob";
	   }
}
