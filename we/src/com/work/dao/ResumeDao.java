package com.work.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.work.model.Job;
import com.work.model.Resume;
import com.work.model.Seeker;

@Service @Transactional
public class ResumeDao {
	@Resource SessionFactory factory;
	//"��"
		public void AddResume(Resume resume) throws Exception{
			Session s = factory.getCurrentSession();
			s.save(resume);
		}
		
		//"ɾ"
		public void DeleteResume(Integer resumeId)throws Exception{
			Session s = factory.getCurrentSession();
			Object resume = s.load(Resume.class, resumeId);
			s.delete(resume);
		}
		
		//"��"
		public void UpdateResume(Resume resume)throws Exception{
			Session s = factory.getCurrentSession();
			s.update(resume);
		}
		
		//��ѯ���м�������Ϣ
			public ArrayList<Resume> QueryAllResume(){
				Session s = factory.getCurrentSession();
				String hql = "From Resume";
				Query q = s.createQuery(hql);
				List resumeList = q.list();
				return (ArrayList<Resume>) resumeList;
			}
			
		//����"resumeid"��ѯresume
			public Resume GetResumeById(Integer resumeid){
				Session s = factory.getCurrentSession();
				Resume resume = (Resume)s.get(Resume.class, resumeid);
				return resume;
			}
		//���ݡ�sid����ѯresume
			public ArrayList<Resume> QueryResumeInfoSid(Seeker seeker){
				Session s = factory.getCurrentSession();
				String hql = "From Resume resume where 1=1";
				if(null != seeker && seeker.getSeekerid()!=0) 
		    		hql = hql + " and resume.seeker.seekerid ='" + seeker.getSeekerid() + "'";
				Query q = s.createQuery(hql);
				List resumeList = q.list();
				return (ArrayList<Resume>) resumeList;
			}
		/*//ͨ��seekerid����resume
			 public ArrayList<Resume> QueryResumeInfoID(int id){
			  Session s = factory.getCurrentSession();
			  String hql ="From Resume resume where resume.seeker.seekerid="+id;
			  Query q = s.createQuery(hql);
			  List resumeList = q.list();
			  return (ArrayList<Resume>) resumeList;
			 }*/

}
