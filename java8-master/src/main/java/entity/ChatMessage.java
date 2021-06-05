package entity;

public class ChatMessage {
    // Текст сообщения
    private String message;
    // Автор сообщения
    private ChatUser author;
    // Временная метка сообщения (в микросекундах)
    private long timestamp;
    // Отображенпя сообщения за х последних секунд
    private long sek = 30;

    public ChatMessage(String message, ChatUser author, long timestamp) {
        super();
        this.message = message;
        this.author = author;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ChatUser getAuthor() {
        return author;
    }

    public void setAuthor(ChatUser author) {
        this.author = author;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public long getSek() {
        return sek;
    }

}