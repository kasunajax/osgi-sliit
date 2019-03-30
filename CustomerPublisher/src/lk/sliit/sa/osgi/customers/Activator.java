package lk.sliit.sa.osgi.customers;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

import lk.sliit.sa.osgi.customers.service.CustomerPublish;
import lk.sliit.sa.osgi.persistence.service.PersistenceService;

public class Activator implements BundleActivator {

	private static BundleContext context;

	static BundleContext getContext() {
		return context;
	}
	ServiceRegistration publicServiceRegistration ;
	ServiceReference reference;

	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		System.out.println("Starting Customer Management Service");
		
		reference = bundleContext.getServiceReference(PersistenceService.class.getName());
		PersistenceService service = (PersistenceService)context.getService(reference);
		
		publicServiceRegistration = bundleContext.registerService(CustomerPublish.class.getName(),new CustomerPublisherImpl(service), null);
	}

	public void stop(BundleContext bundleContext) throws Exception {
		//Activator.context = null;
		System.out.println("Stop Customer Management Service");
		publicServiceRegistration.unregister();
		bundleContext.ungetService(reference);
	}

}
