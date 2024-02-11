package repositoryTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import domain.Address;
import domain.Client;
import domain.factory.EntityFactory;
import repository.IClientJpaRepository;
import repository.DB1.ClientJpaRepositoryDB1;
import repository.DB2.ClientJpaRepositoryDB2;

public class ClientRepositoryPGTest {

	private IClientJpaRepository clienteJpaRepositoryDB1;
	private IClientJpaRepository clienteJpaRepositoryDB2;
	private EntityFactory entityFactory;
	private Client client;
	private Client clientDB1;
	private Client clientDB2;

	public ClientRepositoryPGTest() {
		this.clienteJpaRepositoryDB1 = new ClientJpaRepositoryDB1();
		this.clienteJpaRepositoryDB2 = new ClientJpaRepositoryDB2();
		this.entityFactory = new EntityFactory();
	}

	@Before
	public void init() {
		clearDB();
		
		this.client = entityFactory.getNewClient(90909090L);
		this.clientDB1 = clienteJpaRepositoryDB1.insert(client);
		this.clientDB2 = clienteJpaRepositoryDB2.insert(client);	
	}

	@After
	public void end() {
		clearDB();
	}

	@Test
	public void insertClientTest() {
		assertNotNull(clientDB1);
		assertNotNull(clientDB1.getId());
		assertEquals(clientDB1, client);
		assertEquals(clientDB1.getAdress(), client.getAdress());
		assertEquals(clientDB1.getCelNumber(), client.getCelNumber());
		assertEquals(clientDB1.getCpf(), client.getCpf());
		assertEquals(clientDB1.getName(), client.getName());
		
		
		assertNotNull(clientDB2);
		assertNotNull(clientDB2.getId());
		assertEquals(clientDB2, client);
		assertEquals(clientDB2.getAdress(), client.getAdress());
		assertEquals(clientDB2.getCelNumber(), client.getCelNumber());
		assertEquals(clientDB2.getCpf(), client.getCpf());
		assertEquals(clientDB2.getName(), client.getName());
	}

	@Test
	public void selectClientTest() {
		Client clientTestSelect = clienteJpaRepositoryDB1.select(client.getId());
		assertNotNull(clientTestSelect);
		assertNotNull(clientTestSelect.getId());
		assertEquals(clientTestSelect, client);
		assertEquals(clientTestSelect.getAdress(), client.getAdress());
		assertEquals(clientTestSelect.getCelNumber(), client.getCelNumber());
		assertEquals(clientTestSelect.getCpf(), client.getCpf());
		assertEquals(clientTestSelect.getName(), client.getName());
		
		Client clientTestSelectDB2 = clienteJpaRepositoryDB2.select(client.getId());
		assertNotNull(clientTestSelectDB2);
		assertNotNull(clientTestSelectDB2.getId());
		assertEquals(clientTestSelectDB2, client);
		assertEquals(clientTestSelectDB2.getAdress(), client.getAdress());
		assertEquals(clientTestSelectDB2.getCelNumber(), client.getCelNumber());
		assertEquals(clientTestSelectDB2.getCpf(), client.getCpf());
		assertEquals(clientTestSelectDB2.getName(), client.getName());
	}
	
	@Test
	public void selectByUniqueValueTest() {
		Client clientTestSelect = clienteJpaRepositoryDB1.selectByUniqueValue(client.getCpf());
		assertNotNull(clientTestSelect);
		assertNotNull(clientTestSelect.getId());
		assertEquals(clientTestSelect, client);
		assertEquals(clientTestSelect.getAdress(), client.getAdress());
		assertEquals(clientTestSelect.getCelNumber(), client.getCelNumber());
		assertEquals(clientTestSelect.getCpf(), client.getCpf());
		assertEquals(clientTestSelect.getName(), client.getName());
		
		Client clientTestSelect2 = clienteJpaRepositoryDB2.selectByUniqueValue(client.getCpf());
		assertNotNull(clientTestSelect2);
		assertNotNull(clientTestSelect2.getId());
		assertEquals(clientTestSelect2, client);
		assertEquals(clientTestSelect2.getAdress(), client.getAdress());
		assertEquals(clientTestSelect2.getCelNumber(), client.getCelNumber());
		assertEquals(clientTestSelect2.getCpf(), client.getCpf());
		assertEquals(clientTestSelect2.getName(), client.getName());
	}

