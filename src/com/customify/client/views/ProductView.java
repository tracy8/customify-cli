package com.customify.client.views;

import com.customify.client.services.ProductService;
import com.customify.server.models.ProductModel;
import com.customify.shared.requests_data_formats.ProductFormat;

import java.net.Socket;
import java.util.Date;
import java.util.Scanner;

public class ProductView {
    private Socket socket;

    public ProductView(Socket socket) {
        this.socket = socket;
    }

    public  void init(){
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("\t\tPRODUCT MANAGEMENT");
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("\t\t1.register product");
    }

    public void createProduct() throws Exception {
        Scanner scanner = new Scanner(System.in);
        ProductFormat newProduct = new ProductFormat();

        System.out.println("Enter product name:");
        newProduct.setName(scanner.nextLine());

        System.out.println("Enter business_id:");
        newProduct.setBusiness_id(Integer.parseInt(scanner.nextLine()));

        System.out.println("Enter product price:");
        newProduct.setPrice(Float.parseFloat(scanner.nextLine()));

        System.out.println("Enter quantity you have:");
        newProduct.setQuantity(Integer.parseInt(scanner.nextLine()));

        System.out.println("Enter product description:");
        newProduct.setDescription(scanner.nextLine());

        System.out.println("Enter points to bind with:");
        newProduct.setBondedPoints(Double.parseDouble(scanner.nextLine()));

        System.out.println("Who is registering this product?");
        newProduct.setRegistered_by(Integer.parseInt(scanner.nextLine()));

        newProduct.setCreatedAt("2021/02/04");
        ProductService productService = new ProductService(this.socket);
        productService.addNewProduct(newProduct);

        System.out.println("Product you registered has id of " + newProduct.getProductCode());


    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public void getAll() {
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("\t\tHere is a list of products registered so far");
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

    }
}
