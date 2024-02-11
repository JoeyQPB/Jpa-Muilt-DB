package repository.generic;

import java.io.Serializable;

import domain.interfaces.IPersistence;

public class GenericJpaRepositoryDB3<T extends IPersistence, E extends Serializable> extends JPAGenericRepository<T, E> {

	public GenericJpaRepositoryDB3(Class<T> persistenteClass) {
		super(persistenteClass, "JPA-vendas-3-MySQL");
	}

}
