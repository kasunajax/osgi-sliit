package roommanagementpublisher;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

import lk.sliit.sa.osgi.persistence.service.PersistenceService;

public class Activator implements BundleActivator {

	private static BundleContext context;
	ServiceRegistration registration;
	ServiceReference reference;

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		registration = bundleContext.registerService(RoomManagementPublish.class.getName(), 
				new RoomManageImpl(), null);
		reference = bundleContext.getServiceReference(PersistenceService.class.getName());
		PersistenceService service = (PersistenceService)context.getService(reference);
	}

	public void stop(BundleContext bundleContext) throws Exception {
		System.out.println("Stopping Room management service");
		registration.unregister();
		bundleContext.ungetService(reference);
	}

}
