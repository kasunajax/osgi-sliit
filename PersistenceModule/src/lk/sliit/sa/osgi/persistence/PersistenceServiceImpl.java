package lk.sliit.sa.osgi.persistence;

import lk.sliit.sa.osgi.persistence.service.Factory;
import lk.sliit.sa.osgi.persistence.service.PersistenceService;

public class PersistenceServiceImpl implements PersistenceService{

	@Override
	public Factory getFactory(String type) throws Exception {
		
		if(type.equals(Factory.ROOMS)) {
	
			return new RoomFactoryImpl();
			
		}
		
		throw new Exception("Factory Not Found !");
	}

}
