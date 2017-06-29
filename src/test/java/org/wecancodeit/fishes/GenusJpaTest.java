package org.wecancodeit.fishes;

import static org.junit.Assert.assertEquals;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class GenusJpaTest {
 	
	private static final String STUPIDLY_LONG_DESCRIPTION = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris semper tempus lacus, ac consequat nisl dictum interdum. Fusce vestibulum ligula sit amet laoreet sagittis. Praesent fermentum vel ante vitae maximus. Maecenas pretium aliquet odio sit amet pharetra. Nullam sit amet sapien dictum, hendrerit dolor vel, elementum neque. Donec in pulvinar erat, nec consequat urna. Proin eleifend ipsum id leo ornare, non suscipit felis placerat. Vestibulum et risus sapien. Donec dapibus rhoncus lectus, nec gravida felis iaculis vitae. Mauris et nunc commodo, convallis libero non, malesuada dui. Nam mauris ex, porttitor at egestas at, tempus vel quam. Phasellus cursus ut felis vel gravida. Vestibulum ac hendrerit quam. Nam aliquet purus neque, quis malesuada mi ornare sed. Pellentesque gravida, ipsum eget malesuada efficitur, purus tellus lacinia nisi, vel malesuada diam odio iaculis justo. Proin augue velit, mollis tempor quam at, porta laoreet libero. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Nunc dolor mi, malesuada vitae fermentum vitae, dapibus eu magna. Proin eu risus gravida, iaculis est id, auctor magna. Suspendisse feugiat, dui sed scelerisque efficitur, erat enim dignissim libero, ut molestie enim tellus nec magna. Morbi lectus odio, tempus quis sollicitudin id, maximus ultricies dui. Nulla felis ante, faucibus nec nibh et, tempus vulputate leo. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Interdum et malesuada fames ac ante ipsum primis in faucibus. In suscipit dolor tellus, sed ultrices diam sollicitudin vel. Ut viverra mauris ac suscipit tincidunt. Maecenas semper turpis ut commodo pharetra. Donec ac lectus a enim tempor interdum. Pellentesque leo sapien, accumsan in lorem quis, volutpat maximus ligula. Proin finibus quis nunc et euismod.";
	@Resource
	private GenusRepository genusRepository;
	
	/**
	 * I wrote this test because I wasn't sure whether @Lob would work.
	 */
	@Test
	public void shouldAllowHuuuugeDescription() {
		Genus testGenus = new Genus("test genus", STUPIDLY_LONG_DESCRIPTION);
		genusRepository.save(testGenus);
		
		int id = testGenus.getId();
		
		testGenus = genusRepository.findOne(id);
		assertEquals(STUPIDLY_LONG_DESCRIPTION, testGenus.getDescription());
	}
}
