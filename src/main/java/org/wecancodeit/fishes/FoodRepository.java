package org.wecancodeit.fishes;

import org.springframework.data.repository.CrudRepository;

public interface FoodRepository extends CrudRepository<Food, Long> {

	Food findByName(String name);
	
	Food findByNameContains(String substring);
}
