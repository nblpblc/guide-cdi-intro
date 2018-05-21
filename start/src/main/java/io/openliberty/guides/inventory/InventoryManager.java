package io.openliberty.guides.inventory;

import java.util.Properties;

import javax.enterprise.context.ApplicationScoped;

import io.openliberty.guides.inventory.client.SystemClient;
import io.openliberty.guides.inventory.model.InventoryList;

@ApplicationScoped
public class InventoryManager {
	private InventoryList invList = new InventoryList();
	private SystemClient systemClient = new SystemClient();

	public Properties get(String hostname) {
		systemClient.init(hostname);

		Properties properties = systemClient.getProperties();
		if (properties != null) {
			invList.addToInventoryList(hostname, properties);
		}
		return properties;

	}

	public InventoryList list() {
		return invList;
	}
}
