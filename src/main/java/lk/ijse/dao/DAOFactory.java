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
        CUSTOMER, EMPLOYEE, ENGINEER
    }

    public SuperDao getDAO(DAOTypes daoTypes) {
        switch (daoTypes) {
            case CUSTOMER:
                return new CustomerDaoImpl();
            /*case ITEM:
                return new ItemDAOImpl();
            case ORDER:
                return new OrderDAOImpl();
            case ORDER_DETAIL:
                return new OrderDetailDAOImpl();
            case QUERY:
                return new QueryDAOImpl();*/
            default:
                return null;
        }
    }
}

