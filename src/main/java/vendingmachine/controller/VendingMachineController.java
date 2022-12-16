package vendingmachine.controller;

import vendingmachine.domain.VendingMachine;
import vendingmachine.view.InputView;
import vendingmachine.view.OutputView;

public class VendingMachineController {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private final VendingMachine vendingMachine = new VendingMachine();

    public void run() {
        initCoin();
        initProduct();
        insertMoney();
    }

    private void initCoin() {
        String holdingSum = inputView.readHoldingSum();
        generateCoins(holdingSum);
        outputView.printCoinQuantityList(vendingMachine.getCoinQuantities());
    }

    private void generateCoins(String holdingSum) {
        while (true) {
            try {
                vendingMachine.generateCoins(holdingSum);
                return;
            } catch (IllegalArgumentException e) {
                outputView.printException(e);
            }
        }
    }

    private void initProduct() {
        String productList = inputView.readProductList();
        while (true) {
            try {
                vendingMachine.addProducts(productList);
                return;
            } catch (IllegalArgumentException e) {
                outputView.printException(e);
            }
        }
    }

    private void insertMoney() {
        String insertedMoney = inputView.readInsertedMoney();
        while (true) {
            try {
                vendingMachine.insertMoney(insertedMoney);
                return;
            } catch (IllegalArgumentException e) {
                outputView.printException(e);
            }
        }
    }
}
