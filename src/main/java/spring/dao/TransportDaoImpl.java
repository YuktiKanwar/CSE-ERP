package spring.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.model.Transport;

@Repository
public class TransportDaoImpl implements TransportDao {
	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public void addTransport(Transport t) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(t);
	}

	@Override
	public void updateTransport(Transport t) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		session.update(t);
	}

	@Override
	public List<Transport> listTransports() {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		List<Transport> TransportList = session.createQuery("from Transport").list();
		return TransportList;
	}

	@Override
	public Transport getTransportById(int id) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();		
		Transport t = (Transport) session.load(Transport.class, new Integer(id));
		return t;
	}

	@Override
	public void removeTransport(int id) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		Transport t = (Transport) session.load(Transport.class, new Integer(id));
		if(null != t){
			session.delete(t);
		}
	}

	@Override
	public Transport getTransportByStudentId(int student_id) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();		
		List<Transport> TransportList = session.createQuery("from Transport where student_id =" +student_id).list();
		if(TransportList.isEmpty())
			return null;
		else
			{
			Transport transport = TransportList.get(0);
			return transport;
			}
	}

}
