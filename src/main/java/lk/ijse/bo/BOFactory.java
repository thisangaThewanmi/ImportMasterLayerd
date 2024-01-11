package lk.ijse.bo;

import lk.ijse.entity.Engineer;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {
    }

    public static BOFactory getBoFactory() {
        return (boFactory == null) ? boFactory = new BOFactory() : boFactory;
    }
    public enum BOTypes {
        CUSTOMER, EMPLOYEE, SUPPLIER, MACHINE, STOCK, PLACEORDER, ORDER, PLACE_ENGINEER_STOCK, PLACEGRN, PLACE_MRN, PLACE_MACHINE_INSTALLATION, ENGINNER
    }

    public SuperBO getBO (BOTypes boTypes){
        switch (boTypes){
            case CUSTOMER:
                return new CustomerBOImpl();
            case EMPLOYEE:
                return new EmployeeBOImpl();
            case ENGINNER:
                return new EnginnerBOImpl();
            case SUPPLIER:
                return new SupplierBOImpl();
            case MACHINE:
                return new MachineBoImpl();

            case STOCK:
                return new StockBOImpl();

            case PLACEORDER:
                return new PlaceOrderBoImpl();

            case PLACE_ENGINEER_STOCK:
                return new PlaceEngineerStockBOImpl();

            case PLACEGRN:
                return  new PlaceGRNBoImpl();

            case PLACE_MRN:
                return new PlaceMRNBoImpl();

            case PLACE_MACHINE_INSTALLATION:
                return new PlaceMachineInstallionBOImpl();

            default:
                return null;
        }
    }
}

