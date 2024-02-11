package services;

import domain.Product;
import repository.IProductJpaRepository;

public class ProductService extends GenericService<Product, String> implements IProductService {

	private IProductJpaRepository repository;
	
	public ProductService(IProductJpaRepository repository) {
		super(repository);
		this.repository = repository;
	}
}
