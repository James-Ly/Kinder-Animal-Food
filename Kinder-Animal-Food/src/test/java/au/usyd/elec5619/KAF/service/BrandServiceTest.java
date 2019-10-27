package au.usyd.elec5619.KAF.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import junit.framework.TestCase;
import au.usyd.elec5619.KAF.config.DemoAppConfig;
import au.usyd.elec5619.KAF.model.Brand;
import au.usyd.elec5619.KAF.service.BrandService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigWebContextLoader.class, classes = { DemoAppConfig.class })
@Transactional
@WebAppConfiguration
@Repository
@Rollback(true)
public class BrandServiceTest extends TestCase{
	@Autowired
	private BrandService brandservice;
	
@Test	
	public void testBrandInsert()
	{
		System.out.println("BrandInsert Service Test");
		
		Brand brand = new Brand();
		brand.setBrand_id(199);
		brand.setBrand_category("egg");
		brand.setBrand_name("Sydney egg");
		brand.setImage("https://cdn.vox-cdn.com/thumbor/VD8JI2zquKWDCQuPxlc6Rnh"
				+ "w-1I=/0x0:876x584/1200x800/filters:focal(368x222:508x362)/cdn"
				+ ".vox-cdn.com/uploads/chorus_image/image/62841097/instagram_egg.0.jpg");
		assertTrue(brandservice.insertBrand(brand));
	}
@Test
	public void testSearchBrand()
	{
		System.out.println("BrandSearch Service Test");
		Brand testbrand = brandservice.searchBrand(1);
		System.out.println("brand name:"+testbrand.getBrand_name()+"-----brand category:"+testbrand.getBrand_category()+
				"----brand image:"+testbrand.getImage());
	}
}

