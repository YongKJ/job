package com.work.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.work.model.Application;

@Service @Transactional
public class ApplicationDao {
	@Resource SessionFactory factory;
	
	   //"��"
		public void AddApplication(Application application) throws Exception{
			Session s = factory.getCurrentSession();
			s.save(application);
		}
		//"��"
		public void UpdateApplication(Application application)throws Exception{
			   Session s=factory.getCurrentSession();
			   s.update(application);
			  }
		//"ɾ"
		public void DeleteApplication(Integer applicationid)throws Exception{
		     Session s=factory.getCurrentSession();
		     Object application=s.load(Application.class, applicationid);
		     s.delete(application);
		    }
		//ͨ�������id�õ�����  
	    public Application GetApplicationById(Integer applicationid){
	     Session s=factory.getCurrentSession();
	     Application application=(Application)s.get(Application.class, applicationid);
	     return application;
	    } 
		//��ѯ���й�������Ϣ
		public ArrayList<Application> QueryAllApplication(){
			Session s = factory.getCurrentSession();
			String hql = "From Application";
			Query q = s.createQuery(hql);
			List applicationList = q.list();
			return (ArrayList<Application>) applicationList;
		}
		
		public ArrayList<Application> QueryApplicationByJobId(Integer jobid){
			   Session s=factory.getCurrentSession();
			   String hql="From Application a where a.job.jobid="+jobid;
			   Query q=s.createQuery(hql);
			   List applicationList=q.list();
			   return (ArrayList<Application>) applicationList;
			  }
		//ͨ��seeker��id�ҵ����е������
	    public ArrayList<Application> queryApplicationInfo(int id){
	       Session s = factory.getCurrentSession();
	       String hql ="From Application application where application.seeker.seekerid="+id;
	       Query q = s.createQuery(hql);
	       List applicationList = q.list();
	       return (ArrayList<Application>) applicationList;
	   }

}
