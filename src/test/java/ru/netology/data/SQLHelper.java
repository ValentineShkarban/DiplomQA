package ru.netology.data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLHelper {
    private static final QueryRunner QUERY_RUNNER = new QueryRunner();

    private SQLHelper() {
    }

    private static final String url = System.getProperty("db.url");
    private static final String user = System.getProperty("db.user");
    private static final String password = System.getProperty("db.password");

    private static Connection getConn() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    @SneakyThrows
    public static CreditRequestEntity getCreditRequest() {
        var creditRequest = "SELECT * FROM credit_request_entity WHERE created = (SELECT MAX(created) FROM credit_request_entity);";

        try (var connection = getConn()) {
            return QUERY_RUNNER.query(connection, creditRequest, new BeanHandler<>(CreditRequestEntity.class));
        }
    }

    @SneakyThrows
    public static OrderEntity getOrder() {
        var order = "SELECT * FROM order_entity WHERE created = (SELECT MAX(created) FROM order_entity);";

        try (var connection = getConn()) {
            return QUERY_RUNNER.query(connection, order, new BeanHandler<>(OrderEntity.class));
        }
    }

    @SneakyThrows
    public static PaymentEntity getPayment() {
        var payment = "SELECT * FROM payment_entity WHERE created = (SELECT MAX(created) FROM payment_entity);";

        try (var connection = getConn()) {
            return QUERY_RUNNER.query(connection, payment, new BeanHandler<>(PaymentEntity.class));
        }
    }

    @SneakyThrows
    public static void cleanDatabase() {
        try (var connection = getConn()) {
            QUERY_RUNNER.execute(connection, "DELETE FROM credit_request_entity");
            QUERY_RUNNER.execute(connection, "DELETE FROM order_entity");
            QUERY_RUNNER.execute(connection, "DELETE FROM payment_entity");
        }
    }
}