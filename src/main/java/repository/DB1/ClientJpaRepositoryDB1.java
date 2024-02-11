package repository.DB1;

import domain.Client;
import repository.IClientJpaRepository;
import repository.generic.GenericJpaRepositoryDB1;

public class ClientJpaRepositoryDB1 extends GenericJpaRepositoryDB1<Client, Long> implements IClientJpaRepository {
	public ClientJpaRepositoryDB1() {
		super(Client.class);
	}
}
