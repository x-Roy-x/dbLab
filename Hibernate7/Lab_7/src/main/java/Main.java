import roi.com.*;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Scanner;

public class Main {
    private static final SessionFactory ourSessionFactory;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();

            ourSessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }

    public static void main(final String[] args) throws Exception {
        final Session session = getSession();
        try {
            showMenu(session);

        } finally {
            session.close();
        }
    }

    public static void showMenu(Session session) {
        System.out.println("  Menu:" +
                "\n1. Select all data with table" +
                "\n2. Insert data to table" +
                "\n3. Delete shopper from table" +
                "\n4. Update data in table" +
                "\n5. Select M:M(shopper : good)" +
                "\n6. Insert and delete(M:M)(shopper : good)" +
                "\nAnother number - Exit");

        Scanner scanner = new Scanner(System.in);

        switch (scanner.nextInt()) {
            case 1:
                selectDataWithAllTable(session);

                break;

            case 2:
                System.out.println(
                        "\n1. Insert shopper" +
                                "\n2. Insert password_of_shopper" +
                                "\nAnother number - Exit");
                switch (scanner.nextInt()) {
                    case 1:
                        insertStudent(session);
                        break;

                    case 2:
                        insertGroupOfStudent(session);
                        break;

                    default:
                        showMenu(session);
                        break;
                }
                break;

            case 3:
                deleteStudent(session);
                break;

            case 4:
                updateStudent(session);
                break;

            case 5:
                selectStudentAndLecturer(session);
                break;

            case 6:
                System.out.println(
                        "\n1. Insert data to M:M table" +
                                "\n2. Delete data in M:M table" +
                                "\nAnother number - Exit");

                switch (scanner.nextInt()) {
                    case 1:
                        insertDataToStudentAndLecturer(session);
                        break;

                    case 2:
                        deleteDataWithStudentAndLecturer(session);
                        break;

                    default:
                        showMenu(session);
                        break;
                }
                break;

            default:
                showMenu(session);
                break;
        }

        System.out.println("0 - Show menu" +
                "\nAnother number - Exit");

        switch (scanner.nextInt()) {
            case 0:
                showMenu(session);
                break;

            default:
                break;
        }
    }

    private static void selectDataWithAllTable(Session session) {
        Query query = session.createQuery("from " + "ShopperEntity");
        System.out.format("%3s %-18s %-18s %-18s %s\n", "shopper_id", "surname_of_shopper", "name_of_shopper", "password_of_shopper", "age_of_shopper");
        for (Object obj : query.list()) {
            ShopperEntity shopperEntity = (ShopperEntity) obj;
            System.out.format("%3d %-18s %-18s %-18s %s\n", shopperEntity.getShopperId(),
                    shopperEntity.getSurnameOfShopper(), shopperEntity.getNameOfShopper(), shopperEntity.getPasswordOfShopperByPasswordOfShopper().getPassword_of_shopper(), shopperEntity.getAgeOfShopper());
        }
        System.out.println("\n");

        Query query_2 = session.createQuery("from " + "GoodEntity");
        System.out.format("%3s %-18s %-18s %s\n", "ID", "name_of_good", "country_of_manufacture", "price");
        if (query_2.list() == null) {
            System.out.println("Query2 list = null");
        }
        for (Object obj : query_2.list()) {
            GoodEntity goodEntity = (GoodEntity) obj;
            System.out.format("%3d %-18s %-18s %s\n", goodEntity.getGoodId(), goodEntity.getNameOfGood(), goodEntity.getCountyOfManufacture(), goodEntity.getPrice());
        }
        System.out.println("\n");

        Query query_3 = session.createQuery("from " + "PasswordOfShopperEntity");
        for (Object obj : query_3.list()) {
            PasswordOfShopperEntity passwordOfShopperEntity = (PasswordOfShopperEntity) obj;
            System.out.format("%s\n", passwordOfShopperEntity.getPassword_of_shopper());
        }
        System.out.println("\n");

        System.out.println("Ok");
    }

    private static void deleteStudent(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input shopper id:  ");
        Integer shopperId = scanner.nextInt();

        ShopperEntity studentEntity = session.load(ShopperEntity.class, shopperId);

        if (studentEntity != null) {
            session.beginTransaction();
            Query query = session.createQuery("delete ShopperEntity where shopperId=:shopper_id_code");
            query.setParameter("shopper_id_code", shopperId);
            int result = query.executeUpdate();
            session.getTransaction().commit();
            System.out.println("Ok");
        } else {
            System.out.println("Shopper does not exist");
        }
    }

    private static void updateStudent(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nInput a shopper id: ");
        int shopperId = scanner.nextInt();
        System.out.println("Input new name for shopper: ");
        String new_name = scanner.next();

        ShopperEntity studentEntity = session.load(ShopperEntity.class, shopperId);
        if (studentEntity != null) {
            session.beginTransaction();
            Query query = session.createQuery("update ShopperEntity set name_of_shopper=:new_name_code  where shopperId = :shopper_id_code");
            query.setParameter("new_name_code", new_name);
            query.setParameter("shopper_id_code", shopperId);
            int result = query.executeUpdate();
            session.getTransaction().commit();
        } else System.out.println("Shopper does not exist");

        System.out.println("Ok");
    }

    private static void insertGroupOfStudent(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input a new password: ");
        String newGroup = scanner.next();

        session.beginTransaction();
        PasswordOfShopperEntity newGroupOfStudentEntity = new PasswordOfShopperEntity(newGroup);
        session.save(newGroupOfStudentEntity);
        session.getTransaction().commit();

        System.out.println("Ok");
    }

    public static void deleteDataWithStudentAndLecturer(Session session){
        session.beginTransaction();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input shopper id: ");
        int shopperId = scanner.nextInt();
        System.out.println("Input good id: ");
        int goodId = scanner.nextInt();

        Query query1 = session.createQuery("from ShopperEntity  where shopperId= :shopper_id_code");
        query1.setParameter("shopper_id_code", shopperId);
        ShopperEntity studentEntity = (ShopperEntity) query1.list().get(0);
        Query query2 = session.createQuery("from GoodEntity where goodId= :good_id_code");
        query2.setParameter("good_id_code", goodId);
        GoodEntity lecturerEntity = (GoodEntity) query2.list().get(0);
        studentEntity.deleteLecturerEntity(lecturerEntity);
        session.getTransaction().commit();
        System.out.println("Ok");
    }

    private static void selectStudentAndLecturer(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input shopper id: ");
        Integer shopperId = scanner.nextInt();


        Query query = session.createQuery("from ShopperEntity where shopperId=:shopper_id_code");
        query.setParameter("shopper_id_code", shopperId);
        System.out.format("\nM:M --------------------\n");
        for (Object obj : query.list()) {
            ShopperEntity shopperEntity = (ShopperEntity) obj;
            System.out.format("%3d %-10s %-10s %-10s %-10s %-10s %-10s %-10s %-10s %s\n", shopperEntity.getShopperId(),
                    shopperEntity.getSurnameOfShopper(), shopperEntity.getNameOfShopper(), shopperEntity.getPasswordOfShopperByPasswordOfShopper().getPassword_of_shopper(),
                    shopperEntity.getAgeOfShopper(), " - ", shopperEntity.getGoodEntities().get(0).getGoodId(),
                    shopperEntity.getGoodEntities().get(0).getNameOfGood(), shopperEntity.getGoodEntities().get(0).getCountyOfManufacture(),
                    shopperEntity.getGoodEntities().get(0).getPrice());
        }

        System.out.println("Ok");
    }

    public static void insertDataToStudentAndLecturer(Session session){
        session.beginTransaction();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input shopper id: ");
        Integer artistId = scanner.nextInt();
        System.out.println("Input goods id: ");
        Integer lecturerId = scanner.nextInt();

        Query query1 = session.createQuery("from ShopperEntity where shopperId= :shopper_id_code");
        query1.setParameter("shopper_id_code", artistId);
        ShopperEntity shopperEntity = (ShopperEntity) query1.list().get(0);
        Query query2 = session.createQuery("from GoodEntity  where goodId= :goods_id_code");
        query2.setParameter("goods_id_code", lecturerId);
        GoodEntity goodEntity = (GoodEntity) query2.list().get(0);
        shopperEntity.addLecturerEntity(goodEntity);
        session.getTransaction().commit();
        System.out.println("Ok");
    }

    private static void insertStudent(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input new shopper surname: ");
        String last_name = scanner.next();
        System.out.println("Input new shopper name: ");
        String first_name = scanner.next();
        System.out.println("Input the password of shopper: ");
        String group = scanner.next();
        System.out.println("Input new yer of shopper: ");
        String specialty = scanner.next();

        session.beginTransaction();
        ShopperEntity shopperEntity = new ShopperEntity(last_name, first_name, specialty, new PasswordOfShopperEntity(group));
        session.save(shopperEntity);
        session.getTransaction().commit();
        System.out.println("Ok");
    }

}