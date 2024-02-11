package repository.DB3;

import domain.Client2;
import repository.generic.GenericJpaRepositoryDB3;

public class ClientJpaRepositoryDB3 extends GenericJpaRepositoryDB3<Client2, Long> implements IClientJpaRepositoryMySQL {
	public ClientJpaRepositoryDB3() {
		super(Client2.class);
	}
}
