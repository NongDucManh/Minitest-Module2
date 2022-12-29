package baitap2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class CategoryManager implements Serializable {
    private ArrayList<Category> listCategory;

    public CategoryManager() {
        listCategory = new ArrayList<Category>();
    }

    public Category creatCategory(Scanner scanner) {
        System.out.println("Enter information of Category: ");
        System.out.println("Enter id of Category: ");
        int id = -1;
        try {
            id = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        System.out.println("Enter name of Category: ");
        String name = scanner.nextLine();
        return new Category(id, name);
    }

    public void displayCategory() {
        if (!listCategory.isEmpty()) {
            for (int i = 0; i < listCategory.size(); i++) {
                System.out.println(listCategory.get(i));
            }
        } else {
            System.out.println("Not exist Category in list");
        }
    }

    public void addCategoryProduct(Scanner scanner) {
        listCategory.add(creatCategory(scanner));
        displayCategory();
    }

    public void addCategoryProduct(Category category) {
        listCategory.add(category);
        displayCategory();
    }

    public int searchIdCategory(Scanner scanner) {
        System.out.println("Enter id to search: ");
        int id = -1;
        int index = -1;
        boolean flag = false;
        try {
            id = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < listCategory.size(); i++) {
            if (id == listCategory.get(i).getId()) {
                flag = true;
                index = i;
            }
        }
        if (!flag) {
            System.out.println("Can't find the same id!");
            searchIdCategory(scanner);
        }
        return index;
    }

    public void deleteCategoryById(Scanner scanner) {
        int indexDelete = searchIdCategory(scanner);
        if (indexDelete != -1) {
            listCategory.remove(indexDelete);
            displayCategory();
        }
    }

    public void updateCategoryById(Scanner scanner) {
        int indexUpdate = searchIdCategory(scanner);
        if (indexUpdate != -1) {
            System.out.println("Enter information to update Category: ");
            for (int i = 0; i < listCategory.size(); i++) {
                if (i == indexUpdate) {
                    System.out.println("Enter new name of Category");
                    String name = scanner.nextLine();
                    listCategory.get(i).setName(name);
                }
            }
        }
        displayCategory();
    }

    public Category getCategoryById(int id) {
        return listCategory.get(id);
    }

    public Category find(int idOfCategory) {
        for (Category c : listCategory) {
            if(c.getId() == idOfCategory)
                return c;
        }
        return null;
    }
}
