package com.example.hibernate;
import com.example.hibernate.entity.Region;
import com.example.hibernate.entity.Country;
import com.example.hibernate.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\nMENU");
            System.out.println("1. Add Region");
            System.out.println("2. Add Country");
            System.out.println("3. Exit");
            System.out.print("Choose option: ");
            int choice = Integer.parseInt(sc.nextLine());
            if (choice == 1) {
                System.out.print("Enter region name: ");
                String regionName = sc.nextLine();

                Transaction tx = null;
                try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                    tx = session.beginTransaction();
                    Region region = new Region();
                    region.setRegionName(regionName);
                    session.save(region);
                    tx.commit();
                    System.out.println("region inserted successfully!");
                } catch (Exception e) {
                    if (tx != null) tx.rollback();
                    e.printStackTrace();
                }

            } else if (choice == 2) {
                System.out.print("Enter country ID (eg. IN): ");
                String countryId = sc.nextLine();
                System.out.print("Enter country name: ");
                String countryName = sc.nextLine();

                try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                    System.out.println("available Regions:");
                    List<Region> regions = session.createQuery("from Region", Region.class).list();
                    for (Region r : regions) {
                        System.out.println("ID: " + r.getRegionId() + " | Name: " + r.getRegionName());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                System.out.print("enter existing region ID: ");
                int regionId = Integer.parseInt(sc.nextLine());

                Transaction tx = null;
                try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                    tx = session.beginTransaction();
                    Region region = session.get(Region.class, regionId);
                    if (region == null) {
                        System.out.println("region ID not found!");
                        continue;
                    }

                    Country country = new Country();
                    country.setCountryId(countryId);
                    country.setCountryName(countryName);
                    country.setRegion(region);
                    session.save(country);
                    tx.commit();
                    System.out.println("country inserted successfully!");
                } catch (Exception e) {
                    if (tx != null) tx.rollback();
                    e.printStackTrace();
                }

            } else if (choice == 3) {
                System.out.println("Exiting...");
                break;
            } else {
                System.out.println("invalid choice.");
            }
        }

        sc.close();
    }
}
