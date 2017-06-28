package org.wecancodeit.fishes;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class FishesDemoPopulator implements CommandLineRunner {

	@Resource
	private FishesRepository fishesRepo;
	
	@Resource
	private GenusRepository genusRepo;
	
	@Override
	public void run(String... args) throws Exception {
		Genus neolamprologus = new Genus("Neolamprologus", "Neolamprologus is a genus of cichlids endemic to eastern Africa with all but one species, Neolamprologus devosi from the Malagarasi River"); 
		genusRepo.save(neolamprologus);
		Fish fish = new Fish(neolamprologus, "brevis");
		fishesRepo.save(fish);
		
		Genus julidochromis = new Genus("Julidochromis", "Julidochromis is a genus of cichlids in the subfamily Pseudocrenilabrinae. They are commonly called julies and are endemic to Lake Tanganyika in eastern Africa.");
		genusRepo.save(julidochromis);
		fishesRepo.save(new Fish(julidochromis, "regani"));
	}

}
