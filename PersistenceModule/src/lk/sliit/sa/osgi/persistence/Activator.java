package lk.sliit.sa.osgi.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import lk.sliit.sa.osgi.persistence.service.Factory;
import lk.sliit.sa.osgi.persistence.service.PersistenceService;
import lk.sliit.sa.osgi.persistence.service.RoomFactory;

public class Activator implements BundleActivator {

	private static BundleContext context;

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		
		context.registerService(PersistenceService.class.getName(), new PersistenceServiceImpl(), null);
		
//		RoomFactory fac = (RoomFactory) new PersistenceServiceImpl().getFactory(Factory.ROOMS);
//		fac.add(new RoomImpl(2, "do", "32ds"));

	}

	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
	}

}
