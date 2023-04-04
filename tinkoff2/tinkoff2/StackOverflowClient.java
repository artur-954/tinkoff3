public interface StackOverflowClient {
    @GetMapping("/questions/{id}")
    Mono<QuestionResponse> fetchQuestion(@PathVariable("id") long questionId, @RequestParam("site") String site);
}