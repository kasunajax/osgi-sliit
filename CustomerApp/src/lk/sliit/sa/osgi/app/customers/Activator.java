package lk.sliit.sa.osgi.app.customers;

import java.awt.EventQueue;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import lk.sliit.sa.osgi.dev.customers.service.CustomerService;

public class Activator implements BundleActivator {

	private static BundleContext context;

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		
		ServiceReference<?> ref = context.getServiceReference(CustomerService.class.getName());
		CustomerService srv = (CustomerService) context.getService(ref);
		new Test(srv);
		
	}

	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
	}

}
