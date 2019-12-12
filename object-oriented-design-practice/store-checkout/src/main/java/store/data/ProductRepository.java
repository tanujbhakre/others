package store.data;

import store.bean.Product;

public interface ProductRepository {

	Product getByCode(String code);

}
