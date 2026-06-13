package matcha.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Notification implements INotifiable {
    
    private User recipient;
    private String message;
    private LocalDateTime timestamp;
    
    // Default constructor
    public Notification() {
    }
    
    // Constructor dengan parameter
    public Notification(User recipient, String message, LocalDateTime timestamp) {
        this.recipient = recipient;
        this.message = message;
        this.timestamp = timestamp;
    }

    // === Getter & Setter ===
    public User getRecipient() {
        return recipient;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    // === Implementasi method dari interface INotifiable ===
    @Override
    public void sendAlert(String message) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedTime = (timestamp != null) ? timestamp.format(formatter) : "N/A";
        
        System.out.println("========================================");
        System.out.println("🔔 NOTIFIKASI BARU");
        System.out.println("Kepada   : " + (recipient != null ? recipient.getName() : "Unknown"));
        System.out.println("Waktu    : " + formattedTime);
        System.out.println("Pesan    : " + message);
        System.out.println("========================================");
    }
    
    // Overload method tanpa parameter (opsional, agar kompatibel dengan NotificationManager sebelumnya)
    public void sendAlert() {
        sendAlert(this.message);
    }
}