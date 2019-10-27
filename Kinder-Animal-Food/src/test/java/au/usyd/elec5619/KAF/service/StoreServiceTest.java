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
import au.usyd.elec5619.KAF.model.Store;
import au.usyd.elec5619.KAF.service.StoreService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigWebContextLoader.class, classes = { DemoAppConfig.class })
@Transactional
@WebAppConfiguration
@Repository
@Rollback(true)
public class StoreServiceTest extends TestCase{
	@Autowired
	private StoreService storeservice;
	
@Test	
	public void testStoreInsert()
	{
		System.out.println("StoreInsert Service Test");
		
		Store store = new Store();
		store.setStore_id(199);
		store.setStore_address("4 Magdalene Terrace");
		store.setStore_name("Aldi");
		store.setStore_postcode("2205");
		store.setStore_latitude("100");
		store.setStore_longitude("111");
		store.setStore_state("NSW");
		assertTrue(storeservice.insertStore(store));
	}
@Test
	public void testSearchStore()
	{
		System.out.println("StoreSearch Service Test");
		Store teststore = storeservice.searchStore(1);
		System.out.println("store name:"+teststore.getStore_name()+
				"-----store address:"+teststore.getStore_address()+
				"-----store postcode:"+teststore.getStore_postcode()+
				"-----store state:"+teststore.getStore_state()+
				"-----store latitude:"+teststore.getStore_latitude()+
				"-----store longitude:"+teststore.getStore_longitude());
	}
}
