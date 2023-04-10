package GUI;

import BUS.OrderBUS;
import BUS.RevenueDataPoint;
import BUS.RevenueStatisticsBUS;
import BUS.VegetableBUS;
import Entities.Order;
import Entities.OrderDetail;
import Entities.Vegetable;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class MainConsole {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("1. Thống kê theo các ngày của tuần này");
            System.out.println("2. Hiển thị tất cả hóa đơn");
            System.out.println("3. Thêm hóa đơn");
            System.out.println("4. Xóa hóa đơn");
            System.out.println("5. Tìm kiếm theo tên khách hàng");
            System.out.println("exit. Thoát chương trình");
            System.out.print("Nhập lựa chọn: ");
            String choose = scanner.nextLine();
            boolean isExit = switch (choose.toUpperCase()) {
                case "1" -> {
                    // Thống kê doanh thu theo các ngày của tuần này
                    LocalDate startDate = LocalDate.now().with(DayOfWeek.MONDAY);
                    LocalDate endDate = startDate.plusWeeks(1).minusDays(1);
                    System.out.println(startDate);
                    System.out.println(endDate);
                    RevenueStatisticsBUS revenueStatisticsBus = new RevenueStatisticsBUS();
                    List<RevenueDataPoint> revenueDate = revenueStatisticsBus.getDailyRevenue(startDate, endDate);
                    for (RevenueDataPoint dataPoint : revenueDate) {
                        System.out.println(dataPoint);
                    }
                    yield false;
                }
                case "2" -> {
                    OrderBUS orderBUS = new OrderBUS();
                    List<Order> orders = orderBUS.getOrders();
                    System.out.printf("%20s|%20s|%20s|%20s|%20s|%20s|%20s|\n",
                            "OrderID", "CustomerName", "Total", "OrderDetailID",
                            "Quantity", "VegetableName", "Price");
                    for (Order order : orders) {
                        for (OrderDetail orderDetail : order.getOrderDetails()) {
                            System.out.printf("%20s|%20s|%20s|%20s|%20s|%20s|%20s|\n",
                                    order.getOrderID(), order.getCustomer().getFullname(), order.getTotal(),
                                    orderDetail.getOrderDetailID(), orderDetail.getQuantity(),
                                    orderDetail.getVegetable().getVegetableName(), orderDetail.getPrice());
                        }
                    }
                    yield false;

                }
                case "3" -> {
                    /* ------- Cách lập 1 hóa đơn -------- */
                    OrderBUS orderBUS = new OrderBUS();
                    Order order = new Order(LocalDate.of(2023, 4, 3), 0.0f, "Thử chút");
                    if (orderBUS.addOrder(order, 8)) {
                        System.out.println("Success");
                    }
                    VegetableBUS vegetableBUS = new VegetableBUS();

                    /* Tạo các chi tiết hóa đơn từ hóa đơn vừa thêm trên
                    *  Minh họa nên dùng giá trị random
                    *  */
                    List<OrderDetail> orderDetails = new ArrayList<>();
                    Random rand = new Random();
                    // rand.nextInt(1, 3) -> random from 1 - 3
                    for (int i = 0; i < rand.nextInt(1, 3); i++) {
                        int randVegetableID = rand.nextInt(1, 8);
                        Vegetable vegetable = vegetableBUS.getById(randVegetableID);
                        int randQuantity = rand.nextInt(4);
                        float randPrice = vegetable.getPrice() + rand.nextFloat(3);
                        System.out.printf("Vegetable: name = %s, quantity = %d, price = %f\n", vegetable.getVegetableName(), randQuantity, randPrice);
                        orderDetails.add(new OrderDetail(order, vegetable, randQuantity, randQuantity * randPrice));
                    }

                    if (orderBUS.addOrderDetail(orderDetails)) {
                        System.out.println("Success");
                    }

                    /* Update lại total trong order */
                    float total = 0.0f;
                    for (OrderDetail orderDetail : orderDetails) {
                        total += orderDetail.getQuantity() * orderDetail.getPrice();
                    }
                    order.setTotal(total);
                    orderBUS.updateOrder(order);
                    /* ------- Cách lập 1 hóa đơn -------- */
                    yield false;

                }
                case "4" -> {
                    try {
                        System.out.println("Nhập orderID cần xóa: ");
                        int orderID = Integer.parseInt(scanner.nextLine());
                        OrderBUS orderBUS = new OrderBUS();
                        if (orderBUS.deleteOrder(orderID)) {
                            System.out.println("Successful");
                        } else {
                            System.out.println("Failed");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Sai du lieu");
                    }
                    yield false;
                }
                case "5" -> {
                    System.out.println("Nhập customer name cần tìm: ");
                    String name = scanner.nextLine();
                    OrderBUS orderBUS = new OrderBUS();
                    List<Order> orders = orderBUS.getByCustomerName(name);
                    System.out.printf("%20s|%20s|%20s|%20s|%20s|%20s|%20s|\n",
                            "OrderID", "CustomerName", "Total", "OrderDetailID",
                            "Quantity", "VegetableName", "Price");
                    for (Order order : orders) {
                        for (OrderDetail orderDetail : order.getOrderDetails()) {
                            System.out.printf("%20s|%20s|%20s|%20s|%20s|%20s|%20s|\n",
                                    order.getOrderID(), order.getCustomer().getFullname(), order.getTotal(),
                                    orderDetail.getOrderDetailID(), orderDetail.getQuantity(),
                                    orderDetail.getVegetable().getVegetableName(), orderDetail.getPrice());
                        }
                    }
                    yield false;
                }
                case "EXIT" -> true;
                default -> {
                    System.out.println("Wrong option");
                    yield false;
                }
            };
            if (isExit) {
                break;
            }
        } while (true);

    }
}
