package repositoryTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.Instant;
import java.util.Collection;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import domain.Client2;
import repository.DB3.ClientJpaRepositoryDB3;
import repository.DB3.IClientJpaRepositoryMySQL;

public class ClientRepositoryMySQLTest {

	private IClientJpaRepositoryMySQL clienteJpaRepository;
	private Client2 client;
	private Client2 clientDB;

	public ClientRepositoryMySQLTest() {
		this.clienteJpaRepository = new ClientJpaRepositoryDB3();
	}

	@Before
	public void init() {
		clearDB();
		this.client = new Client2(101010101L, "jj", 11l, Instant.now());
		this.clientDB = clienteJpaRepository.insert(client);
	}

	@After
	public void end() {
		clearDB();
	}
	
	@Test
	public void insertClientTest() {
		assertNotNull(clientDB);
		assertNotNull(clientDB.getId());
		assertEquals(clientDB, client);
		assertEquals(clientDB.getCelNumber(), client.getCelNumber());
		assertEquals(clientDB.getCpf(), client.getCpf());
		assertEquals(clientDB.getName(), client.getName());
	}

	@Test
	public void selectClientTest() {
		Client2 clientTestSelect = clienteJpaRepository.select(client.getId());
		assertNotNull(clientTestSelect);
		assertNotNull(clientTestSelect.getId());
		assertEquals(clientTestSelect, client);
		assertEquals(clientTestSelect.getCelNumber(), client.getCelNumber());
		assertEquals(clientTestSelect.getCpf(), client.getCpf());
		assertEquals(clientTestSelect.getName(), client.getName());

	}
	
	@Test
	public void selectByUniqueValueTest() {
		Client2 clientTestSelect = clienteJpaRepository.selectByUniqueValue(client.getCpf());
		assertNotNull(clientTestSelect);
		assertNotNull(clientTestSelect.getId());
		assertEquals(clientTestSelect, client);
		assertEquals(clientTestSelect.getCelNumber(), client.getCelNumber());
		assertEquals(clientTestSelect.getCpf(), client.getCpf());
		assertEquals(clientTestSelect.getName(), client.getName());
	}

	@Test
	public void selectAllClientTest() {
		 Client2 c2 =  new Client2(06060660L, "jj", 11l, Instant.now());
		 Client2 c3 =  new Client2(7070707L, "jj", 11l, Instant.now());
		 
		 c2 = clienteJpaRepository.insert(c2);
		 c3 = clienteJpaRepository.insert(c3);
		 
		 List<Client2> clients = clienteJpaRepository.selectAll();
		 
		 assertNotNull(clients);
		 assertEquals(clients.size(), 3);
		 assertEquals(clients.get(0).getCpf(), clientDB.getCpf());
		 assertEquals(clients.get(1).getCpf(), c2.getCpf());
		 assertEquals(clients.get(2).getCpf(), c3.getCpf());
		 
		 clienteJpaRepository.delete(c3);
		 
		 clients = clienteJpaRepository.selectAll();
		 assertNotNull(clients);
		 assertEquals(clients.size(), 2);
	}
	

	@Test
	public void updateClientTest() {
		clientDB.setCelNumber(123456789L);
		clientDB.setCpf(123456789L);
		clientDB.setName("Name updated");

		Client2 clientUpdated = clienteJpaRepository.update(clientDB);
		
		assertNotNull(clientUpdated);
		assertNotNull(clientUpdated.getId());
		assertEquals(clientUpdated, client);
		assertEquals(clientUpdated.getCelNumber(), client.getCelNumber());
		assertEquals(clientUpdated.getCpf(), client.getCpf());
		assertEquals(clientUpdated.getName(), client.getName());
	}

	@Test
	public void deleteClientTest() {
		Boolean result = clienteJpaRepository.delete(clientDB);
		assertTrue(result);
	}
	
	private void clearDB() {
		Collection<Client2> list = clienteJpaRepository.selectAll();
		for (Client2 c : list) {
			clienteJpaRepository.delete(c);
		}
	}
}
