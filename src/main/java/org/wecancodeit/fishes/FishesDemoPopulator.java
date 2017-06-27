package org.wecancodeit.fishes;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class FishesDemoPopulator implements CommandLineRunner {

	@Resource
	private FishesRepository repo;
	
	@Override
	public void run(String... args) throws Exception {
		Fish fish = new Fish("Neolamprologus", "brevis");
		repo.save(fish);
		
		repo.save(new Fish("Julidochromis", "regani"));
	}

}
