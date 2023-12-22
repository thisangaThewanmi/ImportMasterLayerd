package lk.ijse.model;

import lk.ijse.db.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ESINModel {

    private static final String PREFIX = "ESI-";
    private static final int NUMBER_WIDTH = 4;
    //private static final AtomicInteger counter = new AtomicInteger(1);

    public static String generateNewId() {
        // Format the number with leading zeros
        int value = getMaxNumericValue();
        String formattedNumber = String.format("%0" + NUMBER_WIDTH + "d",++value);

        // Combine the prefix and formatted number
        return PREFIX + formattedNumber;
    }


    private static int getMaxNumericValue() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        int maxNumericValue = 0;

        try {
            // Obtain a connection
            connection = DbConnection.getInstance().getConnection();

            // Prepare and execute the query
            preparedStatement = connection.prepareStatement(
                    "SELECT MAX(CAST(SUBSTRING(ESIN_No, 5) AS SIGNED)) AS max_numeric_value FROM engineerstock");
            resultSet = preparedStatement.executeQuery();

            // Retrieve the result
            if (resultSet.next()) {
                maxNumericValue = resultSet.getInt("max_numeric_value");
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        } finally {
            // Close resources in the reverse order of their creation
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                // Note: The connection is not closed here since you want to keep it open
            } catch (SQLException e) {
                e.printStackTrace(); // Handle the exception appropriately
            }
        }

        return maxNumericValue;
    }

}
