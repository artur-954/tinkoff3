@Service
public class StackOverflowService {
    private final StackOverflowClient stackOverflowClient;

    public StackOverflowService(StackOverflowClient stackOverflowClient) {
        this.stackOverflowClient = stackOverflowClient;
    }

    public Mono<QuestionResponse> getQuestion(long questionId, String site) {
        return stackOverflowClient.fetchQuestion(questionId, site);
    }
}