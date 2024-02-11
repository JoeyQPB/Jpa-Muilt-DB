package repository.DB1;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import config.ConnectionJPA;
import domain.Client;
import domain.Product;
import domain.ProductQuantity;
import domain.Sale;
import exceptions.RepositoryException;
import repository.ISaleJpaRepository;
import repository.generic.GenericJpaRepositoryDB1;

public class SaleJpaRepository extends GenericJpaRepositoryDB1<Sale, String> implements ISaleJpaRepository {

	private String PERSISTENCE_NAME;
	public SaleJpaRepository(String PERSISTENCE_NAME) {
		super(Sale.class);
		this.PERSISTENCE_NAME = PERSISTENCE_NAME;
	}

	@Override
	public void finalizeSale(Sale sale) {
		super.update(sale);
		
		EntityManager entityManager = ConnectionJPA.getEntityManager(PERSISTENCE_NAME);
		for ( ProductQuantity prod : sale.getproducts()) {
			Product product = prod.getproduct();
			product.setQuantity(product.getQuantity() - prod.getquantity());
			
			
			Product prodJpa = entityManager.merge(product);
			ProductJpaRepositoryDB1 productJpaRepository = new ProductJpaRepositoryDB1();
			productJpaRepository.update(prodJpa);
		}
	}

	@Override
	public void cancelSale(Sale sale) {
		super.update(sale);
	}
	
	@Override
	public Boolean delete(Sale sale) {
		throw new UnsupportedOperationException("OPERAÇÃO NÃO PERMITIDA");
	}
	
	@Override
	public Sale insert(Sale entity){
		EntityManager entityManager = null;
		try {
			entityManager = ConnectionJPA.getEntityManager(PERSISTENCE_NAME);
			
			for ( ProductQuantity prod : entity.getproducts()) {
				Product prodJpa = entityManager.merge(prod.getproduct());
				prod.setproduct(prodJpa);
			}
			
			Client clienntJpa = entityManager.merge(entity.getclient());
			entity.setclient(clienntJpa);
			entityManager.persist(entity);
			
			entityManager.getTransaction().commit();
			
			return entity;
		} catch (RepositoryException e) {
		    if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw new RepositoryException(e.getMessage());
		} finally {
			ConnectionJPA.closeEntityManager(entityManager);
		}
	}
	
	@Override
	public Sale selectWithCollection(String code) {
		EntityManager entityManager = ConnectionJPA.getEntityManager(PERSISTENCE_NAME);
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Sale> query = builder.createQuery(Sale.class);
		Root<Sale> root = query.from(Sale.class);
		root.fetch("client");
		root.fetch("products");
		query.select(root).where(builder.equal(root.get("code"), code));
		TypedQuery<Sale> tpQuery = entityManager.createQuery(query);
		Sale sale = tpQuery.getSingleResult();   
		
		entityManager.getTransaction().commit();
		ConnectionJPA.closeEntityManager(entityManager);
		
		return sale;
	}

	@Override
	public void cleanDB() {
		List<Sale> sales = this.selectAll();
		for (Sale sale : sales) {
			super.delete(sale);
		}
	}
}
