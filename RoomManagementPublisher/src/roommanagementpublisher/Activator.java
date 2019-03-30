package roommanagementpublisher;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

import lk.sliit.sa.osgi.persistence.service.PersistenceService;

public class Activator implements BundleActivator {

	private static BundleContext context;
	ServiceRegistration<?> registration;

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		System.out.println("Starting Room management service");
		
		ServiceReference<?> ref = context.getServiceReference(PersistenceService.class.getName());
		PersistenceService ps = (PersistenceService)context.getService(ref);
		
		registration = bundleContext.registerService(RoomManagementPublish.class.getName(), 
				new RoomManageImpl(ps), null);
		
		
	}

	public void stop(BundleContext bundleContext) throws Exception {
		System.out.println("Stopping Room management service");
		registration.unregister();
	}

}
