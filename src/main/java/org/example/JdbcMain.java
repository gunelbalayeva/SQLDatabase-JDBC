package org.example;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class JdbcMain {
    public static void main(String[] args) {

        String jdbcDriver = "oracle.jdbc.driver.OracleDriver";
        String jdbcurl = "jdbc:oracle:thin:@localhost:1521/XEPDB1";
        String username = "hr";
        String pasword = "hr";

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;


        try {
            // loading the driver
            Class.forName(jdbcDriver);
            // join the base
            connection = DriverManager.getConnection(jdbcurl, username, pasword);
            connection.setAutoCommit(false);

            Scanner sc = new Scanner(System.in);
            String sql = " ";
            long departmentId = 0;
            int count = 0;
            /*

            System.out.print("Iscinin adini daxil edin: ");
            String firstname = sc.next();

            System.out.print("Iscinin soyadini daxil edin: ");
            String lastname = sc.next();

            System.out.print("Iscinin emailini daxil edin: ");
            String email = sc.next();

            System.out.print("Iscinin telefon nomresini daxil edin: ");
            String phone = sc.next();

            System.out.print("Iscinin vezifesini daxil edin: ");
            String jobId = sc.next();

            System.out.print("Iscinin ise qebul tarixini(gun,ay,il) daxil edin: ");
            String hireDateStr = sc.next();
            LocalDate hireDate = LocalDate.parse(hireDateStr, DateTimeFormatter.ofPattern("dd.MM.yyyy"));

            System.out.print("Iscinin maasi daxil edin: ");
            BigDecimal salary = sc.nextBigDecimal();

            System.out.print("Iscinin bonusu daxil edin: ");
            BigDecimal commissionPercent = sc.nextBigDecimal();

            System.out.print("Iscinin department id:");
             departmentId = sc.nextLong();

            System.out.print("Hansi departament iscilerine baxmaq isteyirsiniz? ");
             departmentId = sc.nextLong();

            sql = "INSERT INTO employees(employee_id, first_name, last_name, email, phone_number, hire_date, job_id, salary, commission_pct, department_id )" +
                    "VALUES (EMPLOYEES_SEQ.nextval, ?, ?, ?, ?,?, ?, ?, ?, ?)";
            ps = connection.prepareStatement(sql);

            ps.setString(1, firstname);
            ps.setString(2, lastname);
            ps.setString(3, email);
            ps.setString(4, phone);
            ps.setDate(5, new Date(hireDate.toEpochDay()));
            ps.setString(6, jobId);
            ps.setBigDecimal(7, salary);
            if (commissionPercent.compareTo(BigDecimal.ZERO) > 0) {
                ps.setBigDecimal(8, commissionPercent);
            } else {
                ps.setNull(8, Types.DECIMAL);
            }
            ps.setLong(9, departmentId);
             count = ps.executeUpdate();
            if (count == 1) {
                System.out.println("Isci elave olundu.");
*/
            System.out.println("Hansi maasli iscileri sevindirek?");
            BigDecimal minSalary = sc.nextBigDecimal();

            System.out.println("Ne qeder maas artimi edek? ");
            BigDecimal increase = sc.nextBigDecimal();

            sql = "update employees " +
                    "set salary = salary + ? " + "where salary < ? ";
            ps = connection.prepareStatement(sql);
            ps.setBigDecimal(1, increase);
            ps.setBigDecimal(2, minSalary);

            count = ps.executeUpdate();
            System.out.println(count+ " iscinin maasi artirildi ");

            System.out.println("Bazadan hansi iscini silmek isteyirsiniz? ");
            long employeeId = sc.nextLong();
            sql = "delete from employees where employee_id = ? ";
            ps = connection.prepareStatement(sql);
            ps.setLong(1,employeeId);
            count = ps.executeUpdate();
            if (count > 0) {
                System.out.println(count + " sayda isci silindi ");
            }else {
                System.out.println("Silinmedi ");
            }
            System.out.print("Hansi departament iscilerine baxmaq isteyirsiniz? ");
            departmentId = sc.nextLong();
            // sql injection
            sql = "SELECT\n" +
                    "    employee_id,\n" +
                    "    first_name,\n" +
                    "    last_name,\n" +
                    "    salary,\n" +
                    "    commission_pct,\n" +
                    "    email,\n" +
                    "    phone_number,\n" +
                    "    hire_date,\n" +
                    "    job_id\n" +
                    "FROM\n" +
                    "    employees\n" +
                    "WHERE department_id = ? " +
                    "ORDER BY\n" +
                    "    first_name,\n" +
                    "    last_name";


            ps = connection.prepareStatement(sql);
            ps.setLong(1, departmentId);

            rs = ps.executeQuery();
            List<Employee> employees = new ArrayList<>();
            while (rs.next()) {
                Employee employee = new Employee();
                employee.setId(rs.getLong("employee_id"));
                employee.setFirstname(rs.getString("first_name"));
                employee.setLastname(rs.getString("last_name"));
                employee.setSalary(rs.getBigDecimal("salary"));
                employee.setCommissionPercent(rs.getBigDecimal("commission_pct"));
                employee.setEmail(rs.getString("email"));
                employee.setPhone(rs.getString("phone_number"));
                employee.setHireDate(rs.getDate("hire_date").toLocalDate());

                System.out.println(employee);
                employees.add(employee);
            }
            connection.commit();

        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver tapilmadi." + e.getMessage());
            e.printStackTrace();
        } catch (SQLException e) {
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            System.out.println("Database ile bagli xeta bas verdi." + e.getMessage());
            e.printStackTrace();
        } finally {
            DatabaseUtil.close(rs, ps, connection);
        }
    }
}
