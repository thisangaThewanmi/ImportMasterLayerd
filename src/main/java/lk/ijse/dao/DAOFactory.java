package lk.ijse.dao;

import lk.ijse.dao.custom.Impl.*;
import lk.ijse.dao.custom.OrderDao;

public class DAOFactory {

    public static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getDaoFactory() {
        if (daoFactory == null) {
            return new DAOFactory();
        }
        return daoFactory;
    }

    public enum DAOTypes {
        CUSTOMER, EMPLOYEE, SUPPLIER, MACHINE, STOCK,  ORDER, ORDERDETAIL, ENGINNERSTOCK, ENGINNER_STOCK_DETAILS, GRN, GRN_DETAIL, MRNDETAILS, MRN, MACHINE_INSTALLATION, ENGINNER
    }

    public SuperDao getDAO(DAOTypes daoTypes) {
        switch (daoTypes) {
            case CUSTOMER:
                return new CustomerDaoImpl();
            case EMPLOYEE:
                return new EmployeeDaoImpl();
            case ENGINNER:
                return new EngineerDaoImpl();
            case SUPPLIER:
                return new SupplierDaoImpl();
            case MACHINE:
                return new MachineDaoImpl();
            case STOCK:
                return new StockDaoImpl();
            case ORDER:
                return new OrderDAOImpl();

            case ORDERDETAIL:
                return new OrderDetailDaoImpl();

            case ENGINNERSTOCK:
                return  new EngineerStockDaoImpl();
            case ENGINNER_STOCK_DETAILS:
                return new EngineerStockDetailsDaoImpl();
            case GRN:
                return new GRNDaoImpl();
                case GRN_DETAIL:
                    return new GRNDetailDaoImpl();
                    case MRNDETAILS:
                        return new MrnDetailDaoImpl();
                        case MRN:
                            return new MrnDaoImpl();
                            case MACHINE_INSTALLATION:
                                return new MachineInstallationDaoImpl();
            default:
                return null;
        }
    }
}

