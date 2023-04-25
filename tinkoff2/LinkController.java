@RestController
@RequestMapping("/tgchats/{tgChatId}/links")
public class LinkController {
    private final LinkService linkService;

    public LinkController(LinkService linkService) {
        this.linkService = linkService;
    }

    @PostMapping
    public ResponseEntity<Link> addLink(@PathVariable long tgChatId, @RequestBody LinkRequest linkRequest) {
        URI url;
        try {
            url = new URI(linkRequest.getUrl());
        } catch (URISyntaxException e) {
            return ResponseEntity.badRequest().build();
        }

        Link link = linkService.add(tgChatId, url);
        return ResponseEntity.ok(link);
    }

    @DeleteMapping
    public ResponseEntity<Link> removeLink(@PathVariable long tgChatId, @RequestBody LinkRequest linkRequest) {
        URI url;
        try {
            url = new URI(linkRequest.getUrl());
        } catch (URISyntaxException e) {
            return ResponseEntity.badRequest().build();
        }

        Link link = linkService.remove(tgChatId, url);
        if (link == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(link);
    }

    @GetMapping
    public ResponseEntity<Collection<Link>> listAllLinks(@PathVariable long tgChatId) {
        Collection<Link> links = linkService.listAll(tgChatId);
        return ResponseEntity.ok(links);
    }
}