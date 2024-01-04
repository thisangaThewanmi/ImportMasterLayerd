package lk.ijse.dao;

import lk.ijse.bo.CustomerBOImpl;
import lk.ijse.bo.SuperBO;

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
        CUSTOMER, EMPLOYEE, ENGINNER
    }

    public SuperDao getDAO(DAOTypes daoTypes) {
        switch (daoTypes) {
            case CUSTOMER:
                return new CustomerDaoImpl();
            case EMPLOYEE:
                return new EmployeeDaoImpl();
            case ENGINNER:
                return new EngineerDaoImpl();
            /*case ORDER_DETAIL:
                return new OrderDetailDAOImpl();
            case QUERY:
                return new QueryDAOImpl();*/
            default:
                return null;
        }
    }
}

