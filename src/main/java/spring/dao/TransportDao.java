package spring.dao;

import java.util.List;

import spring.model.Transport;

public interface TransportDao {

	public void addTransport(Transport t);
	public void updateTransport(Transport t);
	public List<Transport> listTransports();
	public Transport getTransportById(int id);
	public void removeTransport(int id);
	public Transport getTransportByStudentId(int student_id);
}
