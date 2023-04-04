@Service
public class GitHubService {
    private final GitHubClient gitHubClient;

    public GitHubService(GitHubClient gitHubClient) {
        this.gitHubClient = gitHubClient;
    }

    public Mono<List<CommitResponse>> getCommits(String owner, String repo, String ref) {
        return gitHubClient.fetchCommits(owner, repo, ref);
    }
}