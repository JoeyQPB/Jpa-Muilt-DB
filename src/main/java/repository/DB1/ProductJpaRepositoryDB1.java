package repository.DB1;

import domain.Product;
import repository.IProductJpaRepository;
import repository.generic.GenericJpaRepositoryDB1;

public class ProductJpaRepositoryDB1 extends GenericJpaRepositoryDB1<Product, String> implements IProductJpaRepository {
	public ProductJpaRepositoryDB1() {
		super(Product.class);
	}
}
