package repository.generic;

import java.io.Serializable;

import domain.interfaces.IPersistence;

public class GenericJpaRepositoryDB1<T extends IPersistence, E extends Serializable> extends JPAGenericRepository<T, E> {

	public GenericJpaRepositoryDB1(Class<T> persistenteClass) {
		super(persistenteClass, "JPA-vendas-1");
	}

}
