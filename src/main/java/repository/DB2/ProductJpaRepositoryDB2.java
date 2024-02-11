package repository.DB2;

import domain.Product;
import repository.IProductJpaRepository;
import repository.generic.GenericJpaRepositoryDB2;

public class ProductJpaRepositoryDB2 extends GenericJpaRepositoryDB2<Product, String> implements IProductJpaRepository {
	public ProductJpaRepositoryDB2() {
		super(Product.class);
	}
}
