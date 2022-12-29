package baitap2;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CategoryManager categoryManager = new CategoryManager();
        ProductManager productManager = new ProductManager(categoryManager);
        menuProductManager(categoryManager, productManager, scanner);
    }
    public static void menuProductManager (CategoryManager categoryManager, ProductManager productManager, Scanner scanner) {
        do {
            System.out.println("MENU");
            System.out.println("1. Add product");
            System.out.println("2. Delete product by id");
            System.out.println("3. Display list of product");
            System.out.println("4. Display product by max price");
            System.out.println("5. Display product by min price");
            System.out.println("6. Display product by max quantity");
            System.out.println("7. Display product by min quantity");
            System.out.println("8. Update product by id");
            System.out.println("9. Search product by name");
            System.out.println("10. Search product by price");
            System.out.println("11. Search product by category");
            System.out.println("12. Add Candy");
            System.out.println("13. Display product is Candy");
            System.out.println("14. Display Candy by max weight");
            System.out.println("15. Add Drinks");
            System.out.println("16. Display product is Drinks");
            System.out.println("17. Display Drinks by bottleType");
            System.out.println("18. Display Drinks by bottleType and price");
            System.out.println("19. Enter to menu of category");
            System.out.println("20. Write To File");
            System.out.println("21. Read Data from File");
            System.out.println("0. Exit");
            int choice = -1;
            System.out.println("Enter your choice");
            try {
                choice = Integer.parseInt(scanner.nextLine());
            }
            catch (NumberFormatException e) {
                e.printStackTrace();
            }
            switch (choice) {
                case 1:
                    productManager.addProduct(scanner);
                    break;
                case 2:
                    productManager.deleteById(scanner);
                    break;
                case 3:
                    productManager.displayProductManager();
                    break;
                case 4:
                    productManager.displayProductByMaxPrice();
                    break;
                case 5:
                    productManager.displayProductByMinPrice();
                    break;
                case 6:
                    productManager.displayProductByQuantityMax();
                    break;
                case 7:
                    productManager.displayProductByQuantityMin();
                    break;
                case 8:
                    productManager.updateProductById(scanner);
                    break;
                case 9:
                    productManager.searchProductByName(scanner);
                    break;
                case 10:
                    productManager.searchProductByPrice(scanner);
                    break;
                case 11:
                    productManager.searchProductByCategory(scanner);
                    break;
                case 12:
                    productManager.addCandyToProductManager(scanner);
                    break;
                case 13:
                    productManager.displayProductIsCandy();
                    break;
                case 14:
                    productManager.displayCandyByMaxWeight();
                    break;
                case 15:
                    productManager.addDrink(scanner);
                    break;
                case 16:
                    productManager.displayDrinks();
                    break;
                case 17:
                    productManager.displayDrinksByBottleType(scanner);
                    break;
                case 18:
                    productManager.displayDrinkByBootleAndPrice();
                    break;
                case 19:
                    menuOfCategory(categoryManager, productManager, scanner);
                    break;
                case 20:
                    productManager.writeToFile("C:\\Users\\Admin\\IdeaProjects\\Minitest-Module2\\src\\baitap2\\file");
                    break;
                case 21:
                   ArrayList<ProductManager> read = productManager.readToFile("C:\\Users\\Admin\\IdeaProjects\\Minitest-Module2\\src\\baitap2\\file");
                    for (int i = 0; i < read.size(); i++) {
                        System.out.println(read.get(i));
                    }
                    break;
                case 0:
                    System.exit(0);
                default:
                    System.out.println("Enter choice: 0~21");
            }
        }
        while(true);
    }

    public static void menuOfCategory(CategoryManager categoryManager, ProductManager productManager, Scanner scanner) {
        do {
            System.out.println("Menu of Category");
            System.out.println("1. Add category of product");
            System.out.println("2. Delete category by id");
            System.out.println("3. Display category");
            System.out.println("4. Update category by id");
            System.out.println("0. Return to menu of product manager");
            int choiceMenuCategory = -1;
            System.out.println("Enter your choice: ");
            try {
                choiceMenuCategory = Integer.parseInt(scanner.nextLine());
            }
            catch (NumberFormatException e) {
                e.printStackTrace();
            }
            switch (choiceMenuCategory) {
                case 1:
                    categoryManager.addCategoryProduct(scanner);
                    break;
                case 2:
                    categoryManager.deleteCategoryById(scanner);
                    break;
                case 3:
                    categoryManager.displayCategory();
                    break;
                case 4:
                    categoryManager.updateCategoryById(scanner);
                    break;
                case 0:
                    menuProductManager(categoryManager,productManager, scanner);
                    break;
                default:
                    System.out.println("Enter choice: 0~4");
            }
        }
        while (true);
    }
}

