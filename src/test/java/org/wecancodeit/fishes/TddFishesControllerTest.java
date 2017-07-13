package org.wecancodeit.fishes;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

public class TddFishesControllerTest {

	@InjectMocks
	private TddFishesController underTest;
	
	@Mock
	private Iterable<Fish> allTheFishes;
	
	@Mock
	private Model model;
	
	@Mock
	private FishRepository fishRepo;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void shouldShowAllFishes() {
		
		when(fishRepo.findAll()).thenReturn(allTheFishes);
		
		underTest.showAll(model);
		
		verify(model).addAttribute("allFishes", allTheFishes);
	}
	
	@Test
	public void shouldShowOneFish() {
		
		long arbitraryId = 42;
		
		Fish oneFish = mock(Fish.class, "selected fish");
		when(fishRepo.findOne(arbitraryId)).thenReturn(oneFish);
		
		underTest.showOne(arbitraryId, model);
		
		verify(model).addAttribute(oneFish); // will be called "fish"
	}
	
	@Test
	public void shouldReturnAllFishesTemplate() {
		
		String result = underTest.showAll(model);
		
		assertThat(result, is("allFishesTemplate"));
	}

}
