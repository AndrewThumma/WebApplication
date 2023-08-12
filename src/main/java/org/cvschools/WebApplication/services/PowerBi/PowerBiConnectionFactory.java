package org.cvschools.WebApplication.services.PowerBi;

/**
 * Used to create new {@see PowerBiConnection} instances.
 *
 * @author Aidan Morgan
 */
public interface PowerBiConnectionFactory {
    /**
     * Create a new {@see PowerBiConnection} for performing operations.
     * @return a new {@see PowerBiConnection} instance.
     */
    public PowerBiConnection create();
}
