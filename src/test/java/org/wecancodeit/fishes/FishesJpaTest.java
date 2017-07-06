package org.wecancodeit.fishes;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * The @DataJpaTest annotation
 * 
 * <p>
 * As per Spring Boot docs, "@DataJpaTest can be used if you want to test JPA
 * applications. By default it will configure an in-memory embedded database,
 * scan for @Entity classes and configure Spring Data JPA repositories.
 * Regular @Component beans will not be loaded into the ApplicationContext.".
 * </p>
 * 
 * <p>
 * Note that this will <strong>not</strong> do things like running
 * {@link CommandLineRunner}s. You don't really want it to for tests like this.
 * You want a squeaky clean database.
 * </p>
 * 
 * <p>
 * See <a href=
 * "https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#boot-features-testing-spring-boot-applications-testing-autoconfigured-jpa-test">Section
 * 41.3.8 Auto-configured Data JPA tests</a> in the Spring Boot documentation.
 * </p>
 * 
 * @see {@link DataJpaTest}
 *
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class FishesJpaTest {

	@Resource
	private FishRepository fishRepository;

	@Resource
	private GenusRepository genusRepository;

	private Genus testGenus;

	private Set<Food> foods = new HashSet<>(asList(new Food("flake"), new Food("freeze dried mysis")));
	
	/**
	 * A method annotated with @{@link Before} runs before <em>each</em> test
	 * method. Such methods are used to do common setup for tests.
	 */
	@Before
	public void createTestGenus() {
		testGenus = new Genus("test genus", "test genus description");
		genusRepository.save(testGenus);
	}

	/**
	 * This test has no assertions—it is expected to fail if Fish isn't
	 * configured properly for JPA.
	 * 
	 * Try commenting out the @Id annotation in Fish, and watch this test fail.
	 */
	@Test
	public void shouldSaveAFish() {
		Fish testFish = new Fish(testGenus, "test species name", foods);
		fishRepository.save(testFish);
	}

	/**
	 * This should fail because "optional=false" was added to the genus mapping.
	 * 
	 * The "expected" attribute on @Test indicates that we expect the exception
	 * to be thrown.
	 */
	@Test(expected = DataIntegrityViolationException.class)
	public void shouldFailOnSaveIfGenusIsNull() {
		Fish testFish = new Fish(null, "test species name", foods);
		fishRepository.save(testFish);
	}

}
