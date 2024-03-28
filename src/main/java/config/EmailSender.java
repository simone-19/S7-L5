package config;


@Component
public class EmailSender {
    private final String apikey;
    private final String sender;

    public EmailSender(@Value("${sendgrid.apikey}") String apikey,
                       @Value("${sengrid.sender}") String sender) {
        this.apikey = apikey;
        this.sender = sender;
    }

    public void sendRegistrationEmail(User user) throws IOException {

        Email from = new Email(sender);
        String subject = "\n" + "Registration successful!";
        Email to = new Email(user.getEmail());
        Content content = new Content("text/plain",
                "Welcome " + user.getName() + " registration confirmed!");
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid(apikey);
        Request request = new Request();
        request.setMethod(Method.POST);
        request.setEndpoint("mail/send");
        request.setBody(mail.build());
        sg.api(request);
    }
}
