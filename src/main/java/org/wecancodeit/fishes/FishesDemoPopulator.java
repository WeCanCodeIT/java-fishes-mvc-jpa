package org.wecancodeit.fishes;

import static java.util.Arrays.asList;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class FishesDemoPopulator implements CommandLineRunner {

	@Resource
	private FishRepository fishesRepo;
	
	@Resource
	private GenusRepository genusRepo;
	
	@Resource
	private FoodRepository foodRepo;
	
	@Override
	public void run(String... args) throws Exception {
		
		Food flake = new Food("flake");
		foodRepo.save(flake);
		
		Food mysis = new Food("freeze dried mysis");
		foodRepo.save(mysis);
		
		Genus neolamprologus = new Genus("Neolamprologus", "Neolamprologus is a genus of cichlids endemic to eastern Africa with all but one species, Neolamprologus devosi from the Malagarasi River, occurring in Lake Tanganyika. It is the largest genus of cichlids in Lake Tanganyika and also the largest genus in the tribe Lamprologini, which includes Altolamprologus, Chalinochromis, Julidochromis, Lamprologus, Lepidiolamprologus, Telmatochromis and Variabilichromis. The latter is a monotypic genus doubtfully distinct from Neolamprologus."); 
		genusRepo.save(neolamprologus);
		// two foods
		Fish fish = new Fish(neolamprologus, "brevis", flake, mysis);
		fishesRepo.save(fish);
		
		Genus julidochromis = new Genus("Julidochromis", "Julidochromis is a genus of cichlids in the subfamily Pseudocrenilabrinae. They are commonly called julies and are endemic to Lake Tanganyika in eastern Africa.");
		genusRepo.save(julidochromis);
		// one food
		fishesRepo.save(new Fish(julidochromis, "regani", mysis));
		
		// no food
		fishesRepo.save(new Fish(julidochromis, "dickfeldi"));
	}

}
