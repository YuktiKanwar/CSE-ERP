package spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.dao.TransportDao;
import spring.model.Transport;
@Service("transportService")
public class TransportServiceImpl implements TransportService {
	@Autowired
	private TransportDao transportDao;
	@Override
	@Transactional
	public void addTransport(Transport t) {
		// TODO Auto-generated method stub
		this.transportDao.addTransport(t);
	}

	@Override
	@Transactional
	public void updateTransport(Transport t) {
		// TODO Auto-generated method stub
		this.transportDao.updateTransport(t);
	}

	@Override
	@Transactional
	public List<Transport> listTransports() {
		// TODO Auto-generated method stub
		return this.transportDao.listTransports();
	}

	@Override
	@Transactional
	public Transport getTransportById(int id) {
		// TODO Auto-generated method stub
		return this.transportDao.getTransportById(id);
	}

	@Override
	@Transactional
	public void removeTransport(int id) {
		// TODO Auto-generated method stub
		this.transportDao.removeTransport(id);
	}

	@Override
	@Transactional
	public Transport getTransportByStudentId(int student_id) {
		// TODO Auto-generated method stub
		return this.transportDao.getTransportByStudentId(student_id);
	}

}
