public interface GitHubClient {
    @GetMapping("/repos/{owner}/{repo}/commits/{ref}")
    Mono<List<CommitResponse>> fetchCommits(@PathVariable String owner, @PathVariable String repo, @PathVariable String ref);
}