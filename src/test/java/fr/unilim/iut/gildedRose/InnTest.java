package fr.unilim.iut.gildedRose;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;


public class InnTest {
	
  @Test
  public void should_list_items() {
	  Inn inn = new Inn();
	  assertThat(inn.getItems()).extracting("name").contains("+5 Dexterity Vest","Aged Brie","Elixir of the Mongoose",
			             "Sulfuras, Hand of Ragnaros","Backstage passes to a TAFKAL80ETC concert","Conjured Mana Cake");
	  
	  assertThat(inn.getItems()).extracting("quality").contains(20,0,7,80,20,6);
	  
	  assertThat(inn.getItems()).extracting("sellIn").contains(10,2,5,0,15,3);
  }
  
  @Test
  public void should_update_quality(){
	  Inn inn = new Inn();
	  inn.updateQuality();
	  inn.updateQuality();
	  
	  assertThat(inn.getItems()).extracting("quality").contains(18,2,5,80,22,4);
	  
	  assertThat(inn.getItems()).extracting("sellIn").contains(8,0,3,0,13,1);
	  
  }
  
  @Test
  public void should_update_quality_100days(){
	  Inn inn = new Inn();
	  for(int day=0; day<100; day++){
		  inn.updateQuality();
	  }
	  
	  assertThat(inn.getItems()).extracting("quality").contains(0,50,0,80,0,0);
	  
	  assertThat(inn.getItems()).extracting("sellIn").contains(-90,-98,-95,0,-85,-97);
	  
  }
  @Test
	public void should_test_against_legacy_code() {
		LegacyInn legacyInn = new LegacyInn();
		Inn inn = new Inn();
		
		for (int day = 0; day < 1000; day++) {
			List<Item> items = inn.getItems();
			List<Item> legacyItems = legacyInn.getItems();
			
			assertThat(items).hasSize(legacyItems.size());

			for (int i = 0; i < legacyItems.size(); i++) {
				Item item = items.get(i);
				Item legacyItem = legacyItems.get(i);
				
				assertThat(item.getName()).isEqualTo(legacyItem.getName());
				assertThat(item.getQuality()).isEqualTo(legacyItem.getQuality());
				assertThat(item.getSellIn()).isEqualTo(legacyItem.getSellIn());
			}

			inn.updateQuality();
			legacyInn.updateQuality();
		}
}
  /*
  @Test
  public void qualiteBaisseDe1QuandUpdate() {
	  
	  Item item = new Item("test", 1, 60);
	  Inn inn = new Inn();
	  inn.getItems().add(item);
	  
	  inn.updateQuality();
	
	  
	  assertEquals(59, inn.getItems().get(6).getQuality());
  }
  
  @Test
  public void qualiteNeBaissePasQuandQualiteAZero() {
	  
	  Item item = new Item("test", 1, 0);
	  Inn inn = new Inn();
	  inn.getItems().add(item);
	  
	  inn.updateQuality();
	
	  
	  assertEquals(0, inn.getItems().get(6).getQuality());
  }
  
  @Test
  public void qualiteAgedOfBrieNaugmentePlusA50() {
	  
	  Item item = new Item("Aged Brie", 2, 50);
	  Inn inn = new Inn();
	  inn.getItems().add(item);
	  
	  inn.updateQuality();
	
	  
	  assertEquals(50, inn.getItems().get(6).getQuality());
  }*/
}