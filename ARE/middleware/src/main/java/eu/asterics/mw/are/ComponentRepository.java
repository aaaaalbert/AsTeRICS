package eu.asterics.mw.are;

import eu.asterics.mw.are.exceptions.BundleManagementException;
import eu.asterics.mw.model.DataType;
import eu.asterics.mw.model.bundle.IComponentType;
import eu.asterics.mw.model.runtime.IRuntimeComponentInstance;
import eu.asterics.mw.services.AstericsErrorHandling;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;



/*
 *    AsTeRICS - Assistive Technology Rapid Integration and Construction Set
 *
 *
 *        d8888      88888888888       8888888b.  8888888 .d8888b.   .d8888b.
 *       d88888          888           888   Y88b   888  d88P  Y88b d88P  Y88b
 *      d88P888          888           888    888   888  888    888 Y88b.    
 *     d88P 888 .d8888b  888   .d88b.  888   d88P   888  888         "Y888b. 
 *    d88P  888 88K      888  d8P  Y8b 8888888P"    888  888            "Y88b.
 *   d88P   888 "Y8888b. 888  88888888 888 T88b     888  888    888       "888
 *  d8888888888      X88 888  Y8b.     888  T88b    888  Y88b  d88P Y88b  d88P
 * d88P     888  88888P' 888   "Y8888  888   T88b 8888888 "Y8888P"   "Y8888P"
 *
 *
 *                    homepage: http://www.asterics.org
 *
 *     This project has been partly funded by the European Commission,
 *                      Grant Agreement Number 247730
 * 
 * 
 *    License: GPL v3.0 (GNU General Public License Version 3.0)
 *                 http://www.gnu.org/licenses/gpl.html
 *
 */


/**
 * In principle, it realizes a map which includes ALL the components in the ARE
 * middleware, mapped from their IDs.
 *
 * @author Nearchos Paspallis [nearchos@cs.ucy.ac.cy]
 *         Date: Jul 14, 2010
 *         Time: 4:11:34 PM
 */
public class ComponentRepository
{
    public static final ComponentRepository instance = 
    	new ComponentRepository();
    private Logger logger = null;
    

    private ComponentRepository()
    {
        super();
        logger = AstericsErrorHandling.instance.getLogger();
    }

    private final Map<String, IComponentType> repository = 
    		new HashMap<String, IComponentType>();
    
    private final Map<String, DefaultComponentFactory> componentFactories = 
    		new HashMap<String, DefaultComponentFactory> ();

    public void install(final IComponentType componentType)
            throws BundleManagementException
    {
        if (componentType == null)
        {

            logger.severe(this.getClass().getName()+
            		": install-> Illegal null argument");
        	throw new NullPointerException("Illegal null argument");
        }

        final String componentTypeID = componentType.getID();

        if (!repository.containsKey(componentTypeID))
        {

        	repository.put(componentTypeID, componentType);
        }
       // System.out.println ("Installing "+componentTypeID+" - "+componentType);
    }

    void uninstall(final IComponentType componentType)
            throws BundleManagementException
    {
        if (componentType == null)
        {
        	 logger.severe(this.getClass().getName()+
     		": uninstall-> Illegal null argument");
        	throw new NullPointerException("Illegal null argument");
        }

        uninstall(componentType.getID());
    }

    void uninstall(final String componentTypeID)
            throws BundleManagementException
    {
        if (!repository.containsKey(componentTypeID))
        {
        	logger.severe(": uninstall-> The specified componentTypeID"
                    + " is already installed in the repository.");
            throw new BundleManagementException("The specified componentTypeID"
                    + " is not found in the repository.");
        }

        
        repository.remove(componentTypeID);
    }

    public Set<IComponentType> getInstalledComponentTypes()
    {
        final Set<IComponentType> installedComponentTypes
                = new LinkedHashSet<IComponentType>();

        installedComponentTypes.addAll(repository.values());

        return installedComponentTypes;
    }

    public IComponentType getComponentType(final String componentTypeID)
    {
       IComponentType res = repository.get(componentTypeID);
       return res;
    }

    // todo remove this debug method
    public void printAll()
    {
        System.out.println("  Bundle repository:");
        for(final String key : repository.keySet())
        {
            System.out.println("    " + key + "\t --> " + repository.get(key));
        }
    }

	public void setComponentFactory(String componentCanonicalName,
			DefaultComponentFactory defaultComponentFactory) {
		this.componentFactories.put(componentCanonicalName, 
				defaultComponentFactory);
		
	}
	  public IRuntimeComponentInstance getInstance (String canonicalName)
	    {
	    	return this.componentFactories.get(canonicalName).getInstance();
	    }
	  

    public DataType getPortDataType(final String componentTypeID, final String portID)
    {
        final IComponentType componentType = repository.get(componentTypeID);
        return componentType == null ? DataType.UNKNOWN : componentType.getPortDataType(portID);
    }
}
