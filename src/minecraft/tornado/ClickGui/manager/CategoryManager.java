package tornado.ClickGui.manager;

import tornado.ClickGui.ClickGui;
import tornado.ClickGui.comp.ClickGuiCategoryButton;
import tornado.Tornado;

import java.util.ArrayList;



public class CategoryManager {
	
	public static int currentPage = 0;
	public static boolean openDragScreen = false;
	
	public static void thisPage(int number) {
		currentPage=number;
		ArrayList<ClickGuiCategoryButton> category = Tornado.instance.clickGui.getClickGuiCategoryButton();
		
		for(int i = 0; i< category.size();i++) {
			if(i != currentPage) {
				category.get(i).setIsOnThisPage(false);
			}
		}
		
		if(currentPage == 4) {
			currentPage = 0;
			openDragScreen = true;
		}
	}
}
