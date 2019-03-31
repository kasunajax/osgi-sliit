package lk.sliit.sa.osgi.application;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import lk.sliit.sa.osgi.customers.service.CustomerPublish;
import lk.sliit.sa.osgi.rooms.service.RoomManagementPublish;


public class Activator implements BundleActivator {

	private static BundleContext context;

	static BundleContext getContext() {
		return context;
	}
	ServiceReference<?> reference;
	ServiceReference<?> reference2;
	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		System.out.println("Welcome to our Hotel Reservation service !!");
		reference = bundleContext.getServiceReference(CustomerPublish.class.getName());
		CustomerPublish customerService = (CustomerPublish)context.getService(reference);
		reference2 = bundleContext.getServiceReference(RoomManagementPublish.class.getName());
		RoomManagementPublish roomService = (RoomManagementPublish)context.getService(reference2);
		
		Performance pmPerformance = new Performance(roomService, customerService);
		pmPerformance.start();
		
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
		bundleContext.ungetService(reference);
		bundleContext.ungetService(reference2);
	}

}
