package lk.sliit.sa.osgi.persistence;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import lk.sliit.sa.osgi.persistence.service.PersistenceService;

public class Activator implements BundleActivator {

	private static BundleContext context;

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		
		System.out.println("Persistence Service Booting ... ");
		
		context.registerService(PersistenceService.class.getName(), new PersistenceServiceImpl(), null);
		
		System.out.println("Persistence Service Loaded Successfully !");
		
//		RoomFactory fac = (RoomFactory) new PersistenceServiceImpl().getFactory(Factory.ROOMS);
//		fac.add(new RoomImpl(2, "do", "32ds"));

	}

	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
	}

}