package com.work.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.work.model.Resume;
import com.work.model.Seeker;

@Service @Transactional
public class SeekerDao {
	@Resource SessionFactory factory;
	//"��"
	public void AddSeeker(Seeker seeker)throws Exception{
		Session s=factory.getCurrentSession();
		s.save(seeker);
	}
	
	//"ɾ"
	public void DeleteSeeker(Integer seekerId)throws Exception{
		Session s=factory.getCurrentSession();
		Object seeker =s.load(Seeker.class, seekerId);
		s.delete(seeker);
	}
	
	//"��"
	public void UpdateSeeker(Seeker seeker)throws Exception{
		Session s=factory.getCurrentSession();
		s.update(seeker);
	}
	//"��"
	//��ѯ����seeker����Ϣ
	public ArrayList<Seeker> QueryAllCustomer(){
		Session s = factory.getCurrentSession();
		String hql = "From Seeker";
		Query q = s.createQuery(hql);
		List seekerList = q.list();
		return (ArrayList<Seeker>) seekerList;
	}
	//����������ȡ����
	public Seeker GetSeekerById(Integer seekerid){
		Session s = factory.getCurrentSession();
		Seeker seeker = (Seeker)s.get(Seeker.class, seekerid);
		return seeker;
	}
	
	//�����û���nickname��ѯ
	 public ArrayList<Seeker> QuerySeekerInfo(String nickname){
		 Session s = factory.getCurrentSession();
		 List seekerList;
		 String hql = "From Seeker seeker where 1=1";
		 if(!nickname.equals("")) {
			 hql = hql+"and seeker.nickname ='" + nickname  + "'";
			 Query q = s.createQuery(hql);
			 seekerList = q.list();
		 }
		 else{
			 seekerList = null;//���û�������ʱ��ѯ���Ϊ��
		 }
		 return (ArrayList<Seeker>) seekerList;
	 }
}
