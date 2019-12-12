package store.data;

import java.util.List;

import store.bean.Product;

public interface ProductsLoader {
	List<Product> getProducts();
}
