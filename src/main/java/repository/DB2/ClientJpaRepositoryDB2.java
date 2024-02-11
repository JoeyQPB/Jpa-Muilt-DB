package repository.DB2;

import domain.Client;
import repository.IClientJpaRepository;
import repository.generic.GenericJpaRepositoryDB2;

public class ClientJpaRepositoryDB2 extends GenericJpaRepositoryDB2<Client, Long> implements IClientJpaRepository {
	public ClientJpaRepositoryDB2() {
		super(Client.class);
	}
}