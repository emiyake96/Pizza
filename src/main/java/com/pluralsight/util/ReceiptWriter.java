package com.pluralsight.util;

import com.pluralsight.model.Order;
import com.pluralsight.model.OrderItem;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Saves a completed order to a timestamped .txt file in the receipts/ folder.
 *
 * Spec: filename format is yyyyMMdd-HHmmss.txt (e.g. 20230329-121523.txt)
 *
 * WHY PrintWriter over System.out? PrintWriter wraps a FileWriter so we can
 * call println() the same way we print to the console — the only difference
 * is the destination. This keeps the receipt format easy to read and maintain.
 *
 * The receipts/ folder is created automatically if it doesn't exist yet.
 */
public class ReceiptWriter {

    // Spec format: yyyyMMdd-HHmmss
    private static final DateTimeFormatter FILENAME_FORMAT =
            DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");

    // Spec format for the human-readable timestamp inside the receipt
    private static final DateTimeFormatter DISPLAY_FORMAT =
            DateTimeFormatter.ofPattern("MMMM dd, yyyy  hh:mm:ss a");

    public static void save(Order order) {

        // ── Build the filename from the order's timestamp ─────────────────
        String filename = order.getOrderTime().format(FILENAME_FORMAT) + ".txt";

        // ── Make sure receipts/ folder exists ─────────────────────────────
        File receiptsDir = new File("receipts");
        if (!receiptsDir.exists()) {
            receiptsDir.mkdirs();
        }

        File receiptFile = new File(receiptsDir, filename);

        // ── Write the receipt ─────────────────────────────────────────────
        try (PrintWriter writer = new PrintWriter(new FileWriter(receiptFile))) {

            writer.println("========================================");
            writer.println("             PIZZA RECEIPT              ");
            writer.println("========================================");
            writer.println("Date: " + order.getOrderTime().format(DISPLAY_FORMAT));
            writer.println("----------------------------------------");

            // Print each item's full description
            List<OrderItem> items = order.getItemsNewestFirst();
            for (int i = 0; i < items.size(); i++) {
                writer.printf("%n%d. %s%n", i + 1, items.get(i).getDescription());
            }

            writer.println("\n----------------------------------------");
            writer.printf("ORDER TOTAL: $%.2f%n", order.getTotal());
            writer.println("========================================");
            writer.println("     Thank you for ordering Pizza!      ");
            writer.println("========================================");

            System.out.println("  Receipt saved: receipts/" + filename);

        } catch (IOException e) {
            System.out.println("  Warning: could not save receipt — " + e.getMessage());
        }
    }
}
