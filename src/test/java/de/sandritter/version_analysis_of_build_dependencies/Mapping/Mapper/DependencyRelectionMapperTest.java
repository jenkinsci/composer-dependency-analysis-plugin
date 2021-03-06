package de.sandritter.version_analysis_of_build_dependencies.Mapping.Mapper;

import static org.junit.Assert.*;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import de.sandritter.version_analysis_of_build_dependencies.Domain.Model.DependencyReflection.DependencyReflectionCollection;
import de.sandritter.version_analysis_of_build_dependencies.Domain.Model.DependencyReflection.JsonDataImage;
import de.sandritter.version_analysis_of_build_dependencies.Mapping.Exception.DataMappingFailedException;
import de.sandritter.version_analysis_of_build_dependencies.Mapping.Mapper.DependencyReflectionMapper;

public class DependencyRelectionMapperTest{
	
	private File composerJson;
	private File composerLock;
	private File composerLockExtended;
	private DependencyReflectionMapper mapper;
	private final int amountPackages = 23;
	private final int amountDirectRequirements = 5;
	
	@Before
	public void setUp() throws MalformedURLException, URISyntaxException{
		ClassLoader classLoader = getClass().getClassLoader();
		composerJson = new File(classLoader.getResource("composer.json").getFile());
		composerLock = new File(classLoader.getResource("composer.lock").getFile());
		composerLockExtended = new File(classLoader.getResource("composer/composer.lock").getFile());
	
		mapper = new DependencyReflectionMapper();		
	}
	
	@Test
	public void testComposerJsonMapping() throws DataMappingFailedException{
		JsonDataImage json = mapper.mapFileToJsonDataImage(composerJson);
		assertEquals(JsonDataImage.class, json.getClass());
	}
	
	@Test
	public void testJsonDataImageRequirements() throws DataMappingFailedException{
		JsonDataImage json = mapper.mapFileToJsonDataImage(composerJson);
		List<String> lst = new ArrayList<String>();
		lst.add("aoe/eft-core");
		lst.add("aoe/eft");
		lst.add("aoe/restler");
		lst.add("luracast/restler");
		lst.add("typo3/cms-core");
		Map<String, String> map = json.getRequirements();
		for (String s : lst){			
			assertTrue(map.containsKey(s));
		}
	}
	
	@Test
	public void testJsonDataImageName() throws DataMappingFailedException{
		JsonDataImage json = mapper.mapFileToJsonDataImage(composerJson);
		assertEquals("aoe/eft-rest-api", json.getName());
	}
	
	@Test
	public void testAmountRequirements() throws DataMappingFailedException{
		JsonDataImage json = mapper.mapFileToJsonDataImage(composerJson);
		assertEquals(amountDirectRequirements, json.getRequirements().size());
	}
	
	@Test
	public void testJsonDataImageType() throws DataMappingFailedException{
		JsonDataImage json = mapper.mapFileToJsonDataImage(composerJson);
		assertEquals(json.getType(), "typo3-cms-extension");
	}
	
	@Test
	public void testComposerLockMapping() throws DataMappingFailedException{
		DependencyReflectionCollection lock = mapper.mapFileToDependencyReflectionData(composerLock);
		assertEquals(DependencyReflectionCollection.class, lock.getClass());
	}
	
	@Test
	public void testAmountPackages() throws DataMappingFailedException{
		DependencyReflectionCollection lock = mapper.mapFileToDependencyReflectionData(composerLock);
		assertEquals(amountPackages, lock.getPackages().size());
	}
	
	@Test(expected=DataMappingFailedException.class) 
	public void testComposerJsonMappingException() throws DataMappingFailedException{
		File json = new File("composer-fail.json");
		mapper.mapFileToJsonDataImage(json);
	}
	
	@Test(expected=DataMappingFailedException.class) 
	public void testComposerLockMappingException()throws DataMappingFailedException{
		File lock = new File("composer-fail.lock");
		mapper.mapFileToDependencyReflectionData(lock);
	}
	
	@Test
	public void shouldMapExtendedComposerLockFile() 
	{
		DependencyReflectionCollection lock;
		try {
			lock = (DependencyReflectionCollection) mapper.mapData(composerLockExtended, DependencyReflectionCollection.class);
			assertEquals(33, lock.getPackages().size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
