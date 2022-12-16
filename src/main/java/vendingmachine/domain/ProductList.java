package vendingmachine.domain;

import vendingmachine.constant.ExceptionConstants;
import vendingmachine.constant.VendingMachineConstants;

import java.util.*;
import java.util.regex.Pattern;

public class ProductList {
    private final List<Product> productList = new ArrayList<>();
    private final Set<String> productNames = new HashSet<>();

    public ProductList(String productList) {
        List<String> splittedProductList = List.of(productList.split(VendingMachineConstants.PRODUCT_DELIMITER));
        validateDuplication(splittedProductList.size());

        Arrays.stream(productList.split(VendingMachineConstants.PRODUCT_DELIMITER))
                .peek(this::validateProduct)
                .forEach(this::addProduct);
    }

    private void validateDuplication(int size) {
        productNames.clear();
        productList.forEach(product -> productNames.add(product.getName()));

        if (productNames.size() != size) {
            throw new IllegalArgumentException(ExceptionConstants.DUPLICATED_PRODUCT_NAME.getMessage());
        }
    }

    private void validateProduct(String product) {
        if (!Pattern.matches(VendingMachineConstants.PRODUCT_REGEX, product)) {
            throw new IllegalArgumentException(ExceptionConstants.INCORRECT_PRODUCT_LIST.getMessage());
        }
    }

    private void addProduct(String product) {
        String parsedProduct = product.substring(1, product.length() - 1);
        List<String> productInfo = List.of(parsedProduct.split(VendingMachineConstants.DELIMITER));

        String name = productInfo.get(0);
        String price = productInfo.get(1);
        String amount = productInfo.get(2);

        productList.add(new Product(name, price, amount));
    }

    private void validateBuyingProduct(String productName) {
        if (!productNames.contains(productName)) {
            throw new IllegalArgumentException(ExceptionConstants.NO_SUCH_PRODUCT.getMessage());
        }
    }
}
