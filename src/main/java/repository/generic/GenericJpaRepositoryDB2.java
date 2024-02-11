package repository.generic;

import java.io.Serializable;

import domain.interfaces.IPersistence;

public class GenericJpaRepositoryDB2<T extends IPersistence, E extends Serializable> extends JPAGenericRepository<T, E> {

	public GenericJpaRepositoryDB2(Class<T> persistenteClass) {
		super(persistenteClass, "JPA-vendas-2");
	}

}
