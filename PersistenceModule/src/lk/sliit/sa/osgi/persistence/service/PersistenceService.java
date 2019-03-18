package lk.sliit.sa.osgi.persistence.service;

public interface PersistenceService {

	public Factory getFactory(String type) throws Exception;
	
}
