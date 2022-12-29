package baitap2;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ProductManager implements Serializable {
    private ArrayList<Product> productManager;
    private CategoryManager categoryManager;
    public ProductManager(CategoryManager categoryManager) {
        this.productManager = new ArrayList<>();
        this.categoryManager = categoryManager;
    }
    public void displayProductManager() {
        if (!productManager.isEmpty()) {
            for (int i = 0; i < productManager.size(); i++) {
                System.out.println(productManager.get(i));
            }
        } else {
            System.out.println("Not exist Product in list");
        }
    }
    public Product creatProduct(Scanner scanner) {
        System.out.println("Creat new product: ");
        int id = -1;
        String name = null;
        int price = -1;
        int quantity = -1;
        Category category = null;
        try {
            System.out.println("Enter id of product: ");
            id = Integer.parseInt(scanner.nextLine());
            System.out.println("Enter name of product: ");
            name = scanner.nextLine();
            System.out.println("Enter price of product: ");
            price = Integer.parseInt(scanner.nextLine());
            System.out.println("Enter quantity of product: ");
            quantity = Integer.parseInt(scanner.nextLine());
            System.out.println("Choose category of product: ");
            category = choiceCategory(scanner);
        }
        catch (IndexOutOfBoundsException|NumberFormatException e) {
            e.printStackTrace();
            creatProduct(scanner);
        }
        return new Product(id, name, price, quantity, category);
    }
    public Category choiceCategory(Scanner scanner) {
        Category category;
        System.out.println("Enter choice category by Id: ");
        int idOfCategory = -1;
        try {
            idOfCategory = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        if (idOfCategory != -1) {
            category = this.categoryManager.find(idOfCategory);
        } else {
            category = this.categoryManager.creatCategory(scanner);
            categoryManager.addCategoryProduct(scanner);
        }
        if (category != null) {
            return category;
        } else {
            return choiceCategory(scanner);
        }
    }
    public void addProduct(Scanner scanner) {
        productManager.add(creatProduct(scanner));
    }
    public int searchIdOfProduct(Scanner scanner) {
        System.out.println("Enter id to search:");
        int index = -1;
        int idToSearch = -1;
        try {
            idToSearch = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        boolean flag = false;
        for (int i = 0; i < productManager.size(); i++) {
            if (productManager.get(i).getId() == idToSearch) {
                index = i;
                flag = true;
            }
        }
        if (!flag) {
            System.out.println("Can't find the same id");
            searchIdOfProduct(scanner);
        }
        return index;
    }
    public void deleteById(Scanner scanner) {
        int indexToDelete = searchIdOfProduct(scanner);
        if (indexToDelete != -1) {
            productManager.remove(indexToDelete);
            System.out.println("List after delete success");
            displayProductManager();
        }
    }
    public void updateProductById(Scanner scanner) {
        int indexToRewrite = searchIdOfProduct(scanner);
        if (indexToRewrite != -1) {
            System.out.println("Enter information to rewrite product ");
            for (int i = 0; i < productManager.size(); i++) {
                if (i == indexToRewrite) {
                    try {
                        System.out.println("Enter new id of product: ");
                        int id = Integer.parseInt(scanner.nextLine());
                        productManager.get(i).setId(id);
                        System.out.println("Enter new name of product: ");
                        String name = scanner.nextLine();
                        productManager.get(i).setName(name);
                        System.out.println("Enter new price of product: ");
                        int price = Integer.parseInt(scanner.nextLine());
                        productManager.get(i).setPrice(price);
                        System.out.println("Enter quantity of product: ");
                        int quantity = Integer.parseInt(scanner.nextLine());
                        productManager.get(i).setQuantity(quantity);
                        System.out.println("Choose category of product: ");
                        Category category = choiceCategory(scanner);
                        productManager.get(i).setCategory(category);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }
            }
            displayProductManager();
        }
    }
    public void displayProductByMaxPrice() {
        int indexMax = 0;
        System.out.println("Product have max Price is: ");
        for (int i = 0; i < productManager.size(); i++) {
            if (productManager.get(i).getPrice() > productManager.get(indexMax).getPrice()) {
                indexMax = i;
            }
        }
        System.out.println(productManager.get(indexMax));
    }
    public void displayProductByMinPrice() {
        int indexMin = 0;
        for (int i = 0; i < productManager.size(); i++) {
            if (productManager.get(i).getPrice() < productManager.get(indexMin).getPrice()) {
                indexMin = i;
            }
        }
        System.out.println("Product have min Price is: ");
        System.out.println(productManager.get(indexMin));
    }
    public void displayProductByQuantityMax() {
        int indexQuantityMax = 0;
        for (int i = 0; i < productManager.size(); i++) {
            if (productManager.get(i).getQuantity() > productManager.get(indexQuantityMax).getQuantity()) {
                indexQuantityMax = i;
            }
        }
        System.out.println("Product have Max Quantity is: ");
        System.out.println(productManager.get(indexQuantityMax));
    }
    public void displayProductByQuantityMin() {
        int indexQuantityMin = 0;
        for (int i = 0; i < productManager.size(); i++) {
            if (productManager.get(i).getQuantity() < productManager.get(indexQuantityMin).getQuantity()) {
                indexQuantityMin = i;
            }
        }
        System.out.println("Product have Min Quantity is: ");
        System.out.println(productManager.get(indexQuantityMin));
    }
    public void searchProductByName(Scanner scanner) {
        System.out.println("Enter name of Product to search");
        String nameToSearch = scanner.nextLine();
        System.out.println("Product have the same name is: ");
        if (!productManager.isEmpty()) {
            boolean flag = false;
            for (int i = 0; i < productManager.size(); i++) {
                if (productManager.get(i).getName().toLowerCase().equals(nameToSearch.toLowerCase())) {
                    System.out.println(productManager.get(i));
                    flag = true;
                }
            }
            if (!flag) {
                System.out.println("Can't find the same name");
            }
        } else {
            System.out.println("Search fail because 'Not exist Category in list'!");
        }
    }
    public void searchProductByPrice(Scanner scanner) {
        System.out.println("Enter price to search");
        try {
            System.out.println("Enter price upper");
            int priceUpper = Integer.parseInt(scanner.nextLine());
            System.out.println("Enter price lower");
            int priceLower = Integer.parseInt(scanner.nextLine());
            boolean flag = false;
            if (!productManager.isEmpty()) {
                for (int i = 0; i < productManager.size(); i++) {
                    if (productManager.get(i).getPrice() >= priceLower && productManager.get(i).getPrice() <= priceUpper) {
                        flag = true;
                        System.out.println(productManager.get(i));
                    }
                }
                if (!flag) {
                    System.out.println("No products found for this price");
                }
            }
            else {
                System.out.println("Search fail because 'Not exist Category in list");
            }
        }
        catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
    public void searchProductByCategory(Scanner scanner) {
        System.out.println("Enter category to search: ");
        Category category = choiceCategory(scanner);
        boolean flag = false;
        if (!productManager.isEmpty()) {
            for (int i = 0; i < productManager.size(); i++) {
                if (productManager.get(i).getCategory().equals(category)) {
                    flag = true;
                    System.out.println(productManager.get(i));
                }
            }
            if (!flag) {
                System.out.println("No products found for this category");
            }
        }
        else {
            System.out.println("Search fail because 'Not exist Category in list");
        }

    }
    public Candy creatCandy(Scanner scanner) {
        System.out.println("Creat new product Candy: ");
        int id = -1;
        String name = null;
        int price = -1;
        int quantity = -1;
        Category category = null;
        int weight = -1;
        try {
            System.out.println("Enter id of product Candy: ");
            id = Integer.parseInt(scanner.nextLine());
            System.out.println("Enter name of product Candy: ");
            name = scanner.nextLine();
            System.out.println("Enter price of product Candy: ");
            price = Integer.parseInt(scanner.nextLine());
            System.out.println("Enter quantity of product Candy: ");
            quantity = Integer.parseInt(scanner.nextLine());
            System.out.println("Choose category of product Candy: ");
            category = choiceCategory(scanner);
            System.out.println("Enter weight of product Candy: ");
            weight = Integer.parseInt(scanner.nextLine());
        }
        catch (IndexOutOfBoundsException|NumberFormatException e) {
            e.printStackTrace();
        }
        return new Candy(id, name, price, quantity, category, weight);
    }
    public void addCandyToProductManager(Scanner scanner) {
        productManager.add((Product) creatCandy(scanner));
        displayProductManager();
    }
    public void displayProductIsCandy() {
        for (int i = 0; i < productManager.size(); i++) {
            if (productManager.get(i) instanceof Candy) {
                System.out.println(productManager.get(i));
            }
        }
    }

    public void displayCandyByMaxWeight() {
        int indexWeightMax = 0;
        int weightMax = 0;
        for (int i = 0; i < productManager.size(); i++) {
            if (productManager.get(i) instanceof Candy) {
                if (((Candy) productManager.get(i)).getWeight() > weightMax) {
                    indexWeightMax = i;
                    weightMax = ((Candy) productManager.get(i)).getWeight();
                }
            }
        }
        System.out.println(productManager.get(indexWeightMax));
    }

    public Drinks creatDrinks (Scanner scanner) {
        System.out.println("Creat new product Drinks: ");
        int id = -1;
        String name = null;
        int price = -1;
        int quantity = -1;
        Category category = null;
        int volume = -1;
        String bootleType = null;
        try {
            System.out.println("Enter id of product Drinks: ");
            id = Integer.parseInt(scanner.nextLine());
            System.out.println("Enter name of product Drinks: ");
            name = scanner.nextLine();
            System.out.println("Enter price of product Drinks: ");
            price = Integer.parseInt(scanner.nextLine());
            System.out.println("Enter quantity of product Drinks: ");
            quantity = Integer.parseInt(scanner.nextLine());
            System.out.println("Choose category of product Drinks: ");
            category = choiceCategory(scanner);
            System.out.println("Enter volume of product Drinks: ");
            volume = Integer.parseInt(scanner.nextLine());
            System.out.println("Enter bottleType of product Drinks ");
            bootleType = scanner.nextLine();
        }
        catch (IndexOutOfBoundsException|NumberFormatException e) {
            e.printStackTrace();
        }
        return new Drinks(id, name, price, quantity, category, volume, bootleType);
    }

    public void addDrink(Scanner scanner) {
        productManager.add((Product)creatDrinks(scanner));
        displayProductManager();
    }

    public void displayDrinks() {
        for (int i = 0; i < productManager.size(); i++) {
            if (productManager.get(i) instanceof Drinks) {
                System.out.println(productManager.get(i));
            }
        }
    }
    public void displayDrinksByBottleType(Scanner scanner){
        System.out.println("Enter bottleType to display");
        String bottleTypeToDisplay = scanner.nextLine();
        boolean flag = false;
        for (int i = 0; i < productManager.size(); i++) {
            if (productManager.get(i) instanceof Drinks) {
                if (((Drinks) productManager.get(i)).getBottleType().equals(bottleTypeToDisplay)) {
                    System.out.println(productManager.get(i));
                    flag = true;
                }
            }
        }
        if (!flag) {
            System.out.println("No products found for this bottleType");
        }
    }
    public void displayDrinkByBootleAndPrice () {
        int indexPriceMax = 0;
        for (int i = 0; i < productManager.size(); i++) {
            if (productManager.get(i) instanceof Drinks) {
                if (((Drinks) productManager.get(i)).getBottleType().toLowerCase().equals("Glass".toLowerCase())) {
                    if (productManager.get(i).getPrice() > productManager.get(indexPriceMax).getPrice()) {
                        indexPriceMax = i;
                        System.out.println(productManager.get(indexPriceMax));
                    }
                }
            }
        }
    }
    public void writeToFile(String nameFile) {
        try {
            FileOutputStream out = new FileOutputStream(nameFile);
            ObjectOutputStream outputStream = new ObjectOutputStream(out);
            outputStream.writeObject(productManager);
            outputStream.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<ProductManager> readToFile(String nameFile) {
        ArrayList<ProductManager> read = new ArrayList<>();
        try {
            FileInputStream input = new FileInputStream(nameFile);
            ObjectInputStream inputStream = new ObjectInputStream(input);
            read = (ArrayList<ProductManager>) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return read;
    }
}
