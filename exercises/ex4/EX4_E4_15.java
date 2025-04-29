package exercises.ex4;

public class EX4_E4_15 {
    public static void main(String[] args) {
        Letter letter = new Letter("Mary", "John");

        letter.addLine("I am sorry we must part.");
        letter.addLine("I wish you all the best.");

        System.out.println(letter.getText());
    }
}

class Letter {
    private String sender;
    private String recipient;
    private String body;

    public Letter(String sender, String recipient) {
        this.sender = sender;
        this.recipient = recipient;
        this.body = "";
    }

    public void addLine(String line) {
        body = body.concat(line).concat("\n");
    }

    public String getText() {
        String letterText = "Dear " + sender + ":\n\n";
        letterText = letterText.concat(body);
        letterText = letterText.concat("\nSincerely,\n\n");
        letterText = letterText.concat(recipient);
        return letterText;
    }
}