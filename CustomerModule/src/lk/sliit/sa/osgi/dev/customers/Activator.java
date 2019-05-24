package lk.sliit.sa.osgi.dev.customers;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import lk.sliit.sa.osgi.dev.customers.service.CustomerService;
import lk.sliit.sa.osgi.persistence.service.PersistenceService;

public class Activator implements BundleActivator {

	private static BundleContext context;

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		
		ServiceReference<?> ref = context.getServiceReference(PersistenceService.class.getName());
		PersistenceService srv = (PersistenceService) context.getService(ref);
		
		context.registerService(CustomerService.class.getName(), new CustomerServiceImpl(srv), null);
		
		
	}

	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
	}

}