	@Test
	public void selectAllClientTest() {
		 Client c2 = entityFactory.getNewClient(80808080L);
		 Client c3 = entityFactory.getNewClient(70707070L);
		 
		 c2 = clienteJpaRepositoryDB1.insert(c2);
		 c3 = clienteJpaRepositoryDB1.insert(c3);
		 
		 List<Client> clients = clienteJpaRepositoryDB1.selectAll();
		 
		 assertNotNull(clients);
		 assertEquals(clients.size(), 3);
		 assertEquals(clients.get(0).getCpf(), clientDB1.getCpf());
		 assertEquals(clients.get(1).getCpf(), c2.getCpf());
		 assertEquals(clients.get(2).getCpf(), c3.getCpf());
		 
		 clienteJpaRepositoryDB1.delete(c3);
		 
		 clients = clienteJpaRepositoryDB1.selectAll();
		 assertNotNull(clients);
		 assertEquals(clients.size(), 2);
	}
	
	@Test
	public void selectAllClientTest2() {
		Client c2 = entityFactory.getNewClient(80808080L);
		Client c3 = entityFactory.getNewClient(70707070L);

		c2 = clienteJpaRepositoryDB2.insert(c2);
		c3 = clienteJpaRepositoryDB2.insert(c3);

		List<Client> clients2 = clienteJpaRepositoryDB2.selectAll();

		assertNotNull(clients2);
		assertEquals(clients2.size(), 3);
		assertEquals(clients2.get(0).getCpf(), clientDB1.getCpf());
		assertEquals(clients2.get(1).getCpf(), c2.getCpf());
		assertEquals(clients2.get(2).getCpf(), c3.getCpf());

		clienteJpaRepositoryDB1.delete(c3);

		clients2 = clienteJpaRepositoryDB1.selectAll();
		assertNotNull(clients2);
		assertEquals(clients2.size(), 2);
	}

	@Test
	public void updateClientTest() {
		Address address = new Address("Street updated", 10, "City updated", "State updated", 450000L);
		clientDB1.setCelNumber(123456789L);
		clientDB1.setCpf(123456789L);
		clientDB1.setName("Name updated");
		clientDB1.setAdress(address);

		Client clientUpdated = clienteJpaRepositoryDB1.update(clientDB1);
		
		assertNotNull(clientUpdated);
		assertNotNull(clientUpdated.getId());
		assertEquals(clientUpdated, client);
		assertEquals(clientUpdated.getAdress(), client.getAdress());
		assertEquals(clientUpdated.getCelNumber(), client.getCelNumber());
		assertEquals(clientUpdated.getCpf(), client.getCpf());
		assertEquals(clientUpdated.getName(), client.getName());
		
		Client clientUpdate2 = clienteJpaRepositoryDB2.update(clientDB2);
		
		assertNotNull(clientUpdate2);
		assertNotNull(clientUpdate2.getId());
		assertEquals(clientUpdate2, client);
		assertEquals(clientUpdate2.getAdress(), client.getAdress());
		assertEquals(clientUpdate2.getCelNumber(), client.getCelNumber());
		assertEquals(clientUpdate2.getCpf(), client.getCpf());
		assertEquals(clientUpdate2.getName(), client.getName());
	}

	@Test
	public void deleteClientTest() {
		Boolean result = clienteJpaRepositoryDB1.delete(clientDB1);
		assertTrue(result);
		
		result = clienteJpaRepositoryDB2.delete(clientDB2);
		assertTrue(result);
	}

	private void clearDB() {
		Collection<Client> list = clienteJpaRepositoryDB1.selectAll();
		Collection<Client> list2 = clienteJpaRepositoryDB2.selectAll();
		for (Client c : list) {
			clienteJpaRepositoryDB1.delete(c);
		}
		for (Client c : list2) {
			clienteJpaRepositoryDB2.delete(c);
		}
	}

}
